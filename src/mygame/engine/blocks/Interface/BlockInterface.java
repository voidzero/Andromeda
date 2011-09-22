/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.blocks.Interface;

import mygame.engine.nodes.GroupNode;

/**
 *
 * @author Dansion
 */
public interface BlockInterface {
    public String getName();
    
    public GroupNode getNode();
    
    public void setAlpha(boolean alpha);
}
