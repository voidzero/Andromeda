/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.input.Interfaces;

import com.jme3.input.event.MouseButtonEvent;
import com.jme3.input.event.MouseMotionEvent;

/**
 *
 * @author Dansion
 */
public interface RawListener {
    public void onAction(String action, MouseMotionEvent evt);
            
    public void onAction(String action, MouseButtonEvent evt);
}
