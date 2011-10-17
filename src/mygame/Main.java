package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import java.util.logging.Level;
import java.util.logging.Logger;
import mygame.engine.Engine;
import mygame.helpers.Share;
/**
 * Andromeda
 * @author BaseHosting.net
 */
public class Main extends SimpleApplication {
    private Actor player = new Actor();
    private String[] player_settings;
    private float counter = 0;
    private Engine engine = null;

   // protected boolean showSettings = false;
    private Geometry lightMdl;

    public Main() {
        super();
        setPauseOnLostFocus(false);
        showSettings = true;

    }

    public static void main(String[] args) {
        Main app = new Main();
        Logger.getLogger("").setLevel(Level.SEVERE);
        app.start();
    }

    public float runTimer(float delta) {
        counter = counter + delta;
        return counter;
    }

    @Override
    public void simpleInitApp() {
        Share.getInstance().appSettings = settings;
        Share.getInstance().inputManager = inputManager;
        Share.getInstance().stateManager = stateManager;
        Share.getInstance().renderManager = renderManager;
        Share.getInstance().rootNode = rootNode;
        Share.getInstance().guiNode = guiNode;
        Share.getInstance().app = this;


        Assets.getInstance().assetManager = assetManager;

        engine = Engine.getInstance();

       // guiNode.detachAllChildren();

        renderManager.setHandleTranslucentBucket(true);
        renderManager.removeMainView("Default");
        renderManager.removePostView("Gui Default");

        showSettings = true;

        player.main("player");
        player.show();

        rootNode.attachChild(engine.rootNode);
        guiNode.attachChild(engine.guiNode);
    }

    @Override
    public void simpleUpdate(float tpf) {
//        engine.update(tpf);
    }

    @Override
    public void simpleRender(RenderManager rm) {
    }
}
