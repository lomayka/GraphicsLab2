package sample;

import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250);
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(new Double[]{
            145.0,100.0,
            135.0,10.0,
            60.0,25.0,
            70.0,120.0,
            135.0,110.0,
            85.0,170.0,
            120.0,240.0,
            185.0,205.0,
            145.0,110.0,
            210.0,150.0,
            260.0,70.0,
            160.0,17.0
        });
        Polyline pol = new Polyline();

        pol.getPoints().addAll(new Double[]{
            100.0,75.0,
            140.0,175.0,
            200.0,90.0,
            100.0,75.0
        });
        
        polygon.setFill(Color.rgb(0,255,0));
        pol.setStroke(Color.DARKMAGENTA);
        pol.setStrokeWidth(6);
        scene.setFill(Color.RED);
        root.getChildren().addAll(polygon, pol);


        primaryStage.setScene(scene);
        primaryStage.show();
    }
}