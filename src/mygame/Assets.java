/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;

/**
 *
 * @author Dansion
 */
public class Assets {
    private static final Assets INSTANCE = new Assets();
    public AssetManager assetManager;
    
    private Assets() {
        
    }
       
    public static Assets getInstance() {
        return INSTANCE;
    }
    
    public static Material loadMaterial(String path) {
        Material mat = new Material(Assets.getInstance().assetManager , path);
        
        return mat;
    }
}
