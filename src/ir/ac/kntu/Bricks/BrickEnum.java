/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.ac.kntu.Bricks;

/**
 *
 * @author Danial Moradi
 */
public enum BrickEnum {
    FAST_BURN, SLOW_BURN, ACCELERATOR, DEACCELERATOR,
    MAGICAL, MAGNETIC, LIFE_ADDING, CHOCOLATE, STAR_BALL, UNDEFINED;

    public static BrickEnum getBrickByNum(int choice) {
        BrickEnum[] brEnum = BrickEnum.values();
        if (choice >= 0 && choice <= brEnum.length) {
            return brEnum[choice];
        } else {
            return BrickEnum.UNDEFINED;
        }
    }
}
