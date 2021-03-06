/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine;

import com.jme3.math.Vector2f;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dansion
 */
public class AnimMap {
    private Vector2f [] offset_array;
    private int length, fps, width, height;
    
    public AnimMap(String mapFile) {
        BufferedReader in = null;
        int i = 0;    
        
        try {            
            in = new BufferedReader(new FileReader(mapFile));
            String str;
            
            //check length of map file
            while ((str = in.readLine()) != null) {
                String[] strArr = str.split(" = ");
                
                //first line of map file = length / frames
                if(length == 0) {
                    length = Integer.parseInt(strArr[0]);
                    fps = Integer.parseInt(strArr[1]);
                    width = Integer.parseInt(strArr[2]);
                    height = Integer.parseInt(strArr[3]);
                    offset_array = new Vector2f[length];
                }
                else {                      
                    String [] coords = strArr[1].split(" ");   
                    float x = 1.0000f * Integer.parseInt(coords[0]) / width;
                    float y = -1.0000f * Integer.parseInt(coords[1]) / height;
                    offset_array[i] = new Vector2f(x, y);               
               
                    i++;
                }
            }
            
            in.close();            
        } catch (IOException ex) {
            Logger.getLogger(AnimMap.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(AnimMap.class.getName()).log(Level.SEVERE, null, ex);
            }
        }           
    }
    
    public Vector2f getUVFrameOffset(int frame) {
        return offset_array[frame];
    }
    
    public int getFps() {
        return fps;
    }
    
    public int length() {
        return length;
    }
}