package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class BasicEnemy extends Enemy{
    private float speed;

    public BasicEnemy(){
        super(1, "huever.png", null);
        Random r = new Random();
        int minRange = 120;
        int maxRange = Gdx.graphics.getWidth() - 165; // 120 + 42
        setCoords(r.nextInt(minRange, maxRange),Gdx.graphics.getHeight() + 10);
        entityCoords.width = 40;
        entityCoords.height = 42;
        speed = 50; // Velocidad de movimiento del enemigo (ajústala según tus necesidades)
    }
    public BasicEnemy(int healthPoints, String sprite, Sound sound) {
        super(healthPoints, sprite, sound);
    }

    public void movement() {
        float delta = Gdx.graphics.getDeltaTime();

        // Calcula el desplazamiento en función de la velocidad y el tiempo transcurrido
        float displacementY = -speed * delta;

        // Actualiza la posición del enemigo
        entityCoords.y += displacementY;

        if(entityCoords.y <= 0){
            reposition();
        }
    }

    private void reposition(){
        Random r = new Random();
        int minRange = 120;
        int maxRange = Gdx.graphics.getWidth() - 165; // 120 + 42
        int heightCooldown = r.nextInt(80);
        setCoords(r.nextInt(minRange, maxRange),Gdx.graphics.getHeight() + heightCooldown);
    }

    public boolean checkCollision(Player player) {
        PlayerProjectile bala = player.getProjectile();
        if (bala.getProjectile().overlaps(this.entityCoords)) {
            System.out.println("ITS SIMPLY PUGGERING");
            bala.setVisible(false);
            bala.setFired(false);
            bala.setCoords(0, Gdx.graphics.getHeight() + 10);
            reposition();
            player.setScore(1);
            return true;
        }
        return false;
    }


    public void update() {
        movement();
    }

    public void draw(SpriteBatch batch){
        batch.draw(sprite, entityCoords.x, entityCoords.y, entityCoords.width, entityCoords.height);
        update();
    }
}
