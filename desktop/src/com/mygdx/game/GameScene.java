package com.mygdx.game;

import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class GameScene {
    private SpriteBatch batch;
    private Player player1, player2;
    private AdvancedEnemy advancedEnemy;
    private BasicEnemy basicEnemy;
    private BasicEnemy basicEnemy2;
    private List<Enemy> enemies;
    public static Texture backgroundTexture;
    public static Sprite backgroundSprite;
    private int maxEnemies = 100;
    private int basicDefeated = 1;
    private int advancedDefeated = 1;

    public GameScene() {
        batch = new SpriteBatch();

        //viewport = new ExtendViewport(1280,720);

        player1 = new Player(1);
        player2 = new Player(2);

        Controllers.clearListeners();
        Controllers.addListener(new PlayerControllerListener(1));

        // Initialize gamepad input for Player 2
        Controllers.addListener(new PlayerControllerListener(2));

        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        enemies = new ArrayList<Enemy>();
        advancedEnemy = new AdvancedEnemy();
        basicEnemy = new BasicEnemy();


        enemies.add(advancedEnemy);
        enemies.add(basicEnemy);
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        backgroundTexture = new Texture("bg3.png");
        backgroundSprite =new Sprite(backgroundTexture);
    }

    private void nSpawn() {

        if (basicDefeated % 12 == 0 ){
            BasicEnemy newBEnemy = new BasicEnemy();
            enemies.add(newBEnemy);
            basicDefeated = 1;
        }
        if (advancedDefeated % 10 == 0){
            AdvancedEnemy newAEnemy = new AdvancedEnemy();
            enemies.add(newAEnemy);
            advancedDefeated = 1;
        }
    }

    public void update(){
        boolean p1Kill, p2Kill;

        for(Enemy enemy : enemies) {
            p1Kill = enemy.checkCollision(player1);
            p2Kill = enemy.checkCollision(player2);

            if(p1Kill || p2Kill){
                if (enemy instanceof BasicEnemy)
                    basicDefeated++;
                    System.out.println("basicdead: " + basicDefeated);
                if (enemy instanceof AdvancedEnemy)
                    advancedDefeated++;
            }
        }

        nSpawn();
    }

    public void draw() {
        //batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        backgroundSprite.draw(batch);

        player1.draw(batch);
        player2.draw(batch);

        for(Enemy enemy : enemies) {
            enemy.draw(batch);
        }

        batch.end();
    }
}

