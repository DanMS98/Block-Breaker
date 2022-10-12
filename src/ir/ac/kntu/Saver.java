/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.ac.kntu;

import ir.ac.kntu.Bricks.Ball;
import ir.ac.kntu.Bricks.BallClone;
import ir.ac.kntu.Bricks.Brick;
import ir.ac.kntu.Bricks.BrickClone;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

/**
 *
 * @author Danial Moradi
 */
public class Saver implements Runnable {

    private Scene scene;
    private Ball ball;
    private BallClone ballClone;
    private ArrayList<Brick> bricks;
    private ArrayList<BrickClone> bricksClone;
    private int score;
    private int life;

    /**
     * will make a saver class
     *
     * @param scene
     * @param bricks
     * @param ball
     * @param score
     */
    public Saver(Scene scene, ArrayList<Brick> bricks, Ball ball, int score) {
        this.scene = scene;
        this.bricks = bricks;
        this.ball = ball;
        this.score = score;
        bricksClone = new ArrayList<BrickClone>();
    }

    /**
     * will get the bricks
     *
     * @return
     */
    public ArrayList<Brick> getBricks() {
        return bricks;
    }

    /**
     * will set the bricks for saving
     *
     * @param bricks
     */
    public void setBricks(ArrayList<Brick> bricks) {
        this.bricks = bricks;
    }

    /**
     * will set the scores for saving
     *
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * will set the life for saving
     *
     * @param life
     */
    public void setLife(int life) {
        this.life = life;
    }

    /**
     * will set the ball for saving
     *
     * @param ball
     */
    public void setBall(Ball ball) {
        this.ball = ball;
    }

    /**
     * will make a .bin file for bricks and saves bricks
     *
     * @return
     */
    private void saveBricks() {
        makeBrickClone();
        System.out.println("saving bricks...");
        File file = new File("Bricks.bin");
        if (file.exists()) {
            file.delete();
            file = new File("Bricks.bin");
        }
        FileOutputStream fileOut = null;
        ObjectOutputStream output = null;
        try {
            fileOut = new FileOutputStream(file);
            output = new ObjectOutputStream(fileOut);
            try {
                for (BrickClone clone : bricksClone) {
                    System.out.println(clone.getX() + " " + clone.getY());
                    output.writeObject(clone);
                }
            } catch (IOException ex) {
                System.out.println("error in writing bricks.bin");
                System.out.println(ex);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            fileOut.close();
            output.close();
        } catch (IOException ex) {
            Logger.getLogger(Saver.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("brick save ended");
    }

    /**
     * will make a .bin file for ints and saves ints
     *
     * @return
     */
    private void saveInts() {
        System.out.println("saving ints...");
        File file = new File("Ints.bin");
        if (file.exists()) {
            file.delete();
            file = new File("Ints.bin");
        }
        FileOutputStream fileOut = null;
        ObjectOutputStream output = null;
        try {
            fileOut = new FileOutputStream(file);
            output = new ObjectOutputStream(fileOut);
            try {
                output.writeObject(Integer.valueOf(score));
                output.writeObject(Integer.valueOf(life));
            } catch (IOException ex) {
                System.out.println("error in writing Ints.bin");
                System.out.println(ex);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            fileOut.close();
            output.close();
        } catch (IOException ex) {
            Logger.getLogger(Saver.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Ints save ended");
    }

    /**
     * will make a .bin file for ball and saves ball
     *
     * @return
     */
    private void saveBall() {
        makeBallClone();
        System.out.println("saving ball...");
        File file = new File("Ball.bin");
        if (file.exists()) {
            file.delete();
            file = new File("Ball.bin");
        }
        FileOutputStream fileOut = null;
        ObjectOutputStream output = null;
        try {
            fileOut = new FileOutputStream(file);
            output = new ObjectOutputStream(fileOut);
            try {
                output.writeObject(ballClone);
            } catch (IOException ex) {
                System.out.println("error in writing Ball.bin");
                System.out.println(ex);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            fileOut.close();
            output.close();
        } catch (IOException ex) {
            Logger.getLogger(Saver.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Ball save ended");
    }

    /**
     * will make a clone of bricks cause bricks cannot be a Serializable
     *
     * @return
     */
    private void makeBrickClone() {
        for (Brick brick : bricks) {
            BrickClone brickClone = new BrickClone(
                    brick.getX(), brick.getY(), brick.showBrickType()
            );
            bricksClone.add(brickClone);
        }
    }

    /**
     * will make a clone of ball cause ball cannot be a Serializable
     *
     * @return
     */
    private void makeBallClone() {
        ballClone = new BallClone(ball.getBallCircle().getLayoutX(),
                ball.getBallCircle().getLayoutY(),
                ball.getxSpeed(), ball.getySpeed());
    }

    /**
     * will run the Thread
     */
    @Override
    public void run() {
        scene.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.S)) {
                saveBricks();
                saveInts();
                saveBall();
            }
        });
    }

}
