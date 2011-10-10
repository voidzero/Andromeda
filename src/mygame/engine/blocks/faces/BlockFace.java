/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.blocks.faces;

import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;

/**
 *
 * @author Dansion
 */
public class BlockFace extends Geometry {
    public Vector3f [] normals;
    public Vector3f [] vertices;
    public Vector2f [] texCoord;

    public int [] indexes;

    public BlockFace() {
        this("BlockFace");
    }

    public BlockFace(String name) {
        super(name);

        if(normals == null || vertices == null || texCoord == null || indexes == null) {
            throw new UnsupportedOperationException("Not enough mesh data");
        }
    }
}
