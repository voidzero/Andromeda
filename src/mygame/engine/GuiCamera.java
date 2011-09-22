/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine;

import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import mygame.helpers.Share;

/**
 *
 * @author Dansion
 */
public class GuiCamera extends com.jme3.renderer.Camera  {
    private static Share shares = Share.getInstance();
    
    public ViewPort guiViewPort;
    
    public GuiCamera() {
        this(shares.appSettings.getWidth(), shares.appSettings.getHeight());
    }
    
    public GuiCamera(int cwidth, int cheight) { 
        this(cwidth, cheight, shares.guiNode);   
    }
    
    public GuiCamera(int cwidth, int cheight, Node guiNode) { 
        super(cwidth, cheight);
        
        guiViewPort = shares.renderManager.createPostView("Gui Default", this);
        guiViewPort.setClearFlags(false, false, false); 
        guiViewPort.attachScene(guiNode);
        
        System.out.println("gui camera ready");
    }
}
