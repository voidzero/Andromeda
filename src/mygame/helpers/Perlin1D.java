/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.helpers;

import com.jme3.math.FastMath;

/**
 *
 * @author Dansion
 */
public class Perlin1D {
    private int size = 64;
    private float [] noise = new float[size];
    private float persistence = 0.5f;
    private int numberOfOctaves = 4;

    public void setSeed(long seed) {
        FastMath.rand.setSeed(seed);

        generateNoise();
    }

    public void setSize(int size) {
        this.size = size;
        noise = new float[size];

        generateNoise();
    }

    private void generateNoise() {
        for(int i = 0; i < size; i++) {
            noise[i] = FastMath.rand.nextFloat();
        }
    }

    private float smoothedNoise_1(int x) {
        int x_bef = x - 1;
        int x_aft = x + 1;

        if(x >= 0 && x < size) {
            if(x_bef < 0 ) {
                x_bef = size - 1;
            }

            if(x_aft > size - 1) {
                x_aft = 0;
            }

            return noise[x] / 2.0f  +  noise[x_bef] / 4.0f  +  noise[x_aft] / 4.0f;
        }

        return 0;
    }

    private float InterpolatedNoiseFlat(float x) {
        int int_x = (int) FastMath.floor(x);
        float fract_x = x - int_x;
        float v1 = smoothedNoise_1(int_x);
        float v2 = smoothedNoise_1(int_x + 1);

        return interpolateFlat(v1, v2, fract_x);
    }

    private float InterpolatedNoiseSmooth(float x) {
        int int_x = (int) FastMath.floor(x);
        float fract_x = x - int_x;
        float v1 = smoothedNoise_1(int_x);
        float v2 = smoothedNoise_1(int_x + 1);

        return interpolateSmooth(v1, v2, fract_x);
    }

    private float interpolateFlat(float v1, float v2, float fract) {
        return  v1 * (1 - fract) + v2 * fract;
    }

    private float interpolateSmooth(float v1, float v2, float fract) {
	float ft = fract * FastMath.PI;
	float f = (1 - FastMath.cos(ft)) * .5f;

	return  v1 * (1 - f) + v2 *f;
    }

    public float PerlinNoiseSmooth(float x) {
        float total = 0;
        float p = persistence;
        int n = numberOfOctaves - 1;

        for(int i = 0; i < n; i++) {
            int frequency = 2 * i;
            float amplitude = FastMath.PI;

            total = total + InterpolatedNoiseSmooth(x * frequency) * amplitude;
        }

        return total;
    }

    public float PerlinNoiseFlat(float x) {
        float total = 0;
        float p = persistence;
        int n = numberOfOctaves - 1;

        for(int i = 0; i < n; i++) {
            int frequency = 2 * i;
            float amplitude = FastMath.PI;

            total = total + InterpolatedNoiseFlat(x * frequency) * amplitude;
        }

        return total;
    }
}
