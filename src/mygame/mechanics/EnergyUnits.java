/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.mechanics;

/**
 *
 * @author Dansion
 */
public class EnergyUnits {
    private float amount = 0.0f;
    private float efficiency = 100.0f;
    
    public void setEfficiency(float efficiency) {
        this.efficiency = efficiency > 100 ? 100 : efficiency;
        this.efficiency = efficiency < 0 ? 0 : efficiency;
    }
    
    public float getEfficiency() {
        return efficiency;
    }
}
