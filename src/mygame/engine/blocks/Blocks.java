/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.blocks;

import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import mygame.engine.blocks.Interface.BlockInterface;
import mygame.engine.nodes.GroupNode;
import mygame.engine.objects.BObject;

/**
 *
 * @author Dansion
 */
public class Blocks extends GroupNode implements BlockInterface {
    public String block_name;
    private int block_amount = 1;
    private int count = 0;
    private boolean solid = true;

    private Block[] blocks;
    private boolean transparant = false;

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
            blocks[i].setAlpha(alpha);
        }
    }

    public int amount() {
        return getBlockAmount();
    }

    public Vector3f getLocation() {
        return this.getLocalTranslation();
    }

    public boolean isSolid() {
        return solid;
    }

    public void optimizeFor(BObject parent, Vector3f b_pos , boolean optimize_neighbours) {
        for(int i = 0; i < block_amount; i++) {
            Block b = blocks[i];
            Vector3f tot = new Vector3f(this.getNode().getLocalTranslation());
            tot.addLocal(b.getLocalTranslation());
            b.optimizeFor(parent, tot, optimize_neighbours);
        }
    }

    public boolean isTransparant() {
        return transparant;
    }

    public void isTransparant(boolean transparant) {
        this.transparant = transparant;
    }

    public void optimizeFor(BObject parent, Vector3f b_pos) {
        optimizeFor(parent, b_pos, false);
    }
}
