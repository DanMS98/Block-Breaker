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
public class BrickClone implements Serializable {

    private double x;
    private double y;
    private double height;
    private double width;
    private int health;
    private boolean triggered;
    private String type;

    /**
     * will make a clone of a brick by given parameters
     *
     * @param x
     * @param y
     * @param type
     */
    public BrickClone(double x, double y, String type) {
        this.x = x;
        this.y = y;
        this.height = 20;
        this.width = 50;
        this.type = type;
    }

    /**
     * getting type of clone of brick
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * setting type of clone of brick
     *
     * @return
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * will return Height of clone of brick
     *
     * @return double
     */
    public double getHeight() {
        return height;
    }

    /**
     * will set Height of clone of brick
     *
     * @return
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * will return getWidth of clone of brick
     *
     * @return
     */
    public double getWidth() {
        return width;
    }

    /**
     * will set getWidth of clone of brick
     *
     * @return
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * aragif
     *
     * @return
     */
    public boolean isTriggered() {
        return triggered;
    }

    /**
     * aragif
     *
     * @return
     */
    public void setTriggered(boolean triggered) {
        this.triggered = triggered;
    }

    /**
     * aragif
     *
     * @return
     */
    public int getHealth() {
        return health;
    }

    /**
     * aragif
     *
     * @return
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * aragif
     *
     * @return
     */
    public double getX() {
        return x;
    }

    /**
     * aragif
     *
     * @return
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * will return y position
     *
     * @return
     */
    public double getY() {
        return y;
    }

    /**
     * will set Y position
     *
     * @return
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * prints the class
     * @return 
     */
    @Override
    public String toString() {
        return "BrickClone{" + "x=" + x + ", y=" + y
                + ", height=" + height + ", Width=" + width
                + ", health=" + health + ", triggered="
                + triggered + ", type=" + type + '}';
    }
}
