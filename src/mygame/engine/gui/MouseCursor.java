/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.gui;

import com.jme3.asset.AssetManager;
import com.jme3.collision.CollisionResults;
import com.jme3.input.RawInputListener;
import com.jme3.input.event.JoyAxisEvent;
import com.jme3.input.event.JoyButtonEvent;
import com.jme3.input.event.KeyInputEvent;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.input.event.MouseMotionEvent;
import com.jme3.input.event.TouchEvent;
import com.jme3.math.Vector2f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import com.jme3.ui.Picture;
import mygame.Assets;
import mygame.engine.Picking;
import mygame.helpers.Share;

/**
 *
 * @author Dansion
 */
public class MouseCursor extends Picture implements RawInputListener{
    private AssetManager assetManager = Assets.getInstance().assetManager;
    private static Share shares = Share.getInstance();
    private int mx = 0;
    private int my = 0;
    private int msx = 0;
    private int msy = 0;
    
    public MouseCursor() {
        this(shares.guiNode);
    }
    
    public MouseCursor(Node guiNode) {
        super("mousecursor");
        
        System.out.println( "mouse_cursor");
        
        setImage(assetManager, "Textures/m_cursor_01.png", true);
        msx = 32;
        msy = 32;
        setWidth(msx);
        setHeight(msy);
        
        //setPosition(shares.appSettings.getWidth()/2-32, shares.appSettings.getHeight()/2-32);
        
        setLocalTranslation(0, 0, 1);
        
        guiNode.attachChild(this);
        
        shares.inputManager.addRawInputListener(this);
        shares.inputManager.setCursorVisible(false);
    }
    
        
    public CollisionResults pick(Node shootables, Camera source) {
        return Picking.cursorPick(shootables, new Vector2f(mx, my), source);
    }
    
    public CollisionResults pickGui(Node shootables, Camera source) {
        return Picking.cursorPickGui(shootables, new Vector2f(mx, my), source);
    }

    //RawInputListener
    public void beginInput() {}

    public void endInput() {}

    public void onJoyAxisEvent(JoyAxisEvent evt) {}

    public void onJoyButtonEvent(JoyButtonEvent evt) {}

    public void onMouseMotionEvent(MouseMotionEvent evt) {
        int dx = evt.getDX();
        int dy = evt.getDY();
        
        mx = mx + dx;
        my = my + dy;
        
        if(mx < 0) {mx = 0;}
        if(my < 0) {my = 0;}
        
        if(mx > shares.appSettings.getWidth() - 1) { mx = shares.appSettings.getWidth() - 1; }
        if(my > shares.appSettings.getHeight() - 1) { my = shares.appSettings.getHeight() - 1; }
        
        int x = mx;
        int y = my;
        
        y = y - msy;
             
        setLocalTranslation(x, y, 1);
    }

    public void onMouseButtonEvent(MouseButtonEvent evt) {}

    public void onKeyEvent(KeyInputEvent evt) {}

    public void onTouchEvent(TouchEvent evt) {}
}
