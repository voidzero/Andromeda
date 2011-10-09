/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.helpers.Graphics;

import com.jme3.math.FastMath;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Dansion
 */
public class Perlin2DGraphics {
    private Color startColor, endColor;
    private BufferedImage img;
    private Graphics graphics;

    public void setSize(int x, int y) {
        img = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);
        graphics = img.getGraphics();

        generate();
    }

    public void setStartColor(Color start) {
        startColor = start;
    }

    public void setEndColor(Color end) {
        endColor = end;
    }

    public Graphics getGraphics() {
        if(graphics != null) {
            return graphics;
        }

        return null;
    }

    private void generate() {
    }
}
