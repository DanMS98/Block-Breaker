/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.ac.kntu;

import javafx.scene.Scene;
import javafx.scene.shape.*;
import javafx.application.Platform;

/**
 *
 * @author Danial Moradi
 */
public class Stick implements Runnable {

    private final Rectangle rectangle;
    private final Scene scene;

    public Stick(Scene scene) {
        rectangle = new Rectangle(scene.getX() + (scene.getWidth() / 2 - 50),
                scene.getY() + scene.getHeight() - 50,
                100, 10);
        this.scene = scene;
    }

    /**
     * returns the fucking rectangle
     * @return 
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * run the stick's Thread
     */
    @Override
    public void run() {

        Platform.runLater(() -> {
            scene.setOnMouseMoved(event -> {
                rectangle.setX(event.getX() - 50);
            });

        });
    }
}
