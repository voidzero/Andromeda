/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine;

import com.jme3.asset.BlenderKey;
import com.jme3.asset.BlenderKey.LoadingResults;
import com.jme3.light.SpotLight;
import com.jme3.material.Material;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue.ShadowMode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.scene.shape.Quad;
import mygame.Assets;
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

    public float trot;
    private Engine() {
        Share.getInstance().guiNode = guiNode;
        scaler.setRootNode(rootNode);
        lighting.testLighting(rootNode);
        
        AnimatedTexture flow = new AnimatedTexture();
        flow.loadAnimation("Textures/anim.png", "Assets/AnimMaps/anim.txt");        
       
        Node g2 = (Node) Assets.getInstance().assetManager.loadModel("Models/powerconduit.blend");
        
        g2.setShadowMode(ShadowMode.Receive);
        g2.scale(1.3f);
        Mesh q = new Quad(10, 10);
        Geometry g = new Geometry("bla", q);
        
        Material m = new Material(Assets.getInstance().assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        m.setTexture("ColorMap", flow.getTexture());

        g.setMaterial(m);
//        g2.setMaterial(m);
//        //rootNode.attachChild(g);
        rootNode.attachChild(g2);
        SpotLight l = new SpotLight();
        l.setPosition(new Vector3f(10,0,0));
        rootNode.addLight(l);
        rootNode.addControl(flow);    
        
        
        mainState.run();       
    }

    public void update(float tpf) {
        lighting.update(tpf);
        trot = FastMath.normalize(trot + tpf, 0, 2);
        Quaternion rotTot = new Quaternion();
    }
    
    public static Engine getInstance() {
        return INSTANCE;
    }
}
