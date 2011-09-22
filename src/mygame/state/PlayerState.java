/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.state;

import com.jme3.scene.Node;

/**
 *
 * @author Dansion
 */
public class PlayerState extends AbstractState {
    public enum Environment { SPACE, SHIP, STARSYSTEM, PLANET, SPACEBODY, GAMEMENU };
    
    public PlayerState(Node parent) {
        super(parent);
    }
}
