/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.gui.Interfaces;

/**
 *
 * @author Dansion
 */
public interface GuiAction {   
    public void onAction();
    
    public void setValue(int value);
    
    public int getValue();
}
