/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;



import Main.MainApp;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


/**
 *
 * @author CS
 */
public class GravityController {
    private Circle Satellite;
    private static Circle  Planet;
    private double velocityX = 0;
    private double velocityY = 0;  
    private double planetmass;

    
    private static final double Gconstant = 0.1; // Simplified G constant for visible effect

    

    
     private long lastUpdate = 0;

     @FXML
     BorderPane GravityBorderPane;
     
     @FXML
     Pane AnimationPane;
     
     @FXML
     Slider PlantMass;
     
     @FXML
     Slider InitialSpeed;
     
     @FXML
     private Slider InitialDistance;
     
     @FXML
     Button StartBtn;
     
     @FXML
     Button ResetBtn;
     
     @FXML
     Button ExitBtn;
     
      @FXML
     Label SpeedTracker;
       
     @FXML
     Label DistanceTracker;
     
  
    private TranslateTransition transition;
   @FXML

   
   
   //buttons
   private void handleExitBtnAction(ActionEvent event) {
    try {
        // Close current window
        Node source = (Node) event.getSource();
        Stage currentStage  = (Stage) source.getScene().getWindow();
        currentStage.close();

        // Open Main window
        MainApp mainApp = new MainApp();
        mainApp.start(new Stage()); // Assumes MainApp extends Application
    } catch (Exception e) {
        
        // Log the error or print stack trace
        e.printStackTrace();
    }
}
    AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0;


            public void handle(long now) {
                if (now - lastUpdate >= 10_000_000) {
                    updateSatellitePosition();
                    lastUpdate = now;
                }
            }
           

        };
 @FXML
private void handleStartBtnAction(ActionEvent event) {
    
    planetmass = PlantMass.getValue() * 100;
        velocityY = InitialSpeed.getValue() / 10;
        Satellite.setTranslateX(InitialDistance.getValue());
        
         if (timer != null) {
                timer.start();
            }
   
}

            public void handle(long now) {
                if (now - lastUpdate >= 10_000_000) {
                    updateSatellitePosition();
                    lastUpdate = now;
                }
            }

private void handleResetBtnAction(ActionEvent event) {
   resetAnimation();
}







       @FXML
    public void initialize() {
        Planet = new Circle(350, 175, 40, Color.BLACK);
        Satellite = new Circle(425, 175, 10, Color.BLUE);
        AnimationPane.getChildren().addAll(Planet, Satellite);
       ResetBtn.setOnAction(e ->  resetAnimation() );  
    }
      
      
           //Collision
      public void checkForCollisionOrBoundary() {
        // Check for collision with the planet
        if (Satellite.getBoundsInParent().intersects(Planet.getBoundsInParent())) {
            
            showAlert("Collision", "The satellite has collided with the planet!");
           resetAnimation();
        }

        // Check if the satellite leaves the frame
        if ((Satellite.getCenterX()-Planet.getCenterX())>500 ||(Satellite.getCenterY()-Planet.getCenterY())>500 ) {
            
            showAlert("Boundary Exit", "The satellite has left the viewing area!");
            resetAnimation();
        }
    }

    private void showAlert(String title, String content) {
    Platform.runLater(() -> {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    });
}


    private void resetAnimation() {
        timer.stop();
        Satellite.setCenterX(425);
        Satellite.setCenterY(175);
        velocityX = 0;
        velocityY = 0;
        PlantMass.setValue(PlantMass.getMin());
        InitialSpeed.setValue(InitialSpeed.getMin());
        InitialDistance.setValue(InitialDistance.getMin());
      
    }
    



private void updateSatellitePosition() {
    
        checkForCollisionOrBoundary();
    
        double dx = Planet.getCenterX() - Satellite.getCenterX();
        double dy = Planet.getCenterY() - Satellite.getCenterY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        Double D = Math.ceil(distance * 10) / 10;
        String Distance = String.format("%.1f", D);
        DistanceTracker.setText(String.valueOf(Distance));

        // Calculate simplified gravitational force
        double force = Gconstant * planetmass / (distance * distance);
        double accelerationX = force * dx / distance;
        double accelerationY = force * dy / distance;

        // Update velocity
        velocityX += accelerationX;
        velocityY += accelerationY;
        Double S = Math.ceil(Math.sqrt(velocityX * velocityX + velocityY * velocityY) * 10) ;
        String Speed = String.format("%.1f", S);
        SpeedTracker.setText(String.valueOf(Speed));
        

        // Update position
        Satellite.setCenterX(Satellite.getCenterX() + velocityX);
        Satellite.setCenterY(Satellite.getCenterY() + velocityY);
       
    }


   

    
}





    