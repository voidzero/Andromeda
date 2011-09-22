/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.nodes;
import com.jme3.scene.Node;


/**
 *
 * @author Dansion
 */
public class GroupNode extends Node{   
    public GroupNode() {
       super();
    }

    public GroupNode(String name) {
        super(name);
        
        System.out.println("New GroupNode : " + name);
    }
}
