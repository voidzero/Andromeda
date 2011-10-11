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
public class LeftFace extends BlockFace implements BlockFaceInterface {
    public LeftFace() {
        super("LeftFace");

        resetMesh();
    }

    public final void resetMesh() {
        indexes[0] = 0;
        indexes[1] = 2;
        indexes[2] = 1;
        indexes[3] = 1;
        indexes[4] = 2;
        indexes[5] = 3;

        normals[0] = new Vector3f(-1, 0, 0);
        normals[1] = new Vector3f(-1, 0, 0);
        normals[2] = new Vector3f(-1, 0, 0);
        normals[3] = new Vector3f(-1, 0, 0);

        vertices[0] = new Vector3f(0.5f, 1f,  0.5f);
        vertices[1] = new Vector3f(0.5f, 1f, -0.5f);
        vertices[2] = new Vector3f(0.5f, 0f,  0.5f);
        vertices[3] = new Vector3f(0.5f, 0f, -0.5f);

        texCoord[0] = new Vector2f(0, 0);
        texCoord[1] = new Vector2f(1, 0);
        texCoord[2] = new Vector2f(0, 1);
        texCoord[3] = new Vector2f(1, 1);

        updateFace();
    }

    public void slopeTopLeft() {
        vertices[0] = new Vector3f(-0.5f, 1f,  0.5f);
        vertices[1] = new Vector3f(-0.5f, 1f, -0.5f);
        updateFace();
    }

    public void slopeTopRight() {}

    public void slopeTopFront() {
        vertices[1] = new Vector3f(0.5f, 1f,  0.5f);
        texCoord[1] = new Vector2f(0, 0);
        updateFace();
    }

    public void slopeTopBack() {
        vertices[0] = new Vector3f(0.5f, 1f,  -0.5f);
        updateFace();
    }

    public void slopeTopLeftBack() {
        vertices[0] = new Vector3f(-0.5f, 1f, -0.5f);
        vertices[1] = new Vector3f(-0.5f, 1f, -0.5f);
        updateFace();
    }

    public void slopeTopRightBack() {}

    public void slopeTopLeftFront() {
        vertices[0] = new Vector3f(-0.5f, 1f, 0.5f);
        vertices[1] = new Vector3f(-0.5f, 1f, 0.5f);
        texCoord[1] = new Vector2f(0, 0);
        updateFace();
    }

    public void slopeTopRightFront() {}

    public void slopeBottomLeft() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void slopeBottomRight() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void slopeBottomFront() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void slopeBottomBack() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void slopeBottomLeftBack() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void slopeBottomRightBack() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void slopeBottomLeftFront() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void slopeBottomRightFront() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
