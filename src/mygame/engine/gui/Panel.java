/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.gui;

import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.texture.Texture;
import com.jme3.ui.Picture;
import mygame.Assets;
import mygame.engine.gui.Interfaces.GuiListener;
import mygame.engine.nodes.GroupNode;
import mygame.helpers.Share;

/**
 *
 * @author Dansion
 */
public class Panel extends GroupNode{
    private String caption = null;

    private Picture panel = null;
    private Share shares = Share.getInstance();
    private Assets assets = Assets.getInstance();
    private Button closeButton = new Button("Close window", "Textures/panel_close_button.png");
    private GroupNode rootNode = this;

    private Texture bg = null;
    private Texture bg_active = null;

    private int corr_x = 0;
    private int corr_y = 0;

    private int width, height;

    public Panel(String caption) {
        this(caption, null);
    }

    public Panel(String caption, String texture) {
        super(caption);

        panel = new Picture(caption);

        setName(caption);

        int s_width = shares.appSettings.getWidth();
        int s_height = shares.appSettings.getHeight();

       Material m = new Material(assets.assetManager, "Common/MatDefs/Misc/Unshaded.j3md");

        if(texture == null) {
            m.setColor("Color", ColorRGBA.Blue);
            setWidth(32);
            setHeight(32);

            corr_x = 16;
            corr_y = 40;
        }
        else {
            bg = assets.assetManager.loadTexture(texture);

            m.setTexture("ColorMap", bg);
            m.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);

            setWidth(bg.getImage().getWidth());
            setHeight(bg.getImage().getHeight());

            corr_x = bg.getImage().getWidth() / 2;
            corr_y = bg.getImage().getHeight() + 20;
        }

        setLocalTranslation((s_width / 2) - corr_x, s_height - corr_y, 0);
        panel.setMaterial(m);

        attachChild(panel);

        rootNode = this;

        closeButton.listener = new GuiListener() {
            public void onAction(int returnValue) {
                getParent().detachChild(rootNode);
            }
        };

        closeButton.setLocalTranslation(getRightX() - 34 , getTopY() - 24, 0);
        closeButton.setWidth(16);
        closeButton.setHeight(16);
        attachChild(closeButton);


        setQueueBucket(Bucket.Gui);
    }

    public boolean isActive() {
        if(getParent() != null) {
            return true;
        }
        else {
            return false;
        }
    }

    public final float getLeftX() {
        return panel.getLocalTranslation().x;
    }

    public final float getRightX() {
        return panel.getLocalTranslation().x + getWidth();
    }

    public final float getBottomY() {
        return panel.getLocalTranslation().y;
    }

    public final float getTopY() {
        return panel.getLocalTranslation().y + getHeight();
    }

    public final int getWidth() {
        return width;
    }

    public final int getHeight() {
        return height;
    }

    public void setWidth(int nwidth) {
        width = nwidth;
        panel.setWidth(width);
    }

    public void setHeight(int nheight) {
        height = nheight;
        panel.setHeight(height);
    }


    @Override
    public final void setName(String name) {
        super.setName(name);

        caption = name;
    }

    @Override
    public final String getName() {
        return caption;
    }

    public void showCloseButton(boolean vis) {
        if(!vis && hasChild(closeButton)) {
            detachChild(closeButton);
        }

        if(vis && !hasChild(closeButton)) {
            attachChild(closeButton);
        }
    }

    public void setActiveBackgroundTexture(String texture) {
        bg_active = assets.assetManager.loadTexture(texture);
    }

    public void showActive(boolean active) {
        Material m = new Material(assets.assetManager, "Common/MatDefs/Misc/Unshaded.j3md");

        if(active && bg_active != null) {
            m.setTexture("ColorMap", bg_active);
        }
        else {
             m.setTexture("ColorMap", bg);
        }

        m.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
        panel.setWidth(getWidth());
        panel.setHeight(getHeight());

        panel.setMaterial(m);
    }
}
