package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Intersector;

public class AdvancedEnemy extends BasicEnemy{
    private float centerX;
    private float centerY;
    private float radius;
    private float angle;
    private float speed;
    private int answer;

    public AdvancedEnemy() {
        super(100, "enemy_prueba.png", null);

        this.speed = 800;
        setCoords(400,600);
        // Variables de prueba/ may change latta idfk
        this.angle = 0;
        this.radius = 50;
        this.centerX = 100;
        this.centerY = 100;
    }

    public void checkAnswer(){

    }

    public void movement() {
        angle += speed * Gdx.graphics.getDeltaTime();
        float x = centerX + radius * MathUtils.cosDeg(angle);
        float y = centerY + radius * MathUtils.sinDeg(angle);
        setCoords(x, y);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(sprite, entityCoords.x, entityCoords.y);
        movement();
        //checkCollision();
    }
}
