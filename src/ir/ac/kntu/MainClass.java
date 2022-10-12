/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.ac.kntu;

import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.geometry.Bounds;

import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import ir.ac.kntu.Bricks.*;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javafx.scene.layout.Pane;
import javafx.scene.layout.FlowPane;
import java.security.SecureRandom;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.effect.*;
import javafx.scene.text.Text;

/**
 *
 * @author Danial Moradi
 */
public class MainClass extends Application {

    private ArrayList<Brick> bricks;
    private ArrayList<BrickClone> bricksClone = new ArrayList<BrickClone>();
    private ArrayList<ChocolateBrick> prizeRecs = new ArrayList<ChocolateBrick>();
    private boolean labExisting = false;
    private boolean loaded;
    private int loadedLife = 0;
    private int level = 2;
    private int score = 0;
    private BallClone ballClone;
    private static SecureRandom thisShit = new SecureRandom();

    /**w
     * launching the program
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * starts the program
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {

        FlowPane root = new FlowPane();
        root.setOrientation(Orientation.HORIZONTAL);
        Button startButton = new Button("New Game");
        Button loadButton = new Button("Load");
        Button exitButton = new Button("Exit");
        root.setHgap(110);

        root.setMargin(startButton, new Insets(20, 0, 20, 20));
        root.getChildren().add(startButton);
        root.getChildren().add(loadButton);
        root.getChildren().add(exitButton);

        primaryStage.setTitle("Press 's' to save");
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });

        startButton.setOnAction(event -> {
            loaded = false;
            startGame(primaryStage);
        });

        loadButton.setOnAction(event -> {
            loaded = true;
            loadBricks();
            loadInts();
            loadBall();
            startGame(primaryStage);
        });

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * loading bricks array-list from a saved file
     */
    private void loadBricks() {
        bricks = new ArrayList<Brick>(0);
        System.out.println("Loading Bricks...");
        File file = new File("Bricks.bin");
        FileInputStream fileIn = null;
        ObjectInputStream input = null;
        try {
            fileIn = new FileInputStream(file);
            input = new ObjectInputStream(fileIn);
            while (true) {
                try {
                    BrickClone clone = (BrickClone) input.readObject();
                    bricksClone.add(clone);
                } catch (EOFException ex) {
                    break;
                } catch (Exception ex) {
                    System.out.println("problem in file records");
                    System.out.println(ex);
                    break;
                }
            }
        } catch (IOException ex) {
            System.out.println("file not found");
            System.out.println(ex);
        }
        bricks = new ArrayList<Brick>();
        for (BrickClone clone : bricksClone) {
            Brick brick = createBrickByType(clone.getX(), clone.getY(), clone.getType());
            System.out.println(clone.getX() + " " + clone.getY());
            bricks.add(brick);
        }
        System.out.println("Loading Bricks ended");
    }

    /**
     * loading ints array-list from a saved file
     */
    private void loadInts() {
        System.out.println("Loading Ints...");
        File file = new File("Ints.bin");
        FileInputStream fileIn = null;
        ObjectInputStream input = null;
        try {
            fileIn = new FileInputStream(file);
            input = new ObjectInputStream(fileIn);
            while (true) {
                try {
                    score = (Integer) input.readObject();
                    loadedLife = (Integer) input.readObject();

                } catch (EOFException ex) {
                    break;
                } catch (Exception ex) {
                    System.out.println("problem in file records");
                    System.out.println(ex);
                    break;
                }
            }
        } catch (IOException ex) {
            System.out.println("file not found");
            System.out.println(ex);
        }
        System.out.println("Loading Ints ended");
    }

    /**
     * loading ball from a saved file
     */
    private void loadBall() {
        System.out.println("Loading ball...");
        File file = new File("Ball.bin");
        FileInputStream fileIn = null;
        ObjectInputStream input = null;
        try {
            fileIn = new FileInputStream(file);
            input = new ObjectInputStream(fileIn);
            while (true) {
                try {
                    ballClone = (BallClone) input.readObject();
                } catch (EOFException ex) {
                    break;
                } catch (Exception ex) {
                    System.out.println("problem in file records");
                    System.out.println(ex);
                    break;
                }
            }
        } catch (IOException ex) {
            System.out.println("file not found");
            System.out.println(ex);
        }
        System.out.println("Loading Ball ended");
    }

