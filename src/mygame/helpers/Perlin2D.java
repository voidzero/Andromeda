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
public class Perlin2D {
    private int size_x = 64;
    private int size_y = 64;
    private float scale = 80;

    private float [][] noise = new float[size_x][size_y];
    private float persistence = 0.2f;
    private float lowClip = 0.7f;
    private float highClip = 1.0f;
    private int numberOfOctaves = 4;

    public Perlin2D(long seed, int scale) {
        setSeed(seed);
        this.scale = scale;
    }

    private void setSeed(long seed) {
        FastMath.rand.setSeed(seed);
    }

    public void setSize(int size_x, int size_y) {
        this.size_x = (int) FastMath.floor(size_x / scale);
        this.size_y = (int) FastMath.floor(size_y / scale);

        noise = new float[this.size_x][this.size_y];

        generateNoise();
    }

    private void generateNoise() {
        int tot = size_x * size_y;

        for(int x = 0; x < size_x; x++) {
            for(int y = 0; y < size_y; y++) {
                float next = FastMath.rand.nextFloat();
                next = next < lowClip ? 0.0f : next;
                next = next > highClip ? 0.0f : next;
                noise[x][y] = next;
            }
        }

        int i = 0;
    }

    private float smoothedNoise_1(int x, int y) {
        int x_bef = x - 1;
        int x_aft = x + 1;
        int y_bef = y - 1;
        int y_aft = y + 1;

        x = (int) FastMath.normalize(x, 0, size_x - 1);
        y = (int) FastMath.normalize(y, 0, size_y - 1);

        x_bef = (int) FastMath.normalize(x_bef, 0, size_x - 1);
        y_bef = (int) FastMath.normalize(y_bef, 0, size_y - 1);

        x_aft = (int) FastMath.normalize(x_aft, 0, size_x - 1);
        y_aft = (int) FastMath.normalize(y_aft, 0, size_y - 1);

        //calc
        float corners = (noise[x_bef][y_bef] + noise[x_aft][y_bef] + noise[x_bef][y_aft] + noise[x_aft][y_aft]) / 16;
        float sides = (noise[x_bef][y] + noise[x_aft][y] + noise[x][y_bef] + noise[x][y_aft]) / 8;
        float center  =  noise[x][y] / 4;

        return corners + sides + center;
    }

    private float InterpolatedNoiseFlat(float x, float y) {
        int int_x = (int) FastMath.floor(x);
        int int_y = (int) FastMath.floor(y);
        int int_x2 = int_x + 1;
        int int_y2 = int_y + 1;
        float fract_x = x - int_x;
        float fract_y = y - int_y;

        if(int_x2 > size_x - 1) {
            int_x2 = int_x2 % (size_x - 1);
        }

        if(int_y2 > size_y - 1) {
            int_y2 = int_y2 % (size_y - 1);
        }

        float v1 = smoothedNoise_1(int_x, int_y);
        float v2 = smoothedNoise_1(int_x2, int_y);
        float v3 = smoothedNoise_1(int_x, int_y2);
        float v4 = smoothedNoise_1(int_x2, int_y2);

        float i1 = interpolateFlat(v1, v2, fract_x);
        float i2 = interpolateFlat(v3, v4, fract_x);

        return interpolateFlat(i1, i2, fract_y);
    }

    private float InterpolatedNoiseSmooth(float x, float y) {
        x = x / scale;
        y = y / scale;
        int int_x = (int) FastMath.floor(x);
        int int_y = (int) FastMath.floor(y);
        int int_x2 = int_x + 1;
        int int_y2 = int_y + 1;
        float fract_x = x - int_x;
        float fract_y = y - int_y;

        if(int_x2 > size_x - 1) {
            int_x2 = int_x2 % (size_x - 1);
        }

        if(int_y2 > size_y - 1) {
            int_y2 = int_y2 % (size_y - 1);
        }

        float v1 = smoothedNoise_1(int_x, int_y);
        float v2 = smoothedNoise_1(int_x2, int_y);
        float v3 = smoothedNoise_1(int_x, int_y2);
        float v4 = smoothedNoise_1(int_x2, int_y2);

        float i1 = interpolateSmooth(v1, v2, fract_x);
        float i2 = interpolateSmooth(v3, v4, fract_x);

        return interpolateSmooth(i1, i2, fract_y);
    }

    private float interpolateFlat(float v1, float v2, float fract) {
        return  v1 * (1 - fract) + v2 * fract;
    }

    private float interpolateSmooth(float v1, float v2, float fract) {
	float ft = fract * FastMath.PI;
	float f = (1 - FastMath.cos(ft)) * .5f;

	return  v1 * (1 - f) + v2 *f;
    }

    public float PerlinNoiseSmooth(float x, float y) {
        float total = 0;
        float p = persistence;
        int n = numberOfOctaves;

        for(int i = 1; i <= n; i++) {
            float frequency = p * i;
            float amplitude = i / n;

            total = total + InterpolatedNoiseSmooth(x * frequency, y * frequency) * amplitude;
        }

        return total;
    }

    public float PerlinNoiseFlat(float x, float y) {
        float total = 0;
        float p = persistence;
        int n = numberOfOctaves;

        for(int i = 1; i <= n; i++) {
            float frequency = p * i;
            float amplitude = i / n;

            total = total + InterpolatedNoiseFlat(x * frequency, y * frequency) * amplitude;
        }

        return total;
    }
}
