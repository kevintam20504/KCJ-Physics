/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Richochetpackage;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;

/**
 *
 * @author JD
 */
public class RichochetSimController {
    
      RichochetSimulationSettings settings = new RichochetSimulationSettings();
       private double VELOCITY = settings.getVelocity();
        private double ANGLE = settings.getangle();
        private double GRAVITY = settings.getGravity() / 10;
    
     //  private double vx = 0;
     //   private double vy = 0;
        
         private long timeStart;
          private long timeEnd;
         private Circle golfBall = new Circle(10, Color.BLACK);
    private Path path = new Path();
    
         public void startTime() {
        timeStart = System.currentTimeMillis();
    }

    public long getTime() {
        timeEnd = System.currentTimeMillis();
        return timeEnd - timeStart;
    }
    
         
    public double calculateHorizontalComponent(double angleOfShot, double initialSpeed, double time) {
        double timeSeconds = time / 1000.0;
        double angleInRadians = Math.toRadians(angleOfShot);
        double horizontalComponent = initialSpeed * Math.cos(angleInRadians) * timeSeconds - (0.5 * timeSeconds * timeSeconds);
        return horizontalComponent;
    }
    
     public double calculateVerticalComponent(double angleOfShot, double gravity, double initialSpeed, double time) {
        double timeSeconds = time / 1000.0;
        double angleInRadians = Math.toRadians(angleOfShot);
        double verticalComponent = initialSpeed * Math.sin(angleInRadians) * timeSeconds - (0.5 * gravity * timeSeconds * timeSeconds) ;
        return verticalComponent;
    }
    
     public double calculateHorizontalVelocity(double angleOfShot, double initialSpeed, double time) {
        double timeSeconds = time / 1000.0;
        double angleInRadians = Math.toRadians(angleOfShot);

        double velocity = settings.getVelocity();
        double gravity = settings.getGravity() / 10;

        double horizontalVelocity = velocity * Math.cos(angleInRadians);
        return horizontalVelocity;
    }
    
     public double calculateVerticalAcceleration(double gravity) {
        double verticalAcceleration = -gravity;
        return verticalAcceleration;
    }
    // continue here later
        
    
}



   /*
          public class Constants {
    public static double GRAVITY = 9.81; 
}
        public double calculateAngularFrequency(double angle, double speed) {
   
    double theta = Math.toRadians(angle);
    
  
    double initialVelocityX = speed * Math.cos(theta);
    double initialVelocityY = speed * Math.sin(theta);
    double timeOfFlight = 2 * initialVelocityY / Constants.GRAVITY;
    double range = initialVelocityX * timeOfFlight;
    double maxHeight = Math.pow(initialVelocityY, 2) / (2 * Constants.GRAVITY);
    double angularFrequency = 2 * Math.PI / timeOfFlight;
    
    return angularFrequency;
}
    */ 