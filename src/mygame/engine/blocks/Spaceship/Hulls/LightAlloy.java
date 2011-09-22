/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.blocks.Spaceship.Hulls;

import com.jme3.asset.AssetManager;
import mygame.Assets;
import mygame.engine.blocks.Block;
import mygame.engine.blocks.Blocks;

/**
 *
 * @author Dansion
 */
public class LightAlloy extends Blocks{
    public Block topBlock;    
    public Block midBlock;
    public Block midBlock2;
    public Block lowBlock;
    private final AssetManager assetManager = Assets.getInstance().assetManager;
    
    public LightAlloy() {
        super(4, "Light alloy");

        topBlock = new Block();
        midBlock = new Block();
        midBlock2 = new Block();
        lowBlock = new Block();
        
        topBlock.setBlockMaterial("Materials/ship_plating_01.j3m");
        midBlock2.setBlockMaterial("Materials/ship_plating_01.j3m");
        midBlock.setBlockMaterial("Materials/ship_plating_01.j3m");
        lowBlock.setBlockMaterial("Materials/ship_plating_01.j3m");
        
        addBlock(topBlock, 0, 3, 0);
        addBlock(midBlock2, 0, 2, 0);
        addBlock(midBlock, 0, 1, 0);
        addBlock(lowBlock, 0, 0, 0);
    }
}
