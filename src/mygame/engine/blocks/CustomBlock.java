/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.blocks;

import mygame.engine.blocks.Interface.BlockInterface;
import mygame.engine.nodes.GroupNode;

/**
 *
 * @author Dansion
 */
public class CustomBlock implements BlockInterface {
    private String name = null;
    
    public void setName(String new_name) {
        name = new_name;
    }
    
    public String getName() {
        return name;
    }

    public GroupNode getNode() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setAlpha(boolean alpha) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int amount() {
        return 1;
    }

    public Block getBlock(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
