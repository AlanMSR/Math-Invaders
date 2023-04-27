package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class Player extends ApplicationAdapter {

    private Texture shipTexture;
    private Rectangle shipRectangle;
    private SpriteBatch batch;

    @Override
    public void create() {

        if (loadTexture()) {
            System.out.println("Failed to load texture.");
            Gdx.app.exit();
        }

        shipRectangle = new Rectangle();
        shipRectangle.x = 800 / 2 - 64 / 2;
        shipRectangle.y = 20;
        shipRectangle.width = 64;
        shipRectangle.height = 64;

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

    public void move() {
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) shipRectangle.x -= 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) shipRectangle.x += 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) shipRectangle.y += 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) shipRectangle.y -= 200 * Gdx.graphics.getDeltaTime();

        if(shipRectangle.x < 0)shipRectangle.x = 0;
        if(shipRectangle.x > 800 - 64) shipRectangle.x = 800 - 64;
    }

    @Override
    public void render()
    {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        batch.begin();
        batch.draw(shipTexture, shipRectangle.x, shipRectangle.y);
        batch.end();

        move();
    }
}
