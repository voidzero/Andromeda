/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.input;

import com.jme3.input.RawInputListener;
import com.jme3.input.event.InputEvent;
import com.jme3.input.event.JoyAxisEvent;
import com.jme3.input.event.JoyButtonEvent;
import com.jme3.input.event.KeyInputEvent;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.input.event.MouseMotionEvent;
import com.jme3.input.event.TouchEvent;
import mygame.engine.input.Interfaces.RawListener;
import mygame.helpers.Share;

/**
 *
 * @author Dansion
 */
public class Mouse implements RawInputListener{
    private Share shares = Share.getInstance();
    private RawListener listener = null;
    
    public Mouse() {
        shares.inputManager.addRawInputListener(this);
    }

    public void setListener(RawListener listener) {
        this.listener = listener;
    }
    
    public void detachListener() {
        if(listener != null) {
            shares.inputManager.removeRawInputListener(this);
        }
    }
    
    public void attachListener() {
        if(listener != null) {
            shares.inputManager.addRawInputListener(this);
        }
    }
    
    private void call(String action, MouseMotionEvent event) {
        if(listener != null) {
            listener.onAction(action, event);
        }
    }
    
    private void call(String action, MouseButtonEvent event) {
        if(listener != null) {
            listener.onAction(action, event);
        }
    }
    
    public void beginInput() {
    }

    public void endInput() {
    }

    public void onJoyAxisEvent(JoyAxisEvent evt) {
    }

    public void onJoyButtonEvent(JoyButtonEvent evt) {
    }

    public void onMouseMotionEvent(MouseMotionEvent evt) {
        call("onMouseMotion", evt);
    }

    public void onMouseButtonEvent(MouseButtonEvent evt) {
        call("onMouseButton", evt);
    }

    public void onKeyEvent(KeyInputEvent evt) {}

    public void onTouchEvent(TouchEvent evt) {} 
}
