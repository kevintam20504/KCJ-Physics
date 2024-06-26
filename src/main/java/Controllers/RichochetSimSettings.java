/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;

/**
 *
 * @author JD
 */
public class RichochetSimSettings {
    //  RichochetController rsc = new RichochetController();
    private RichochetSimSettings ssc;
    
    @FXML
    Pane renderingPane;

   // @FXML
   // Rectangle background;

    @FXML
    SplitMenuButton DDMMaterialofWall;
    @FXML
    Button btnStart;

    @FXML
    Button btnPause;

    @FXML
    Button btnReset;

    @FXML
    Slider SldAngleOfWall;

    @FXML
    Slider SlfSpeedOfProjectile;
    @FXML
    Slider sldSlfAngleofShot;
    
    @FXML
    
    Slider sldSWindressistance;

    @FXML
    ToggleButton BtnToggleButton;

    @FXML 
    LineChart GrpDistance;
    
     @FXML 
    LineChart GrpSpeed;
     
         @FXML 
    Pane Paneforscene;
   // @FXML
   // ToggleButton tgbtnDampingOFF;
//<<<<<<< Updated upstream:src/main/java/Controllers/RichochetController.java
         
         
         
   
       public void initialize() {
       }
       
     
     
     public void initEventHandlers() {
         
            GrpSpeed.setDisable(false);
            GrpDistance.setDisable(false);

          btnPause.setDisable(true);
        btnReset.setDisable(true);
        
        SldAngleOfWall.setDisable(false);
        SlfSpeedOfProjectile.setDisable(false);
        sldSlfAngleofShot.setDisable(false);
        sldSWindressistance.setDisable(false);
        BtnToggleButton.setDisable(false);
        
        
      
        
        
     }
     
//>>>>>>> Stashed changes:src/main/java/Controllers/RichochetSimSettings.java
}

