/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.ac.kntu.Bricks;

import javafx.scene.paint.Color;

/**
 *
 * @author Danial Moradi
 */
public class FastBurnBrick extends Brick {

    public FastBurnBrick(double x, double y) {
        super(x, y);
        super.getBrickRectangle().setFill(Color.BURLYWOOD);
    }

    @Override
    /**
     * aragif
     *
     * @return
     */
    public String showBrickType() {
        return "LB";
    }

    @Override
    /**
     * aragif
     *
     * @return
     */
    public int showBrickId() {
        return 3;
    }

    /**
     * handles collusion of ball with a FastBurnBrick Brick
     *
     * @param ball
     * @return
     */
    public int handleBall(Ball ball) {
        return 1;
    }
}
