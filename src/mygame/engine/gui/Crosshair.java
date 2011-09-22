/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine.gui;

import com.jme3.asset.AssetManager;
import com.jme3.system.AppSettings;
import com.jme3.ui.Picture;
import mygame.Assets;
import mygame.helpers.Share;

/**
 *
 * @author Dansion
 */
public class Crosshair extends Picture {
    private AssetManager assetManager = Assets.getInstance().assetManager;
    private AppSettings settings = Share.getInstance().appSettings;
    
    public Crosshair() {
        super("crosshair");
        
        setImage(assetManager, "Textures/crosshair.png", true);
        setWidth(32);
        setHeight(32);
        setPosition(settings.getWidth()/2-16, settings.getHeight()/2-16);
    }    
}
