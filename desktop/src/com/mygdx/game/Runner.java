package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.math.Intersector;

import java.util.ArrayList;
import java.util.List;

public class Runner extends ApplicationAdapter {

    public enum GameState {
        MENU, PLAY, QUIT
    }
    public static GameState gameState;

    private Skin skin;
    private Stage stage;
    ////
    // MIERDA DE LUIS ARRIBA
    ////
    private SpriteBatch batch;
    private Player player1, player2;
    private AdvancedEnemy advancedEnemy;
    private List<Enemy> enemies;
    public static Texture backgroundTexture;
    public static Sprite backgroundSprite;
    private MenuState menuState;

    @Override
    public void create() {

        batch = new SpriteBatch();
        menuState = new MenuState();
        Gdx.input.setInputProcessor(menuState.stage);

        player1 = new Player(1);
        player2 = new Player(2);

        Controllers.clearListeners();
        Controllers.addListener(new PlayerControllerListener(1));

        // Initialize gamepad input for Player 2
        Controllers.addListener(new PlayerControllerListener(2));

        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        enemies = new ArrayList<Enemy>();
        advancedEnemy = new AdvancedEnemy();
        enemies.add(advancedEnemy);
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        backgroundTexture = new Texture("bg3.png");
        backgroundSprite = new Sprite(backgroundTexture);

        gameState = GameState.MENU;
    }

    @Override
    public void render() {

        ScreenUtils.clear(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        menuState.stage.act();
        menuState.stage.draw();
        batch.begin();
        //backgroundSprite.draw(batch);

        //player1.draw(batch);
        //player2.draw(batch);

        //advancedEnemy.draw(batch);

        batch.end();

        advancedEnemy.checkCollision(player1.getProjectile());
        advancedEnemy.checkCollision(player2.getProjectile());
        /*
        for(Enemy enemy : enemies) {
            enemy.draw(batch);
        }

        for(Enemy enemy : enemies) {
            enemy.checkCollision(player1.getProjectile());
            enemy.checkCollision(player2.getProjectile());
        }
         */

    }

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }
}
