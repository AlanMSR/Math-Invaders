package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameScene {
    private SpriteBatch batch;
    private SideInterface p1Interface, p2Interface;
    private Player player1, player2;
    private AdvancedEnemy advancedEnemy;
    private BasicEnemy basicEnemy;
    private List<Enemy> enemies;
    public static Texture backgroundTexture;
    public static Sprite backgroundSprite;
    private int maxEnemies = 50;
    private int basicDefeated = 1;
    private int advancedDefeated = 1;

    public GameScene() {
        batch = new SpriteBatch();

        player1 = new Player(1);
        player2 = new Player(2);

        p1Interface = new SideInterface("pacinop.png", player1.getPlayerNumber());
        p2Interface = new SideInterface("laurentp.png", player2.getPlayerNumber());
        p2Interface.setToRight();

        Controllers.clearListeners();
        Controllers.addListener(new PlayerControllerListener(1));

        // Initialize gamepad input for Player 2
        Controllers.addListener(new PlayerControllerListener(2));

        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        enemies = new ArrayList<Enemy>();
        advancedEnemy = new AdvancedEnemy();
        basicEnemy = new BasicEnemy();

        FinalBoss boss = new FinalBoss();

        enemies.add(advancedEnemy);
        enemies.add(basicEnemy);
        enemies.add(boss);
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
            if (enemy instanceof BasicEnemy) {
                p1Kill = enemy.checkCollision(player1);
                p2Kill = enemy.checkCollision(player2);

                if(p1Kill || p2Kill){
                    basicDefeated++;
                    System.out.println("basicdead: " + basicDefeated);
                }
            }
            if (enemy instanceof AdvancedEnemy) {
                p1Kill = enemy.checkCollision(player1);
                p2Kill = enemy.checkCollision(player2);

                if(p1Kill || p2Kill){
                   advancedDefeated++;
                    System.out.println("advanceddead: " + advancedDefeated);
                }
            }
        }

        p1Interface.updateCurrentNumber(player1.getProjectile().getNumber());
        p1Interface.updateScore(player1.getScore());
        p2Interface.updateCurrentNumber(player2.getProjectile().getNumber());
        p2Interface.updateScore(player2.getScore());
        nSpawn();
    }

    public void draw() {
        //batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        backgroundSprite.draw(batch);

        p1Interface.draw(batch);
        p2Interface.draw(batch);

        player1.draw(batch);
        player2.draw(batch);

        for(Enemy enemy : enemies) {
            enemy.draw(batch);
        }

        batch.end();
    }

    private class SideInterface {
        private BitmapFont font, player;
        private SimplePoint fontCoords, playerCoords;
        private Rectangle container, imageContainer;
        final private Texture persona, backgrond;
        final private TextureRegion imageRegion, backgroundRegion;
        private Rectangle currentBullet, previousBullet, nextBullet;
        private Texture numbersTexture;
        private TextureRegion[] bulletOfSets;
        private int score = 0;
        private int playerN = 0;
        private int number = 1;

        public SideInterface(String image_path, int playerN) {
            this.playerN = playerN;
            persona = new Texture(Gdx.files.internal(image_path));
            imageRegion = new TextureRegion(persona);

            if (playerN == 1) {
                backgrond = new Texture(Gdx.files.internal("GUIJ1.png"));
                backgroundRegion = new TextureRegion(backgrond);
            } else {
                backgrond = new Texture(Gdx.files.internal("GUIJ2V2.png"));
                backgroundRegion = new TextureRegion(backgrond);
            }

            container = new Rectangle(0, 0,120, Gdx.graphics.getHeight());

            imageContainer = new Rectangle(container.width - 60 - 20, container.height - 90 - 70, 60, 90);
            System.out.println(imageRegion.getRegionHeight());

            font = new BitmapFont();
            fontCoords = new SimplePoint();
            fontCoords.setLocation(container.width - 60 - 23, container.height - 90 - 105);

            player = new BitmapFont();
            playerCoords = new SimplePoint();
            playerCoords.setLocation(container.width - 90, container.height - 40);

            currentBullet = new Rectangle(container.width - 60 - 10, container.height - 90 - 320, 30, 30);
            numbersTexture = new Texture(Gdx.files.internal("numbers&shi.png"));

            previousBullet = new Rectangle(container.width - 60 - 40, container.height - 90 - 360, 20, 20);
            nextBullet = new Rectangle(container.width - 60 + 30, container.height - 90 - 250, 20, 20);

            bulletOfSets = new TextureRegion[9];
            bulletOfSets[0] = new TextureRegion(numbersTexture, 178, 258, 12, 30);
            bulletOfSets[1] = new TextureRegion(numbersTexture, 215, 258, 12, 30);
            bulletOfSets[2] = new TextureRegion(numbersTexture, 248, 258, 12, 30);
            bulletOfSets[3] = new TextureRegion(numbersTexture, 9, 299, 12, 30);
            bulletOfSets[4] = new TextureRegion(numbersTexture, 43, 299, 12, 30);
            bulletOfSets[5] = new TextureRegion(numbersTexture, 77, 299, 12, 30);
            bulletOfSets[6] = new TextureRegion(numbersTexture, 111, 299, 12, 30);
            bulletOfSets[7] = new TextureRegion(numbersTexture, 146, 299, 12, 30);
            bulletOfSets[8] = new TextureRegion(numbersTexture, 180, 299, 12, 30);
        }

        public void setToRight() {
            container.x = Gdx.graphics.getWidth() - 120;
            this.imageContainer.x = container.x + 60 - 40;
            this.currentBullet.x = container.x + 60 - 23;
            this.currentBullet.y = container.y + 80; //brute force baby
            this.previousBullet.x = container.x + 60 - 50;
            this.previousBullet.y = container.y + 40;
            this.nextBullet.x = container.x + 60 + 20;
            this.nextBullet.y = container.y + 150;
            this.fontCoords.setLocation(container.x + 60 - 30, fontCoords.getY());
            this.playerCoords.setLocation(container.x + 60 - 30, playerCoords.getY());
        }

        public void updateCurrentNumber(int currentNumber) {
            this.number = currentNumber;
        }

        public void updateScore(int score) {
            this.score = score;
        }

        public int checkBulletNumber(int number) {
            if (number > 8)
                number = 0;
            if (number == -1) {
                number = 8;
            }

            return number;
        }

        public void draw(SpriteBatch batch) {
            batch.draw(backgroundRegion, container.x, container.y, container.width, container.height);
            //player.draw(batch, "Player " + playerN, playerCoords.getX(), playerCoords.getY());
            batch.draw(imageRegion, imageContainer.x, imageContainer.y, imageContainer.width, imageContainer.height);
            batch.draw(bulletOfSets[number - 1], currentBullet.x, currentBullet.y, currentBullet.width, currentBullet.height);
            batch.draw(bulletOfSets[checkBulletNumber(number - 2)], previousBullet.x, previousBullet.y, previousBullet.width, previousBullet.height);
            batch.draw(bulletOfSets[checkBulletNumber(number)], nextBullet.x, nextBullet.y, nextBullet.width, nextBullet.height);
            font.draw(batch, "Score: " + score, fontCoords.getX(), fontCoords.getY());
        }
    }

    private class SimplePoint {
        private int x,y;
        public SimplePoint() {

        }
        public void setLocation(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
    }
}
