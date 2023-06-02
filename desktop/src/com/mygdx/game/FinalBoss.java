package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FinalBoss extends AdvancedEnemy{
    private boolean introComplete;
    private float targetY;
    private float introSpeed;
    public FinalBoss() {
        super(10,"boss.png" ,null);
        setCoords(122, Gdx.graphics.getHeight() + 80);
        introComplete = false;
        targetY = 360;
        introSpeed = 50;
        entityCoords.width = Gdx.graphics.getWidth() - 240; // 240 por las dos interfaces(120 c/u)
        entityCoords.height = 250;
    }

    public void introMovement() {
        if (!introComplete) {
            float currentY = entityCoords.y;
            if (currentY > targetY) {
                float newY = currentY - introSpeed * Gdx.graphics.getDeltaTime();
                if (newY <= targetY) {
                    newY = targetY;
                    introComplete = true;
                }
                entityCoords.y = newY;
            } else {
                introComplete = true;
            }
        }
    }


    public boolean checkCollision(Player player) {
        PlayerProjectile bala = player.getProjectile();
        if(shieldActive) {
            breakShield(player);
        }
        if (!shieldActive) {
            if (bala.getProjectile().overlaps(this.entityCoords)) {
                bala.setVisible(false);
                bala.setFired(false);
                bala.setCoords(0, Gdx.graphics.getHeight() + 10);
                player.setScore(15);
                healthPoints -= bala.getNumber();
                if (healthPoints <= 0) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void reposition() {}

    public void draw(SpriteBatch batch){
        if (shieldActive)
            batch.setColor(Color.RED);
        batch.draw(sprite, entityCoords.x, entityCoords.y, entityCoords.width, entityCoords.height);
        batch.setColor(Color.WHITE);
        introMovement();
    }
}