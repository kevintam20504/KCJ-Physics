package Main;

import Controllers.FXMLMainMenuController;
import Controllers.FXMLMainAppController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//hello


public class MainApp extends Application {

    private final static Logger logger = LoggerFactory.getLogger(MainApp.class);
//test1
    @Override
    public void start(Stage primaryStage) {
        try {
            logger.info("Bootstrapping the application...");
            //-- 1) Load the scene graph from the specified FXML file and 
            // associate it with its FXML controller.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainMenu.fxml"));
            loader.setController(new FXMLMainMenuController());
            Pane root = loader.load();
            //-- 2) Create and set the scene to the stage.
            Scene scene = new Scene(root, 600, 400);
            primaryStage.setScene(scene);
            primaryStage.sizeToScene();
            // We just need to bring the main window to front.
            primaryStage.setAlwaysOnTop(true);            
            primaryStage.show();
            primaryStage.setAlwaysOnTop(false);
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
/*
package edu.vanier.template;

 
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class MainApp extends Application {

    private static final double GRAVITY = 2;
    private double wallAngle = 120;           
    private Line slantedWall;
    private Circle ball;
    

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        Scene scene = new Scene(root, 800, 600);

        double centerX = scene.getWidth() / 2;
        double centerY = scene.getHeight() / 2;

        slantedWall = new Line(centerX - 100, centerY, centerX + 100, centerY);
        slantedWall.setStroke(Color.BLACK);

        ball = new Circle(20, Color.BLACK);
        ball.setCenterX(centerX - 200);
        ball.setCenterY(centerY);

   
        Rotate rotateWall = new Rotate(wallAngle, centerX, centerY);
        Rotate rotateBall = new Rotate(0, centerX, centerY);

        slantedWall.getTransforms().add(rotateWall);
        ball.getTransforms().add(rotateBall);

        root.getChildren().addAll(slantedWall, ball);

        primaryStage.setTitle("Bouncing Ball Simulation");
        primaryStage.setScene(scene);
        primaryStage.show();

      
        AnimationTimer timer = new AnimationTimer() {
            double angle = wallAngle;

            @Override
            public void handle(long now) {
                update(angle);
                angle = 1; 
            }
        };
        timer.start();
    }

    private void update(double angle) {
               double speed = 5;
        double radians = Math.toRadians(angle);

        ball.setCenterX(ball.getCenterX() + Math.cos(radians) * speed);
        ball.setCenterY(ball.getCenterY() + Math.sin(radians) * speed);

       
        if (ball.getBoundsInParent().intersects(slantedWall.getBoundsInParent())) {
          
            double angleOfIncidence = wallAngle - angle;
            double angleOfReflection = 2 * angleOfIncidence;

          
            Rotate rotateBall = new Rotate(angleOfReflection, ball.getCenterX(), ball.getCenterY());
            ball.getTransforms().set(0, rotateBall);
        }
    }
    
  
    

    public static void main(String[] args) {
        launch(args);
    }
}
*/