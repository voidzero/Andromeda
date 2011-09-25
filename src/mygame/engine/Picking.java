/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine;

import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.math.Quaternion;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import mygame.engine.gui.Button;
import mygame.engine.gui.Interfaces.GuiAction;
import mygame.helpers.Share;
import mygame.helpers.Various;

/**
 *
 * @author Dansion
 */
public class Picking {
    private static Share shares = Share.getInstance();
    
    public static CollisionResults cursorPickGui(Node shootables, Vector2f coords, Camera cam) {
        Vector3f origin = new Vector3f(coords.x, coords.y, 0.0f);
        Vector3f direction = new Vector3f(coords.x, coords.y, 0.3f);
        
        direction.subtractLocal(origin).normalizeLocal();

        Ray ray = new Ray(origin, direction);
        CollisionResults results = new CollisionResults();
    
        shootables.collideWith(ray, results);
        System.out.println("----- Collisions? " + results.size() + "-----");
        for (int i = 0; i < results.size(); i++) {
            // For each hit, we know distance, impact point, name of geometry.
            float dist = results.getCollision(i).getDistance();
            Vector3f pt = results.getCollision(i).getContactPoint();
            String hit = results.getCollision(i).getGeometry().getName();
            System.out.println("* Collision #" + i);
            System.out.println("  You shot " + hit + " at " + pt + ", " + dist + " wu away.");
            System.out.println(" Class  " + results.getCollision(i).getGeometry().getClass().getSimpleName());
            
            //Gui Code
            //==========================================================================================
            //Buttons
            if("Picture".equals(results.getCollision(i).getGeometry().getClass().getSimpleName())) {
                Node geom = results.getCollision(i).getGeometry().getParent();
                
                if(geom instanceof GuiAction) {
                    GuiAction g = (GuiAction) geom;
                    g.onAction();
                }
            }

        }
        return null;
    }
    
    public static CollisionResults cursorPick(Node shootables, Vector2f coords, Camera cam) {
        Vector3f origin = cam.getWorldCoordinates(coords, 0.0f);
        Vector3f direction = cam.getWorldCoordinates(coords, 0.3f);
        
        direction.subtractLocal(origin).normalizeLocal();

        Ray ray = new Ray(origin, direction);
        CollisionResults results = new CollisionResults();
    
        shootables.collideWith(ray, results);
        System.out.println("----- Collisions? " + results.size() + "-----");
        for (int i = 0; i < results.size(); i++) {
            // For each hit, we know distance, impact point, name of geometry.
            float dist = results.getCollision(i).getDistance();
            Vector3f pt = results.getCollision(i).getContactPoint();
            String hit = results.getCollision(i).getGeometry().getName();
            System.out.println("* Collision #" + i);
            System.out.println("  You shot " + hit + " at " + pt + ", " + dist + " wu away.");
        }
        return null;
    }
    
    public static CollisionResults cameraPick(Node shootables, Camera cam) {    
       Vector2f center = new Vector2f(shares.appSettings.getWidth() / 2, shares.appSettings.getHeight() / 2);
       Vector3f origin = cam.getWorldCoordinates(center, 0.0f);
       Vector3f direction = cam.getWorldCoordinates(center, 0.3f);

       direction.subtractLocal(origin).normalizeLocal();

       Ray ray = new Ray(origin, direction);
       
       CollisionResults results = new CollisionResults();
       shootables.collideWith(ray, results);
//        System.out.println("----- Collisions? " + results.size() + "-----");
//        for (int i = 0; i < results.size(); i++) {
//            // For each hit, we know distance, impact point, name of geometry.
//            float dist = results.getCollision(i).getDistance();
//            Vector3f pt = results.getCollision(i).getContactPoint();
//            String hit = results.getCollision(i).getGeometry().getName();
//            System.out.println("* Collision #" + i);
//            System.out.println("  You shot " + hit + " at " + pt + ", " + dist + " wu away.");
//       }
       
       return results;
    }
}

