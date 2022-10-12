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
public class SlowBurnBrick extends Brick {

    public SlowBurnBrick(double x, double y) {
        super(x, y);
        super.getBrickRectangle().setFill(Color.BLACK);
        super.getText().setFill(Color.WHITE);
    }

    @Override
    /**
     * aragif
     *
     * @return
     */
    public String showBrickType() {
        return "WB";
    }

    @Override
    /**
     * aragif
     *
     * @return
     */
    public int showBrickId() {
        return 6;
    }
    
    /**
     * handles collusion of ball with a SlowBurnBrick Brick
     *
     * @param ball
     * @return
     */
    public int handleBall(Ball ball) {
        this.setHealth(this.getHealth()-1);
        return 1;
    }

}
