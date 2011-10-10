/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.helpers.Graphics;

import com.jme3.math.FastMath;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import mygame.helpers.Perlin2D;

/**
 *
 * @author Dansion
 */
public class Perlin2DGraphics {
    private Color startColor = new Color(0,0,0,0), endColor = new Color(1,1,1,1);
    private BufferedImage img;
    private Graphics graphics;

    public void setSize(int x, int y) {
        img = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);
        graphics = img.getGraphics();
    }

    public void setStartColor(Color start) {
        startColor = start;
    }

    public void setEndColor(Color end) {
        endColor = end;
    }

    public Graphics getGraphics() {
        generate();

        if(graphics != null) {
            return graphics;
        }

        return null;
    }

    public BufferedImage getImage() {
        generate();

        if(graphics != null) {
            return img;
        }

        return null;
    }

    public void clearGraphics() {
        if(graphics != null) {
            graphics.setColor(new Color(0,0,0,0));
            graphics.fillRect(0, 0, img.getWidth() - 1, img.getHeight() - 1);
        }
    }

    private void generate() {
        if(graphics != null) {
            clearGraphics();

            Perlin2D perlin = new Perlin2D(232135328, 140);
            perlin.setSize(img.getWidth(), img.getHeight());

            int tot = img.getWidth() * img.getHeight();

            for(int x = 0; x<img.getWidth(); x++) {
                for(int y = 0; y<img.getHeight(); y++) {
                    float mix = perlin.PerlinNoiseSmooth(x, y);

                    int r = (int) FastMath.floor(startColor.getRed() * (1 - mix) + endColor.getRed() * mix);
                    int g = (int) FastMath.floor(startColor.getGreen() * (1 - mix) + endColor.getGreen() * mix);
                    int b = (int) FastMath.floor(startColor.getBlue() * (1 - mix) + endColor.getBlue() * mix);
                    int a = (int) FastMath.floor(startColor.getAlpha() * (1 - mix) + endColor.getAlpha() * mix);

                    Color mixColor = new Color(r, g, b, a);

                    graphics.setColor(mixColor);

                    graphics.fillRect(x, y, 1, 1);
                }
            }
        }
    }
}
