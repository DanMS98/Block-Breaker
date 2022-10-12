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
public class StarBallBrick extends Brick {

    public StarBallBrick(double x, double y) {
        super(x, y);
        super.getBrickRectangle().setFill(Color.DEEPPINK);
        super.getText().setFill(Color.WHITE);
    }

    /**
     * returns type of Brick
     *
     * @return String
     */
    @Override
    public String showBrickType() {
        return "*";
    }

    /**
     * returns brick's ID
     *
     * @return Int
     */
    @Override
    public int showBrickId() {
        return -2;
    }

    /**
     * handles collusion of ball with a StarBallBrick Brick
     *
     * @param ball
     * @return
     */
    @Override
    public int handleBall(Ball ball) {
        ball.setxSpeed(20);
        ball.setySpeed(20);
        ball.setStar(true);
        ball.setStarTime(70);
        ball.getOpTimeline().play();
        return 1;
    }

}
