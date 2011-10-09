/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.state;

import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import mygame.engine.Scaling;
import mygame.space.BackDrop;
import mygame.space.Planet;

/**
 *
 * @author Dansion
 */
public class Space extends AbstractState{
    private Scaling space = new Scaling();
    private BackDrop backdrop = new BackDrop();
    private String [] objects = {"Sun", "Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune"};
    private float [] sizes = {
            Scaling.getWorldValue(Scaling.Unit.KM, 1392000), 
            Scaling.getWorldValue(Scaling.Unit.KM, 4880), 
            Scaling.getWorldValue(Scaling.Unit.KM, 12104), 
            Scaling.getWorldValue(Scaling.Unit.KM, 12756), 
            Scaling.getWorldValue(Scaling.Unit.KM, 6794), 
            Scaling.getWorldValue(Scaling.Unit.KM, 142984), 
            Scaling.getWorldValue(Scaling.Unit.KM, 120536),
            Scaling.getWorldValue(Scaling.Unit.KM, 51118), 
            Scaling.getWorldValue(Scaling.Unit.KM, 49572)
        };
    
    private Planet [] planets = new Planet[9];
    
    private float [] distances = {
            0, 
            Scaling.getWorldValue(Scaling.Unit.KM, 57910000f), 
            Scaling.getWorldValue(Scaling.Unit.KM, 108208930f), 
            Scaling.getWorldValue(Scaling.Unit.KM, 149597870f), 
            Scaling.getWorldValue(Scaling.Unit.KM, 227936640f), 
            Scaling.getWorldValue(Scaling.Unit.KM, 778412010f), 
            Scaling.getWorldValue(Scaling.Unit.KM, 1426725400f),
            Scaling.getWorldValue(Scaling.Unit.KM, 2870972200f), 
            Scaling.getWorldValue(Scaling.Unit.KM, 4498252900f)
        };
    
    public Space(Node parent) {
        super(parent);
        
        backdrop.setLocalScale(400000);
        
        rootNode.attachChild(space);
      //  rootNode.attachChild(backdrop);
        
        //Test code :
        for(int i = 0; i < 9; i++) {
            System.out.println("Adding " + objects[i]);
            planets[i] = new Planet(objects[i], distances[i], sizes[i]);
            space.attachChild(planets[i]);
        }
        
        //    getCamera().setLocation(new Vector3f(5000, 0, 5000));
    }
    
    @Override
    public void update(float tpf) {
        //space.setViewer(getCamera());
        space.setViewerLocation(new Vector3f(0, 0, Scaling.getWorldValue(Scaling.Unit.KM, 1392500)));
    }
}
