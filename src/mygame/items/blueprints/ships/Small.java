/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.items.blueprints.ships;

import mygame.items.blueprints.BluePrint;

/**
 *
 * @author Dansion
 */
public class Small implements BluePrint{
    public int getSizeX() {
        return 20;
    }

    public int getSizeY() {
        return 24;
    }

    public int getSizeZ() {
        return 48;
    }

    public int getFloorHeight() {
        return 4;
    }    
}
