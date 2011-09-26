/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.gui.Panels;

import java.util.logging.Level;
import java.util.logging.Logger;
import mygame.engine.blocks.Interface.BlockInterface;
import mygame.engine.gui.Interfaces.GuiAction;
import mygame.engine.gui.Interfaces.GuiListener;
import mygame.engine.nodes.GroupNode;

/**
 *
 * @author Dansion
 */
public class BlockSelectBar extends GroupNode implements GuiAction {
    private int size;
    private int value = 0;
    private int scroll_x = 0;
    
    private BlockPreview [] buttons = null;
    private BlockInterface [] blocks = null;
    
    public GuiListener listener = null;
    
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
    
    public BlockInterface getCurrentBlock() {
        try {
            return blocks[getValue()].getClass().newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(BlockSelectBar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(BlockSelectBar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    private void updateBar() {
        if(blocks != null) {
            scroll_x = scroll_x < blocks.length - size ? scroll_x : blocks.length - size;
            
            int max = scroll_x + size;
            
            max = max < blocks.length ? max : blocks.length;
            
            for(int i = 0; i < max; i++) {
                buttons[i].setPreview(blocks[i]);
                buttons[i].setValue(i);
                
                buttons[i].listener = new GuiListener() {

                    public void onAction(int returnValue) {
                        setValue(returnValue);
                    }
                };
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
        
        clearActiveButtons();
        
        buttons[value].showActive(true);
        onAction();
    }

    public int getValue() {
        return value;
    }
    
    private void clearActiveButtons() {
        for(int i = 0; i < buttons.length; i++) {
            buttons[i].showActive(false);
        }
    }
}
