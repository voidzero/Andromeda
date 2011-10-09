/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.blocks.Interface;

import com.jme3.math.Vector3f;
import mygame.engine.blocks.Block;
import mygame.engine.nodes.GroupNode;

/**
 *
 * @author Dansion
 */
public interface BlockInterface {
    public Integer x = 0;
    
    public String getName();
    
    public GroupNode getNode();
    
    public void setAlpha(boolean alpha);
    
    public int amount();
    
    public Block getBlock(int index);
    
    public Vector3f getLocation();
    
    public boolean isSolid();
}
