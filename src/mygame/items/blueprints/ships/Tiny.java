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
public class Tiny implements BluePrint{
    public int getSizeX() {
        return 8;
    }

    public int getSizeY() {
        return 8;
    }

    public int getSizeZ() {
        return 16;
    }

    public int getFloorHeight() {
        return 4;
    }    
}
