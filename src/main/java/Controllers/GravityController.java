/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Main.MainApp;
//import Models.Ball;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author JD
 */
public class GravityController {
    private Circle Satellite;
    private Timeline timeline;
    
     @FXML
     BorderPane GravityBorderPane;
     
     @FXML
     Pane AnimationPane;
     
     @FXML 
     LineChart SpeedChart;
     
     @FXML 
     LineChart DistanceChart;
     
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
     private Circle smallBlueBall;
    private TranslateTransition transition;
   @FXML
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
 @FXML
private void handleStartBtnAction(ActionEvent event) {
    try {
      SatelliteMover Satellite1 = new SatelliteMover(Satellite, 0, -20, InitialSpeed.getValue(), -20);
      Satellite1.start();
    } catch (Exception e) {
        
        // Log the error or print stack trace
        e.printStackTrace();
    }
}



      public void initialize() {
        Circle Planet = new Circle(350, 175, 40, Color.BLACK);
        Satellite = new Circle(400, 175, 10, Color.BLUE);
        AnimationPane.getChildren().addAll(Planet, Satellite);

        
      
    }
      
//adbdabua
}
     

