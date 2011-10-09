/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.helpers;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Dansion
 */
public class FileInStream extends DataInputStream{    
    public FileInStream(String file) throws FileNotFoundException, IOException {
        super(new FileInputStream(file));
        
        //check type
        int t = readInt();
        
        if(t != 0) {
            throw new IOException("This is not a normal file");
        }
    }
    
    public FileInStream(String file, int version, int sub_version) throws FileNotFoundException, IOException {
        super(new FileInputStream(file));
        
        //check type
        int t = readInt();
        
        if(t != 1) {
            throw new IOException("This is not a versioned file");
        }
        
        int v = readInt();
        int sv = readInt();
        
        if(v != version || sv != sub_version) {
            throw new IOException("Incompatible version");
        }
    }
}
