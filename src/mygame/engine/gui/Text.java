/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.gui;

import com.jme3.font.BitmapFont;
import mygame.Assets;
import mygame.helpers.GameConfig;

/**
 *
 * @author Dansion
 */

public class Text extends com.jme3.font.BitmapText {
    private final static Assets assets = Assets.getInstance();
    
    private static BitmapFont font = assets.assetManager.loadFont("Interface/Fonts/Default.fnt");
    
    public Text(String caption) {
        super(font);
        
        setSize(font.getCharSet().getRenderedSize());
        setText(caption);
        setColor(GameConfig.guiTextColor);
    }
}
