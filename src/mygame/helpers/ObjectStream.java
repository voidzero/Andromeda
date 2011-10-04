/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Dansion
 */
public class ObjectStream {
    private String file;
    
    public ObjectStream(String filename) throws FileNotFoundException, IOException {
        File f = new File(filename);
        
        if(!f.exists()) {
            f.createNewFile();
        }
    }
    
    public void save(Object obj, String filename) throws FileNotFoundException, IOException {
        FileOutputStream f_out = null;
        ObjectOutputStream obj_out = null;
        
        f_out = new FileOutputStream(filename);
        obj_out = new ObjectOutputStream (f_out);
        obj_out.writeObject ( obj );   
    }
    
    public Object load(String filename) throws FileNotFoundException, IOException, ClassNotFoundException {
        // Read from disk using FileInputStream
        FileInputStream f_in = new FileInputStream(filename);

        // Read object using ObjectInputStream
        ObjectInputStream obj_in = new ObjectInputStream (f_in);

        // Read an object
        Object obj = obj_in.readObject();
        
        return obj;
    }
}
