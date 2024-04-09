/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Richochetpackage;

/**
 *
 * @author JD
 */
public class RichochetSimulationSettings {
  

    private double mass = 100;
    private double vx = 0.01;
    private double vy = 100;
    private double angle = 0.99;
    private double velocity = 0;
      double Gravity = 1;

    public RichochetSimulationSettings() {
    }

public double getMass() {
    return mass;
}

public void setMass(double mass) {
    this.mass = mass;
}

    public double getvx() {
        return vx;
    }

    public void setK(double vx) {
        this.vx = vx;
    }

    public double getvy() {
        return vy;
    }
    public double getGravity() {
        return Gravity;
    }

    public void setGravity(double Gravity) {
        this.Gravity = Gravity;
    }
    public void setvy(double vy) {
        this.vy = vy;
    }

    public double getangle() {
        return angle;

    }

    public void setangle(double angle) {
        this.angle = angle;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }
@Override
    public String toString() {
    return "SimulationSettings{" + "velocity=" + velocity + ", mass=" + mass + ", velocity x=" + vx + ", Gravity=" + Gravity + ", velocity Y=" + vy + ", Angle=" + angle + '}';
    }
}

