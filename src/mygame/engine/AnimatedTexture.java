/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine;

import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.material.Material;
import com.jme3.math.FastMath;
import com.jme3.math.Vector2f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.Control;
import com.jme3.texture.Texture;
import java.io.IOException;
import mygame.Assets;
import mygame.helpers.Share;

/**
 *
 * @author Dansion
 */

public class AnimatedTexture implements Control {
    private String textureFile, mapFile;
    private AnimMap map;
    private Material mat;
    private Texture texture;
    private int fps = 15;
    private int last_frame = -1, frame = 0, frames = 59;
    private float time = 0;
    
    public AnimatedTexture() {
    }
    
    public void loadAnimation(String texture_file, String map_file) {
        textureFile = texture_file;
        mapFile = map_file;
        map = new AnimMap(mapFile);
        
        fps = map.getFps();
        texture = Assets.getInstance().assetManager.loadTexture(textureFile);
        
        if(texture != null) {
           mat = new Material(Assets.getInstance().assetManager, "MatDefs/AnimatedShaded.j3md");
           mat.setTexture("DiffuseMap", texture);
           mat.setVector2("TranslateAmount", Vector2f.ZERO);
           mat.setBoolean("TranslateUV", true);
        }
        
        Share.getInstance().rootNode.addControl(this);
    }  
    
    public void update(float tpf) {
        time += tpf;
        
        if(mat != null && (int) FastMath.floor(((time) * fps) % frames) != last_frame) {            
            frame = (int) FastMath.floor((time * fps) % frames);
            
            mat.setVector2("TranslateAmount", map.getUVFrameOffset(frame));
            last_frame = frame;
        }
    }

    public Texture getTexture() {
        return texture;
    }
    
    public Material getMaterial() {
        return mat;
    }
    
    public Control cloneForSpatial(Spatial spatial) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setSpatial(Spatial spatial) {}

    public void setEnabled(boolean enabled) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isEnabled() {
        return true;
    }


    public void render(RenderManager rm, ViewPort vp) {}

    public void write(JmeExporter ex) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void read(JmeImporter im) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
