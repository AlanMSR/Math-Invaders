package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;

public class Player extends Entity {
    private final int playerNumber; // Player number: 1 or 2
    private final Sound shootSound;
    private Texture shipTexture;
    private Rectangle shipRectangle;
    private PlayerProjectile projectile;
    Controller controller;
    private boolean leftShoulderPressed = false;
    private boolean rightShoulderPressed = false;
    //private boolean hasShot = false;
    private int score = 0;

    public Player(int playerNumber) {

        super();
        this.playerNumber = playerNumber;
        if (loadTexture()) {
            System.out.println("Failed to load texture.");
            Gdx.app.exit();
        }

        shipRectangle = new Rectangle();
        shipRectangle.x = 800 / 2 - 64 / 2;
        shipRectangle.width = 45;
        shipRectangle.height = 45;
        if (playerNumber == 1) {
            projectile = new PlayerProjectile(1);
            shipRectangle.y = 20;
        } else {
            projectile = new PlayerProjectile(2);
            shipRectangle.y = 100;
        }
        shipRectangle.width = 64;
        shipRectangle.height = 64;

        shootSound = Gdx.audio.newSound(Gdx.files.internal("shoot.wav"));
    }

    public boolean loadTexture() {
        boolean result = false;

        try
        {
            if (playerNumber == 1) {
                //shipTexture = new Texture(Gdx.files.internal("blue_ship.png"));
                shipTexture = new Texture(Gdx.files.internal("sfs1.png"));
            } else {
                shipTexture = new Texture(Gdx.files.internal("sfs2.png"));
            }
        } catch (Exception e) {
            System.out.println("oh no hermano");
            e.printStackTrace();
            result = true;
        }

        return result;
    }

    public void shot() {
        if (Controllers.getControllers().size >= playerNumber) {
            controller = Controllers.getControllers().get(playerNumber - 1);
            if (controller.getButton(0) && (playerNumber == 1 || playerNumber == 2)) {
                if (!projectile.getVisible()) {
                    shootSound.play();
                    projectile.setVisible(true);
                    projectile.shoot(shipRectangle.x, shipRectangle.y);
                }
            }

        } else {
            if (playerNumber == 1 && (Gdx.input.isKeyPressed(Input.Keys.L))) {
                // Player 1 shoot logic
                if (!projectile.getVisible()) {
                    shootSound.play();
                    projectile.setVisible(true);
                    projectile.shoot(shipRectangle.x, shipRectangle.y);
                }
            } else if (playerNumber == 2 && Gdx.input.isKeyPressed(Input.Keys.G)) {
                // Player 2 shoot logic
                if (!projectile.getVisible()) {
                    shootSound.play();
                    projectile.setVisible(true);
                    projectile.shoot(shipRectangle.x, shipRectangle.y);
                }
            }
        }

        changeProjectile();
    }

    public void movement() {
        float speed = 300 * Gdx.graphics.getDeltaTime();
        float xAxisValue = 0;
        float yAxisValue = 0;

        // Check if the respective player's controller is connected
        if (Controllers.getControllers().size >= playerNumber) {

            controller = Controllers.getControllers().get(playerNumber - 1);
            // Retrieve the axis values for movement
            xAxisValue = controller.getAxis(0);
            yAxisValue = -controller.getAxis(1);

            // Retrieve the D-pad values for movement if axis values are not active
            if (Math.abs(xAxisValue) < 0.2 && Math.abs(yAxisValue) < 0.2) {
                if (controller.getButton(13)) {
                    xAxisValue = -1;
                } else if (controller.getButton(14)) {
                    xAxisValue = 1;
                }

                if (controller.getButton(12)) {
                    yAxisValue = -1;
                } else if (controller.getButton(11)) {
                    yAxisValue = 1;
                }
            }

        }

        // Use controller values for movement if available
        if (xAxisValue != 0) {
            shipRectangle.x += xAxisValue * speed;
            if (shipRectangle.x < 120) {
                shipRectangle.x = 120;
            } else if (shipRectangle.x > Gdx.graphics.getWidth() - shipRectangle.width - 120) {
                shipRectangle.x = Gdx.graphics.getWidth() - shipRectangle.width - 120;
            }
        }
        if (yAxisValue != 0) {
            shipRectangle.y += yAxisValue * speed;
            if (shipRectangle.y < 0) {
                shipRectangle.y = 0;
            } else if (shipRectangle.y > Gdx.graphics.getHeight() - shipRectangle.height) {
                shipRectangle.y = Gdx.graphics.getHeight() - shipRectangle.height;
            }
        }

        // Use keyboard input if no controller input is detected or active
        if (xAxisValue == 0 && yAxisValue == 0) {
            if (playerNumber == 1) {
                // Player 1 keyboard input logic
                if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                    shipRectangle.x -= speed;
                    if (shipRectangle.x < 0) {
                        shipRectangle.x = 0;
                    }
                }
                if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                    shipRectangle.x += speed;
                    if (shipRectangle.x > Gdx.graphics.getWidth() - shipRectangle.width - 120) {
                        shipRectangle.x = Gdx.graphics.getWidth() - shipRectangle.width - 120;
                    }
                }
                if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                    shipRectangle.y += speed;
                    if (shipRectangle.y > Gdx.graphics.getHeight() - shipRectangle.height) {
                        shipRectangle.y = Gdx.graphics.getHeight() - shipRectangle.height;
                    }
                }
                if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                    shipRectangle.y -= speed;
                    if (shipRectangle.y < 0) {
                        shipRectangle.y = 0;
                    }
                }
            } else if (playerNumber == 2) {
                // Player 2 keyboard input logic
                if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                    shipRectangle.x -= speed;
                    if (shipRectangle.x < 120) {
                        shipRectangle.x = 120;
                    }
                }
                if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                    shipRectangle.x += speed;
                    if (shipRectangle.x > Gdx.graphics.getWidth() - shipRectangle.width - 120) {
                        shipRectangle.x = Gdx.graphics.getWidth() - shipRectangle.width - 120;
                    }
                }
                if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                    shipRectangle.y += speed;
                    if (shipRectangle.y > Gdx.graphics.getHeight() - shipRectangle.height - 120) {
                        shipRectangle.y = Gdx.graphics.getHeight() - shipRectangle.height;
                    }
                }
                if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                    shipRectangle.y -= speed;
                    if (shipRectangle.y < 0) {
                        shipRectangle.y = 0;
                    }
                }
            }
        }
    }

    public void changeProjectile() {
        if (Controllers.getControllers().size >= playerNumber) {
            controller = Controllers.getControllers().get(playerNumber - 1);
            boolean currentLeftShoulderPressed = controller.getButton(9);  // L shoulder button
            boolean currentRightShoulderPressed = controller.getButton(10); // R shoulder button


            if (currentLeftShoulderPressed && !leftShoulderPressed) {
                projectile.substractNumber();
            }
            if (currentRightShoulderPressed && !rightShoulderPressed) {
                projectile.addNumber();
            }

            leftShoulderPressed = currentLeftShoulderPressed;
            rightShoulderPressed = currentRightShoulderPressed;
        }

    }

    public void update() {
        shot();
        movement();
    }

    public void draw(SpriteBatch batch) {
        update();

        batch.draw(shipTexture, shipRectangle.x, shipRectangle.y, shipRectangle.width, shipRectangle.height);
        projectile.draw(batch);
    }

    public int getPlayerNumber(){
        return playerNumber;
    }

    public PlayerProjectile getProjectile() {
        return this.projectile;
    }

    public void setScore(int score){
        this.score += score;
    }

    public int getScore() {
        return score;
    }
}
