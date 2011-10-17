/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.gui;

import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.scene.control.UpdateControl;
import com.jme3.ui.Picture;
import com.jme3.util.BufferUtils;
import mygame.Assets;
import mygame.engine.nodes.GroupNode;

/**
 *
 * @author Dansion
 */
public class ProgressBar extends GroupNode {
    private float progress;
    private float max = 1.0f;
    private float min = 0.0f;
    private Picture bg = new Picture("ProgressBarBg");
    private Picture progress_bar = new Picture("ProgressBar");
    private ProgressBarControl control = new ProgressBarControl();

    public ProgressBar() {
        super("ProgressBar");
        bg.setImage(Assets.getInstance().assetManager, "Textures/progress_bar_bg.png", true);
        bg.setWidth(512);
        bg.setHeight(32);
        progress_bar.setImage(Assets.getInstance().assetManager, "Textures/progress_bar.png", true);
        progress_bar.setWidth(512);
        progress_bar.setHeight(32);

        attachChild(bg);
        attachChild(progress_bar);
        progress_bar.getMesh().setDynamic();
        control.setTarget(this);

        addControl(control);
    }

    public void update() {
        Vector3f [] coord = new Vector3f[4];
        Vector2f [] tex = new Vector2f[4];

        coord[0] = new Vector3f(0, 1, 0);
        coord[1] = new Vector3f(0, 0, 0);
        coord[2] = new Vector3f(progress * 1.0f, 0, 0);
        coord[3] = new Vector3f(progress * 1.0f, 1, 0);

        tex[0] = new Vector2f(0, 1);
        tex[1] = new Vector2f(0, 0);
        tex[2] = new Vector2f(progress * 1.00f, 0);
        tex[3] = new Vector2f(progress * 1.00f, 1);

        progress_bar.getMesh().getBuffer(Type.Position).updateData(BufferUtils.createFloatBuffer(coord));
        progress_bar.getMesh().getBuffer(Type.TexCoord).updateData(BufferUtils.createFloatBuffer(tex));
    }

    public void setProgress(float progress) {
        progress = progress > max ? max : progress;
        this.progress = progress;

    }

    public float getProgress() {
        return progress;
    }
}

class ProgressBarControl extends UpdateControl {
    private ProgressBar target = null;

    @Override
    public void update(float tpf) {
        if(target != null) {
            target.update();
        }
    }

    public void setTarget(ProgressBar target) {
        this.target = target;
    }
}
