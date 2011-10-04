/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine;

import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.material.Material;
import com.jme3.math.FastMath;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.Control;
import com.jme3.texture.Texture;
import java.io.IOException;
import mygame.Assets;

/**
 *
 * @author Dansion
 */

public class AnimatedTexture implements Control {
    private String textureFile, mapFile;
    private AnimMap map;
    private Material mat;
    private Texture texture;
    private Spatial spatial;
    private int fps = 15;
    private int frame = 0, frames = 59;
    private float time = 0;
    
    public AnimatedTexture() {
    }
    
    public void loadAnimation(String texture_file, String map_file) {
        textureFile = texture_file;
        mapFile = map_file;
        map = new AnimMap(mapFile);
        
        texture = Assets.getInstance().assetManager.loadTexture(textureFile);
    }  
    
    public void update(float tpf) {
        if(texture != null && mat == null) {
           mat = new Material(Assets.getInstance().assetManager, "MatDefs/AnimatedShaded.j3md");
        }
        
        if(mat != null) {
            time += tpf;
            frame = (int) FastMath.floor((time * fps) % frames);
            
            mat.setInt("ani-frame", frame);
        }
        
        //@TODO aniated material shader UV Transpose : 
    }

    public Texture getTexture() {
        return texture;
    }
    
    public Control cloneForSpatial(Spatial spatial) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setSpatial(Spatial spatial) {
        this.spatial = spatial;
    }

    public void setEnabled(boolean enabled) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isEnabled() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    public void render(RenderManager rm, ViewPort vp) {}

    public void write(JmeExporter ex) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void read(JmeImporter im) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
