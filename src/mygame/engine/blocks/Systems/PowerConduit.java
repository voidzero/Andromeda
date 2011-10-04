/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.blocks.Systems;

import mygame.engine.AnimatedTexture;
import mygame.engine.blocks.CustomBlock;

/**
 *
 * @author Dansion
 */
public class PowerConduit extends CustomBlock {
    private AnimatedTexture tex = new AnimatedTexture();
    
    public PowerConduit() {
        super();
        
        super.loadBlenderModel("Models/powerconduit.blend");
        
        tex.loadAnimation("Textures/anim.png", "Assets/AnimMaps/anim.txt");
        
        setName("Power - conduit");
    }
}
