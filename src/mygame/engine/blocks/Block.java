/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.blocks;

import com.jme3.collision.CollisionResult;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import mygame.Assets;
import mygame.engine.blocks.Interface.BlockInterface;
import mygame.engine.blocks.faces.BackFace;
import mygame.engine.blocks.faces.BlockFace;
import mygame.engine.blocks.faces.BottomFace;
import mygame.engine.blocks.faces.FrontFace;
import mygame.engine.blocks.faces.Interfaces.BlockFaceInterface;
import mygame.engine.blocks.faces.LeftFace;
import mygame.engine.blocks.faces.RightFace;
import mygame.engine.blocks.faces.TopFace;
import mygame.engine.nodes.GroupNode;
import mygame.engine.objects.BObject;

/**
 *
 * @author Dansion
 */
public class Block extends GroupNode implements BlockInterface {
    public boolean isSolid;
    public long strength = 100;
    public long health;
    private boolean solid = true;
    public float size_x = 1.0f, size_y = 1.0f, size_z = 1.0f;
    public int x = 0, y = 0, z = 0;

    public BlockFaceInterface [] faces = {new TopFace(), new BottomFace(), new FrontFace(), new BackFace(), new LeftFace(), new RightFace()};
    private boolean transparant = false;

    public Block() {
        this("block");
    }

    public Block(String name) {
        super(name);

        health = strength;
        setName(name);

        createBlock();
    }

    public void size(float x, float y, float z) {
        scale(x, y, z);
        size_x = x;
        size_y = y;
        size_z = z;
        refreshBlock();
    }

    private void buildBlock() {
        //Top - 0
        attachChild((BlockFace) faces[0]);

        //Bottom - 1
        attachChild((BlockFace) faces[1]);

        //Front - 2
        attachChild((BlockFace) faces[2]);

        //Back - 3
        attachChild((BlockFace) faces[3]);

        //Left - 4
        attachChild((BlockFace) faces[4]);

        //Right - 5
        attachChild((BlockFace) faces[5]);
    }


