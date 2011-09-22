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
import mygame.engine.gui.Interfaces.GuiListener;
import mygame.engine.nodes.GroupNode;
import mygame.helpers.Share;

/**
 *
 * @author Dansion
 */
public class Panel extends GroupNode{
    private String caption = null;
    public GuiListener listener = null;
    
    private Picture panel = null;
    private Share shares = Share.getInstance();
    private Assets assets = Assets.getInstance();
    private Button closeButton = new Button("Close window");
    private GroupNode panelNode = this;
    
    private int corr_x = 0;
    private int corr_y = 0;
    
    public Panel(String caption) {
        this(caption, null);
    }

    public Panel(String caption, String texture) {
        super(caption);
        
        panel = new Picture(caption);
        
        setName(caption);
        
        int sw = shares.appSettings.getWidth();
        int sy = shares.appSettings.getHeight();
        
       Material m = new Material(assets.assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        
        if(texture == null) {          
            m.setColor("Color", ColorRGBA.Blue);
            panel.setWidth(32);
            panel.setHeight(32);
        
            //@ TODO Button caption
            corr_x = 16;
            corr_y = 40;          
        }
        else {      
            Texture t = assets.assetManager.loadTexture(texture);
            
            m.setTexture("ColorMap", t);
            m.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
            
            int nh = (t.getImage().getHeight() * sw - 40) / t.getImage().getWidth();
            
            panel.setWidth(sw - 40);
            panel.setHeight(nh);
            
            corr_x = (sw - 40) / 2;
            corr_y = nh + 20;
        }
        
        panel.setLocalTranslation((sw / 2) - corr_x, sy - corr_y, 0);
        panel.setMaterial(m);
        
        attachChild(panel);
        
        panelNode = this;
        
        closeButton.listener = new GuiListener() {
            public void onAction() {
                System.out.println("Close!");
                getParent().attachChild(panelNode);
            }
        };
        
        closeButton.setLocalTranslation((sw / 2) + corr_x - 34 , sy - 40, 0);
        closeButton.setWidth(16);
        closeButton.setHeight(16);
        attachChild(closeButton);
        
        
        setQueueBucket(Bucket.Gui);       
    }
    
    public void onAction() {
        if(listener != null) {
            listener.onAction();
        }
    }
    
    public boolean isActive() {      
        if(getParent() != null) {
            return true;
        }
        else {
            return false;
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
