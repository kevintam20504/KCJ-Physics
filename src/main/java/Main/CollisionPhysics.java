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

    private long mass1;
    private double velocity1;
    private long mass2;
    private double velocity2;
    private double elasticity;

    public CollisionPhysics() {
        this(0, 0, 0, 0, 0);
    }

    public CollisionPhysics(long mass1, double velocity1, long mass2, double velocity2, double elasticity) {
        this.mass1 = mass1;
        this.velocity1 = velocity1;
        this.mass2 = mass2;
        this.velocity2 = velocity2;
        this.elasticity = elasticity;
    }

    //gets velocity of block1 after collision
    public double getVelocity1Final() {
        double velocity = 0;
        if (elasticity == 0) {
            velocity = ((mass1 * velocity1) + (mass2 * velocity2)) / (mass1 + mass2);
            return velocity;
        } else if (elasticity == 1) {
            velocity = ((mass1 * velocity1) + (2 * mass2 * velocity2) - (mass2 * velocity1)) / (mass1 + mass2);
        } else if (elasticity > 0 && elasticity < 1) {
            //https://physics.stackexchange.com/questions/660602/how-is-partial-elasticity-calculated
            velocity = (elasticity * mass2 * velocity2 - elasticity * mass2 * velocity1 + mass1 * velocity1 + mass2 * velocity2) / (mass1 + mass2);
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
        } else if (elasticity > 0 && elasticity < 1) {
            //https://physics.stackexchange.com/questions/660602/how-is-partial-elasticity-calculated
            velocity = (elasticity * mass1 * velocity1 - elasticity * mass1 * velocity2 + mass1 * velocity1 + mass2 * velocity2) / (mass1 + mass2);
        }
        return velocity;
    }

    //getters and setters for masses,velocities,and elasticity
    public double getMass1() {
        return this.mass1;
    }

    public void setMass1(long mass1) {
        this.mass1 = mass1;
    }

    public double getMass2() {
        return this.mass2;
    }

    public double getVelocity1() {
        return this.velocity1;
    }

    public double getVelocity2() {
        return this.velocity2;
    }

    public void setMass2(long mass2) {
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

//    @Override
//    public String toString() {
//        return "CollisionPhysics{" + "dist=" + distance + "mass1=" + mass1 + ", velocity1=" + velocity1 + ", mass2=" + mass2
//                + ", velocity2=" + velocity2 + ", elasticity=" + elasticity + ", collisionTime=" + this.getCollisionTime()
//                + ", block1Dist=" + this.getBlock1DistanceTravelled() + ", block2Dist=" + this.getBlock2DistanceTravelled()
//                + ", velocity1F=" + this.getVelocity1Final() + ", velocity2F=" + this.getVelocity2Final() + '}';
//    }
    @Override
    public String toString() {
        return "CollisionPhysics{" + "velocity1=" + velocity1 + ", velocity2=" + velocity2 + ", velocity1F=" + this.getVelocity1Final() + ", velocity2F=" + this.getVelocity2Final() + '}';
    }

}