    /**
     * starts the game
     *
     * @param primaryStage
     */
    private void startGame(Stage primaryStage) {
        Pane canvas = new Pane();
        Scene scene = new Scene(canvas, 800, 500, Color.AQUAMARINE);
        canvas.setLayoutX(0);
        canvas.setLayoutY(0);
        Text scoreTxt = new Text();
        scoreTxt.setLayoutX(20);
        scoreTxt.setLayoutY(450);
        scoreTxt.setScaleX(2);
        scoreTxt.setScaleY(2);
        Text lifeTxt = new Text();
        lifeTxt.setLayoutX(750);
        lifeTxt.setLayoutY(450);
        lifeTxt.setScaleX(2);
        lifeTxt.setScaleY(2);
        Bloom bloom = new Bloom();
        Glow glow = new Glow();
        lifeTxt.setEffect(bloom);
        lifeTxt.setEffect(glow);

        makeStage(canvas, scene.getHeight());
        Ball ball = new Ball();
        if (loaded) {
            ball.setLife(loadedLife);
            ball.setxSpeed(ballClone.getySpeed());
            ball.setySpeed(ballClone.getxSpeed());
            ball.getBallCircle().setLayoutX(ballClone.getX());
            ball.getBallCircle().setLayoutY(ballClone.getY());
        } else {
            ball.getBallCircle().relocate(5, 200);
        }
        Stick stick = new Stick(scene);
        Thread stickThread = new Thread(stick);
        stickThread.start();
        Saver saver = new Saver(scene, bricks, ball, score);
        Thread saverThread = new Thread(saver);
        saverThread.start();

        canvas.getChildren().add(ball.getBallCircle());
        canvas.getChildren().add(stick.getRectangle());
        canvas.getChildren().add(scoreTxt);
        canvas.getChildren().add(lifeTxt);

        primaryStage.setScene(scene);
        primaryStage.show();

        Timeline ballTimeline = new Timeline(new KeyFrame(Duration.millis(20),
                new EventHandler<ActionEvent>() {

            /**
             * will handle ball movements
             *
             * @param t
             */
            @Override
            public void handle(ActionEvent t) {
                saver.setScore(score);
                saver.setLife(ball.getLife());
                saver.setBall(ball);
                for (ChocolateBrick chBrick : prizeRecs) {
                    if (chBrick.getChocolateRectangle().getY()
                            + chBrick.getChocolateRectangle().getHeight() >= stick.getRectangle().getY()
                            && chBrick.getChocolateRectangle().getY()
                            + chBrick.getChocolateRectangle().getHeight()
                            <= stick.getRectangle().getY() + stick.getRectangle().getHeight()
                            && chBrick.getChocolateRectangle().getX()
                            + chBrick.getChocolateRectangle().getHeight() >= stick.getRectangle().getX()
                            && chBrick.getChocolateRectangle().getX()
                            - chBrick.getChocolateRectangle().getHeight() <=
                            stick.getRectangle().getX() + stick.getRectangle().getWidth() - 25) {
                        chBrick.getChRecTimeline().pause();
                        canvas.getChildren().remove(chBrick.getChocolateRectangle());
                        score += 3;
                        prizeRecs.remove(chBrick);
                        break;
                    }
                }

                if (!checkWin()) {
                    if (!ball.isTrapped()) {
                        ball.getBallCircle().setLayoutX(ball.getBallCircle().getLayoutX()
                                + ball.getxSpeed());
                        ball.getBallCircle().setLayoutY(ball.getBallCircle().getLayoutY()
                                + ball.getySpeed());
                        scoreTxt.setText("score:" + Integer.toString(score));
                        lifeTxt.setText("life:" + Integer.toString(ball.getLife()));
                    } else {
                        if (ball.getTrapTime() != 0) {
                            ball.setTrapTime(ball.getTrapTime() - 1);
                        } else {
                            ball.setTraped(false);
                        }
                    }
                    Bounds bounds = canvas.getBoundsInLocal();
                    if (!ball.isStar()) {
                        ball.getBallCircle().setOpacity(1);
                        handleWalls(bounds);
                        if (ball.getBallCircle().getLayoutY()
                                >= (bounds.getMaxY() - ball.getBallCircle().getRadius())) {
                            if (ball.getLife() != 0) {
                                ball.setxSpeed(5);
                                ball.setySpeed(5);
                                ball.getBallCircle().relocate(5, 200);
                                ball.setLife(ball.getLife() - 1);
                            } else {
                                Platform.exit();
                            }
                        }
                    } else {
                        handleWalls(bounds);
                        if (ball.getBallCircle().getLayoutY()
                                >= (bounds.getMaxY() - ball.getBallCircle().getRadius())) {
                            ball.setySpeed(-ball.getySpeed());
                        }
                        if (ball.getStarTime() != 0) {
                            ball.setStarTime(ball.getStarTime() - 1);
                        } else {
                            setBallSpeedToNormal();
                            ball.getOpTimeline().pause();
                            ball.setStar(false);
                        }
                    }
                    handleBrickCollusion();
                    handleStickCollusion();
                } else {
                    level++;
                    ball.setxSpeed(5 + level - 1);
                    ball.setySpeed(5 + level - 1);
                    ball.getBallCircle().relocate(5, 200);
                    makeStage(canvas, scene.getHeight());
                    saver.setBricks(bricks);
                }
            }

            /**
             * sets ball speeds back to normal
             */
            private void setBallSpeedToNormal() {
                if (ball.getxSpeed() > 0 && ball.getySpeed() > 0) {
                    ball.setxSpeed(5);
                    ball.setySpeed(5);
                }
                if (ball.getxSpeed() < 0 && ball.getySpeed() > 0) {
                    ball.setxSpeed(-5);
                    ball.setySpeed(5);
                }
                if (ball.getxSpeed() > 0 && ball.getySpeed() < 0) {
                    ball.setxSpeed(5);
                    ball.setySpeed(-5);
                }
                if (ball.getxSpeed() < 0 && ball.getySpeed() < 0) {
                    ball.setxSpeed(-5);
                    ball.setySpeed(-5);
                }
            }

            /**
             * will handle balls collusion with walls
             *
             * @param bounds
             */
            private void handleWalls(Bounds bounds) {
                if (ball.getBallCircle().getLayoutX() <= (bounds.getMinX()
                        + ball.getBallCircle().getRadius())
                        || ball.getBallCircle().getLayoutX()
                        >= (bounds.getMaxX() - ball.getBallCircle().getRadius())) {
                    ball.setxSpeed(-ball.getxSpeed());
                }

                if ((ball.getBallCircle().getLayoutY()
                        <= (bounds.getMinY() + ball.getBallCircle().getRadius()))) {
                    ball.setySpeed(-ball.getySpeed());
                }
            }

            /**
             * will check if bricks is empty
             *
             * @return
             */
            private boolean checkWin() {
                return bricks.isEmpty();
            }

            /**
             * will handle balls collusion with bricks
             *
             */
            private void handleBrickCollusion() {
                for (Brick brick : bricks) {
                    if ((ball.getBallCircle().getLayoutY() - ball.getBallCircle().getRadius()
                            <= brick.getBrickRectangle().getY() + brick.getBrickRectangle().getHeight())
                            && (ball.getBallCircle().getLayoutY() + ball.getBallCircle().getRadius()
                            >= brick.getBrickRectangle().getY())
                            && (ball.getBallCircle().getLayoutX() >= brick.getBrickRectangle().getX())
                            && (ball.getBallCircle().getLayoutX() <= brick.getBrickRectangle().getX()
                            + brick.getBrickRectangle().getWidth())) {
                        if (brick.showBrickType().equals("CB")) {
                            ChocolateBrick chBrick = (ChocolateBrick) brick;
                            prizeRecs.add(chBrick);
                            canvas.getChildren().add(chBrick.getChocolateRectangle());
                        }
                        score += brick.handleBall(ball);
                        ball.setySpeed(-ball.getySpeed());
                        if (brick.showBrickType().equals("LAB")) {
                            labExisting = false;
                        }
                        if (brick.showBrickType().equals("WB")) {
                            if (brick.getHealth() <= 0) {
                                removeBrick(brick);
                            } else {
                                System.out.println(brick.getHealth());
                                brick.setHealth(brick.getHealth() - 1);
                            }
                        } else {
                            removeBrick(brick);
                        }
                        break;
                    }

                    if ((ball.getBallCircle().getLayoutX() + ball.getBallCircle().getRadius()
                            >= brick.getBrickRectangle().getX())
                            && (ball.getBallCircle().getLayoutX() - ball.getBallCircle().getRadius()
                            <= brick.getBrickRectangle().getX() + brick.getBrickRectangle().getWidth())
                            && (ball.getBallCircle().getLayoutY() >= brick.getBrickRectangle().getY())
                            && (ball.getBallCircle().getLayoutY() <= brick.getBrickRectangle().getY()
                            + brick.getBrickRectangle().getHeight())) {
                        if (brick.showBrickType().equals("CB")) {
                            ChocolateBrick chBrick = (ChocolateBrick) brick;
                            prizeRecs.add(chBrick);
                            canvas.getChildren().add(chBrick.getChocolateRectangle());
                        }
                        score += brick.handleBall(ball);
                        ball.setxSpeed(-ball.getxSpeed());
                        if (brick.showBrickType().equals("LAB")) {
                            labExisting = false;
                        }
                        if (brick.showBrickType().equals("WB")) {
                            if (brick.getHealth() <= 0) {
                                removeBrick(brick);
                            } else {
                                brick.setHealth(brick.getHealth() - 1);
                            }
                        } else {
                            removeBrick(brick);
                        }
                        break;
                    }
                }
            }

            /**
             * removes a brick from scene and array-list
             *
             */
            private void removeBrick(Brick brick) {
                canvas.getChildren().remove(brick.getBrickRectangle());
                canvas.getChildren().remove(brick.getText());
                bricks.remove(brick);
            }

            /**
             * will handle balls collusion with the stick
             *
             */
            private void handleStickCollusion() {
                if ((ball.getBallCircle().getLayoutY() + ball.getBallCircle().getRadius()
                        >= stick.getRectangle().getY())
                        && (ball.getBallCircle().getLayoutY()
                        + ball.getBallCircle().getRadius() <= stick.getRectangle().getY()
                        + stick.getRectangle().getHeight())
                        && (ball.getBallCircle().getLayoutX()
                        + ball.getBallCircle().getRadius() >= stick.getRectangle().getX())
                        && (ball.getBallCircle().getLayoutX()
                        - ball.getBallCircle().getRadius() <= stick.getRectangle().getX()
                        + stick.getRectangle().getWidth())) {
                    ball.setySpeed(-ball.getySpeed());

                    if ((ball.getBallCircle().getLayoutX() + ball.getBallCircle().getRadius()
                            >= stick.getRectangle().getX())
                            && (ball.getBallCircle().getLayoutX()
                            + ball.getBallCircle().getRadius() < stick.getRectangle().getX() + 15)) {
                        ball.setxSpeed(ball.getxSpeed() - 7);

                    }
                    if ((ball.getBallCircle().getLayoutX() + ball.getBallCircle().getRadius()
                            >= stick.getRectangle().getX() + 15)
                            && (ball.getBallCircle().getLayoutX()
                            + ball.getBallCircle().getRadius() < stick.getRectangle().getX() + 25)) {
                        ball.setxSpeed(ball.getxSpeed() - 5);

                    }

                    if ((ball.getBallCircle().getLayoutX() + ball.getBallCircle().getRadius()
                            >= stick.getRectangle().getX() + 25)
                            && (ball.getBallCircle().getLayoutX()
                            + ball.getBallCircle().getRadius() <= stick.getRectangle().getX() + 45)) {
                        ball.setxSpeed(ball.getxSpeed() - 3);

                    }

                    if ((ball.getBallCircle().getLayoutX() + ball.getBallCircle().getRadius()
                            >= stick.getRectangle().getX() + 65)
                            && (ball.getBallCircle().getLayoutX() + ball.getBallCircle().getRadius()
                            < stick.getRectangle().getX() + 85)) {
                        ball.setxSpeed(ball.getxSpeed() + 3);

                    }
                    if ((ball.getBallCircle().getLayoutX() + ball.getBallCircle().getRadius()
                            >= stick.getRectangle().getX() + 85)
                            && (ball.getBallCircle().getLayoutX()
                            + ball.getBallCircle().getRadius() < stick.getRectangle().getX() + 95)) {
                        ball.setxSpeed(ball.getxSpeed() + 5);

                    }
                    if ((ball.getBallCircle().getLayoutX() + ball.getBallCircle().getRadius()
                            >= stick.getRectangle().getX() + 95)
                            && (ball.getBallCircle().getLayoutX()
                            + ball.getBallCircle().getRadius() <= stick.getRectangle().getX() + 110)) {
                        ball.setxSpeed(ball.getxSpeed() + 7);

                    }
                }
            }
        }));

        ballTimeline.setCycleCount(Timeline.INDEFINITE);
        ballTimeline.play();

    }

