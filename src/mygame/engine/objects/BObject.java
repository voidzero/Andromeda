/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.objects;
import com.jme3.math.Vector3f;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mygame.engine.blocks.Block;
import mygame.engine.blocks.BlockIndex;
import mygame.engine.blocks.BlockIndex.categories;
import mygame.engine.blocks.Blocks;
import mygame.engine.blocks.Interface.BlockInterface;
import mygame.engine.blocks.Lists.SpaceShips;
import mygame.engine.nodes.GroupNode;

/**
 *
 * @author Dansion
 */
public class BObject extends GroupNode{
    public ArrayList<BlockInterface> blocks = new ArrayList<BlockInterface>();
    public BlockIndex inventory = new SpaceShips();
    
    public BObject() {
        super();
    }
    
    public void addBlock(BlockInterface block, Vector3f position) {
        try {
            BlockInterface newBlock = block.getClass().newInstance();
            
            blocks.add(newBlock);
            
            newBlock.getNode().setLocalTranslation(position);
            attachChild(newBlock.getNode());
        } catch (InstantiationException ex) {
            Logger.getLogger(BObject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(BObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Block getBlock(float x, float y, float z) {
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
