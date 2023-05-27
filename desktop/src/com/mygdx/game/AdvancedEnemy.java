package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import java.util.Random;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Intersector;

import java.util.Random;

public class AdvancedEnemy extends BasicEnemy{
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

    public AdvancedEnemy() {
        super(2, "enemy_prueba.png", null);
        this.speed = 30;
        setCoords(400,900);
        // Variables de prueba/ may change latta idfk
        this.angle = 0;
        this.radius = 70;
        this.centerX = 300;
        this.centerY = 500;

        //
        this.answer = 2;
    }

    public void checkAnswer(Player player) {
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
            if(totalDamage == answer) {
                healthPoints -= totalDamage;
                System.out.println("Enemy killed");
                Random random = new Random();
                int x = random.nextInt(Gdx.graphics.getWidth());
                int y = Gdx.graphics.getHeight() + 10;
                //entityCoords.x = 800;
                this.centerX = x;
                this.setCoords(centerX, y);
                System.out.println(entityCoords);

                healthPoints = random.nextInt(9) + 1;
                answer = healthPoints;
                p1BShot = false;
                p2BShot = false;
                p1Damage = 0;
                p2Damage = 0;
                player.setScore(3);
            }
        }
        System.out.println("Vida: " + healthPoints);
    }

    public void movement() {
        angle += speed * Gdx.graphics.getDeltaTime();
        float x = centerX + radius * MathUtils.cosDeg(angle);
        float y = centerY + radius * MathUtils.sinDeg(angle);
        float downOffset = speed * Gdx.graphics.getDeltaTime(); // Valor de desplazamiento hacia abajo
        setCoords(x, y - downOffset); // Restamos el desplazamiento hacia abajo a la coordenada y
        centerY -= downOffset; // Movemos también el centro del círculo hacia abajo
    }

    public boolean checkCollision(Player player) {
        PlayerProjectile bala = player.getProjectile();
        if (!invincibility) {
            if (bala.getProjectile().overlaps(this.entityCoords)) {
                System.out.println("ITS PUGGERING");
                bala.setVisible(false);
                bala.setFired(false);
                bala.setCoords(0, Gdx.graphics.getHeight() + 10);

                checkAnswer(player);
                return true;
            }
        }
        return false;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(sprite, entityCoords.x, entityCoords.y);
        movement();
        //checkCollision();
    }
}
