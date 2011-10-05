/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.state;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import mygame.Assets;
import mygame.engine.Scaling;
import mygame.space.BackDrop;

/**
 *
 * @author Dansion
 */
public class Space extends AbstractState{
    private Scaling space = new Scaling();
    private BackDrop backdrop = new BackDrop();
    
    public Space(Node parent) {
        super(parent);
        
        backdrop.setLocalScale(5000);
        
        rootNode.attachChild(space);
        rootNode.attachChild(backdrop);
        
        //Test code :
        Mesh testcube1 = new Box(10, 10, 10);
        Mesh testcube2 = new Box(20, 20, 20);
        
        Geometry g1 = new Geometry("tc1", testcube1);
        Geometry g2 = new Geometry("tc2", testcube2);
        
        Material m1 = new Material(Assets.getInstance().assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Material m2 = new Material(Assets.getInstance().assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        
        m1.setColor("Color", ColorRGBA.Blue);
        m2.setColor("Color", ColorRGBA.Red);
        
        g1.setLocalTranslation(-40, 10, -50);
        g2.setLocalTranslation(40, -10, 50);
        
        g1.setMaterial(m1);
        g2.setMaterial(m2);
        
        space.attachChild(g1);
        space.attachChild(g2);
    }
    
    @Override
    public void update(float tpf) {
        space.setViewerLocation(getCamera().getLocation());
    }
}
