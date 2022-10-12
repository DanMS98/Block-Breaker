/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.ac.kntu.Bricks;

import java.io.Serializable;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 *
 * @author Danial Moradi
 */
public class Ball implements Serializable {

    private double x;
    private double y;
    private double ySpeed;
    private double xSpeed;
    private Circle ballCircle;
    private boolean trapped;
    private boolean star;
    private int trapTime;
    private int starTime;
    private int life;
    private KeyValue opacityVal;
    private KeyFrame opFrame;
    private Timeline opTimeline;

    /**
     * will make a new ball:)
     */
    public Ball() {
        life = 3;
        xSpeed = 5;
        ySpeed = 5;
        ballCircle = new Circle(10, Color.DARKSLATEBLUE);
        trapped = false;
        star = false;
        opacityVal = new KeyValue(ballCircle.opacityProperty(), 0);
        opFrame = new KeyFrame(Duration.millis(80), opacityVal);
        opTimeline = new Timeline();
        opTimeline.setCycleCount(Timeline.INDEFINITE);
        opTimeline.setAutoReverse(true);
        opTimeline.getKeyFrames().addAll(opFrame);
    }

    /**
     * will return remaining time of star mode
     *
     * @return
     */
    public int getStarTime() {
        return starTime;
    }

    /**
     * will set time of star mode
     *
     * @param starTime
     */
    public void setStarTime(int starTime) {
        this.starTime = starTime;
    }

    /**
     * will tell if ball is in the star mode
     *
     * @return
     */
    public boolean isStar() {
        return star;
    }

    /**
     * will return ball shining timeline
     *
     * @return
     */
    public Timeline getOpTimeline() {
        return opTimeline;
    }

    /**
     * will set star mode
     *
     * @param star
     */
    public void setStar(boolean star) {
        this.star = star;
    }

    /**
     * will return the remaining lifes
     *
     * @return
     */
    public int getLife() {
        return life;
    }

    /**
     * will set lifes of ball
     *
     * @param life
     */
    public void setLife(int life) {
        this.life = life;
    }

    /**
     * will return remaining time of trap time
     *
     * @return
     */
    public int getTrapTime() {
        return trapTime;
    }

    /**
     * will set trap time
     *
     * @param trapTime
     */
    public void setTrapTime(int trapTime) {
        this.trapTime = trapTime;
    }

    /**
     * will determine if ball is in SB trap or not
     *
     * @return
     */
    public boolean isTrapped() {
        return trapped;
    }

    /**
     * will set if ball is in SB trap or not
     * @param trapped 
     */
    public void setTraped(boolean trapped) {
        this.trapped = trapped;
    }

    /**
     * return x
     *
     * @return
     */
    public double getX() {
        return x;
    }

    /**
     * sets x
     *
     * @param x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * return y
     *
     * @return
     */
    public double getY() {
        return y;
    }

    /**
     * sets y
     *
     * @param y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * will return ball's vertical speed
     *
     * @return
     */
    public double getySpeed() {
        return ySpeed;
    }

    /**
     * will set ball's vertical speed
     *
     * @return
     */
    public void setySpeed(double ySpeed) {
        this.ySpeed = ySpeed;
    }

    /**
     * will return ball's horizontal speed
     *
     * @return
     */
    public double getxSpeed() {
        return xSpeed;
    }

    /**
     * will set ball's horizontal speed
     *
     * @return
     */
    public void setxSpeed(double xSpeed) {
        this.xSpeed = xSpeed;
    }

    /**
     * will return ball's circle
     *
     * @return
     */
    public Circle getBallCircle() {
        return ballCircle;
    }

    /**
     * will set ball's circle
     *
     * @param ballCircle
     */
    public void setBallCircle(Circle ballCircle) {
        this.ballCircle = ballCircle;
    }

}
