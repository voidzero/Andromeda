/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.state;

import com.jme3.scene.Node;
import mygame.engine.Scaling;
import mygame.space.BackDrop;

/**
 *
 * @author Dansion
 */
public class Space extends AbstractState{
    private Scaling space = new Scaling();
    private BackDrop backdrop = new BackDrop();
    
    public Space(Node parent) {
        super(parent);
        
        backdrop.setLocalScale(5000);
        
        rootNode.attachChild(space);
        rootNode.attachChild(backdrop);
    }
}
