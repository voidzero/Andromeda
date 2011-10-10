/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.space;

import com.jme3.material.Material;
import com.jme3.material.RenderState.FaceCullMode;
import com.jme3.math.FastMath;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture2D;
import com.jme3.texture.plugins.AWTLoader;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import mygame.Assets;
import mygame.helpers.Graphics.Perlin2DGraphics;

/**
 *
 * @author Dansion
 */
public class BackDrop extends Geometry {
    private  Material mat;

    public BackDrop () {
        super("Galaxy BackDrop", new Box(.5f, .5f, .5f));

        mat = new Material(Assets.getInstance().assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.getAdditionalRenderState().setFaceCullMode(FaceCullMode.Off);

        setMaterial(mat);

        renderBackDrop();
    }

    private void renderBackDrop() {
        //Init image buffer and loader
        BufferedImage img = new BufferedImage(1024, 1024, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();
        Texture2D myTex = new Texture2D();
        AWTLoader awtLoader = new AWTLoader();

        //Draw to image
        //placeholder backdrop
        g.setColor(new Color(0, 0, 7));
        g.fillRect(0, 0, 1023, 1023);

        g.setColor(new Color(240, 240, 240));

        //BG Stars
        for(int i=0; i<1250; i++) {
            g.fillRect((int) FastMath.floor(FastMath.rand.nextFloat() * 1023), (int) FastMath.floor(FastMath.rand.nextFloat() * 1023), 1, 1);
        }

        //Nebula
        Perlin2DGraphics nebula = new Perlin2DGraphics();
        nebula.setSize(1024, 1024);
        nebula.setEndColor(new Color(5,62,148,255));
        BufferedImage neb = nebula.getImage();

        int tot = img.getWidth() * img.getHeight();

        for(int x = 0; x<img.getWidth(); x++) {
            for(int y = 0; y<img.getHeight(); y++) {
                Color col_1 = img.getRGB(x, y) == 0 ? new Color(0,0,0,1) : new Color(img.getRGB(x, y));
                Color col_2 = neb.getRGB(x, y) == 0 ? new Color(0,0,0,0) : new Color(neb.getRGB(x, y));

                float delta_1 = 1;//0.5f;//col_1.getAlpha() / 255.00f;
                float delta_2 = 0.5f;//col_2.getAlpha() / 255.00f;
                               //     System.out.println(col_2.getTransparency());
                int r = (int) FastMath.floor((col_1.getRed() * delta_1) * (1 - delta_2) + col_2.getRed() * delta_2);
                int gg = (int) FastMath.floor((col_1.getGreen() * delta_1) * (1 - delta_2) + col_2.getGreen() * delta_2);
                int b = (int) FastMath.floor((col_1.getBlue() * delta_1) * (1 - delta_2) + col_2.getBlue() * delta_2);

                Color mix = new Color(r, gg, b);//(int) delta_2 * 255);

                g.setColor(mix);
                g.fillRect(x, y, 1, 1);
            }
        }

        //FG Stars
        g.setColor(new Color(240, 240, 240));

        for(int i=0; i<250; i++) {
            g.fillRect((int) FastMath.floor(FastMath.rand.nextFloat() * 1023), (int) FastMath.floor(FastMath.rand.nextFloat() * 1023), 2, 2);
        }

        myTex.setImage(awtLoader.load(img, false));

        mat.setTexture("ColorMap", myTex);
    }
}
