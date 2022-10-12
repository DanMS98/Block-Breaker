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
public class LifeAddingBrick extends Brick {

    public LifeAddingBrick(double x, double y) {
        super(x, y);
        super.getBrickRectangle().setFill(Color.ORANGE);
        super.getText().setFill(Color.WHITE);
    }

    /**
     * returns type of Brick
     *
     * @return String
     */
    @Override
    public String showBrickType() {
        return "LAB";
    }

    /**
     * returns brick's ID
     *
     * @return Int
     */
    @Override
    public int showBrickId() {
        return 0;
    }

    /**
     * handles collusion of ball with a LifeAddingBrick Brick
     *
     * @param ball
     * @return
     */
    @Override
    public int handleBall(Ball ball) {
        ball.setLife(ball.getLife() + 1);
        return 0;
    }

}
