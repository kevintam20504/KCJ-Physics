/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Settings;

/**
 *
 * @author JD
 */
public class RichochetSimulationSettings {
    private double mass = 100;
     private double velocity = 0;
      private double angle = 0;
      private double wind =0;
      
      
          public RichochetSimulationSettings() {
    }
          
          public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }
    
      public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity= velocity;
    }
    
      public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }
    
      public double getWind() {
        return wind;
    }

    public void setWind(double wind) {
        this.wind = wind;
    }
}
