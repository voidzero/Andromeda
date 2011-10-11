/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.blocks.faces;

import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import mygame.engine.blocks.faces.Interfaces.BlockFaceInterface;

/**
 *
 * @author Dansion
 */
public class TopFace extends BlockFace implements BlockFaceInterface {
    public TopFace() {
        super("TopFace");

        resetMesh();
    }

    public final void resetMesh() {
        indexes[0] = 0;
        indexes[1] = 2;
        indexes[2] = 1;
        indexes[3] = 1;
        indexes[4] = 2;
        indexes[5] = 3;

        normals[0] = new Vector3f(0, 1, 0);
        normals[1] = new Vector3f(0, 1, 0);
        normals[2] = new Vector3f(0, 1, 0);
        normals[3] = new Vector3f(0, 1, 0);

        vertices[0] = new Vector3f(-0.5f, 1f, -0.5f);
        vertices[1] = new Vector3f( 0.5f, 1f, -0.5f);
        vertices[2] = new Vector3f(-0.5f, 1f,  0.5f);
        vertices[3] = new Vector3f( 0.5f, 1f,  0.5f);

        texCoord[0] = new Vector2f(0, 0);
        texCoord[1] = new Vector2f(1, 0);
        texCoord[2] = new Vector2f(0, 1);
        texCoord[3] = new Vector2f(1, 1);

        updateFace();
    }

    public void slopeTopLeft() {
        setVisible(false);
    }

    public void slopeTopRight() {
        setVisible(false);
    }

    public void slopeTopFront() {
        setVisible(false);
    }

    public void slopeTopBack() {
        setVisible(false);
    }

    public void slopeTopLeftBack() {
        setVisible(false);
    }

    public void slopeTopRightBack() {
        setVisible(false);
    }

    public void slopeTopLeftFront() {
        setVisible(false);
    }

    public void slopeTopRightFront() {
        setVisible(false);
    }

    public void slopeBottomLeft() {}
    public void slopeBottomRight() {}
    public void slopeBottomFront() {}
    public void slopeBottomBack() {}
    public void slopeBottomLeftBack() {}
    public void slopeBottomRightBack() {}
    public void slopeBottomLeftFront() {}
    public void slopeBottomRightFront() {}
}
