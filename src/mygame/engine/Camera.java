/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine;

import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.event.InputEvent;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.input.event.MouseMotionEvent;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import mygame.engine.input.Interfaces.RawListener;
import mygame.engine.input.KeyInputs;
import mygame.engine.input.Mouse;
import mygame.helpers.Share;

/**
 
 * @author Dansion
 */
public class Camera extends com.jme3.renderer.Camera {   
    public enum CameraMode { STATIC, FLY, FLY_CLIP, FOLLOW, FOLLOW_CLIP, FPS };
    private static Share shares = Share.getInstance();
    
    public ViewPort viewPort;
    private Node rootNode = null;
    private CameraMode mode = CameraMode.STATIC;
    private KeyInputs kInputs;
    private Mouse mouse = null;
    private RawListener mouseListener = null;
    private AnalogListener listener = null;
    
    public Camera() { 
        this(shares.appSettings.getWidth(), shares.appSettings.getHeight());
    }
    
    public Camera(int cwidth, int cheight) { 
        this(cwidth, cheight, shares.rootNode);
    }
    
    public Camera(int cwidth, int cheight, Node rootNode) {
        this(cwidth, cheight, rootNode, "Default");
    }
    
    public Camera(int cwidth, int cheight, String name) {
        this(cwidth, cheight, shares.rootNode, name);
    }
    
    public Camera(int cwidth, int cheight, Node rootNode, String name) {
        super(cwidth, cheight);
        
        setLocation(new Vector3f(0f, 1.8f, 10f));
        lookAt(new Vector3f(0f, 1.8f, 0f), Vector3f.UNIT_Y);

        setViewPort(0.0f, 1.0f, 0.0f, 1.0f);
        setFrustumPerspective(75f, (float) cwidth / cheight, 0.01f, 5000f);
        
        setName(name);
        
        viewPort = shares.renderManager.createMainView(name, this);
        viewPort.attachScene(rootNode);
        viewPort.setBackgroundColor(ColorRGBA.Black);
        viewPort.setEnabled(true);
        viewPort.setClearFlags(true, true, true);
        
        shares.renderManager.setCamera(this, false);
    }
    
    public void setMode(CameraMode newMode) {
        mode = newMode;
        System.out.println("Camera '" + getName() + "' mode now is " + newMode.name());
        
        if(newMode == CameraMode.FLY_CLIP) {
          setFreeMovementActions();
        }
        
        if(newMode == CameraMode.STATIC) {
            detachListeners();
        }
    }
    
    public CameraMode getMode() {
        return mode;
    }
    
    private void setFreeMovementActions() {
          kInputs = new KeyInputs("Camera", "Free movement");
                
          mouseListener = new RawListener() {
                public void onAction(String action, MouseMotionEvent evt) {
                    if("onMouseMotion".equals(action)) {
                        int x = evt.getDX();
                        int y = evt.getDY();
                        
                        lookAtDirection(getDirection(), Vector3f.UNIT_Y);
                        Quaternion org = getRotation();
                                            
                        Quaternion pitch = new Quaternion().fromAngleAxis(-1 * ((y * FastMath.PI * 2) / shares.appSettings.getHeight()), getLeft());                       
                        Quaternion yaw = new Quaternion().fromAngleAxis(-1 * ((x * FastMath.PI * 2) / shares.appSettings.getWidth()), Vector3f.UNIT_Y);
                        Quaternion roll = new Quaternion().fromAngleAxis(0, Vector3f.UNIT_Z);

                        org = roll.multLocal(org);
                        org = yaw.multLocal(org);
                        org = pitch.multLocal(org);

                        setRotation(org);                      
                    }
                }

                public void onAction(String action, MouseButtonEvent evt) {}
          };
                  
          mouse = new Mouse();
          
          mouse.setListener(mouseListener);
                   
          String [] mainActions = {"FC_Forward", "FC_Back", "FC_Left", "FC_Right", "FC_Up", "FC_Down"};
          int [] mainTriggers = {KeyInput.KEY_W, KeyInput.KEY_S, KeyInput.KEY_A, KeyInput.KEY_D, KeyInput.KEY_E, KeyInput.KEY_Q};
          
          kInputs.addInputs(mainTriggers, mainActions);       
          
          listener = new AnalogListener() {
              public void onAnalog(String name, float value, float tpf) {
                float speed = tpf * 5;
                
                if("FC_Forward".equals(name)) {
                    Vector3f dir = getDirection();
                    dir.multLocal(speed);                  
                    setLocation(getLocation().addLocal(dir));
                }
                
                if("FC_Back".equals(name)) {
                    Vector3f dir = getDirection();
                    dir.multLocal(speed);
                    setLocation(getLocation().subtractLocal(dir));
                }
                
                if("FC_Left".equals(name)) {
                    Vector3f dir = getLeft();
                    dir.multLocal(speed);
                    setLocation(getLocation().addLocal(dir));
                }
                
                if("FC_Right".equals(name)) {
                    Vector3f dir = getLeft();
                    dir.multLocal(speed);
                    setLocation(getLocation().subtractLocal(dir));
                }
                
                if("FC_Up".equals(name)) {
                    Vector3f dir = new Vector3f(Vector3f.UNIT_Y);
                    dir.multLocal(speed);
                    setLocation(getLocation().add(dir));
                }
                
                if("FC_Down".equals(name)) {
                    Vector3f dir = new Vector3f(Vector3f.UNIT_Y);
                    dir.multLocal(speed);
                    setLocation(getLocation().subtract(dir));
                }
              }
          };
          
          kInputs.addListener(listener);
    }
    
    
    private void detachListeners() {
        detachMouseListener();
        detachActionListener();
    }

    private void detachMouseListener() {
        if(mouse != null) {
            if(mouseListener != null) {
                mouse.detachListener();
            }
        }
    }

    private void detachActionListener() {
        if(kInputs != null) {
            if(listener != null) {
                kInputs.detachListener();
            }
        }
    }
}
