/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.gui.Panels;

import com.jme3.light.AmbientLight;
import com.jme3.math.ColorRGBA;
import java.util.ArrayList;
import java.util.Arrays;
import mygame.engine.blocks.BlockIndex;
import mygame.engine.blocks.Interface.BlockInterface;
import mygame.engine.gui.Interfaces.GuiListener;
import mygame.engine.gui.List;
import mygame.engine.gui.Panel;
import mygame.engine.gui.Text;

/**
 *
 * @author Dansion
 */
public class BlockCatalog extends Panel {
    private BlockIndex catalog = null;
    private BlockInterface current = null;
    private Text currentName = new Text("Name :");
    private List catList = new List(5);
    private BlockPreview preview = new BlockPreview();
    private BlockSelectBar selectBar = new BlockSelectBar(14);
    
    public BlockCatalog(BlockIndex catalog) {
        super("Catalog_panel", "Textures/block_catalog.png");
        
        setCatalog(catalog);
        
        //Previewbox Light
        AmbientLight light = new AmbientLight();
        light.setColor(new ColorRGBA(3, 3, 3, 1));      
        addLight(light);
        
        //Previewbox
        preview.setLocalTranslation(getRightX() - 32 - preview.getWidth(), getTopY() - 32 - preview.getHeight(), 0);
        attachChild(preview);
        
        //Block Information Labels
        currentName.setLocalTranslation(getLeftX() + 200, getTopY() - 32, 0);
        attachChild(currentName);  
        
        //Category List
        catList.setLocalTranslation(getLeftX(), getBottomY() + 32, 0);
        attachChild(catList);
        
        //selectbar
        selectBar.setLocalTranslation(getLeftX() + 160, getBottomY() + 32, 0);
        selectBar.listener = new GuiListener() {
            public void onAction(int returnValue) {
                setSelected(selectBar.getCurrentBlock());             
            }
        };
                
        attachChild(selectBar);
    }
    
    public void setSelected(BlockInterface block) {
        current = block;
        current.setAlpha(false);
        
        currentName.setText("Name :" + current.getName());
        preview.setPreview(current);   
    }
    
    public void setSelected(String name) {
        current = catalog.getBlock(name);
        current.setAlpha(false);
        
        currentName.setText("Name :" + current.getName());
        preview.setPreview(current);
    }
    
    public BlockInterface getSelected() {
        return selectBar.getCurrentBlock();
    }
    
    public void update(float tpf) {
        
    }
    
    public final void setCatalog(BlockIndex cat) {
        this.catalog = cat;
        
        String [] captions = this.catalog.getCategoryList();
        Integer [] values = this.catalog.getCategoryValueList();
        
        catList.setList(new ArrayList(Arrays.asList(captions)), new ArrayList(Arrays.asList(values)));
        
        catList.setValue(0);       
        selectBar.setIndex(this.catalog.getCategoryBlocks(0));
        
        setSelected(selectBar.getCurrentBlock());
    }
}
