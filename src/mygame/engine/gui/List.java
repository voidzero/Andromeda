/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.gui;

import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.texture.Texture;
import java.util.ArrayList;
import mygame.Assets;
import mygame.engine.gui.Interfaces.GuiListener;
import mygame.engine.nodes.GroupNode;

/**
 *
 * @author Dansion
 */
public class List extends GroupNode {
    private Assets assets = Assets.getInstance();
    
    private ArrayList <String> captions = new <String> ArrayList();
    private ArrayList <Integer> values = new <Integer> ArrayList();
    
    private int visibleOptions;
    private int length;
    private int value;
    public int scroll_y;
    
    private Button upButton = null;
    private Button downButton = null;
    private ArrayList <Button> options = new <Button> ArrayList();  
    private String optionTexture, upTexture, downTexture;
    private Texture up = null, down = null, option = null;
    
    public GuiListener listener = null;
    
    public List(int visibleOptions) {
        this("Textures/block_cat_button.png", "Textures/block_cat_button_up.png", "Textures/block_cat_button_down.png", visibleOptions);
    }
    
    public List(String option_background_texture, String list_up_texture, String list_down_texture, int visibleOptions) {
        super("List");
        
        setQueueBucket(Bucket.Gui);
        
        this.visibleOptions = visibleOptions;
        
        optionTexture = option_background_texture;
        upTexture = list_up_texture;
        downTexture = list_down_texture;
        
        upButton = new Button("Up", upTexture);
        downButton = new Button("", downTexture);
        
        upButton.setLocalTranslation(0, visibleOptions * 32 + downButton.getHeight(), 0);
        downButton.setLocalTranslation(0, 0, 0);
        
        attachChild(upButton);
        attachChild(downButton);
        
        scroll_y = 0;
        value = -1;
    }
    
    public void setList(ArrayList <String> cap, ArrayList <Integer> val) {
        if(cap.size() == val.size()) {
            length = cap.size();
            captions = cap;
            values = val;
        }
        
        updateList();
    }
    
    public void updateList () {
        options.clear();
        
        for(int i = 0;i < captions.size(); i++) {
            options.add(i, new Button(captions.get(i), optionTexture));
            options.get(i).showCaption();
        }
        
        renderList();
    }
    
    public void renderList() {
        if(scroll_y > options.size()) {
            scroll_y = options.size() - visibleOptions;
        }
        
        int max = scroll_y + visibleOptions;
        
        if(max > options.size()) {
            max = options.size();
        }
        
        int s = 1;
        
        for(int i = scroll_y; i < max; i++) {
           options.get(i).setLocalTranslation(0, downButton.getHeight() + (visibleOptions - s) * options.get(i).getHeight(), 0);
           attachChild(options.get(i));
           
           s++;
        }
    }
    
    public void setValue(int value) {
        this.value = value;
    }
    
    public void onAction() {
        if(listener != null) {
            listener.onAction();
        }
    }
    
    public void hideUp() {
        
    }
    
    public void HideDown() {
        
    }
}