    public void optimizeFor(BObject parent, Vector3f b_pos, boolean optimize_neighbours) {
        resetBlock();

        //block obj_loc
        Vector3f pos = this.getNode().getLocalTranslation();

        //back
        BlockInterface bbb = parent.getBlock(b_pos.x, b_pos.y, b_pos.z + 1, true);

        if(bbb != null && optimize_neighbours) {
            bbb.optimizeFor(parent, new Vector3f(b_pos.x, b_pos.y, b_pos.z + 1), false);
        }

        boolean bbs = bbb != null ? (bbb.isSolid() && (!bbb.isTransparant() || (bbb.isTransparant() && isTransparant()))) : false;

        //front
        BlockInterface bfb = parent.getBlock(b_pos.x, b_pos.y, b_pos.z - 1, true);

        if(bfb != null && optimize_neighbours) {
            bfb.optimizeFor(parent, new Vector3f(b_pos.x, b_pos.y, b_pos.z - 1), false);
        }

        boolean bfs = bfb != null ? (bfb.isSolid() && (!bfb.isTransparant() || (bfb.isTransparant() && isTransparant()))) : false;

        //left
        BlockInterface blb = parent.getBlock(b_pos.x + 1, b_pos.y, b_pos.z, true);

        if(blb != null && optimize_neighbours) {
            blb.optimizeFor(parent, new Vector3f(b_pos.x + 1, b_pos.y, b_pos.z), false);
        }

        boolean bls = blb != null ? blb.isSolid() && (!blb.isTransparant() || (blb.isTransparant() && isTransparant())) : false;

        //right
        BlockInterface brb = parent.getBlock(b_pos.x - 1, b_pos.y, b_pos.z, true);

        if(brb != null && optimize_neighbours) {
            brb.optimizeFor(parent, new Vector3f(b_pos.x - 1, b_pos.y, b_pos.z), false);
        }

        boolean brs = brb != null ? brb.isSolid() && (!brb.isTransparant() || (brb.isTransparant() && isTransparant())) : false;

        //top
        BlockInterface btb = parent.getBlock(b_pos.x, b_pos.y + 1, b_pos.z, true);

        if(btb != null && optimize_neighbours) {
            btb.optimizeFor(parent, new Vector3f(b_pos.x, b_pos.y + 1, b_pos.z), false);
        }

        boolean bts = btb != null ? btb.isSolid() && (!btb.isTransparant() || (btb.isTransparant() && isTransparant())) : false;

        //under
        BlockInterface bub = parent.getBlock(b_pos.x, b_pos.y - 1, b_pos.z, true);

        if(bub != null && optimize_neighbours) {
            bub.optimizeFor(parent, new Vector3f(b_pos.x, b_pos.y - 1, b_pos.z), false);
        }

        boolean bus = bub != null ? bub.isSolid() && (!bub.isTransparant() || (bub.isTransparant() && isTransparant())) : false;

        //left-back
        BlockInterface blbb = parent.getBlock(b_pos.x + 1, b_pos.y, b_pos.z + 1, true);

        if(blbb != null && optimize_neighbours) {
            blbb.optimizeFor(parent, new Vector3f(b_pos.x + 1, b_pos.y, b_pos.z + 1), false);
        }

        boolean blbs = blbb != null ? blbb.isSolid() && (!blbb.isTransparant() || (blbb.isTransparant() && isTransparant())) : false;

        //right-back
        BlockInterface brbb = parent.getBlock(b_pos.x - 1, b_pos.y, b_pos.z + 1, true);

        if(brbb != null && optimize_neighbours) {
            brbb.optimizeFor(parent, new Vector3f(b_pos.x - 1, b_pos.y, b_pos.z + 1), false);
        }

        boolean brbs = brbb != null ? brbb.isSolid() && (!brbb.isTransparant() || (brbb.isTransparant() && isTransparant())) : false;

        //left-front
        BlockInterface blfb = parent.getBlock(b_pos.x + 1, b_pos.y, b_pos.z - 1, true);

        if(blfb != null && optimize_neighbours) {
            blfb.optimizeFor(parent, new Vector3f(b_pos.x + 1, b_pos.y, b_pos.z - 1), false);
        }

        boolean blfs = blfb != null ? blfb.isSolid() && (!blfb.isTransparant() || (blfb.isTransparant() && isTransparant())) : false;

        //right-front
        BlockInterface brfb = parent.getBlock(b_pos.x - 1, b_pos.y, b_pos.z - 1, true);

        if(brfb != null && optimize_neighbours) {
            brfb.optimizeFor(parent, new Vector3f(b_pos.x - 1, b_pos.y, b_pos.z - 1), false);
        }

        boolean brfs = brfb != null ? brfb.isSolid() && (!brfb.isTransparant() || (brfb.isTransparant() && isTransparant())) : false;

        //some culling
        if(bts) {
            faces[0].setVisible(false);
        }

        if(bus) {
            faces[1].setVisible(false);
        }

        if(bfs) {
            faces[2].setVisible(false);
        }

        if(bbs) {
            faces[3].setVisible(false);
        }

        if(bls) {
            faces[4].setVisible(false);
        }

        if(brs) {
            faces[5].setVisible(false);
        }

        //sloping - top free
        if(!bts) {
            //left
            if((!bls && bbs && bfs && brs) || (!bls && !bbs && !bfs && brs)) {
                for(int i = 0; i < faces.length; i++) {
                    faces[i].slopeTopLeft();
                }
            }

            //right
            if((!brs && bbs && bfs && bls) || (!brs && !bbs && !bfs && bls)) {
                for(int i = 0; i < faces.length; i++) {
                    faces[i].slopeTopRight();
                }
            }

            //back
            if((!bbs && bls && brs && bfs) || (!bbs && !bls && !brs && bfs)) {
                for(int i = 0; i < faces.length; i++) {
                    faces[i].slopeTopBack();
                }
            }

            //front
            if((!bfs && bls && brs && bbs) || (!bfs && !bls && !brs && bbs)) {
                for(int i = 0; i < faces.length; i++) {
                    faces[i].slopeTopFront();
                }
            }

            //back-left
            if(!bbs && !bls && brs && bfs && brfs) {
                for(int i = 0; i < faces.length; i++) {
                    faces[i].slopeTopLeftBack();
                }
            }

            //back-right
            if(!bbs && !brs && bls && bfs && blfs) {
                for(int i = 0; i < faces.length; i++) {
                    faces[i].slopeTopRightBack();
                }
            }

            //front-left
            if(!bfs && !bls && brs && bbs && brbs) {
                for(int i = 0; i < faces.length; i++) {
                    faces[i].slopeTopLeftFront();
                }
            }

            //front-right
            if(!bfs && !brs && bls && bbs && blbs) {
                for(int i = 0; i < faces.length; i++) {
                    faces[i].slopeTopRightFront();
                }
            }
        }
    }

    public void optimizeFor(BObject parent, Vector3f b_pos) {
        optimizeFor(parent, b_pos, false);
    }

