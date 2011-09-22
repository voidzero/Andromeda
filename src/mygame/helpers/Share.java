/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.helpers;

import com.jme3.app.state.AppStateManager;
import com.jme3.input.InputManager;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;

/**
 *
 * @author Dansion
 */
public class Share {
    public Node rootNode;
    public RenderManager renderManager;
    public InputManager inputManager;
    public AppStateManager stateManager; 
    public AppSettings appSettings;
    public Node guiNode;
    
    //Singleton instance 
    private static final Share INSTANCE = new Share();
    
    private Share() {
        
    }
    
    public static Share getInstance() {
        return INSTANCE;
    }
}
