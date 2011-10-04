/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine;

import com.jme3.light.SpotLight;
import com.jme3.material.Material;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.shape.Quad;
import mygame.Assets;
import mygame.engine.blocks.Systems.PowerConduit;
import mygame.engine.nodes.GroupNode;
import mygame.helpers.Share;
import mygame.state.MainState;

/**
 *
 * @author Dansion
 */
public class Engine {
    private static final Engine INSTANCE = new Engine();
    
    public GroupNode rootNode = new GroupNode();
    public GroupNode guiNode = new GroupNode();
    public Scaling scaler = new Scaling();
    private Lighting lighting = new Lighting();
    private MainState mainState = MainState.getInstance(rootNode);

    private Engine() {
        Share.getInstance().guiNode = guiNode;
        scaler.setRootNode(rootNode);
        lighting.testLighting(rootNode);
              
        mainState.run();       
    }

    public void update(float tpf) {
        lighting.update(tpf);
    }
    
    public static Engine getInstance() {
        return INSTANCE;
    }
}
