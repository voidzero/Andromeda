/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.blocks;

import com.jme3.asset.AssetManager;
import com.jme3.collision.CollisionResult;
import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector2f;
import com.jme3.scene.Mesh;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.util.BufferUtils;
import com.jme3.util.TangentBinormalGenerator;
import mygame.Assets;
import mygame.engine.blocks.Interface.BlockInterface;
import mygame.engine.nodes.GroupNode;
import mygame.engine.nodes.RenderNode;
import mygame.engine.objects.BObject;

/**
 *
 * @author Dansion
 */
public class Block extends RenderNode implements BlockInterface {
    public boolean isSolid;
    public long strength = 100;
    public long health;
    public float size_x = 1.0f, size_y = 1.0f, size_z = 1.0f;
    public int x = 0, y = 0, z = 0;
    private GroupNode node = new GroupNode();
    
    public Vector3f [] normals = new Vector3f[24];
    public Vector3f [] vertices = new Vector3f[24];
    public Vector2f [] texCoord = new Vector2f[24];
    
    //Faces                   Front         Back          Top              Left                Right               Bottom
    public int [] indexes = { 0,2,1, 1,2,3, 4,6,5, 5,6,7, 8,10,9, 9,10,11, 12,14,13, 13,14,15, 16,18,17, 17,18,19, 20,22,21, 21,22,23 };
    
    public Block() {
        this("block");
    }

    public Block(String name) {
        super(name);
        mesh = new Mesh();

        health = strength;
        
        createBlock();
        
        node.attachChild(this);
    }
    
    public void size(float x, float y, float z) {
        scale(x, y, z);
        size_x = x;
        size_y = y;
        size_z = z;
        refreshBlock();
    }
    
    private void buildBlock() {
        //front
        normals[0] = new Vector3f(0, 0, 1);
        normals[1] = new Vector3f(0, 0, 1);
        normals[2] = new Vector3f(0, 0, 1);
        normals[3] = new Vector3f(0, 0, 1);
        
        vertices[0] = new Vector3f(-0.5f, 1f, 0.5f);
        vertices[1] = new Vector3f( 0.5f, 1f, 0.5f);
        vertices[2] = new Vector3f(-0.5f, 0f, 0.5f);
        vertices[3] = new Vector3f( 0.5f, 0f, 0.5f);
        
        texCoord[0] = new Vector2f(0,0);      
        texCoord[1] = new Vector2f(1,0); 
        texCoord[2] = new Vector2f(0,1);    
        texCoord[3] = new Vector2f(1,1); 
        
        //back
        normals[4] = new Vector3f(0, 0, -1);
        normals[5] = new Vector3f(0, 0, -1);
        normals[6] = new Vector3f(0, 0, -1);
        normals[7] = new Vector3f(0, 0, -1);
        
        vertices[4] = new Vector3f( 0.5f, 1f, -0.5f);
        vertices[5] = new Vector3f(-0.5f, 1f, -0.5f);
        vertices[6] = new Vector3f( 0.5f, 0f, -0.5f);
        vertices[7] = new Vector3f(-0.5f, 0f, -0.5f);
        
        texCoord[4] = texCoord[0];      
        texCoord[5] = texCoord[1]; 
        texCoord[6] = texCoord[2];    
        texCoord[7] = texCoord[3];       
        
        //top
        normals[8] = new Vector3f(0, 1, 0);
        normals[9] = new Vector3f(0, 1, 0);
        normals[10] = new Vector3f(0, 1, 0);
        normals[11] = new Vector3f(0, 1, 0);
        
        vertices[8]  = new Vector3f(-0.5f,  1f, -0.5f);
        vertices[9]  = new Vector3f( 0.5f,  1f, -0.5f);
        vertices[10] = new Vector3f(-0.5f,  1f,  0.5f);
        vertices[11] = new Vector3f( 0.5f,  1f,  0.5f);
        
        texCoord[8]  = texCoord[0];      
        texCoord[9]  = texCoord[1]; 
        texCoord[10] = texCoord[2];    
        texCoord[11] = texCoord[3];
        
        //left
        normals[12] = new Vector3f(-1, 0, 0);
        normals[13] = new Vector3f(-1, 0, 0);
        normals[14] = new Vector3f(-1, 0, 0);
        normals[15] = new Vector3f(-1, 0, 0);
        
        vertices[12]  = new Vector3f(-0.5f,  1f, -0.5f);
        vertices[13]  = new Vector3f(-0.5f,  1f,  0.5f);
        vertices[14]  = new Vector3f(-0.5f, 0f, -0.5f);
        vertices[15]  = new Vector3f(-0.5f, 0f,  0.5f);
        
        texCoord[12] = texCoord[0];      
        texCoord[13] = texCoord[1]; 
        texCoord[14] = texCoord[2];    
        texCoord[15] = texCoord[3];
        
        //right
        normals[16] = new Vector3f(1, 0, 0);
        normals[17] = new Vector3f(1, 0, 0);
        normals[18] = new Vector3f(1, 0, 0);
        normals[19] = new Vector3f(1, 0, 0);
        
        vertices[16]  = new Vector3f(0.5f,  1f,  0.5f);
        vertices[17]  = new Vector3f(0.5f,  1f, -0.5f);
        vertices[18]  = new Vector3f(0.5f, 0f,  0.5f);
        vertices[19]  = new Vector3f(0.5f, 0f, -0.5f);        
        
        texCoord[16] = texCoord[0];      
        texCoord[17] = texCoord[1]; 
        texCoord[18] = texCoord[2];    
        texCoord[19] = texCoord[3];
        
        //bottom
        normals[20] = new Vector3f(0, -1, 0);
        normals[21] = new Vector3f(0, -1, 0);
        normals[22] = new Vector3f(0, -1, 0);
        normals[23] = new Vector3f(0, -1, 0);
        
        vertices[20] = new Vector3f(-0.5f, 0f,  0.5f);
        vertices[21] = new Vector3f( 0.5f, 0f,  0.5f);
        vertices[22]  = new Vector3f(-0.5f, 0f, -0.5f);
        vertices[23]  = new Vector3f( 0.5f, 0f, -0.5f);
        
        texCoord[20]  = texCoord[0];      
        texCoord[21]  = texCoord[1]; 
        texCoord[22] = texCoord[2];    
        texCoord[23] = texCoord[3];
    }
    
