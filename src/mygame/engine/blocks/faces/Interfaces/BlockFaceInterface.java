/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.blocks.faces.Interfaces;

import com.jme3.material.Material;
import mygame.engine.blocks.faces.BlockFace.Sloping;

/**
 *
 * @author Dansion
 */
public interface BlockFaceInterface {
    public void resetMesh();

    public void slopeTopLeft();

    public void slopeTopRight();

    public void slopeTopFront();

    public void slopeTopBack();

    public void slopeTopLeftBack();

    public void slopeTopRightBack();

    public void slopeTopLeftFront();

    public void slopeTopRightFront();

    public void slopeBottomLeft();

    public void slopeBottomRight();

    public void slopeBottomFront();

    public void slopeBottomBack();

    public void slopeBottomLeftBack();

    public void slopeBottomRightBack();

    public void slopeBottomLeftFront();

    public void slopeBottomRightFront();

    public void setAlpha(boolean alpha);

    public void setVisible(boolean visible);

    public void setMaterial(Material material);

    public void setSloping(Sloping mode);

    public boolean isSloped();

    public Sloping getSlopeMode();
}
