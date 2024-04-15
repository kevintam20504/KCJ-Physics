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

    private static double distance;//distance between blocks in meters. note the scale is 1m=100pixels
    private double mass1;
    private double velocity1;
    private double mass2;
    private double velocity2;
    private double elasticity;

    public CollisionPhysics() {
        this(0, 0, 0, 0, 0);
    }

    public CollisionPhysics(double mass1, double velocity1, double mass2, double velocity2, double elasticity) {
        this.mass1 = mass1;
        this.velocity1 = velocity1;
        this.mass2 = mass2;
        this.velocity2 = velocity2;
        this.elasticity = elasticity;
    }

    //gets the time at which the blocks collide
    public double getCollisionTime() {
        double time = distance / (velocity1 + Math.abs(velocity2));
        return time;
    }

    //gets the distance travelled by block1 
    public double getBlock1DistanceTravelled() {
        double dist = velocity1 * getCollisionTime();
        return dist;
    }

    //gets the distance travelled by block2
    public double getBlock2DistanceTravelled() {
        double dist = Math.abs(velocity2) * getCollisionTime();
        return dist;
    }

    //gets velocity of block1 after collision
    public double getVelocity1Final() {
        double velocity = 0;
        if (elasticity == 0) {
            velocity = ((mass1 * velocity1) + (mass2 * velocity2)) / (mass1 + mass2);
            return velocity;
        } else if (elasticity == 1) {
            velocity = ((mass1 * velocity1) + (2 * mass2 * velocity2) - (mass2 * velocity1)) / (mass1 + mass2);
        } else {
            //TODO 0<elasticity<1
        }
        return velocity;
    }

    //gets velocity of block2 after collision
    public double getVelocity2Final() {
        double velocity = 0;
        if (elasticity == 0) {
            velocity = ((mass1 * velocity1) + (mass2 * velocity2)) / (mass1 + mass2);
            return velocity;
        } else if (elasticity == 1) {
            velocity = ((2 * mass1 * velocity1) + (mass2 * velocity2) - (mass1 * velocity2)) / (mass1 + mass2);
        } else {
            //TODO 0<elasticity<1
        }
        return velocity;
    }

    //getters and setters for distance between blocks,masses,velocities,and elasticity
    public static double getDistance() {
        return distance;
    }

    public static void setDistance(double distance) {
        CollisionPhysics.distance = distance;
    }

    public double getMass1() {
        return this.mass1;
    }

    public void setMass1(double mass1) {
        this.mass1 = mass1;
    }

    public double getMass2() {
        return this.mass2;
    }

    public void setMass2(double mass2) {
        this.mass2 = mass2;
    }

    public void setVelocity1(double velocity1) {
        this.velocity1 = velocity1;
    }

    public void setVelocity2(double velocity2) {
        this.velocity2 = velocity2;
    }

    public double getElasticity() {
        return this.elasticity;
    }

    public void setElasticity(double elasticity) {
        this.elasticity = elasticity;
    }

    @Override
    public String toString() {
        return "CollisionPhysics{" + "dist=" + distance + "mass1=" + mass1 + ", velocity1=" + velocity1 + ", mass2=" + mass2
                + ", velocity2=" + velocity2 + ", elasticity=" + elasticity + ", collisionTime=" + this.getCollisionTime()
                + ", block1Dist=" + this.getBlock1DistanceTravelled() + ", block2Dist=" + this.getBlock2DistanceTravelled()
                + ", velocity1=" + this.getVelocity1Final() + ", velocity2=" + this.getVelocity2Final() + '}';
    }

}
