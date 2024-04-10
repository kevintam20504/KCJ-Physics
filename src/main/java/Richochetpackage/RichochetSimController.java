/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Richochetpackage;
import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.util.Duration;
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
     AnimationTimer animation;
     //  private double vx = 0;
     //   private double vy = 0;
      PathTransition pathTransition;
        
         private long timeStart;
          private long timeEnd;
         private Circle golfBall = new Circle(10, Color.BLACK);
    private Path path = new Path();
    
        public long getTimeStart() {
        return timeStart;
    }

    public long getTimeEnd() {
        return timeEnd;
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
  
      public void startTime() {
        timeStart = System.currentTimeMillis();
    }

    public long getTime() {
        timeEnd = System.currentTimeMillis();
        return timeEnd - timeStart;

    }

    public void setGolfBall(Circle golfBall) {
        this.golfBall = golfBall;
    }

    public Circle getGolfBall() {
        return golfBall;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public Path getPath() {
        return path;
    }
    
       
    
}



  