    /**
     * will design stage by taking a Pane and scene hight for chBricks Timeline
     *
     * @param canvas
     * @param chDest
     */
    private void makeStage(Pane canvas, double chDest) {
        if (!loaded) {
            bricks = makeBricks(level, chDest);
        }
        for (Brick brick : bricks) {
            canvas.getChildren().add(brick.getBrickRectangle());
            canvas.getChildren().add(brick.getText());
        }
    }

    /**
     * will make array list of bricks
     *
     * @param row
     * @param chDest
     * @return
     */
    private ArrayList<Brick> makeBricks(int row, double chDest) {
        ArrayList<Brick> makingBricks = new ArrayList<Brick>();
        double x = 100;
        double y = 50;
        for (int j = 0; j < row; j++) {
            for (int i = 0; i < 11; i++) {
                Brick brick = createRandomBrick(x, y, chDest);
                makingBricks.add(brick);
                x += 55;
            }
            x = 100;
            y += 50;
        }
        return makingBricks;
    }

    /**
     * will create a random Brick by getting the x and y of it's rectangle
     *
     * @param x
     * @param y
     * @param chBrickDestination
     * @return
     */
    private Brick createRandomBrick(double x, double y, double chBrickDestination) {
        int choice = thisShit.nextInt(9);
        Brick brick = null;
        BrickEnum type = BrickEnum.getBrickByNum(choice);
        switch (type) {
            case FAST_BURN:
                brick = new FastBurnBrick(x, y);
                break;
            case SLOW_BURN:
                int randHealth;
                randHealth = thisShit.nextInt(3);
                brick = new SlowBurnBrick(x, y);
                brick.setHealth(randHealth);
                break;
            case ACCELERATOR:
                brick = new AcceleratorBrick(x, y);
                break;
            case DEACCELERATOR:
                brick = new DeacceleratorBrick(x, y);
                break;
            case MAGNETIC:
                brick = new MagneticBrick(x, y);
                break;
            case LIFE_ADDING:
                if (!labExisting) {
                    brick = new LifeAddingBrick(x, y);
                    labExisting = true;
                } else {
                    brick = new FastBurnBrick(x, y);
                }
                break;
            case CHOCOLATE:
                brick = new ChocolateBrick(x, y, chBrickDestination);
                break;
            case STAR_BALL:
                brick = new StarBallBrick(x, y);
                break;
            default:
                /*just to be safe*/
                brick = new FastBurnBrick(x, y);
                break;
        }
        return brick;
    }

    /**
     * will create a brick by getting it's type and x and y for it's rectangle
     *
     * @param x
     * @param y
     * @param type
     * @return
     */
    private Brick createBrickByType(double x, double y, String type) {
        Brick brick = null;
        switch (type) {
            case "AB":
                brick = new AcceleratorBrick(x, y);
                break;
            case "CB":
                brick = new ChocolateBrick(x, y);
                break;
            case "DB":
                brick = new DeacceleratorBrick(x, y);
                break;
            case "LB":
                brick = new FastBurnBrick(x, y);
                break;
            case "LAB":
                brick = new LifeAddingBrick(x, y);
                break;
            case "SB":
                brick = new MagneticBrick(x, y);
                break;
            case "WB":
                brick = new SlowBurnBrick(x, y);
                break;
            case "*":
                brick = new StarBallBrick(x, y);
                break;
            default:
                break;
        }
        return brick;
    }

}
