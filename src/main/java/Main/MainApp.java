package Main;

import Controllers.FXMLMainMenuController;
import java.util.logging.Level;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//hello
public class MainApp extends Application {

    private final static Logger logger = LoggerFactory.getLogger(MainApp.class);
    public static Scene scene;

    @Override
    public void start(Stage primaryStage) {
        try {
            logger.info("Bootstrapping the application...");
            // Load the scene of the primary stage.
            Parent root = loadFXML("MainMenu", new FXMLMainMenuController());
            scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.sizeToScene();
            // Put this appliation's main window on top of other already-opened windows
            // upon launching the app.
            primaryStage.setAlwaysOnTop(true);
            primaryStage.show();
            primaryStage.setAlwaysOnTop(false);
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    /**
     * Changes the primary stage's current scene.
     *
     * @param fxmlFile The name of the FXML file to be loaded.
     * @param fxmlController An instance of the FXML controller to be associated
     * with the loaded FXML scene graph.
     */
    public static void switchScene(String fxmlFile, Object fxmlController) {
        try {
            scene.setRoot(loadFXML(fxmlFile, fxmlController));
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Loads a scene graph from an FXML file.
     *
     * @param fxmlFile The name of the FXML file to be loaded.
     * @param fxmlController An instance of the FXML controller to be associated
     * with the loaded FXML scene graph.
     * @return The root node of the loaded scene graph.
     * @throws IOException
     */
    public static Parent loadFXML(String fxmlFile, Object fxmlController) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/fxml/" + fxmlFile + ".fxml"));
        fxmlLoader.setController(fxmlController);
        return fxmlLoader.load();
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
