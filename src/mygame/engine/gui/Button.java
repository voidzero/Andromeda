/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.gui;

import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.ui.Picture;
import mygame.Assets;
import mygame.engine.gui.Interfaces.GuiListener;

/**
 *
 * @author Dansion
 */
public class Button extends Picture{
    private String caption = null;
    public GuiListener listener = null;
    
    private Assets assets = Assets.getInstance();
    
    public Button(String caption) {
        this(caption, null);
    }

    public Button(String caption, String texture) {
        super(caption);
        
        setName(caption);
        
        setWidth(32);
        setHeight(32);
        setBoundRefresh();
        
        Material m = new Material(assets.assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        
        if(texture == null) {
            m.setColor("Color", ColorRGBA.Blue);
            //@ TODO Button caption
        }
        else {
            m.setTexture("ColorMap", assets.assetManager.loadTexture(texture));
            m.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
        }
        
        setMaterial(m);
                
        setQueueBucket(Bucket.Gui);
        setLocalTranslation(100, 100, 0);
    }
    
    public void onAction() {
        if(listener != null) {
            listener.onAction();
        }
    }

    @Override
    public final void setName(String name) {
        caption = name;
    }

    @Override
    public final String getName() {
        return caption;
    }
}
