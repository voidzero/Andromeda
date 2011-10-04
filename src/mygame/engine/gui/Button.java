/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.gui;

import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.texture.Texture;
import com.jme3.ui.Picture;
import mygame.Assets;
import mygame.engine.gui.Interfaces.GuiAction;
import mygame.engine.gui.Interfaces.GuiListener;
import mygame.engine.nodes.GroupNode;

/**
 *
 * @author Dansion
 */
public class Button extends GroupNode implements GuiAction{
    public GuiListener listener = null;
    private Texture tex = null;
    private Text caption = null;
    private int value = 0;
    
    private Picture bg = null;
    
    
    private Assets assets = Assets.getInstance();
    
    public Button(String caption) {
        this(caption, null);
    }

    public Button(String caption, String texture) {
        super(caption);
        
        setName(caption);
        
        bg = new Picture(caption);
        
        bg.setWidth(32);
        bg.setHeight(32);
        setBoundRefresh();
        
        Material m = new Material(assets.assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        
        if(texture == null) {
            m.setColor("Color", ColorRGBA.Blue);
            
            //caption
            Text capt = new Text(caption);
            capt.setLocalTranslation(4, 8, 0);
            attachChild(capt);
        }
        else {
            tex = assets.assetManager.loadTexture(texture); 
            m.setTexture("ColorMap", tex);
            m.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
            
            bg.setWidth(getWidth());
            bg.setHeight(getHeight());
        }
        
        bg.setMaterial(m);
                
        bg.setQueueBucket(Bucket.Gui);
        
        attachChild(bg);
    }
    
    public void onAction() {
        if(listener != null) {
            listener.onAction(value);
        }
    }

    @Override
    public final void setName(String name) {
        super.setName(name);
    }

    @Override
    public final String getName() {
        return super.getName();
    }
    
    public final int getHeight() {
        if(tex != null) {
            return tex.getImage().getHeight();
        }
        
        return 32;
    }
    
    public final int getWidth() {
        if(tex != null) {
            return tex.getImage().getWidth();
        }
        
        return 32;
    }
    
    public final void setWidth(int w) {
        bg.setWidth(w);
    }
    
    public final void setHeight(int h) {
        bg.setHeight(h);
    }
    
    public void setCaptionColor(ColorRGBA newColor) {
        caption.setColor(newColor);
    }
    
    public void showCaption() {
        caption = new Text(getName());
        caption.setLocalTranslation(12 , 26 , 0);
        
        attachChild(caption);
    }
    
    public void setValue(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    
    public void showHighlight(String highLightTexture, boolean vis) {
        Material m = new Material(assets.assetManager, "Common/MatDefs/Misc/Unshaded.j3md");

        if(vis) {
            Texture t = assets.assetManager.loadTexture(highLightTexture);
            m.setTexture("ColorMap", t);
        }
        else {
            m.setTexture("ColorMap", tex);           
        }
        
        m.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
        bg.setWidth(getWidth());
        bg.setHeight(getHeight());

        bg.setMaterial(m);
    }
}