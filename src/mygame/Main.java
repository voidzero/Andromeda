package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import java.util.logging.Level;
import java.util.logging.Logger;
import mygame.engine.Engine;
import mygame.helpers.Math.MersenneTwisterFast;
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

        Assets.getInstance().assetManager = assetManager;

        engine = Engine.getInstance();

        guiNode.detachAllChildren();

        MersenneTwisterFast rand = new MersenneTwisterFast(12345);

        for(int i = 1; i < 6; i++) {
            System.out.println(rand.nextInt());
        }

        rand = new MersenneTwisterFast(12345);

        for (int i = 1; i < 6; i++) {
            System.out.println(rand.nextInt(1000));
        }


        rand = new MersenneTwisterFast(12345);

        for (int i = 1; i < 6; i++) {
            System.out.println(rand.nextInt(i));
        }

        renderManager.setHandleTranslucentBucket(true);
        renderManager.removeMainView("Default");
        renderManager.removePostView("Gui Default");

        showSettings = false;

        player.main("player");
        player.show();

        rootNode.attachChild(engine.rootNode);
        guiNode.attachChild(engine.guiNode);
    }

    @Override
    public void simpleUpdate(float tpf) {
        engine.update(tpf);
    }

    @Override
    public void simpleRender(RenderManager rm) {
    }
}
