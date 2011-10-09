/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.space;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.shape.Sphere;
import mygame.Assets;

/**
 *
 * @author Dansion
 */
public class Planet extends Geometry {
    private float sun_distance, size;
    public Planet(String name, float sun_distance, float size) {
        this(name, sun_distance, size, "");      
    }
    
    public Planet(String name, float sun_distance, float size, String seed) {
        super(name);
        
        this.sun_distance = sun_distance;
        this.size = size;
        
        Mesh testcube1 = new Sphere(16, 16, size / 2);
        
        setMesh(testcube1);
        
        Material m1 = new Material(Assets.getInstance().assetManager, "Common/MatDefs/Misc/Unshaded.j3md");

        if(name.equals("Mercury")) {
            m1.setColor("Color", ColorRGBA.Yellow);
        }
        else {
            m1.setColor("Color", ColorRGBA.randomColor());
        }
        
        setLocalTranslation(0, 0, sun_distance);       
        
        setMaterial(m1);
    }

    public float getSize() {
        return size;
    }
}
