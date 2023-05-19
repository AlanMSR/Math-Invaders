package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;

public class Player {
    private Sound shootSound;
    private Texture shipTexture;
    private Rectangle shipRectangle;
    private SpriteBatch batch;
    private PlayerProjectile projectile;

    public Player() {
        //Controllers.addListener((ControllerListener) this);
        if (loadTexture()) {
            System.out.println("Failed to load texture.");
            Gdx.app.exit();
        }

        projectile = new PlayerProjectile();

        shipRectangle = new Rectangle();
        shipRectangle.x = 800 / 2 - 64 / 2;
        shipRectangle.y = 20;
        shipRectangle.width = 64;
        shipRectangle.height = 64;

        shootSound = Gdx.audio.newSound(Gdx.files.internal("shoot.wav"));

        batch = new SpriteBatch();
    }

    public boolean loadTexture() {
        boolean result = false;

        try
        {
            shipTexture = new Texture(Gdx.files.internal("red_ship.png"));

        } catch (Exception e) {
            System.out.println("oh no hermano");
            e.printStackTrace();
            result = true;
        }

        return result;
    }

    public void shot() {

        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            if (!projectile.getVisible()) {
                shootSound.play();
                System.out.println(projectile.getNumber());
                projectile.setVisible(true);
                projectile.shoot(shipRectangle.x, shipRectangle.y);
            }
        }
    }

    public void move() {
        float speed = 200 * Gdx.graphics.getDeltaTime();
        float xAxisValue = 0;
        float yAxisValue = 0;

        // Check if a controller is connected
        if (Controllers.getControllers().size > 0) {
            // Get the first connected controller
            Controller controller = Controllers.getControllers().first();

            // Retrieve the D-pad values for movement
            if (controller.getButton(0))
                xAxisValue = -1;
            else if (controller.getButton(1))
                xAxisValue = 1;

            if (controller.getButton(2))
                yAxisValue = -1;
            else if (controller.getButton(3))
                yAxisValue = 1;
        }

        // Use controller values for movement if available
        if (Math.abs(xAxisValue) > 0.1f) {
            shipRectangle.x += xAxisValue * speed;
            if (shipRectangle.x < 0) {
                shipRectangle.x = 0;
            } else if (shipRectangle.x > Gdx.graphics.getWidth() - shipRectangle.width) {
                shipRectangle.x = Gdx.graphics.getWidth() - shipRectangle.width;
            }
        }
        if (Math.abs(yAxisValue) > 0.1f) {
            shipRectangle.y -= yAxisValue * speed;
            if (shipRectangle.y < 0) {
                shipRectangle.y = 0;
            } else if (shipRectangle.y > Gdx.graphics.getHeight() - shipRectangle.height) {
                shipRectangle.y = Gdx.graphics.getHeight() - shipRectangle.height;
            }
        }

        // Use keyboard input if no controller input is detected
        if (Math.abs(xAxisValue) <= 0.1f && Math.abs(yAxisValue) <= 0.1f) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                shipRectangle.x -= speed;
                if (shipRectangle.x < 0) {
                    shipRectangle.x = 0;
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                shipRectangle.x += speed;
                if (shipRectangle.x > Gdx.graphics.getWidth() - shipRectangle.width) {
                    shipRectangle.x = Gdx.graphics.getWidth() - shipRectangle.width;
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
        }
    }


    public void draw(SpriteBatch batch) {
        batch.draw(shipTexture, shipRectangle.x, shipRectangle.y);
        projectile.draw(batch);

        shot();
        move();
    }
}
