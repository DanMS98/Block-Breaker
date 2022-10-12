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
public class DeacceleratorBrick extends Brick {

    public DeacceleratorBrick(double x, double y) {
        super(x, y);
        super.getBrickRectangle().setFill(Color.BLUE);
    }

    @Override
    /**
     * aragif
     *
     * @return
     */
    public String showBrickType() {
        return "DB";
    }

    @Override
    /**
     * aragif
     *
     * @return
     */
    public int showBrickId() {
        return 2;
    }

    /**
     * handles collusion of ball with a deaccelerator Brick
     *
     * @param ball
     * @return
     */
    public int handleBall(Ball ball) {
        if (ball.getySpeed() > 0) {
            if (ball.getySpeed() - 5 != 0) {
                ball.setySpeed(ball.getySpeed() - 5);
            }
        }
        if (ball.getySpeed() <= 0) {
            if (ball.getySpeed() + 5 != 0) {
                ball.setySpeed(ball.getySpeed() + 5);
            }
        }
        return 1;
    }
}
