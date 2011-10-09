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
public class Huge implements BluePrint {
    public int getSizeX() {
        return 100;
    }

    public int getSizeY() {
        return 56;
    }

    public int getSizeZ() {
        return 400;
    }

    public int getFloorHeight() {
        return 4;
    }
}
