/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.space;

import com.jme3.material.Material;
import com.jme3.material.RenderState.FaceCullMode;
import com.jme3.math.FastMath;
import com.jme3.scene.Geometry;
import com.jme3.scene.control.UpdateControl;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture2D;
import com.jme3.texture.plugins.AWTLoader;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import mygame.Assets;
import mygame.helpers.Graphics.Perlin2DGraphics;
import mygame.helpers.threadloader.Loader;
import mygame.helpers.threadloader.interfaces.LoaderTask;

/**
 *
 * @author Dansion
 */
public class BackDrop extends Geometry {
    private Material mat;
    private Loader load = new Loader();
    private BackDropControl control = new BackDropControl();

    public BackDrop () {
        super("Galaxy BackDrop", new Box(.5f, .5f, .5f));

        control.setTarget(this);
        addControl(control);

        mat = new Material(Assets.getInstance().assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.getAdditionalRenderState().setFaceCullMode(FaceCullMode.Off);
        setMaterial(mat);

        renderBackDrop();
        load.run();
    }

    public void update() {
        load.checkStatus();
    }

    private void renderBackDrop() {
        load.task = new LoaderTask() {
            public void task(Loader loader) {
                //Init image buffer and loader
                BufferedImage img = new BufferedImage(1024, 1024, BufferedImage.TYPE_INT_ARGB);
                Graphics g = img.getGraphics();
                Texture2D myTex = new Texture2D();
                AWTLoader awtLoader = new AWTLoader();

                load.setProgress(0.05f);

                //Draw to image
                //placeholder backdrop
                g.setColor(new Color(0, 0, 7));
                g.fillRect(0, 0, 1023, 1023);
                g.setColor(new Color(240, 240, 240));

                //BG Stars
                for(int i=0; i<1250; i++) {
                    g.fillRect((int) FastMath.floor(FastMath.rand.nextFloat() * 1023), (int) FastMath.floor(FastMath.rand.nextFloat() * 1023), 1, 1);
                    load.setProgress(0.05f + (i / 1250.00f) * 0.20f);
                }

                //Nebula
                Perlin2DGraphics nebula = new Perlin2DGraphics();
                nebula.setSize(1024, 1024);
                nebula.setEndColor(new Color(5,62,148,255));
                BufferedImage neb = nebula.getImage();

                loader.setProgress(0.75f);
                //This is a lot easier than a double for loop ^^
                g.drawImage(neb, 0, 0, null);
                //FG Stars
                g.setColor(new Color(240, 240, 240));

                for(int i=0; i<250; i++) {
                    g.fillRect((int) FastMath.floor(FastMath.rand.nextFloat() * 1023), (int) FastMath.floor(FastMath.rand.nextFloat() * 1023), 2, 2);
                    loader.setProgress(0.75f + (i / 250.00f) * 0.25f);
                }

                myTex.setImage(awtLoader.load(img, false));

                mat.setTexture("ColorMap", myTex);
            }
        };
    }

    class BackDropControl extends UpdateControl {
        private BackDrop target = null;

    @Override
    public void update(float tpf) {
        if(target != null) {
            target.update();
        }
    }

    public void setTarget(BackDrop target) {
        this.target = target;
    }
}
}
