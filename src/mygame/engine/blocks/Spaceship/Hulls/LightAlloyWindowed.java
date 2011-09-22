/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.blocks.Spaceship.Hulls;

import com.jme3.material.RenderState.BlendMode;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import mygame.engine.blocks.Block;
import mygame.engine.blocks.Blocks;

/**
 *
 * @author Dansion
 */
public class LightAlloyWindowed extends Blocks{
    public Block topBlock;
    public Block midBlock;
    public Block midBlock2;
    public Block lowBlock;

    
    public LightAlloyWindowed() {
        super(4, "Light alloy (windowed)");
       
        topBlock = new Block();
        midBlock = new Block();
        midBlock2 = new Block();
        lowBlock = new Block();
        
        midBlock.setQueueBucket(Bucket.Translucent);
        midBlock2.setQueueBucket(Bucket.Translucent);
               
        midBlock.setBlockMaterial("Materials/window_01.j3m");        
        midBlock.getMaterial().setBoolean("m_UseAlpha", true);
        midBlock.getMaterial().getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
        midBlock2.setBlockMaterial("Materials/window_01.j3m");        
        midBlock2.getMaterial().setBoolean("m_UseAlpha", true);
        midBlock2.getMaterial().getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
        
        topBlock.setBlockMaterial("Materials/ship_plating_01.j3m");
        lowBlock.setBlockMaterial("Materials/ship_plating_01.j3m");
        
        addBlock(topBlock, 0, 3, 0);
        addBlock(midBlock2, 0, 2, 0);
        addBlock(midBlock, 0, 1, 0);
        addBlock(lowBlock, 0, 0, 0);
    }
}
