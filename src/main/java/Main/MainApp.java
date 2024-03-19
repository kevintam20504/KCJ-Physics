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


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;


public class MainApp extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private Circle ball;
    private Line slope;

    private double ballXSpeed = 2; // Horizontal speed
    private double ballYSpeed = 0; // Vertical speed
    private double gravity = 0.5; // Gravity value
    private double slopeAngle = Math.toRadians(30); // Slope angle in degrees

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();

        createBall();
        createSlope();

        root.getChildren().addAll(ball, slope);

        Scene scene = new Scene(root, WIDTH, HEIGHT);

        primaryStage.setTitle("Bouncing Ball with Slope");
        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();
    }

    private void createBall() {
        ball = new Circle(20, Color.BLUE);
        ball.setCenterX(WIDTH / 4.0);
        ball.setCenterY(0);
    }

    private void createSlope() {
        slope = new Line(200, HEIGHT - 100, WIDTH - 200, HEIGHT - 200);
        slope.setStroke(Color.GREEN);
    }

   private void update() {
    // Apply gravity
    ballYSpeed += gravity;

    // Update the ball's position based on the vertical and horizontal speeds
    ball.setCenterY(ball.getCenterY() + ballYSpeed);
    ball.setCenterX(ball.getCenterX() + ballXSpeed);

    // Check for collisions with the slope
    if (checkSlopeCollision()) {
        // Ball hits the slope, so calculate the new speeds
        double slopeNormalAngle = Math.atan2(slope.getEndY() - slope.getStartY(), slope.getEndX() - slope.getStartX());
        double incidentAngle = slopeNormalAngle - slopeAngle;

        // Calculate the reflection angle
        double reflectionAngle = Math.PI - incidentAngle;

        // Adjust the speeds based on the reflection angle
        double newYSpeed = Math.sin(reflectionAngle) * Math.abs(ballYSpeed);
        double newXSpeed = Math.cos(reflectionAngle) * Math.abs(ballYSpeed);

        // Apply the new speeds
        ballYSpeed = 0; // No vertical bounce
        ballXSpeed = -newXSpeed; // Bounce to the left

        // Adjust the ball's position to prevent it from passing through the slope
        double distanceToSlope = ball.getCenterY() - (slope.getStartY() - slope.getStartX() * Math.tan(slopeAngle));
        ball.setCenterY(slope.getStartY() - slope.getStartX() * Math.tan(slopeAngle) - ball.getRadius());
    }

    // Check for collisions with the bottom of the window
    if (ball.getCenterY() >= HEIGHT - ball.getRadius()) {
        // Ball hits the bottom of the window, so reverse the Y speed to make it bounce
        ballYSpeed = -ballYSpeed;
    }
}

   private boolean checkSlopeCollision() {
    double ballX = ball.getCenterX();
    double ballY = ball.getCenterY();

    double x1 = slope.getStartX();
    double y1 = slope.getStartY();
    double x2 = slope.getEndX();
    double y2 = slope.getEndY();

    // Calculate the expected y position based on the slope and ball's x position
    double expectedY = slopeAngle * (ballX - x1) + y1;

    // Check if the ball's trajectory intersects with the line segment
    boolean intersects = lineIntersectsBallTrajectory(ballX, ballY, ballX + ballXSpeed, ballY + ballYSpeed, x1, y1, x2, y2);

    // If there is an intersection, adjust the ball's position
    if (intersects) {
        ball.setCenterY(expectedY - ball.getRadius());
    }

    return intersects;
}

private boolean lineIntersectsBallTrajectory(double startX, double startY, double endX, double endY, double x1, double y1, double x2, double y2) {
    double denominator = (endY - startY) * (x2 - x1) - (endX - startX) * (y2 - y1);
    if (denominator == 0) {
        return false;
    }

    double t = ((x1 - startX) * (y2 - y1) - (y1 - startY) * (x2 - x1)) / denominator;
    double u = -((startX + t * (endX - startX) - x1) * (startY - y1) - (startY + t * (endY - startY) - y1) * (x2 - x1)) / denominator;

    return t >= 0 && t <= 1 && u >= 0 && u <= 1;
}

    public static void main(String[] args) {
        launch(args);
    }
}
*/