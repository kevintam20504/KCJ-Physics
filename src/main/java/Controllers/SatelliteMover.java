/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

/**
 *
 * @author chris
 */
import javafx.animation.AnimationTimer;
import javafx.scene.shape.Circle;

public class SatelliteMover {
    private Circle Satellite;
    private double velocityX;
    private double accelerationX;
    private double velocityY;
    private double accelerationY;

    private long lastUpdate = 0;

    public SatelliteMover(Circle Satellite, double initialVelocityX, double accelerationX, double initialVelocityY, double accelerationY) {
        this.Satellite = Satellite;
        this.velocityX = initialVelocityX;
        this.accelerationX = accelerationX;
        this.velocityY = initialVelocityY;
        this.accelerationY = accelerationY;
    }

    public void start() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (lastUpdate > 0) {
                    double deltaTime = (now - lastUpdate) / 1e9;

                    // Update velocityX
                    velocityX += accelerationX * deltaTime;
                    if (velocityX < -50 || velocityX > 50) {
                        accelerationX *= -1;
                    }

                    // Update velocityY
                    velocityY += accelerationY * deltaTime;
                    if (velocityY < -50 || velocityY > 50) {
                        accelerationY *= -1;
                    }

                    // Update position
                    double newX = Satellite.getLayoutX() + velocityX * deltaTime;
                    double newY = Satellite.getLayoutY() + velocityY * deltaTime;
                    Satellite.setLayoutX(newX);
                    Satellite.setLayoutY(newY);
                }
                lastUpdate = now;
            }
        }.start();
    }
}