    public void refreshBlock(BObject master) {
        //autoslope block
        boolean bl = false;
        boolean br = false;
        boolean bf = false;
        boolean bb = false;
        boolean bt = false;
        boolean bu = false;
        
        if(master.getBlock(x-1, y, z).isSolid) {bl = true;}        
        if(master.getBlock(x+1, y, z).isSolid) {br = true;}        
        if(master.getBlock(x, y, z+1).isSolid) {bf = true;}        
        if(master.getBlock(x, y, z-1).isSolid) {bb = true;}        
        if(master.getBlock(x, y+1, z).isSolid) {bt = true;}        
        if(master.getBlock(x, y-1, z).isSolid) {bu = true;}
        
        //front or back sloping 
        if(bl && br) {
            if(!bf && bb) {
                //slope front
            }

            if(bf && !bb) {
                //slope back
            }              
        }
        
        if(bf && bb) {
            if(!bl && br) {
                //slope lefr
            }
            
            if(bl && !br) {
                //slope right
            }
        }

        
        refreshBlock();
    }
    
    public void refreshBlock() {
//        
        mesh.setBuffer(Type.Normal, 3, BufferUtils.createFloatBuffer(normals));
        mesh.setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(vertices));
        mesh.setBuffer(Type.TexCoord, 2, BufferUtils.createFloatBuffer(texCoord));
        mesh.setBuffer(Type.Index,    1, BufferUtils.createIntBuffer(indexes));

        TangentBinormalGenerator.generate(mesh);
        
        mesh.updateBound();            
    }
    
    private void createBlock() {
        buildBlock();
        refreshBlock();
       // mesh.setStatic();
        
        Material mat = new Material(Assets.getInstance().assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        
        setMaterial(mat);
       // setQueueBucket(Bucket.Opaque);
    }

    public void setBlockMaterial(String matFile) {
        Material mat = Assets.getInstance().assetManager.loadMaterial(matFile);
        setMaterial(mat);
        
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
        return node;
    }

    public void setAlpha(boolean alpha) {   
        if(alpha) {
            getMaterial().getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
        }
        else {
            Material mat = getMaterial();
            mat.getAdditionalRenderState().setBlendMode(BlendMode.Off);
            setMaterial(mat);
            setQueueBucket(Bucket.Inherit);
        }
    }
    
    public int amount() {
        return 1;
    }
    
    public Block getBlock(int index) {
        return this;
    }
}
