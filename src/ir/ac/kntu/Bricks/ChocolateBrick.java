/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.ac.kntu.Bricks;

import javafx.scene.shape.Rectangle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author Danial Moradi
 */
public class ChocolateBrick extends Brick {

    private Rectangle chocolateRectangle;
    private KeyValue chRecVal;
    private KeyFrame chRecFrame;
    private Timeline chRecTimeline;

    public ChocolateBrick(double x, double y) {
        this(x, y, 600);
    }

    public ChocolateBrick(double x, double y, double dest) {
        super(x, y);
        super.getBrickRectangle().setFill(Color.CHOCOLATE);
        super.getText().setFill(Color.WHITE);
        chocolateRectangle = new Rectangle(x + 20, y + 15, 10, 25);
        chocolateRectangle.setFill(Color.CHOCOLATE);
        chRecVal = new KeyValue(chocolateRectangle.yProperty(), dest);
        chRecFrame = new KeyFrame(Duration.millis(2000), chRecVal);
        chRecTimeline = new Timeline(chRecFrame);
        chRecTimeline.setCycleCount(1);
        chRecTimeline.setAutoReverse(false);
    }

    /**
     * return prize brick time-line
     *
     * @return
     */
    public Timeline getChRecTimeline() {
        return chRecTimeline;
    }

    /**
     * set prize brics time-line
     *
     * @param chRecTimeline
     */
    public void setChRecTimeline(Timeline chRecTimeline) {
        this.chRecTimeline = chRecTimeline;
    }

    /**
     * return prize brick rectangle
     *
     * @return
     */
    public Rectangle getChocolateRectangle() {
        return chocolateRectangle;
    }

    /**
     * sets prize brick rectangle
     *
     */
    public void setChocolateRectangle(Rectangle chocolateRectangle) {
        this.chocolateRectangle = chocolateRectangle;
    }

    /**
     * returns type of Brick
     *
     * @return String
     */
    @Override
    public String showBrickType() {
        return "CB";
    }

    /**
     * returns brick's ID
     *
     * @return Int
     */
    @Override
    public int showBrickId() {
        return -1;
    }

    /**
     * handles the collusion of ball with a chocolate brick
     *
     * @param ball
     * @return
     */
    @Override
    public int handleBall(Ball ball) {
        chRecTimeline.play();
        return 1;
    }

}
