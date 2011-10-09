/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.state;

import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import mygame.engine.Camera.CameraMode;
import mygame.engine.Picking;
import mygame.engine.blocks.Block;
import mygame.engine.blocks.BlockSelection;
import mygame.engine.geom.Grid;
import mygame.engine.gui.Interfaces.GuiListener;
import mygame.engine.gui.Panels.BlockCatalog;
import mygame.engine.objects.BObject;
import mygame.items.blueprints.BluePrint;

/**
 *
 * @author Dansion
 */
public class Builder extends AbstractState{
    private int size_x = 30;
    private int size_y = 20;
    private int size_z = 60;
    
    private Picking pick = new Picking();
    private BlockSelection sel = null;
    
    private BObject obj_loaded = new BObject();
    private BlockCatalog catalogPanel = new BlockCatalog(obj_loaded.inventory);
    private BluePrint blueprint;
    private boolean guiMode;
    private Grid grid;
    
    public Builder(Node parent) {
        this(parent, 30, 20, 60);
    }
    
    public Builder(Node parent, int size_x, int size_y, int size_z) {  
        super(parent);
        
        this.size_x = size_x;
        this.size_y = size_y;
        this.size_z = size_z;
        
        String [] keyActions = {"floorDown"        , "floorUp"            , "Catalog"};
        int [] keyTriggers = {KeyInput.KEY_LBRACKET, KeyInput.KEY_RBRACKET, KeyInput.KEY_C};
        
        String [] mouseActions = {"addBlock",             "removeBlock"};
        int [] mouseTriggers =   {MouseInput.BUTTON_LEFT, MouseInput.BUTTON_RIGHT};
        
        setKeyInputs("Builder", keyTriggers, keyActions);
        setMouseActions(mouseActions, mouseTriggers);
        
        actionListener = new ActionListener() {
            public void onAction(String name, boolean keyPressed, float tpf) {
                if("addBlock".equals(name) && keyPressed) {
                    if(!guiMode && !sel.isBlocked()) { 
                        obj_loaded.addBlock(catalogPanel.getSelected(), checkCollision());
                    }
                    else {
                        if(isCursorActive()) {
                            //Pick Gui
                            CollisionResults shootables = getCursor().pickGui(guiNode, guiCam);
                        }
                    }
                }
                
                if("floorUp".equals(name) && keyPressed) {
                    obj_loaded.setCurrentFloor(obj_loaded.getCurrentFloor() + 1);
                    grid.setFloor(obj_loaded.getFloorHeight(), obj_loaded.getCurrentFloor());
                }
                
                if("floorDown".equals(name) && keyPressed) {
                    obj_loaded.setCurrentFloor(obj_loaded.getCurrentFloor() - 1);
                    grid.setFloor(obj_loaded.getFloorHeight(), obj_loaded.getCurrentFloor());
                }
                
                if("Catalog".equals(name) && keyPressed) {
                    if(catalogPanel.isActive()) {
                        enableCursor(false);
                        getCamera().setMode(CameraMode.FLY_CLIP);
                        getCamera().setMovementSpeed(20);
                        guiMode = false;
                        guiNode.detachChild(catalogPanel);
                    } 
                    else {
                        enableCursor(true);
                        getCamera().setMode(CameraMode.STATIC);
                        guiMode = true;
                        guiNode.attachChild(catalogPanel);
                    }
                }
                
                if("removeBlock".equals(name) && keyPressed) {
                    System.out.println("removeBlock");
                    
                }
            }
        };
        
        catalogPanel.listener = new GuiListener() {
            public void onAction(int returnValue) {
                sel.setMask(catalogPanel.getSelected());
            }
        };
        
        sel = new BlockSelection(obj_loaded, catalogPanel.getSelected());
        rootNode.attachChild(sel);
        rootNode.attachChild(obj_loaded);
        initGrid();
        
        showCrossHair(true);
        setKeyListener(actionListener);
        
        getCamera().setMode(CameraMode.FLY_CLIP);
        getCamera().setMovementSpeed(20);
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
        if(isCursorActive()) {
            guiMode = true;
        }
    }
    
    @Override
    public void onDetach() {
    }
    
    public Vector3f checkCollision() {
       CollisionResults collisions = cameraPick(rootNode);

        if(collisions.size() > 0) {
            for(int i = 0;i<collisions.size();i++) {
                CollisionResult col = collisions.getCollision(i);
                if(col.getGeometry().getName() != null) {
                    if(col.getGeometry().getName().contains("blockSelection") == false) {
                        Vector3f newBlockPos = Block.blockCollisionToGrid(col);
                        
                        return newBlockPos;
                    }
                }
            }
        }
        
        return new Vector3f();
    }
    
    public void loadBlueprint(BluePrint blue_print) {
        //set size
        size_x = blue_print.getSizeX();
        size_y = blue_print.getSizeY();
        size_z = blue_print.getSizeZ();
        
        //clear object
        obj_loaded = new BObject();
        
        //refresh grid
        refreshGrid();
    }
}
