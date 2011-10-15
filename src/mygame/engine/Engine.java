/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine;

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
