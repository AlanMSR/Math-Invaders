package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import java.util.Random;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

public class AdvancedEnemy extends BasicEnemy {
    private float centerX;
    private float centerY;
    private float radius;
    private float angle;
    private float speed;
    private int answer;
    private boolean invincibility = false;
    public boolean p1BShot = false;
    public boolean p2BShot = false;
    private int p1Damage;
    private int p2Damage;
    private TextureRegion[] enemiesSprites;
    private Texture prueba;
    protected boolean shieldActive = true;
    protected int shieldHealth = 10;

    public AdvancedEnemy() {
        super(2,"enemy_ships.png",null);
        this.speed = 30;
        setCoords(400,900);
        entityCoords.width = 32;
        entityCoords.height = 45;

        this.angle = 0;
        this.radius = 70;
        this.centerX = 300;
        this.centerY = 500;
        this.answer = 2;

        prueba = new Texture("huever.png");
        this.enemiesSprites = new TextureRegion[16];
        loadSprites();
    }

    public AdvancedEnemy(int healthPoints,String sprite, Sound sound){
        super(healthPoints, sprite, sound);
    }

    public void updateAnswer() {
        Random random = new Random();
        healthPoints = random.nextInt(2,17);
        answer = healthPoints;
    }

    public boolean checkAnswer(Player player) {
        PlayerProjectile bala = player.getProjectile();
        if (bala.getId() == 1) {
            this.p1BShot = true;
            this.p1Damage = bala.getNumber();
        }
        if (bala.getId() == 2) {
            this.p2BShot = true;
            this.p2Damage = bala.getNumber();
        }

        if (p1BShot && p2BShot) {

            int totalDamage = p1Damage + p2Damage;

            if (totalDamage == answer) {
                healthPoints -= totalDamage;
                System.out.println("Enemy killed");
                updateAnswer();
                p1BShot = false;
                p2BShot = false;
                p1Damage = 0;
                p2Damage = 0;
                player.setScore(3);
                reposition();
                shieldActive = true;
                shieldHealth = 10;
                return false;
            } else {
                invincibility = true;
            }
        }

        System.out.println("Vida: " + healthPoints);
        return false;
    }

    public void loadSprites() {

        enemiesSprites[0] = new TextureRegion(sprite, 40, 0,  122, 270); //numero 2
        enemiesSprites[1] = new TextureRegion(sprite, 224, 0, 122, 270);
        enemiesSprites[2] = new TextureRegion(sprite, 414, 0, 122, 270);
        enemiesSprites[3] = new TextureRegion(sprite, 606, 0, 122, 270); // numero 5
        enemiesSprites[4] = new TextureRegion(sprite, 32, 287, 122, 270); //6
        enemiesSprites[5] = new TextureRegion(sprite, 222, 287, 122, 270); //7
        enemiesSprites[6] = new TextureRegion(sprite, 402, 287, 122, 270); //8
        enemiesSprites[7] = new TextureRegion(sprite, 604, 287, 122, 270); // 9
        enemiesSprites[8] = new TextureRegion(sprite, 32, 575, 122, 270); //10
        enemiesSprites[9] = new TextureRegion(sprite, 222, 575, 122, 270); // 11
        enemiesSprites[10] = new TextureRegion(sprite, 402, 575, 122, 270); // 12
        enemiesSprites[11] = new TextureRegion(sprite, 604, 575, 122, 270); //13
        enemiesSprites[12] = new TextureRegion(sprite, 32, 863, 122, 270); //14
        enemiesSprites[13] = new TextureRegion(sprite, 222, 863, 122, 270); //15
        enemiesSprites[14] = new TextureRegion(sprite, 402, 863, 122, 270); //16
        enemiesSprites[15] = new TextureRegion(sprite, 604, 863, 122, 270); //17
    }

    public void movement() {
        Random random = new Random();
        angle += speed * Gdx.graphics.getDeltaTime();
        float x = centerX + radius * MathUtils.cosDeg(angle);
        float y = centerY + radius * MathUtils.sinDeg(angle);
        float downOffset = speed * Gdx.graphics.getDeltaTime(); // Valor de desplazamiento hacia abajo
        setCoords(x, y - downOffset); // Restamos el desplazamiento hacia abajo a la coordenada y
        centerY -= downOffset; // Movemos también el centro del círculo hacia abajo

        if(entityCoords.y <= 0){
            reposition();
            healthPoints = random.nextInt(2,17);
            answer = healthPoints;
            this.invincibility = false;
            //changeText
        }
    }

    public void reposition() {
        Random r = new Random();
        int minRange = 130;
        int maxRange = Gdx.graphics.getWidth() - 175; // 120 + 42
        int heightCooldown = r.nextInt(80);
        centerX = r.nextInt(minRange, maxRange);
        centerY = Gdx.graphics.getHeight() + heightCooldown;
        setCoords(r.nextInt(minRange, maxRange),Gdx.graphics.getHeight() + heightCooldown);
        //System.out.println("donde estas? " + entityCoords);
    }

    public boolean checkCollision(Player player) {
        PlayerProjectile bala = player.getProjectile();
        if(shieldActive) {
            breakShield(player);
        }

        if (!shieldActive) {
            if (!invincibility) {
                if (bala.getProjectile().overlaps(this.entityCoords)) {
                    bala.setVisible(false);
                    bala.setFired(false);
                    bala.setCoords(0, Gdx.graphics.getHeight() + 10);

                    return checkAnswer(player);
                    //return true;
                }
            }
        }
        return false;
    }

    public void breakShield(Player player) {
        PlayerProjectile bala = player.getProjectile();
        if (bala.getProjectile().overlaps(this.entityCoords)) {
            bala.setVisible(false);
            bala.setFired(false);
            bala.setCoords(0, Gdx.graphics.getHeight() + 10);
            shieldHealth -= 1;
            if (shieldHealth <= 0) {
                deactivateShield();
            }
        }
    }

    public void deactivateShield(){
        shieldActive = false;
    }

    public void draw(SpriteBatch batch) {
        if (shieldActive)
            batch.setColor(Color.BLUE);
        batch.draw(enemiesSprites[answer - 2], entityCoords.x, entityCoords.y, entityCoords.width, entityCoords.height);
        batch.setColor(Color.WHITE);
        //batch.draw(prueba, entityCoords.x, entityCoords.y, entityCoords.width, entityCoords.height);
        movement();
        //checkCollision();
    }
}
