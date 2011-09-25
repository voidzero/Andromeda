/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.blocks;

import mygame.engine.blocks.Interface.BlockInterface;

/**
 *
 * @author Dansion
 */
public interface BlockIndex {
    public enum categories {};
    
    public BlockInterface getBlock(String name);
    
    public String [] getCategory(String category);
    
    public String [] getCategoryList();
    
    public Integer [] getCategoryValueList();
}
