/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.blocks;

import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import mygame.engine.blocks.Interface.BlockInterface;
import mygame.engine.nodes.GroupNode;

/**
 *
 * @author Dansion
 */
public class Blocks extends GroupNode implements BlockInterface {
    public String block_name;
    private int block_amount = 1;
    private int count = 0;
    
    private Block[] blocks;
    
    public Blocks(int size, String name) {
        super(name);
        
        block_name = name;
        block_amount = size;
        blocks = new Block[size];
    }
    
    public void addBlock(Block block, int x, int y, int z) {
        if(emptyBlock()) {
            attachChild(block);
            
            block.x = x;
            block.y = y;
            block.z = z;
            
            block.setLocalTranslation(x, y, z);
            emptyBlock(block);
        }
    }
    
    public void removeBlock(Block block) {
        detachChild(block);
        
        blocks[findBlock(block)] = null;
        count--;
    }
    
    public boolean emptyBlock() {
        if( count < block_amount ) {
            return true;
        }
        else {
            return false;
        }
    }
    
    private void emptyBlock(Block block) {
        blocks[findBlock(null)] = block;
        count++;
    }
    
    private int findBlock(Block block) {
        boolean empty = false;
        
        if (block == null) {
            empty = true;
        }
        
        for(int i = 0; i < blocks.length; i++) {
            if (empty) {
                if(blocks[i] == null) {
                    return i;
                }
            }
            else {
                if(blocks[i].equals(block)) {
                    return i;
                }
            }
        }

        return -1;
    }
    
    public int getBlockAmount() {
        return block_amount;
    }
    
    public Block getBlock(int i) {
        return blocks[i];
    }

    public GroupNode getNode() {
        return this;
    }

    public void setAlpha(boolean alpha) {
        Material mat = null;
        
        for(int i=0;i<block_amount;i++) {
            if(alpha) {
                blocks[i].getMaterial().getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
            }
            else {
                mat = blocks[i].getMaterial();
                mat.getAdditionalRenderState().setBlendMode(BlendMode.Off);
                blocks[i].setMaterial(mat);
                blocks[i].setQueueBucket(Bucket.Inherit);
            }
        }
    }
    
    public int amount() {
        return getBlockAmount();
    }
}
