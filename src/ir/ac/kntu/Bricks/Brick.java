/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.ac.kntu.Bricks;

import java.io.Serializable;
import java.util.Objects;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 *
 * @author Danial Moradi
 */
public abstract class Brick implements PlaceHaver, Serializable {

    private double x;
    private double y;
    private Rectangle brickRectangle;
    private int health;
    private boolean triggered;
    private Text text;

    /**
     * will make a new Brick
     *
     * @param x
     * @param y
     */
    public Brick(double x, double y) {
        this.x = x;
        this.y = y;
        brickRectangle = new Rectangle(x, y, 50, 20);
        text = new Text(x + 20, y + 15, showBrickType());
    }

    /**
     * getting text inside a brick
     *
     * @return
     */
    public Text getText() {
        return text;
    }

    /**
     * getting brick rectangle
     *
     * @return
     */
    public Rectangle getBrickRectangle() {
        return brickRectangle;
    }

    /**
     * setting brick rectangle
     *
     * @return
     */
    public void setBrickRectangle(Rectangle brickRectangle) {
        this.brickRectangle = brickRectangle;
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
     * returns Y position
     *
     * @return
     */
    public double getY() {
        return y;
    }

    /**
     * set Y position
     *
     * @param y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * aragif
     *
     * @return
     */
    public abstract String showBrickType();

    /**
     * aragif
     *
     * @return
     */
    public abstract int showBrickId();

    /**
     * will handle collusion with ball
     *
     * @param ball
     * @return
     */
    public abstract int handleBall(Ball ball);

    /**
     * it's a fricking hashcode
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        hash = 67 * hash + Objects.hashCode(this.brickRectangle);
        hash = 67 * hash + this.health;
        hash = 67 * hash + (this.triggered ? 1 : 0);
        hash = 67 * hash + Objects.hashCode(this.text);
        return hash;
    }

    /**
     * it's a fricking equals
     *
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Brick other = (Brick) obj;
        if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
            return false;
        }
        if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y)) {
            return false;
        }
        if (this.health != other.health) {
            return false;
        }
        if (this.triggered != other.triggered) {
            return false;
        }
        if (!Objects.equals(this.brickRectangle, other.brickRectangle)) {
            return false;
        }
        if (!Objects.equals(this.text, other.text)) {
            return false;
        }
        return true;
    }

    /**
     * it's a fucking tostring methode
     *
     * @return
     */
    @Override
    public String toString() {
        return "Brick{" + "x=" + x
                + ", y=" + y + ", brickRectangle=" + brickRectangle
                + ", health=" + health + ", triggered=" + triggered
                + ", text=" + text + '}';
    }

}
