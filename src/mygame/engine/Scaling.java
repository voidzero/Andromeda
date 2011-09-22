/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine;

import mygame.engine.nodes.GroupNode;

/**
 *
 * @author Dansion
 */

public class Scaling {
    private double normalRegion = 1000;
    private double outerRegion = 1000000;
    private GroupNode centerNode;  
    
    public void setRootNode(GroupNode rootNode) {
        centerNode = rootNode;
    }
}