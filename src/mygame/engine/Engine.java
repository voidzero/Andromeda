/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine;

import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import mygame.engine.nodes.GroupNode;
import mygame.engine.blocks.Blocks;
import mygame.engine.blocks.Spaceship.Hulls.LightAlloy;
import mygame.engine.blocks.Spaceship.Hulls.LightAlloyWindowed;
import mygame.engine.objects.BObject;
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
    public Blocks myBlock5 = new LightAlloyWindowed();
    public Scaling scaler = new Scaling();
    private Lighting lighting = new Lighting();
    private MainState mainState = MainState.getInstance(rootNode);
    public Quaternion rot = new Quaternion(0, 0, 0, 1);
    public Quaternion rot2 = new Quaternion(1, 0, 0, 0);
    public float trot;
    private Engine() {
        Share.getInstance().guiNode = guiNode;
        scaler.setRootNode(rootNode);
        lighting.testLighting(rootNode);
        mainState.run();
        
//        Block selection = new Block();
//        selection.setBlockMaterial("Materials/block_select_01.j3m");
//        Block selection2 = new Block();
//        selection2.setBlockMaterial("Materials/block_select_02.j3m");
        
        Blocks myBlock = new LightAlloyWindowed();
        Blocks myBlock4 = new LightAlloyWindowed();
        
        Blocks myBlock2 = new LightAlloy();
        Blocks myBlock3 = new LightAlloy();
        BObject temp = new BObject();

        
       // float [] angles = {FastMath.PI / 2, 0f, 0f};
       // rot.fromAngles(angles);
        rot.normalizeLocal();
        
       // float [] angles2 = {0f, FastMath.PI / 2, FastMath.PI / 4};
       // rot2.fromAngles(angles2);
        rot2.normalizeLocal();
        //rot = rot.(rot2);
        
        myBlock5.setLocalRotation(rot);
            
        //rootNode.attachChild(myBlock);
        rootNode.attachChild(myBlock2);
        rootNode.attachChild(myBlock3);
        rootNode.attachChild(myBlock4);
        rootNode.attachChild(myBlock5);
        
        myBlock4.setLocalTranslation(-1, 0, 0);
        myBlock5.setLocalTranslation( 1, 0, 0);
        myBlock2.setLocalTranslation(-2, 0, 0);
        myBlock3.setLocalTranslation( 2, 0, 0);
    }

    public void update(float tpf) {
        lighting.update(tpf);
        trot = FastMath.normalize(trot + tpf, 0, 2);
        Quaternion rotTot = new Quaternion();
        rotTot.slerp(rot, rot2, trot);
        myBlock5.setLocalRotation(rotTot);
    }
    
    public static Engine getInstance() {
        return INSTANCE;
    }
}
