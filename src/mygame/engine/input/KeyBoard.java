/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.input;

import java.util.ArrayList;

/**
 *
 * @author Dansion
 */
public class KeyBoard {
    private final static KeyBoard INSTANCE = new KeyBoard();
    private ArrayList <KeyInputs> keyInputGroups = new ArrayList();
    
    private KeyBoard() {      
    }
    
    public static KeyBoard getInstance() {
        return INSTANCE;
    }

    public void register(KeyInputs keys) {
        System.out.println("reg" + keys.getKeyGroup());
        keyInputGroups.add(keys);
    }
}
