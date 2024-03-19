/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author JD
 */


    

public class RichochetController {
    @FXML
    Pane renderingPane;

    @FXML
    Rectangle background;

    @FXML
    Button btnStart;

    @FXML
    Button btnPause;

    @FXML
    Button btnReset;

    @FXML
    Slider SpeedOfProjectile;

    @FXML
    Slider sldAmplitude;
    @FXML
    Slider sldSpringConstant;

    @FXML
    ToggleButton tgbtnDampingON;

    @FXML
    ToggleButton tgbtnDampingOFF;

}

