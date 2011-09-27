/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine;

import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
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
