/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.helpers;

/**
 *
 * @author Dansion
 */
public class Gui {
    public static int getTopY() {
        return Share.getInstance().appSettings.getHeight() - 1;
    }

    public static int getBottomY() {
        return 0;
    }

    public static int getLeftX() {
        return 0;
    }

    public static int getRightX() {
        return Share.getInstance().appSettings.getWidth() - 1;
    }
}
