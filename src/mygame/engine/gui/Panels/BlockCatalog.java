/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.gui.Panels;

import com.jme3.light.AmbientLight;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import mygame.engine.blocks.BlockIndex;
import mygame.engine.blocks.Interface.BlockInterface;
import mygame.engine.gui.Panel;
import mygame.engine.nodes.GroupNode;
import mygame.helpers.Share;

/**
 *
 * @author Dansion
 */
public class BlockCatalog extends Panel {
    private GroupNode preview = null;
    private BlockIndex catalog = null;
    
    public BlockCatalog(BlockIndex catalog) {
        super("Catalog_panel", "Textures/block_catalog.png");
        
        this.catalog = catalog;
        
        AmbientLight light = new AmbientLight();
        light.setColor(new ColorRGBA(3, 3, 3, 1));      
        addLight(light);
    }
    
    public void setSelected(String name) {
        BlockInterface prev = catalog.getBlock(name);
        prev.setAlpha(false);
        
        preview = prev.getNode();
        
        preview.setQueueBucket(Bucket.Gui);
        
        attachChild(preview);
        
        updatePreview();
    }
    
    public void updatePreview() {
        if(preview != null) {
            System.out.println("updating preview");
            preview.move(Share.getInstance().appSettings.getWidth() - 92, Share.getInstance().appSettings.getHeight() - 124, 0);
            preview.setLocalScale(20, 20, 20);
        }
    }
    
    public void update(float tpf) {
        
    }
}
