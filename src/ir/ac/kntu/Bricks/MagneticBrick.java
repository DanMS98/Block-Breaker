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
public class MagneticBrick extends Brick {

    public MagneticBrick(double x, double y) {
        super(x, y);
        super.getBrickRectangle().setFill(Color.GRAY);
    }

    @Override
    /**
     * aragif
     *
     * @return
     */
    public String showBrickType() {
        return "SB";
    }

    @Override
    /**
     * aragif
     *
     * @return
     */
    public int showBrickId() {
        return 5;
    }

    /**
     * handles collusion of ball with a MagneticBrick Brick
     *
     * @param ball
     * @return
     */
    public int handleBall(Ball ball) {
        ball.setTraped(true);
        ball.setTrapTime(70);
        return 2;
    }

}
