/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

/**
 *
 * @author 2289679
 */
public class CollisionPhysics {

    //distance between blocks in meters. note the scale is 1m=100pixels
    private static final double DISTANCE = 8;
    private double mass1 = 0;
    private double mass2 = 0;
    private double velocity1 = 0;
    private double velocity2 = 0;
    private double elasticity = 0;

    private double getCollisionTime() {
        double time = DISTANCE / (this.velocity1 + this.velocity2);
        return time;
    }

    private double getBlock1DistanceTravelled() {
        double distance = this.velocity1 * getCollisionTime();
        return distance;
    }

    private double getBlock2DistanceTravelled() {
        double distance = this.velocity2 * getCollisionTime();
        return distance;
    }
    
    private double getVelocity1(){
       return 0; 
    }
    
    private double getVelocity2(){
        return 0;
    }

}
