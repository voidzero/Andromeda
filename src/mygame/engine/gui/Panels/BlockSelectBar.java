/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.gui.Panels;

import mygame.engine.blocks.Interface.BlockInterface;
import mygame.engine.blocks.Spaceship.Hulls.LightAlloy;
import mygame.engine.gui.Interfaces.GuiAction;
import mygame.engine.gui.Interfaces.GuiListener;
import mygame.engine.nodes.GroupNode;

/**
 *
 * @author Dansion
 */
public class BlockSelectBar extends GroupNode implements GuiAction {
    private int size;
    private int value = -1;
    
    private GuiListener listener = null;
    private BlockPreview [] buttons = null;
    private BlockInterface [] blocks = null;
    
    public BlockSelectBar(int bar_size) {
        super("BlockSelectBar");
        
        size = bar_size;
        
        buttons = new BlockPreview[size];
        
        for(int i = 0; i < size; i++) {
            buttons[i] = new BlockPreview();
            
            buttons[i].setWidth(48);
            buttons[i].setHeight(48);
            buttons[i].setLocalTranslation(i * 48, 0, 0);
            buttons[i].setActiveBackgroundTexture("Textures/block_preview_panel_active.png");
            buttons[i].showCloseButton(false);
            
            attachChild(buttons[i]);
        }
    }
    
    public void setIndex(BlockInterface [] index) {
        blocks = new BlockInterface[index.length];
        blocks = index;
        
        updateBar();
    }
    
    private void updateBar() {
        if(blocks != null) {
            int max = blocks.length > size ? size : blocks.length;
            
            for(int i = 0; i < max; i++) {
                buttons[i].setPreview(blocks[i]);
            }
        }
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
}
