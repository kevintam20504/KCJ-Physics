
package Controllers;

import Main.MainApp;
import Models.Ball;
import java.net.URL;
import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author JD
 */
public class RichochetController {
     private Circle projectile;
    
   
    
     @FXML
    Pane Paneforscene;



   
    
    @FXML
    Button BtnStart;

 
    
    @FXML
    Button BtnStop;

   @FXML
    Button BtnReset;
   
   

      @FXML
    Slider  SldWallAngle ;
   

    @FXML
    Slider SldSpeed;
    @FXML
    Slider SldShotAngle;
    
    @FXML
private Slider SldWind;

    private double gravity =.098;
    @FXML
    ToggleButton BtnGravity;

    @FXML 
    LineChart GrpDistance;
    
     @FXML 
    LineChart GrpSpeed;
     
     
     
   
     
           private Circle ball;
    private double velocityX = 2;
    private double velocityY = 0;
    private double accelerationY = 0.1;
    private AnimationTimer animationTimer;

   @FXML
   
    private double initialXPosition;
    private double initialYPosition;
   private Line horizontalWall;
    private double horizontalWallLength = 10;
    
    
    
    
    
public void initialize() {

    

    createBall();
Eventhandelers();
     Wanderstellen();
   initializeHandlers();
   
     createHorizontalWall();
     
       setupSliders();
       
        BtnGravity.setSelected(false);
  BtnGravity.setOnAction(e -> toggleGravity());
    
}



private void setupSliders() {

 SldSpeed.setMin(5);
    SldSpeed.setMax(20);
    SldSpeed.setValue(1); 
    
      SldShotAngle.setMin(0);
    SldShotAngle.setMax(100);
    SldShotAngle.setValue(0);
     SldShotAngle.setBlockIncrement(0.1);

     SldWind.setMin(0);
     SldWind.setMax(5);
             SldWind.setValue(0);
  
    SldSpeed.valueProperty().addListener((obs, oldVal, newVal) -> {
        velocityX = newVal.doubleValue();
        System.out.println("Speed adjusted to: " + velocityX);  
    });      
     initialXPosition = ball.getLayoutX();
    
     /* SldShotAngle.valueProperty().addListener((obs, oldVal, newVal) -> {
        adjustShootingAngle(newVal.doubleValue());
    });*/
     
       SldShotAngle.valueProperty().addListener((obs, oldVal, newVal) -> updateHorizontalWallPosition());

}



private void createBall() {
 
    ball = new Circle(10);
    ball.setLayoutX(50); 
    ball.setLayoutY(200); 
    Paneforscene.getChildren().add(ball);
}
 





 private Line slantedWall;
private final double wallAngle =0; 

private void Wanderstellen(){
    double centerX = 200 + Paneforscene.getWidth();
    double centerY = 210;

    slantedWall = new Line(centerX - 50, centerY, centerX + 100, centerY);
    slantedWall.setStroke(Color.BLACK);
    Rotate rotateWall = new Rotate(wallAngle, centerX, centerY);
    slantedWall.getTransforms().add(rotateWall);

    Paneforscene.getChildren().add(slantedWall);
}


 private void initializeHandlers() {
        BtnStart.setOnAction(e -> startSimulation());
       // BtnStop.setOnAction(e -> stopSimulation());
        BtnStop.setOnAction(e -> resetSimulation());
//BtnReset
        
        BtnStop.setDisable(true);
        BtnReset.setDisable(true);

        SldWallAngle.valueProperty().addListener((obs, oldVal, newVal) -> rotateWall(newVal.doubleValue()));
        

    }
 
 
public void Eventhandelers(){
BtnStop.setDisable(true);
        BtnReset.setDisable(true);
    
}



private boolean gravityEnabled = false;  

private void toggleGravity() {
    gravityEnabled = BtnGravity.isSelected();
    System.out.println("Gravity toggled: " + (gravityEnabled ? "On" : "Off"));
}



  
  

 private void stopSimulation() {
        if (animationTimer != null) {
            animationTimer.stop();
        }
        BtnStart.setDisable(false);
        BtnStop.setDisable(true);
    }
 
 private void resetSimulation() {
        stopSimulation();
        ball.setLayoutX(50);
        ball.setLayoutY(200);
      SldSpeed.setValue(0);
    SldShotAngle.setValue(0);
    SldWind.setValue(0);
    SldWallAngle.setValue(0);
      rotateWall(0);
      
     velocityX = 0;
    velocityY = 0;
 
     BtnGravity.setSelected(false);
     toggleGravity();
         slantedWall.getTransforms().clear();
        slantedWall.getTransforms().clear();
    rotateWall(SldWallAngle.getValue());
       BtnReset.setDisable(true);
    BtnStart.setDisable(false);
    BtnStop.setDisable(true);
    
     SldWallAngle.setDisable(false);
     SldSpeed.setDisable(false);
     SldShotAngle.setDisable(false);
     SldWind.setDisable(false);
     BtnGravity.setDisable(false);
        
    }
 
 
  
