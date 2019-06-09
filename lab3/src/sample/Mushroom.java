package sample;


import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Mushroom extends Application {

    public static void main(String args[]) {
        launch(args);
    }

    private void setProps(Ellipse el, Group root){
        el.setStroke(Color.BLACK);
        el.setFill(Color.WHITESMOKE);
        el.setStrokeWidth(1);
        root.getChildren().add(el);
    }

    private void setDot(double x, double y, Group root){
        var el = new Ellipse(x, y, 5, 5);
        setProps(el, root);
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 1200, 600);

        //back
        var back = new Ellipse(414, 414, 200, 100);
        back.setFill(Color.GREEN);
        back.setStroke(Color.BLACK);
        back.setStrokeWidth(1);
        root.getChildren().add(back);
        //foot big

        var lowFoot1 = new MoveTo(353, 280);
        var lowFoot2 = new LineTo(353, 435);
        var lowFoot3 = new QuadCurveTo( 358, 447, 371, 447);
        var lowFoot4 = new LineTo(392, 446);
        var lowFoot5 = new LineTo(406, 433);
        var lowFoot6 = new LineTo(395, 309);
        var footBigLow = new Path();
        footBigLow.setStrokeWidth(2);
        footBigLow.setStroke(Color.BLACK);
        footBigLow.setFill(Color.WHITESMOKE);
        footBigLow.getElements().addAll(lowFoot1, lowFoot2, lowFoot3, lowFoot4, lowFoot5, lowFoot6 );
        root.getChildren().add(footBigLow);

        var highFoot1 = new MoveTo(357, 240);
        var highFoot2 = new LineTo(348, 276);
        var highFoot3 = new LineTo(336, 296);
        var highFoot4 = new QuadCurveTo(352, 293, 357, 303);
        var highFoot5 = new QuadCurveTo(372, 296, 378, 310);
        var highFoot6 = new QuadCurveTo(388, 303, 396, 310);
        var highFoot7 = new LineTo(415, 290);
        var highFoot8 = new QuadCurveTo(395, 264, 398, 243);
        var footBigHigh = new Path();
        footBigHigh.setStrokeWidth(2);
        footBigHigh.setStroke(Color.BLACK);
        footBigHigh.setFill(Color.WHITESMOKE);
        footBigHigh.getElements().addAll(highFoot1, highFoot2, highFoot3, highFoot4, highFoot5, highFoot6, highFoot7,
                highFoot8);
        root.getChildren().add(footBigHigh);

        //head big

        var hBig1 = new MoveTo(274, 190);
        var hBig2 = new LineTo(347, 147);
        var hBig3 = new QuadCurveTo(383, 129, 426, 144);
        var hBig4 = new LineTo(466, 173);
        var hBig5 = new QuadCurveTo(510, 178, 492, 207);
        var hBig6 = new QuadCurveTo(377, 272, 272, 207);
        var hBig7 = new LineTo(274, 190);
        var hBig = new Path();
        hBig.setStrokeWidth(2);
        hBig.setStroke(Color.BLACK);
        hBig.setFill(Color.RED);
        hBig.getElements().addAll(hBig1, hBig2, hBig3, hBig4, hBig5, hBig6, hBig7);
        root.getChildren().add(hBig);

        //dots for big

        var dot1 = new Ellipse(300, 200, 20, 10);
        var dot2 = new Ellipse(340, 200, 25, 15);
        var dot3 = new Ellipse(330, 180, 10, 10);
        var dot4 = new Ellipse(403, 206, 25, 8);
        var dot5 = new Ellipse(430, 189, 20, 10);
        var dot6 = new Ellipse(456, 183, 20, 10);
        var dot7 = new Ellipse(351, 176, 15, 8);
        var dot8 = new Ellipse(401, 165, 15, 8);
        var dot9 = new Ellipse(380, 193, 12, 8);

        setProps(dot1, root);
        setProps(dot2, root);
        setProps(dot3, root);
        setProps(dot4, root);
        setProps(dot5, root);
        setProps(dot6, root);
        setProps(dot7, root);
        setProps(dot8, root);
        setProps(dot9, root);

        //small foot
        var footSLow = new Ellipse(478, 433, 30, 40);
        setProps(footSLow, root);

        var sLow1 = new MoveTo(454, 410);
        var sLow2 = new LineTo(465, 380);
        var sLow3 = new LineTo(499, 370);
        var sLow4 = new LineTo(503, 410);
        var sLow = new Path();
        sLow.setStrokeWidth(1);
        sLow.setStroke(Color.BLACK);
        sLow.setFill(Color.WHITESMOKE);
        sLow.getElements().addAll(sLow1, sLow2, sLow3, sLow4);
        root.getChildren().add(sLow);

        //head small
        var headSmall = new Ellipse(475, 343, 40, 40);
        headSmall.setFill(Color.RED);
        headSmall.setStroke(Color.BLACK);
        headSmall.setStrokeWidth(3);
        root.getChildren().add(headSmall);

        setDot(450, 353, root);
        setDot(466, 333, root);
        setDot(474, 360, root);
        setDot(490, 343, root);
        setDot(496, 360, root);
        setDot(462, 327, root);
        setDot(481, 326, root);


        // Animation
        int cycleCount = 2;
        int time = 2000;

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(time), root);
        scaleTransition.setToX(2);
        scaleTransition.setToY(2);
        scaleTransition.setAutoReverse(true);

        RotateTransition rotateTransition = new RotateTransition(Duration.millis(time), root);
        rotateTransition.setByAngle(360f);
        rotateTransition.setCycleCount(cycleCount);
        rotateTransition.setAutoReverse(true);

        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(time), root);
        translateTransition.setFromX(150);
        translateTransition.setToX(50);
        translateTransition.setCycleCount(cycleCount + 1);
        translateTransition.setAutoReverse(true);

        TranslateTransition translateTransition2 = new TranslateTransition(Duration.millis(time), root);
        translateTransition2.setFromX(50);
        translateTransition2.setToX(150);
        translateTransition2.setCycleCount(cycleCount + 1);
        translateTransition2.setAutoReverse(true);

        ScaleTransition scaleTransition2 = new ScaleTransition(Duration.millis(time), root);
        scaleTransition2.setToX(0.1);
        scaleTransition2.setToY(0.1);
        scaleTransition2.setCycleCount(cycleCount);
        scaleTransition2.setAutoReverse(true);

        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(
                rotateTransition,
                scaleTransition,
                scaleTransition2,
                translateTransition
        );
        parallelTransition.setCycleCount(Timeline.INDEFINITE);
        parallelTransition.play();
//        // End of animation

        primaryStage.setResizable(false);
        primaryStage.setTitle("Lab 3");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
