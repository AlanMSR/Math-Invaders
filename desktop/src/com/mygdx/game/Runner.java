package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.controllers.Controllers;

public class Runner extends ApplicationAdapter {
    private SpriteBatch batch;
    private Player player1;

    @Override
    public void create() {
        player1 = new Player();
        batch = new SpriteBatch();

        Controllers.clearListeners();
        Controllers.addListener(new PlayerControllerListener(1));

        // Initialize gamepad input for Player 2
        Controllers.addListener(new PlayerControllerListener(2));
    }

    @Override
    public void render() {
        ScreenUtils.clear(1, 1, 1, 1);
        batch.begin();
        player1.draw(batch);
        batch.end();
    }
}