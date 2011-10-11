/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine;

import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.material.Material;
import com.jme3.material.RenderState.FaceCullMode;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.Control;
import com.jme3.scene.shape.Box;
import java.io.IOException;
import mygame.Assets;
import mygame.engine.nodes.GroupNode;
import mygame.helpers.Share;

/**
 *
 * @author Dansion
 */

public class Scaling extends GroupNode implements Control {
    public static enum Unit {MM, CM, M, KM, AU, LY, PC};
    private static float [] scales = {0.001f, 0.01f, 1, 1000f, 149597870700f, 9460730472580800f, 30841981340613408f};

    private float scaleRegion = 80000;
    private float nearSpace = Scaling.getWorldValue(Unit.AU, 1);
    private float scaledSpace = Scaling.getWorldValue(Unit.AU, 20);

    private Camera view = null;

    //debugBox is are the outer bounds for the world nodes within this class
    private Geometry debugBox = new Geometry("ScalingDebugBox", new Box(.5f, .5f, .5f));

    public Scaling() {
        super("ScaleNode");

        Material debugMat = new Material(Assets.getInstance().assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        debugMat.setColor("Color", ColorRGBA.Red);
        debugMat.getAdditionalRenderState().setWireframe(true);
        debugMat.getAdditionalRenderState().setFaceCullMode(FaceCullMode.Off);

        debugBox.setMaterial(debugMat);
        debugBox.setLocalScale(20000, 20000, 20000);
        debugBox.setLocalTranslation(0, 0, 0);

        //attachChild(debugBox);

        Share.getInstance().rootNode.addControl(this);
    }

    public void setViewerLocation(Vector3f location) {
        if(view != null) {
            view.setLocation(location);

            Vector3f cam_pos = new Vector3f(view.getLocation());

            float scale = 1;//scaleRegion / scaledSpace;

            for(int i = 0; i < 3; i++) {
                cam_pos.set(i, cam_pos.get(i) * scale);
                view.getLocation().set(i, 0);
            }

            setLocalTranslation(cam_pos);
        }
    }

    public void setViewer(Camera view) {
        this.view = view;
    }

    @Override
    public int attachChild(Spatial child) {
        int i = super.attachChild(child);

        child.setUserData("scaled_pos", new Vector3f(child.getLocalTranslation()));

        return i;
    }

    public void update(float tpf) {
        if(view != null) {
            Vector3f cam_pos = new Vector3f(view.getLocation());

            float scale = 1;//scaleRegion / scaledSpace;

            for(int i = 0; i < 3; i++) {
                cam_pos.set(i, cam_pos.get(i) * scale);
                view.getLocation().set(i, 0);
            }

            getLocalTranslation().addLocal(cam_pos);
//            System.out.println(getLocalTranslation());
        }
    }

    public void setSpatial(Spatial spatial) {}

    public void setEnabled(boolean enabled) {}

    public boolean isEnabled() {
        return true;
    }

    public void render(RenderManager rm, ViewPort vp) {}

    //Static functions :
    public static float getWorldValue(Unit unit, float value) {
        return value * scales[unit.ordinal()];
    }

    public static float getScaleValue(Unit unit, float world_value) {
        return world_value / scales[unit.ordinal()];
    }

    ////=======================================unsuported interface functions :
    public Control cloneForSpatial(Spatial spatial) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void write(JmeExporter ex) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void read(JmeImporter im) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}