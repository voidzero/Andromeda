/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.gui.Panels;

import mygame.engine.blocks.Interface.BlockInterface;
import mygame.engine.gui.Interfaces.GuiAction;
import mygame.engine.gui.Interfaces.GuiListener;
import mygame.engine.gui.Panel;
import mygame.engine.nodes.GroupNode;

/**
 *
 * @author Dansion
 */
public class BlockPreview extends Panel implements GuiAction {
    private BlockInterface preview = null;
    private int value = -1;
    private int default_size = 128;
    
    public GuiListener listener = null;
    
    public BlockPreview() {
        super("BlockPreview", "Textures/block_preview_panel.png");
                
        showCloseButton(false);
    }
    
    public void setPreview(BlockInterface block) {
        preview = block;
        preview.setAlpha(false);
        preview.getNode().setLocalTranslation(getWidth() / 2, 4, 0);
        preview.getNode().setLocalScale(30, 30, 30);
        
        attachChild(preview.getNode());
        
        rescalePreview();
    }
    
    public void clearPreview() {
        if(preview != null) {
            detachChild(preview.getNode());
            preview = null;
        }
    }
    
    @Override
    public void setHeight(int nheight) {
        super.setHeight(nheight);
        
       // rescalePreview();
    }
    
    @Override
    public void setWidth(int nwidth) {
        super.setWidth(nwidth);
        
      //  rescalePreview();
    }

    public void onAction() {
        if(listener != null) {
            listener.onAction(getValue());
        }
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    private void rescalePreview() {
        int t = default_size;
        
        if(preview != null) {        
            preview.getNode().setLocalScale(((float) getWidth() / t) * 30.0f, ((float) getHeight() / t) * 30.0f, ((float) getWidth() /t) * 30.0f);
            preview.getNode().setLocalTranslation(getWidth() / 2, ((float) getHeight() / t) * 4, 0);
        }
    }
}
