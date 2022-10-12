/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.ac.kntu.Bricks;

import java.io.Serializable;

/**
 *
 * @author Danial Moradi
 */
public class BallClone implements Serializable {

    private double x;
    private double y;
    private double ySpeed;
    private double xSpeed;

    /**
     * will make a clone of ball by given parameters
     *
     * @param x
     * @param y
     */
    public BallClone(double x, double y) {
        this(x, y, 5, 5);
    }

    /**
     * will make a clone of ball by given parameters
     *
     * @param x
     * @param y
     */
    public BallClone(double x, double y, double ySpeed, double xSpeed) {
        this.x = x;
        this.y = y;
        this.ySpeed = ySpeed;
        this.xSpeed = xSpeed;
    }

    /**
     * will return y speed
     *
     * @return
     */
    public double getySpeed() {
        return ySpeed;
    }

    /**
     * will set vertical speed
     *
     * @param ySpeed
     */
    public void setySpeed(double ySpeed) {
        this.ySpeed = ySpeed;
    }

    /**
     * will return vertical speed
     *
     * @param ySpeed
     */
    public double getxSpeed() {
        return xSpeed;
    }

    /**
     * will set horizontal speed
     *
     * @param ySpeed
     */
    public void setxSpeed(double xSpeed) {
        this.xSpeed = xSpeed;
    }

    /**
     * will return horizontal speed
     *
     * @param ySpeed
     */
    public double getX() {
        return x;
    }

    /**
     * will set horizontal position
     *
     * @param ySpeed
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * will return Vertical position
     *
     * @return
     */
    public double getY() {
        return y;
    }

    /**
     * will set Vertical position
     *
     * @return
     */
    public void setY(double y) {
        this.y = y;
    }
}
