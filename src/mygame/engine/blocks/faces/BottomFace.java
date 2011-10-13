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
public class BottomFace extends BlockFace implements BlockFaceInterface {
    public BottomFace() {
        super("BottomFace");

        resetMesh();
    }

    public final void resetMesh() {
        mode = Sloping.none;
        indexes[0] = 0;
        indexes[1] = 2;
        indexes[2] = 1;
        indexes[3] = 1;
        indexes[4] = 2;
        indexes[5] = 3;

        normals[0] = new Vector3f(0, -1, 0);
        normals[1] = new Vector3f(0, -1, 0);
        normals[2] = new Vector3f(0, -1, 0);
        normals[3] = new Vector3f(0, -1, 0);

        vertices[0] = new Vector3f( 0.5f, 0f, -0.5f);
        vertices[1] = new Vector3f(-0.5f, 0f, -0.5f);
        vertices[2] = new Vector3f( 0.5f, 0f,  0.5f);
        vertices[3] = new Vector3f(-0.5f, 0f,  0.5f);

        texCoord[0] = new Vector2f(0, 0);
        texCoord[1] = new Vector2f(1, 0);
        texCoord[2] = new Vector2f(0, 1);
        texCoord[3] = new Vector2f(1, 1);

        updateFace();
    }

    public void slopeTopLeft() {
        resetMesh();
    }

    public void slopeTopRight() {
        resetMesh();
    }

    public void slopeTopFront() {
        resetMesh();
    }

    public void slopeTopBack() {
        resetMesh();
    }

    public void slopeTopLeftBack() {
        resetMesh();
    }

    public void slopeTopRightBack() {
        resetMesh();
    }

    public void slopeTopLeftFront() {
        resetMesh();
    }

    public void slopeTopRightFront() {
        resetMesh();
    }

    public void slopeBottomLeft() {
        setVisible(false);
    }

    public void slopeBottomRight() {
        setVisible(false);
    }

    public void slopeBottomFront() {
        setVisible(false);
    }

    public void slopeBottomBack() {
        setVisible(false);
    }

    public void slopeBottomLeftBack() {
        setVisible(false);
    }

    public void slopeBottomRightBack() {
        setVisible(false);
    }

    public void slopeBottomLeftFront() {
        setVisible(false);
    }

    public void slopeBottomRightFront() {
        setVisible(false);
    }
}
