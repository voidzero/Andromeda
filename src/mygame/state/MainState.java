/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.state;

import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.scene.Node;

/**
 *
 * @author Dansion
 */
public class MainState extends AbstractState{
    private static MainState INSTANCE = null;
    private Builder builder = null;
    private Space space = new Space(rootNode);
    
    private MainState(Node parent) {
        super(parent);
        
        switchState(space);
        
        int [] mainTriggers =   {KeyInput.KEY_I, KeyInput.KEY_F11  , KeyInput.KEY_TAB};
        String [] mainActions = {"infoToggle"  , "fullScreenToggle", "mouseToggle"};
        
        int [] globalTriggers =   {KeyInput.KEY_B};
        String [] globalActions = {"buildToggle"};    
        
        String [] mouseActions = {"leftClick"};
        int [] mouseTriggers =   {MouseInput.BUTTON_LEFT};
        
        setKeyInputs("Main", mainTriggers, mainActions);
        setGlobalKeyInputs("Main_Global", globalTriggers, globalActions);
        setMouseActions(mouseActions, mouseTriggers);
                
        actionListener = new ActionListener() {
            public void onAction(String name, boolean keyPressed, float tpf) {
                if("infoToggle".equals(name) && keyPressed) {}

                if("leftClick".equals(name)  && keyPressed) {
                    if(isCursorActive()) {
                        getCursor().pickGui(guiNode, guiCam);
                    }
                }

                if("buildToggle".equals(name) && keyPressed) {
                    if(builder == null) {
                        builder = new Builder(rootNode);
                        
                        enableCursor(false);
                        
                        switchState(builder);
                    }
                    else {
                        if(builder.isEnabled()) {
                            enableCursor(true);
                        }
                        else {
                            enableCursor(false);
                        }
                        switchState(builder);
                    }
                }
                
                if("fullScreenToggle".equals(name) && keyPressed) {}
                
                if("mouseToggle".equals(name) && keyPressed) {
                    if(isCursorActive()) {
                        enableCursor(false);
                    }
                    else {
                        enableCursor(true);
                    }
                }
            }
        };
        
        setKeyListener(actionListener);
        
        enableCamera(true);
        enableCursor(true);
        enableGuiCam(true);
    }
       
    @Override
    public void update(float tpf) {
        super.update(tpf);      
    }

    public static MainState getInstance(Node parent) {
        if(INSTANCE == null) {
            INSTANCE = new MainState(parent);
        }
        
        return INSTANCE;
    }
}


