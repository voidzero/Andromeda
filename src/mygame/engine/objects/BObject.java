/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.objects;
import java.util.ArrayList;
import mygame.engine.blocks.Block;
import mygame.engine.blocks.BlockIndex;
import mygame.engine.blocks.BlockIndex.categories;
import mygame.engine.blocks.Blocks;
import mygame.engine.blocks.Lists.SpaceShips;
import mygame.engine.nodes.GroupNode;

/**
 *
 * @author Dansion
 */
public class BObject extends GroupNode{
    public ArrayList<Block> blocks = new ArrayList<Block>();
    public BlockIndex inventory = new SpaceShips();
    
    public BObject() {
        super();
    }
    
    public void addBlock(Block block) {
        blocks.add(block);
    }
    
    public void addBlock(Blocks block) {
        int amm = block.getBlockAmount();
        for(int i=0; i<amm; i++) {
            
        }
    }
    
    public Block getBlock(long x, long y, long z) {
        return null;
    }
    
    public void loadInEditor() {
    }
    
    public void save() {
        //@TODO Save Object
    }
    
    public void load() {
        //@TODO Load Object
    }
}
