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

/**
 *
 * @author Dansion
 */

public class Scaling extends GroupNode implements Control {
    public static enum Unit {MM, CM, M, KM, AU, LY};
    private static float [] scales = {0.001f, 0.01f, 1, 0.001f, 1000, 1000000};
    
    private float nearRegion = 500;
    private float normalRegion = 5000;
    private Vector3f viewer = new Vector3f();
    
    //debugBox is are the outer bounds for the world nodes within this class
    private Geometry debugBox = new Geometry("ScalingDebugBox", new Box(.5f, .5f, .5f));
    
    public Scaling() {
        super("ScaleNode");
        
        Material debugMat = new Material(Assets.getInstance().assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        debugMat.setColor("Color", ColorRGBA.Red);
        debugMat.getAdditionalRenderState().setWireframe(true);
        debugMat.getAdditionalRenderState().setFaceCullMode(FaceCullMode.Off);
        
        debugBox.setMaterial(debugMat);
        debugBox.setLocalScale(5000, 5000, 5000);
        debugBox.setLocalTranslation(0, 0, 0);
        
        attachChild(debugBox);
    }
    
    public static float getWorldValue(Unit unit, float value) {
        return value * scales[unit.ordinal()];
    }
    
    public static float getScaleValue(Unit unit, float world_value) {
        return world_value / scales[unit.ordinal()];
    }
    
    public void update(float tpf) {
        //update scales according to viewer position
    }

    public void setEnabled(boolean enabled) {}

    public boolean isEnabled() {
        return true;
    }

    public void render(RenderManager rm, ViewPort vp) {}
    
    //unsuported interface functions :
    public Control cloneForSpatial(Spatial spatial) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setSpatial(Spatial spatial) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void write(JmeExporter ex) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void read(JmeImporter im) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}