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
    private int playerNumber; // Player number: 1 or 2
    private Sound shootSound;
    private Texture shipTexture;
    private Rectangle shipRectangle;
    private PlayerProjectile projectile;

    public Player(int playerNumber) {
        this.playerNumber = playerNumber;
        if (loadTexture()) {
            System.out.println("Failed to load texture.");
            Gdx.app.exit();
        }

        projectile = new PlayerProjectile();

        shipRectangle = new Rectangle();
        shipRectangle.x = 800 / 2 - 64 / 2;
        if (playerNumber == 1) {
            shipRectangle.y = 20;
        } else {
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
            shipTexture = new Texture(Gdx.files.internal("red_ship.png"));

        } catch (Exception e) {
            System.out.println("oh no hermano");
            e.printStackTrace();
            result = true;
        }

        return result;
    }

    public void shot() {
        if (playerNumber == 1 && Gdx.input.isKeyPressed(Input.Keys.L)) {
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

    public void move() {
        float speed = 200 * Gdx.graphics.getDeltaTime();
        float xAxisValue = 0;
        float yAxisValue = 0;

        // Check if a controller is connected
        if (Controllers.getControllers().size > 0) {
            Controller controller = Controllers.getControllers().get(playerNumber - 1);
            // Retrieve the axis values for movement
            xAxisValue = controller.getAxis(0);
            yAxisValue = -controller.getAxis(1);

            // Retrieve the D-pad values for movement if axis values are not active
            if (Math.abs(xAxisValue) < 0.2 && Math.abs(yAxisValue) < 0.2) {
                if (controller.getButton(playerNumber == 1 ? 13 : 4)) {
                    xAxisValue = -1;
                } else if (controller.getButton(playerNumber == 1 ? 14 : 5)) {
                    xAxisValue = 1;
                }

                if (controller.getButton(playerNumber == 1 ? 12 : 6)) {
                    yAxisValue = -1;
                } else if (controller.getButton(playerNumber == 1 ? 11 : 7)) {
                    yAxisValue = 1;
                }
            }
        }

        // Use controller values for movement if available
        if (xAxisValue != 0) {
            shipRectangle.x += xAxisValue * speed;
            if (shipRectangle.x < 0) {
                shipRectangle.x = 0;
            } else if (shipRectangle.x > Gdx.graphics.getWidth() - shipRectangle.width) {
                shipRectangle.x = Gdx.graphics.getWidth() - shipRectangle.width;
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
            } else if (playerNumber == 2) {
                // Player 2 keyboard input logic
                if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                    shipRectangle.x -= speed;
                    if (shipRectangle.x < 0) {
                        shipRectangle.x = 0;
                    }
                }
                if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                    shipRectangle.x += speed;
                    if (shipRectangle.x > Gdx.graphics.getWidth() - shipRectangle.width) {
                        shipRectangle.x = Gdx.graphics.getWidth() - shipRectangle.width;
                    }
                }
                if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                    shipRectangle.y += speed;
                    if (shipRectangle.y > Gdx.graphics.getHeight() - shipRectangle.height) {
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
    /*
    public void move() {
        float speed = 200 * Gdx.graphics.getDeltaTime();
        float xAxisValue = 0;
        float yAxisValue = 0;

        // Check if a controller is connected
        if (Controllers.getControllers().size > 0) {
            // Get the first connected controller
            Controller controller = Controllers.getControllers().first();

            // Retrieve the axis values for movement
            xAxisValue = controller.getAxis(0);
            yAxisValue = -controller.getAxis(1);

            // Retrieve the D-pad values for movement if axis values are not active
            if (Math.abs(xAxisValue) < 0.2 && Math.abs(yAxisValue) < 0.2) {
                if (controller.getButton(13))
                    xAxisValue = -1;
                else if (controller.getButton(14))
                    xAxisValue = 1;

                if (controller.getButton(12))
                    yAxisValue = -1;
                else if (controller.getButton(11))
                    yAxisValue = 1;
            }
        }

        // Use controller values for movement if available
        if (xAxisValue != 0) {
            shipRectangle.x += xAxisValue * speed;
            if (shipRectangle.x < 0) {
                shipRectangle.x = 0;
            } else if (shipRectangle.x > Gdx.graphics.getWidth() - shipRectangle.width) {
                shipRectangle.x = Gdx.graphics.getWidth() - shipRectangle.width;
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
*/

    public void draw(SpriteBatch batch) {
        batch.draw(shipTexture, shipRectangle.x, shipRectangle.y);
        projectile.draw(batch);

        shot();
        move();
    }
}
