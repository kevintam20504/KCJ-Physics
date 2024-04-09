/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import javafx.scene.paint.Color;

/**
 *
 * @author JD
 */
public class Ball {
    
    private double x;
    private double y;
    private double amplitude;
    private double radius;
    private double k;
    private double mass;
    private double damping;
    private double velocity;
    private Color color;

    public Ball(double x, double amplitude, double radius, double k, double mass, double damping, double velocity, Color color) {
        this.x = x;
        this.y = 0;
        this.amplitude = amplitude;
        this.radius = radius;
        this.k = k;
        this.mass = mass;
        this.damping = damping;
        this.velocity = velocity;
        this.color = color;
    }
    public Ball(){
        
    }
    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }
    
    
}
