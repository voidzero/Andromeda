/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.blocks.Lists;

import mygame.engine.blocks.BlockIndex;
import mygame.engine.blocks.Interface.BlockInterface;
import mygame.engine.blocks.Spaceship.Hulls.LightAlloy;
import mygame.engine.blocks.Spaceship.Hulls.LightAlloyWindowed;

/**
 *
 * @author Dansion
 */
public class SpaceShips implements BlockIndex {
    public String [] getCategory(String category) {
        switch(categories.valueOf(category)) {
            case Hulls: {
                String [] res = {"LightAlloyBlock", "LightAlloy", "LightAlloyWindowed"};
                return res;
            }
        }
        
        return null;
    }

    public enum categories { Hulls, Floors, Inner_Walls, Doors, Ceilings, Engines};

    public BlockInterface getBlock(String name) {
        BlockInterface res = null;
               
        res = name.equals("LightAlloy") ? res = new LightAlloy(): null;
        res = name.equals("LightAlloyWindowed") ? res = new LightAlloyWindowed(): null;
        
        return res;
    }
    
    
    public SpaceShips () {
        
    }

}
