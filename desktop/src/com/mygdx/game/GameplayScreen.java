package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameplayScreen extends ScreenAdapter {

    private GameScene gameScene;

    @Override
    public void show() {
        gameScene = new GameScene();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.2f, 0.6f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameScene.update();
        gameScene.draw();
    }

    @Override
    public void resize(int width, int height) {

    }
}