/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.helpers;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Dansion
 */
public class FileOutStream extends DataOutputStream{    
    public FileOutStream(String file) throws FileNotFoundException, IOException {
        super(new FileOutputStream(file));
        
        //write filetype = normal = 0 to file
        writeInt(0);
    }
    
    public FileOutStream(String file, int version, int sub_version) throws FileNotFoundException, IOException {
        super(new FileOutputStream(file));
        
        //write filetype = versioned = 1 to file
        writeInt(1);
        
        //write version
        writeInt(version);
        writeInt(sub_version);
    }
}
