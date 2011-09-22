/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.blocks;

import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import mygame.Assets;
import mygame.engine.nodes.GroupNode;
import mygame.engine.objects.BObject;

/**
 *
 * @author Dansion
 */
public class BlockSelection extends GroupNode {
    private BObject target = null;
    private Vector3f target_pos = null;
    
    private Blocks mask = null;
    private Block mask_source = null;
    private Blocks mask_source_blocks = null;
    
    private AssetManager assets = Assets.getInstance().assetManager;
    private int size = 0;
    
    private String sel_allowed = "Materials/block_select_01.j3m";
    private String sel_denied = "Materials/block_select_02.j3m";
    
    public BlockSelection(BObject object) {
        this(object, new Block("blockSelection"));
    }
    
    public BlockSelection(BObject object, Block mask) {
        super("blockSelection");
        
        setBlockMask(mask);
    }
    
    public BlockSelection(BObject object, Blocks mask) {
        super("blockSelection");
        
        setBlocksMask(mask);
    }
    
    public void setPlacing(Vector3f position) {
        target_pos = position;
        setLocalTranslation(target_pos);
    }
    
    private void updatePlacing() {
        setLocalTranslation(target_pos);
    }
    
    public void update() {
        updateMask();
        if(target_pos != null && mask != null) {
            updatePlacing();
        }
    }
    
    private void setBlockMask(Block mask_s) {
        clearMask();
        
        mask_source = mask_s;
        
        size = 1;
        
        mask = new Blocks(size, "blockselection");
        
        Block b2 = new Block();
        b2.setQueueBucket(Bucket.Transparent);
        b2.setBlockMaterial(sel_allowed);
        mask.addBlock(b2, 0, 0, 0);
    }
    
    public void setBlocksMask(Blocks mask_s) {
        clearMask();
        
        mask_source_blocks = mask_s;
       
        size = mask_s.getBlockAmount();
        mask = new Blocks(size, "blockselection");
        
        for(int i = 0; i<size; i++) {
            Block b = mask_s.getBlock(i);
            Block b2 = new Block("blockSelection");
            b2.setBlockMaterial(sel_allowed);
            b2.setQueueBucket(Bucket.Translucent);
            mask.addBlock(b2, b.x, b.y,  b.z);
        }
        
        updateMask();
    }
    
    public void clearMask() {
        this.mask = null;
        
        updateMask();
        
        mask_source = null;
        mask_source_blocks = null;
    }
    
    public void updateMask() {
        if(mask == null) {
            detachAllChildren();
        }
        else {
            attachChild(mask);
        }
    }
}
