/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mygame.engine.geom;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.util.BufferUtils;
import mygame.Assets;
import mygame.engine.nodes.RenderNode;

/**
 *
 * @author Dansion
 */
public class Grid extends RenderNode {
    private int size_x, size_z;
    private float off_x = 0.5f;
    private float off_z = -0.5f;
    
    private Vector3f[] vertices;
    private Vector3f[] normals;
    private Vector2f[] texCoords;
    private int[] indexes;
    private AssetManager assetManager = Assets.getInstance().assetManager;
   
    
    public Grid() {
        super(null);
        
        size_x = 20;
        size_z = 20;
        
        initGrid();
    }
    
    public Grid(int size_x, int size_z) {
        super(null);
        
        this.size_x = size_x;
        this.size_z = size_z;
        
        initGrid();
    }
    
    public Grid(String name, int size_x, int size_z) {
        super(name);
        
        this.size_x = size_x;
        this.size_z = size_z;
        
        initGrid();
    }
    
    private void initGrid() {
        normals = new Vector3f[size_x * size_z * 4];
        vertices = new Vector3f[size_x * size_z * 4];
        texCoords = new Vector2f[size_x * size_z * 4];
        indexes = new int[size_x * size_z * 6];
        
        int counter = 0;
        int index_counter = 0;
        
        for(int sx = 0;sx<size_x;sx++) {
            for(int sz = 0;sz<size_z;sz++) {
                index_counter = (int) (counter * 1.5);

                // 0
                normals[counter] = new Vector3f(0,1,0);
                normals[counter + 1] = new Vector3f(0,1,0);
                normals[counter + 2] = new Vector3f(0,1,0);
                normals[counter + 3] = new Vector3f(0,1,0);
                
                vertices[counter] = new Vector3f((float) (sx - (size_x / 2.0f) + off_x), 0.0f, (float) (sz - (size_z / 2.0f) + off_z) );
                texCoords[counter] = new Vector2f(0,0);
                
                // 1
                vertices[counter + 1] = new Vector3f((float) (sx - (size_x / 2.0f) + 1.0f + off_x), 0.0f, (float) (sz - (size_z / 2.0f) + off_z) );
                texCoords[counter + 1] = new Vector2f(1,0);

                // 2 
                vertices[counter + 2] = new Vector3f((float) (sx - (size_x / 2.0f) + off_x), 0.0f, (float) (sz - (size_z / 2.0f) + 1.0f + off_z) );
                texCoords[counter + 2] = new Vector2f(0,1);

                
                // 3
                vertices[counter + 3] = new Vector3f((float) (sx - (size_x / 2.0f) + 1.0f + off_x), 0.0f, (float) (sz - (size_z / 2.0f) + 1.0f + off_z));
                texCoords[counter + 3] = new Vector2f(1,1);
                
                indexes[index_counter] = counter;
                indexes[index_counter + 1] = counter + 2;
                indexes[index_counter + 2] = counter + 1;
                indexes[index_counter + 3] = counter + 1;
                indexes[index_counter + 4] = counter + 2;
                indexes[index_counter + 5] = counter + 3;
                
                counter = counter + 4;
            }
        }        
        
        refreshGrid();
    }
    
    public void refreshGrid() {         
        mesh = new Mesh();
        
        mesh.setBuffer(Type.Normal, 3, BufferUtils.createFloatBuffer(normals));
        mesh.setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(vertices));
        mesh.setBuffer(Type.TexCoord, 2, BufferUtils.createFloatBuffer(texCoords));
        mesh.setBuffer(Type.Index,    1, BufferUtils.createIntBuffer(indexes));
        
        mesh.setStatic();
        mesh.updateBound();
        
        Material mat = assetManager.loadMaterial("Materials/default_grid.j3m");
        //mat.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
        //mat.setTransparent(true);
        setQueueBucket(Bucket.Translucent);
        setMaterial(mat);
    }
    
    public void setSize(int size_x, int size_z) {
        this.size_x = size_x;
        this.size_z = size_z;
       
        initGrid();   
    }
}