  private void rotateWall(double angle) {
        Rotate rotate = new Rotate(-angle, slantedWall.getStartX(), slantedWall.getStartY());
        slantedWall.getTransforms().clear();
        slantedWall.getTransforms().add(rotate);
    }
  
  
  
  
 public void handle(long now) {
    if (gravityEnabled) {
        velocityY += gravity;
    }
    
     

    double newX = ball.getLayoutX() + velocityX;
    double newY = ball.getLayoutY() + velocityY;

    double distanceX = Math.abs(newX - initialXPosition);
    
     
    System.out.println("Distance traveled in X: " + distanceX);
    
     if (newY >= initialYPosition) {
  
        animationTimer.stop();
        System.out.println("Ball returned to or passed initial y position."+distanceX);
         startSimulation();
    }
     
     ball.setLayoutX(newX);
    ball.setLayoutY(newY);
    
    if (newX - ball.getRadius() < slantedWall.getEndX() && newY >= slantedWall.getStartY() && newY <= slantedWall.getEndY()) {
       
        PauseTransition pauseTransition = new PauseTransition(Duration.millis(.000001));
        pauseTransition.setOnFinished(event -> {
          
            double wallAngleRadians = Math.toRadians(wallAngle);
            double newAngle = wallAngleRadians * 2;
            velocityX = Math.cos(newAngle) * 10;
            velocityY = Math.sin(newAngle) * 10;
        });
        pauseTransition.play();

       
        newX = slantedWall.getEndX() + ball.getRadius();
    }
 if (newY >= horizontalWall.getStartY() && newY <= horizontalWall.getStartY() + 10) {
            System.out.println("Win");
        }
  
    ball.setLayoutX(newX);
    ball.setLayoutY(newY);
}
 
 
 private double dragCoefficient = 0.05;  

private void applyWindResistance() {
    double windSpeed = SldWind.getValue();  

    double dragForce = -dragCoefficient * windSpeed;
    velocityX += dragForce;
    System.out.println("Wind applied with speed: " + windSpeed + ", new VelocityX: " + velocityX);
}
 

 private void createHorizontalWall() {
        double centerX = 200 + Paneforscene.getWidth();
        double centerY = 300; 
  horizontalWallLength = 10; 
       horizontalWall = new Line(centerX - horizontalWallLength / 2, centerY, centerX + horizontalWallLength / 2, centerY);
    horizontalWall.setStroke(Color.BLACK);
    Paneforscene.getChildren().add(horizontalWall);
    }

 private double prevShotAngle = 0;
 private double horizontalWallPosition = 200;
 
 private void updateHorizontalWallPosition() {
    double angle = SldShotAngle.getValue();
    double deltaAngle = angle - prevShotAngle;
    double increment = 100; 

   
    if (deltaAngle > 0) {
        horizontalWallPosition += increment;
    } else if (deltaAngle < 0) {
        horizontalWallPosition -= increment;
    }


    double paneWidth = Paneforscene.getWidth();
    if (horizontalWallPosition < 0) {
        horizontalWallPosition = 0;
    } else if (horizontalWallPosition > paneWidth) {
        horizontalWallPosition = paneWidth;
    }

    double centerX = horizontalWallPosition;
    double centerY = 300;


    double newX1 = centerX - horizontalWallLength / 2;
    double newX2 = centerX + horizontalWallLength / 2;

    horizontalWall.setStartX(newX1);
    horizontalWall.setEndX(newX2);
    horizontalWall.setStartY(centerY);
    horizontalWall.setEndY(centerY);

   
    prevShotAngle = angle;
}
 
  private void startSimulation() {
   double xVelocity = SldSpeed.getValue();

    if (animationTimer == null) {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                
                if (gravityEnabled) {
                    velocityY += gravity;
                }

        //   double   dragforce = -windspeed;
         //  velocityX += dragforce;
                 
                  applyWindResistance();
                double newX = ball.getLayoutX() + velocityX;
                double newY = ball.getLayoutY() + velocityY;

                ball.setLayoutX(newX);
                ball.setLayoutY(newY);

               
                if (ball.getBoundsInParent().intersects(slantedWall.getBoundsInParent())) {
    

    double wallAngle = -SldWallAngle.getValue();
    double newAngle = Math.toRadians(2 * wallAngle);
    double initialUpwardVelocity =  xVelocity *Math.cos(wallAngle); 
    
    velocityX = Math.cos(newAngle) * xVelocity;
    velocityY = -Math.abs(Math.sin(newAngle) * initialUpwardVelocity); 

    System.out.println("test for new velocities after wall collision -> VelocityX: " + velocityX + ", VelocityY: " + velocityY);
     //System.out.println("test for new velocities after wall collision -> drag: " + dragforce );
}
            }
        };
   // }
    }
    animationTimer.start();
    SldWallAngle.setDisable(true);
     SldSpeed.setDisable(true);
     SldShotAngle.setDisable(true);
     SldWind.setDisable(true);
     BtnGravity.setDisable(true);
   
    BtnStart.setDisable(true);
    BtnStop.setDisable(false);
    BtnReset.setDisable(false);
}
}
