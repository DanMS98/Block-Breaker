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
public class AcceleratorBrick extends Brick {

    public AcceleratorBrick(double x, double y) {
        super(x, y);
        super.getBrickRectangle().setFill(Color.RED);
    }

    @Override
    /**
     * kosherkosherkosher
     */
    public String showBrickType() {
        return "AB";
    }

    @Override
    /**
     * kosherkosherkosher
     */
    public int showBrickId() {
        return 1;
    }

    /**
     * increase the ball's vertical speed
     * @param ball
     * @return 
     */
    @Override
    public int handleBall(Ball ball) {
        if (ball.getySpeed() > 0) {
            ball.setySpeed(ball.getySpeed() + 5);
        }
        if(ball.getySpeed() <= 0){
            ball.setySpeed(ball.getySpeed() - 5);
        }
        return 1;
    }

}
