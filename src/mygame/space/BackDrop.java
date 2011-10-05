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
        
        for(int i=0; i<1000; i++) {
            g.fillRect((int) FastMath.floor(FastMath.rand.nextFloat() * 1023), (int) FastMath.floor(FastMath.rand.nextFloat() * 1023), 1, 1);
        }
        
        myTex.setImage(awtLoader.load(img, false));
        mat.setTexture("ColorMap", myTex);
    }
}
