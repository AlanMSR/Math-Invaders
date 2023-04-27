package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class Player extends ApplicationAdapter {

    private Sound shootSound;
    private Texture shipTexture;
    private Rectangle shipRectangle;
    private SpriteBatch batch;
    private PlayerProjectile projectile;

    @Override
    public void create() {

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

    public boolean loadTexture()
    {
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

        if(Gdx.input.isKeyPressed(Input.Keys.A))
            if (!projectile.getVisible())
            {
                shootSound.play();
                projectile.setVisible(true);
                projectile.shoot(shipRectangle.x, shipRectangle.y);
            }

    }

    public void move() {
        float speed = 200 * Gdx.graphics.getDeltaTime();
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

    @Override
    public void render()
    {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        batch.begin();
        batch.draw(shipTexture, shipRectangle.x, shipRectangle.y);
        projectile.draw(batch);
        batch.end();

        move();
        shot();

        //projectile.shoot(shipRectangle.x, shipRectangle.y);
    }


}
