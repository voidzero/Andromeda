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
                String [] res = {"LightAlloy", "LightAlloyWindowed"};
                return res;
            }
        }
        
        return null;
    }

    public BlockInterface[] getCategoryBlocks(int category_index) {
        categories [] t = categories.values();
        String [] sres = getCategory(t[category_index].name());
        
        BlockInterface [] res = new BlockInterface[sres.length];
        
        for(int i = 0; i < sres.length; i++) {
            System.out.println("getting" + sres[i]);
            res[i] = getBlock(sres[i]);
        }
        
        return res;
    }

    public enum categories { Hulls, Floors, Inner_Walls, Doors, Ceilings, Engines};

    public BlockInterface getBlock(String name) {
        BlockInterface res = null;
        
        if(name.equals("LightAlloy")) { res = new LightAlloy();}
        if(name.equals("LightAlloyWindowed")) { res = new LightAlloyWindowed();}
        
        return res;
    }
    
    
    public SpaceShips () {
        
    }
    
    public String [] getCategoryList () {
        categories [] l = categories.values();
        String [] res = new String[l.length];
        
        for(int i = 0; i< l.length; i++) {
            res[i] = l[i].name();
        }
        
        return res;
    }
    
    public Integer [] getCategoryValueList() {
        categories [] l = categories.values();
        Integer [] res = new Integer[l.length];
        
        for(int i = 0; i< l.length; i++) {
            res[i] = l[i].ordinal();
        }
        
        return res;    
    }
}
