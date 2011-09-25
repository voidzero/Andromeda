/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.gui.Panels;

import mygame.engine.gui.Panel;
import mygame.engine.nodes.GroupNode;

/**
 *
 * @author Dansion
 */
public class BlockPreview extends Panel {
    private GroupNode preview = null;
    
    public BlockPreview() {
        super("BlockPreview", "Textures/block_preview_panel.png");
    }
    
    public void setPreview(GroupNode block) {
        preview = block;
        
        preview.setLocalTranslation(getWidth() / 2, 4, 0);
        preview.setLocalScale(30, 30, 30);
        
        attachChild(preview);
        
        showCloseButton(false);
    }
    
    public void clearPreview() {
        if(preview != null) {
            detachChild(preview);
            preview = null;
        }
    }
    
    @Override
    public void setHeight(int nheight) {
        super.setHeight(nheight);
    }
    
    @Override
    public void setWidth(int nwidth) {
        super.setWidth(nwidth);
    }
}
