/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.engine;

import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.light.PointLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue.ShadowMode;
import com.jme3.scene.Node;

/**
 *
 * @author Dansion
 */
public class Lighting {
    public PointLight pl = new PointLight();
    float angle;
    
    public void Lighting() {
        
    }
    
    public void testLighting(Node subject) {
        AmbientLight am = new AmbientLight();
        am.setColor(new ColorRGBA(0.99f, 0.99f, 0.99f, 1.0f));
        subject.setShadowMode(ShadowMode.Cast);
        subject.addLight(am);
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-30f, 4, -20f));

        sun.setColor(new ColorRGBA(1,1,1,1));
        
        pl.setPosition(new Vector3f(0f, 2f, -4f));
        pl.setRadius(8);
        subject.addLight(pl);
        subject.addLight(sun);
        pl.setColor(new ColorRGBA(1,0.8f,0.2f,1));
    }

    void update(float tpf) {
        angle += tpf * 0.50f;
        angle %= FastMath.TWO_PI;

        pl.setPosition(new Vector3f(FastMath.cos(angle) * 4f, 1f, FastMath.sin(angle) * 4f));
    }
}