    public void refreshBlock() {
        for(int i = 0; i < faces.length; i++) {
            BlockFace b = (BlockFace) faces[i];
            b.updateFace();
        }
    }

    public void resetBlock() {
        for(int i = 0; i < faces.length; i++) {
            BlockFaceInterface b = (BlockFaceInterface) faces[i];
            b.setVisible(true);
            b.resetMesh();
        }
    }

    private void createBlock() {
        buildBlock();
        refreshBlock();

        Material mat = new Material(Assets.getInstance().assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);

        setMaterial(mat);
    }

    public void setBlockMaterial(String matFile) {
        Material mat = Assets.getInstance().assetManager.loadMaterial(matFile);

        for(int i = 0; i < faces.length; i++) {
            BlockFace b = (BlockFace) faces[i];
            b.setMaterial(mat);
        }
    }

    public static Vector3f vectorToGrid(Vector3f vector) {
        for(int i = 0;i<3;i++) {
            float pv = vector.get(i);

            if(i == 1) {
                pv = pv - 0.5f;
            }
            //end correction

            if(pv < 0) {
                pv = ((pv % 1) <= -0.5) ? FastMath.floor(pv) : FastMath.ceil(pv);
            }
            else {
                pv = ((pv % 1) <= 0.5) ? FastMath.floor(pv) : FastMath.ceil(pv);
            }
        }

        return vector;
    }

    public static Vector3f blockCollisionToBlock(CollisionResult face) {
        Vector3f contact = face.getContactPoint();
        Vector3f normal = face.getContactNormal();
        Vector3f target = new Vector3f();

        for(int i = 0;i<3;i++) {
            float pf = contact.get(i);

             //y-axis corrections
            if(i == 1) {
                pf = pf - 0.5f;
            }
            //end correction

            //positioning through normal
            if(normal.get(i) < 0) {
                pf = FastMath.ceil(pf);
                target.set(i, pf);
                continue;
            }

            if(normal.get(i) > 0) {
                pf = FastMath.floor(pf);
                target.set(i, pf);
                continue;
            }

            //positioning through coords
            if(pf < 0) {
                pf = ((pf % 1) <= -0.5) ? FastMath.floor(pf) : FastMath.ceil(pf);
                pf = pf + normal.get(i);
            }
            else {
                pf = ((pf % 1) <= 0.5) ? FastMath.floor(pf) : FastMath.ceil(pf);
            }

            target.set(i, pf);
        }

        return target;
    }

    public static Vector3f blockCollisionToGrid(CollisionResult face) {
        Vector3f contact = face.getContactPoint();
        Vector3f normal = face.getContactNormal();
        Vector3f target = new Vector3f();

        for(int i = 0;i<3;i++) {
            float pf = contact.get(i);

             //y-axis corrections
            if(i == 1) {
                pf = pf - 0.5f;
            }
            //end correction

            //positioning through normal
            if(normal.get(i) < 0) {
                pf = FastMath.floor(pf);
                target.set(i, pf);
                continue;
            }

            if(normal.get(i) > 0) {
                pf = FastMath.ceil(pf);
                target.set(i, pf);
                continue;
            }

            //positioning through coords
            if(pf < 0) {
                pf = ((pf % 1) <= -0.5) ? FastMath.floor(pf) : FastMath.ceil(pf);
                pf = pf + normal.get(i);
            }
            else {
                pf = ((pf % 1) <= 0.5) ? FastMath.floor(pf) : FastMath.ceil(pf);
            }

            target.set(i, pf);
        }

        return target;
    }

    public GroupNode getNode() {
        return this;
    }

    public void setAlpha(boolean alpha) {
        if(alpha) {}
        else {
            //gui alpha fix
            setQueueBucket(Bucket.Inherit);
        }

        for(int i = 0;i < faces.length; i++) {
            if(hasChild((BlockFace) faces[i])) {
                BlockFace b = (BlockFace) faces[i];
                b.setAlpha(alpha);
            }
        }
    }

    public int amount() {
        return 1;
    }

    public Block getBlock(int index) {
        return this;
    }

    public Vector3f getLocation() {
        return getLocalTranslation();
    }

    public boolean isSolid() {
        return solid;
    }

    public void isSolid(boolean solid) {
        this.solid = solid;
    }

    @Override
    public final void setName(String name) {
        super.setName(name);

        for(int i = 0;i < faces.length;i++) {
            BlockFace b = (BlockFace) faces[i];
            b.setName(name);
        }
    }

    public boolean isTransparant() {
        return transparant;
    }

    public void isTransparant(boolean transparant) {
        this.transparant = transparant;
    }
}
