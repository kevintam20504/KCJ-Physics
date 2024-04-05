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
    private static double distance;
    private double mass1;
    private double mass2;
    private double velocity1;
    private double velocity2;
    private double elasticity;
    private double momentum = (mass1 * velocity1) + (mass2 * velocity2);

    private double getCollisionTime() {
        double time = distance / (this.velocity1 + this.velocity2);
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

    private double getVelocity1() {
        double velocity = 0;
        if (this.elasticity == 0) {
            velocity = this.momentum / (mass1 + mass2);
            return velocity;
        } else if (this.elasticity == 1) {

        } else {

        }
        return velocity;
    }

    private double getVelocity2() {
        double velocity = 0;
        if (this.elasticity == 0) {
            velocity = this.momentum / (mass1 + mass2);
            return velocity;
        } else if (this.elasticity == 1) {

        } else {

        }
        return velocity;
    }

}
