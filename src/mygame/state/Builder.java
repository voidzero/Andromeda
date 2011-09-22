/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.state;

import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import mygame.engine.Camera.CameraMode;
import mygame.engine.Picking;
import mygame.engine.blocks.Block;
import mygame.engine.blocks.BlockSelection;
import mygame.engine.blocks.Blocks;
import mygame.engine.blocks.Spaceship.Hulls.LightAlloyWindowed;
import mygame.engine.geom.Grid;
import mygame.engine.gui.Panels.BlockCatalog;
import mygame.engine.nodes.GroupNode;
import mygame.engine.objects.BObject;

/**
 *
 * @author Dansion
 */
public class Builder extends AbstractState{
    private int size_x = 30;
    private int size_y = 20;
    private int size_z = 60;
    private int roomheight = 4;
    private int floorHeight = 0;
    
    private Picking pick = new Picking();
    private BlockSelection sel = null;
    
    private String blockToPlace = "LightAlloyWindowed";
    private BObject obj_loaded = new BObject();
    private BlockCatalog catalogPanel = new BlockCatalog(obj_loaded.inventory);
    
    private Grid grid;
    
    public Builder(Node parent) {
        this(parent, 30, 20, 60);
    }
    
    public Builder(Node parent, int size_x, int size_y, int size_z) {  
        super(parent);
        
        this.size_x = size_x;
        this.size_z = size_z;
        
        String [] mouseActions = {"addBlock",             "removeBlock"};
        int [] mouseTriggers =   {MouseInput.BUTTON_LEFT, MouseInput.BUTTON_RIGHT};
        
        setMouseActions(mouseActions, mouseTriggers);
        
        actionListener = new ActionListener() {
            public void onAction(String name, boolean keyPressed, float tpf) {
                if("addBlock".equals(name) && keyPressed) {                
                    GroupNode blb = obj_loaded.inventory.getBlock(blockToPlace).getNode();
                    rootNode.attachChild(blb);
                    blb.setLocalTranslation(checkCollision());
                }
                
                if("removeBlock".equals(name) && keyPressed) {
                    System.out.println("removeBlock");
                    
                    if(catalogPanel.isActive()) {
                        guiNode.detachChild(catalogPanel);
                    } 
                    else {
                        guiNode.attachChild(catalogPanel);
                    }
                }
                
                if("openCataLog".equals(name) && keyPressed) {
                    
                }
            }
        };
        guiNode.attachChild(catalogPanel);
        catalogPanel.setSelected(blockToPlace);
        
        Blocks myBlock = new LightAlloyWindowed();
        sel = new BlockSelection(new BObject(), myBlock);
        rootNode.attachChild(sel);
        
        initGrid();
        
        showCrossHair(true);
    }
    
    private void initGrid() {
        grid = new Grid("builderFloorGrid", size_x, size_z);
        rootNode.attachChild(grid);
    }
    
    public void refreshGrid() {
        grid.setSize(size_x, size_z);
    }
    
    public void edit(BObject object) throws Exception {
        if(obj_loaded == null) {
            obj_loaded = object;
        }
        else {
            throw new Exception("There is already an object loaded in the Builder");
        }
        //@TODO Builder edit / load
    }
    
    public void save(BObject object) {
        object.save();
    }
    
    @Override
    public void update(float tpf) {
        super.update(tpf);

        catalogPanel.update(tpf);
        sel.setPlacing(checkCollision());
    }
    
    @Override
    public void onAttach() {
        getCamera().setMode(CameraMode.FLY_CLIP);
    }
    
    @Override
    public void onDetach() {
    }
    
    public Vector3f checkCollision() {
       CollisionResults collisions = cameraPick(shares.rootNode);

        if(collisions != null) {
            for(int i = 0;i<collisions.size();i++) {
                CollisionResult col = collisions.getCollision(i);
                if(col.getGeometry().getName() != null) {
                    if(col.getGeometry().getName().contains("blockSelection") == false) {
                        Vector3f newBlockPos = Block.blockCollisionToGrid(col);

                        newBlockPos.setY(floorHeight);
                        return newBlockPos;
                    }
                }
            }
        }
        
        return new Vector3f();
    }
}
