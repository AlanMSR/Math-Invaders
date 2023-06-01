package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FinalBoss extends AdvancedEnemy{
    private boolean introComplete;
    private float targetY;
    private float introSpeed;
    public FinalBoss() {
        super(10,"boss.png" ,null);
        setCoords(122, Gdx.graphics.getHeight() + 80);
        introComplete = false;
        targetY = 360; // Altura objetivo para el movimiento introductorio
        introSpeed = 50; // Velocidad de movimiento introductoria
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

    public boolean checkCollision(Player player){
        PlayerProjectile bala = player.getProjectile();
        if (bala.getProjectile().overlaps(this.entityCoords)) {
            System.out.println("ITS SIMPLY PUGGERING");
            bala.setVisible(false);
            bala.setFired(false);
            bala.setCoords(0, Gdx.graphics.getHeight() + 10);
            player.setScore(15);
            healthPoints -= bala.getNumber();
            if(healthPoints <= 0) {

                return true;
            }
        }
        return false;

    }

    public boolean getAlive(){
        if(!isAlive){

        }
    return false;
    }
    public void draw(SpriteBatch batch){
        batch.draw(sprite, entityCoords.x, entityCoords.y, entityCoords.width, entityCoords.height);
        introMovement();
    }
}