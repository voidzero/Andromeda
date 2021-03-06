/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.blocks.faces;

import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.util.BufferUtils;
import com.jme3.util.TangentBinormalGenerator;
import mygame.engine.blocks.faces.Interfaces.BlockFaceInterface;
import mygame.engine.nodes.RenderNode;

/**
 *
 * @author Dansion
 */
public class BlockFace extends RenderNode {
    public Vector3f [] normals = new Vector3f[4];
    public Vector3f [] vertices = new Vector3f[4];
    public Vector2f [] texCoord = new Vector2f[4];

    public int [] indexes = new int[6];

    public enum Sloping {slopeTopLeft, slopeTopRight, slopeTopFront, slopeTopBack, slopeTopLeftBack, slopeTopRightBack, slopeTopLeftFront, slopeTopRightFront,
                         slopeBottomLeft, slopeBottomRight, slopeBottomFront, slopeBottomBack, slopeBottomLeftBack, slopeBottomRightBack, slopeBottomLeftFront, slopeBottomRightFront,
                         none
                      };

    public Sloping mode = Sloping.none;

    public BlockFace() {
        this("BlockFace");
    }

    public BlockFace(String name) {
        super(name);

        if(normals == null || vertices == null || texCoord == null || indexes == null) {
            throw new UnsupportedOperationException("Not enough mesh data");
        }
    }

    public final void updateFace() {
        mesh = new Mesh();
        mesh.setDynamic();

        mesh.setBuffer(Type.Normal, 3, BufferUtils.createFloatBuffer(normals));
        mesh.setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(vertices));
        mesh.setBuffer(Type.TexCoord, 2, BufferUtils.createFloatBuffer(texCoord));
        mesh.setBuffer(Type.Index,    1, BufferUtils.createIntBuffer(indexes));

        TangentBinormalGenerator.generate(mesh);

        mesh.updateBound();
    }

    public void setAlpha(boolean alpha) {
        if(alpha) {
            getMaterial().getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
            getMaterial().setBoolean("m_UseAlpha", true);
        }
        else {
            Material mat = getMaterial();
            mat.getAdditionalRenderState().setBlendMode(BlendMode.Off);
            mat.setBoolean("m_UseAlpha", false);
            setMaterial(mat);
            setQueueBucket(Bucket.Inherit);
        }
    }

    public void setVisible(boolean visible) {
        if(visible) {
            setCullHint(CullHint.Dynamic);
        }
        else {
            setCullHint(CullHint.Always);
        }
    }

     public void setSloping(Sloping mode) {
         BlockFaceInterface f = (BlockFaceInterface) this;

         this.mode = mode;

         switch(mode) {
             case none:
                 f.resetMesh();
                 break;
             case slopeTopLeft:
                 f.slopeTopLeft();
                 break;
             case slopeTopRight:
                 f.slopeTopRight();
                 break;
             case slopeTopFront:
                 f.slopeTopFront();
                 break;
             case slopeTopBack:
                 f.slopeTopBack();
                 break;
             case slopeTopLeftBack:
                 f.slopeTopLeftBack();
                 break;
             case slopeTopRightBack:
                 f.slopeTopRightBack();
                 break;
             case slopeTopLeftFront:
                 f.slopeTopLeftFront();
                 break;
             case slopeTopRightFront:
                 f.slopeTopRightFront();
                 break;
             case slopeBottomLeft:
                 f.slopeBottomLeft();
                 break;
             case slopeBottomRight:
                 f.slopeBottomRight();
                 break;
             case slopeBottomFront:
                 f.slopeBottomFront();
                 break;
             case slopeBottomBack:
                 f.slopeBottomBack();
                 break;
             case slopeBottomLeftBack:
                 f.slopeBottomLeftBack();
                 break;
             case slopeBottomRightBack:
                 f.slopeBottomRightBack();
                 break;
             case slopeBottomLeftFront:
                 f.slopeBottomLeftFront();
                 break;
             case slopeBottomRightFront:
                 f.slopeBottomRightFront();
                 break;
         }
     }

     public boolean isSloped() {
         if(mode == Sloping.none) {
             return false;
         }
         else {
             return true;
         }
     }

     public Sloping getSlopeMode() {
         return mode;
     }
}
