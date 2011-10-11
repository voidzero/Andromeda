/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.blocks;

import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Node;
import mygame.Assets;
import mygame.engine.blocks.Interface.BlockInterface;
import mygame.engine.nodes.GroupNode;
import mygame.engine.objects.BObject;

/**
 *
 * @author Dansion
 */
public class CustomBlock extends GroupNode implements BlockInterface {
    private Node model;
    private boolean solid = true;
    private boolean transparant = false;

    public CustomBlock() {
        super("CustomBlock");
    }

    public void loadBlenderModel(String fname) {
        setName(fname);

        model = (Node) Assets.getInstance().assetManager.loadModel(fname);

        //blender scale fix @TODO why do I have to rescale blender models??
        model.scale(1.31f);

        this.attachChild(model);
    }

    public GroupNode getNode() {
        return this;
    }

    public void setAlpha(boolean alpha) {
        if(alpha) {
        }
        else {
            this.setQueueBucket(Bucket.Inherit);
        }
    }

    public int amount() {
        return 1;
    }

    public Block getBlock(int index) {
        return new Block();
    }

    public Vector3f getLocation() {
        return this.getLocalTranslation();
    }

    public boolean isSolid() {
        return solid;
    }

    public void optimizeFor(BObject parent, Vector3f b_pos) {}
    
    public void optimizeFor(BObject parent, Vector3f b_pos, boolean optimize_neighbours) {}

    public boolean isTransparant() {
        return transparant;
    }

    public void isTransparant(boolean transparant) {
        this.transparant = transparant;
    }
}
