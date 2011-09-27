/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.gui.Panels;

import java.util.logging.Level;
import java.util.logging.Logger;
import mygame.engine.blocks.Interface.BlockInterface;
import mygame.engine.gui.Button;
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
    private Button leftButton = new Button("leftButton", "Textures/bar_left.png");
    private Button rightButton = new Button("leftButton", "Textures/bar_right.png");
    
    public GuiListener listener = null;
    
    public BlockSelectBar(int bar_size) {
        super("BlockSelectBar");
        
        leftButton.setWidth(12);
        leftButton.setHeight(48);
        
        rightButton.setWidth(12);
        rightButton.setHeight(48);
        
        size = bar_size;
        
        buttons = new BlockPreview[size];
        
        for(int i = 0; i < size; i++) {
            buttons[i] = new BlockPreview();
            
            buttons[i].setWidth(48);
            buttons[i].setHeight(48);
            buttons[i].setLocalTranslation(i * 48 + leftButton.getWidth(), 0, 0);
            buttons[i].setActiveBackgroundTexture("Textures/block_preview_panel_active.png");
            buttons[i].showCloseButton(false);
            
            attachChild(buttons[i]);
        }
        
        leftButton.listener = new GuiListener() {
            public void onAction(int returnValue) {
                scroll_x = scroll_x - 1;
                updateBar();
            }
        };
        
        rightButton.listener = new GuiListener() {
            public void onAction(int returnValue) {
                scroll_x = scroll_x + 1;
                updateBar();
            }
        };
        
        leftButton.setLocalTranslation(22, 0, 0);
        rightButton.setLocalTranslation(leftButton.getWidth() + size * 48, 0, 0);
        attachChild(leftButton);
        attachChild(rightButton);
    }
    
    public void setIndex(BlockInterface [] index) {
        if(index != null) {
            blocks = new BlockInterface[index.length];
            blocks = index;

            setValue(0);
        }
        else {
            clearButtons();
        }
    }
    
    public BlockInterface getCurrentBlock() {
        return blocks[getValue()];
    }
    
    private void updateBar() {
        clearButtons();
        
        if(blocks != null) {
            scroll_x = scroll_x < 0 ? 0 : scroll_x;
            scroll_x = scroll_x < blocks.length ? scroll_x : blocks.length;
            scroll_x = blocks.length < size ? 0 : scroll_x;
            
            int max = scroll_x + size;
            
            max = max < blocks.length ? max : blocks.length;
            
            int cor_x = 0 - scroll_x;
            int cor = 0;
//            System.out.println(cor_x);       
//            System.out.println(max);       
//            System.out.println(scroll_x);       
            for(int i = scroll_x; i < max; i++) {
                cor = i + cor_x;
                                     
                buttons[cor].setPreview(blocks[i]);
                buttons[cor].setValue(i);
                
                buttons[cor].listener = new GuiListener() {
                    public void onAction(int returnValue) {
                        setValue(returnValue);
                    }
                };
                
                if(buttons[cor].getValue() == getValue()) {
                    buttons[cor].showActive(true);
                }
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
        
        updateBar();
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
    
    private void clearButtonActions() {
        for(int i = 0; i < size; i++) {
            buttons[i].listener = null;
        }
    }
    
    private void clearButtonPreviews() {
        for(int i = 0; i < size; i++) {
            buttons[i].clearPreview();
        }
    }
    
    public void clearButtons() {
        clearActiveButtons();
        clearButtonActions();
        clearButtonPreviews();
    }
}
