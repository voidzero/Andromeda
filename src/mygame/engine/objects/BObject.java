/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.objects;
import com.jme3.math.Vector3f;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mygame.engine.blocks.Block;
import mygame.engine.blocks.BlockIndex;
import mygame.engine.blocks.Blocks;
import mygame.engine.blocks.CustomBlock;
import mygame.engine.blocks.Interface.BlockInterface;
import mygame.engine.blocks.Lists.SpaceShips;
import mygame.engine.nodes.GroupNode;
import mygame.helpers.FileInStream;
import mygame.helpers.FileOutStream;

/**
 *
 * @author Dansion
 */
public class BObject extends GroupNode {
    public ArrayList<BlockInterface> blocks = new ArrayList<BlockInterface>();
    public BlockIndex inventory = new SpaceShips();

    private int floorHeight = 4;
    private int currentFloor = 1;
    private int floorAmount = 3;

    public BObject() {
        super();
    }

    public void addBlock(BlockInterface block, Vector3f position) {
        try {
            BlockInterface newBlock = block.getClass().newInstance();

            blocks.add(newBlock);

            newBlock.getNode().setLocalTranslation(position);
            attachChild(newBlock.getNode());

        } catch (InstantiationException ex) {
            Logger.getLogger(BObject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(BObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeBlock(Vector3f position) {
        BlockInterface b = getBlock(position);

        if(b != null) {
            b.getNode().getParent().detachChild(b.getNode());

            blocks.remove(b);
        }
    }

    public BlockInterface getBlock(float x, float y, float z) {
        for(int i = 0; i < blocks.size(); i++) {
            BlockInterface b = blocks.get(i);

            for(int i2 = 0; i2 < b.amount(); i2++) {
                Block b2 = b.getBlock(i2);
                if(b2.getWorldTranslation().x == x && b2.getWorldTranslation().y == y && b2.getWorldTranslation().z == z) {
                    return b;
                }
            }
        }

        return null;
    }

    public BlockInterface getBlock(Vector3f position) {
        return getBlock(position.x, position.y, position.z);
    }

    public int getFloorHeight() {
        return floorHeight;
    }

    public void setCurrentFloor(int floor) {
        currentFloor = floor;

        if(currentFloor > floorAmount) {
            currentFloor = floorAmount;
        }

        if( currentFloor < 1 ) {
            currentFloor = 1;
        }
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getCurrentFloorY() {
        return getCurrentFloor() * getFloorHeight() - getFloorHeight();
    }

    public void loadInEditor() {
    }

    public final void save(String fname) {
        try {
            FileOutStream f = new FileOutStream(fname, 1, 0);

            f.writeInt(blocks.size());

            for(int i = 0; i < blocks.size(); i++) {
                BlockInterface b = blocks.get(i);

                if(b instanceof Block) {
                    f.writeInt(0);
                }

                if(b instanceof Blocks) {
                    f.writeInt(1);
                }

                if(b instanceof CustomBlock) {
                    f.writeInt(2);
                }

                f.writeUTF(b.getClass().getName());
                f.writeFloat(b.getLocation().x);
                f.writeFloat(b.getLocation().y);
                f.writeFloat(b.getLocation().z);
            }

            f.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BObject.class.getName()).log(Level.SEVERE, "Could not save object to : " + fname, ex);
        } catch (IOException ex) {
            Logger.getLogger(BObject.class.getName()).log(Level.SEVERE, "Could not write to object : " + fname, ex);
        }
    }

    public static BObject load(String fname) {
        FileInStream f = null;
        BObject obj = new BObject();

        try {
            f = new FileInStream(fname, 1, 0);

            //read BlockInterface amount
            int amount = f.readInt();

            for(int i = 0; i < amount; i++) {
                int b_type = f.readInt();
                String b_class = f.readUTF();
                Vector3f pos = new Vector3f(f.readFloat(), f.readFloat(), f.readFloat());

                try {
                    BlockInterface b = (BlockInterface) Class.forName(b_class).newInstance();

                    obj.addBlock(b, pos);
                } catch (InstantiationException ex) {
                    Logger.getLogger(BObject.class.getName()).log(Level.SEVERE, b_class, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(BObject.class.getName()).log(Level.SEVERE, b_class, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(BObject.class.getName()).log(Level.SEVERE, b_class, ex);
                }
            }

            f.close();
            obj.optimize();

            return obj;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BObject.class.getName()).log(Level.SEVERE, "Could not find object : " + fname, ex);
        } catch (IOException ex) {
            Logger.getLogger(BObject.class.getName()).log(Level.SEVERE, "Could not read from object : " + fname, ex);
        }

        return null;
    }

    public void optimize() {
        for(int i = 0; i < blocks.size(); i++) {
            BlockInterface b = blocks.get(i);
            b.optimizeFor(this, b.getNode().getLocalTranslation());
        }
    }

    //@TODO BObject saving / loading blueprint if available
}
