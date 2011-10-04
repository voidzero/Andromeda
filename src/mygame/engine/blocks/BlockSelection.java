/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.blocks;

import com.jme3.asset.AssetManager;
import com.jme3.collision.CollisionResults;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import mygame.Assets;
import mygame.engine.blocks.Interface.BlockInterface;
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
    private BlockInterface mask_source = null;
    
    private AssetManager assets = Assets.getInstance().assetManager;
    private int size = 0;
    private boolean isBlocked = false;
    
    private String sel_allowed = "Materials/block_select_01.j3m";
    private String sel_denied = "Materials/block_select_02.j3m";
    
    public BlockSelection(BObject object) {
        this(object, new Block("blockSelection"));
    }
    
    public BlockSelection(BObject object, BlockInterface mask) {
        super("blockSelection");
        
        target = object;
        setMask(mask);
    }
    
    public void setPlacing(Vector3f position) {
        if(position != null && mask != null) {
            target_pos = position;

            updatePlacing();
        }
    }
    
    private void updatePlacing() {
        setLocalTranslation(target_pos);
        
        isBlocked = false;
        //check if mask intersects other blocks
        CollisionResults results = new CollisionResults();

        for(int mb = 0; mb < mask.amount(); mb++) {
            Vector3f b_loc = new Vector3f(mask.getBlock(mb).getLocalTranslation());
            b_loc.addLocal(getLocalTranslation());
            
            mask.getBlock(mb).setBlockMaterial(sel_allowed);
            
            if(b_loc.y >= target.getCurrentFloorY() + target.getFloorHeight()  || b_loc.y < target.getCurrentFloorY()) {
               mask.getBlock(mb).setBlockMaterial(sel_denied);
               isBlocked = true;
               continue;
            }
            
            target.collideWith(getWorldBound(), results);
            
            for(int i = 0; i < results.size(); i++) {
                if(results.getCollision(i).getGeometry() instanceof BlockInterface) {
                    BlockInterface block = (BlockInterface) results.getCollision(i).getGeometry();
                    Vector3f loc = block.getBlock(0).getLocation();                    

                    if( loc.equals(b_loc)) {
                       mask.getBlock(mb).setBlockMaterial(sel_denied);
                       isBlocked = true;
                       break;
                    }
                }
            }
        }
    }
    
    public void update() {
        updateMask();
        
    }
    
    public void setMask(BlockInterface newMask) {
        clearMask();
       
        size = newMask.amount();
        mask = new Blocks(size, "blockselection");
        
        for(int i = 0; i<size; i++) {
            Block b = newMask.getBlock(i);
            Block b2 = new Block("blockSelection");
            b2.setBlockMaterial(sel_allowed);
            b2.setQueueBucket(Bucket.Translucent);
            mask.addBlock(b2, b.x, b.y,  b.z);
        }
        
        updateMask();
    }
    
    public void clearMask() {
        detachAllChildren();
        
        mask = null;
    }
    
    public void updateMask() {
        if(mask == null) {
            detachAllChildren();
        }
        else {
            attachChild(mask);
        }
    }
    
    public boolean isBlocked() {
        return isBlocked;
    }
}
