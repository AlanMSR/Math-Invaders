package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BasicEnemy extends Enemy{
    private float speed;

    public BasicEnemy(){
        super(50, "enemy_prueba.png", null);
        setCoords(500,400);
        speed = 100; // Velocidad de movimiento del enemigo (ajústala según tus necesidades)
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
    }

    public void checkCollision(PlayerProjectile projectile){

    }

    public void draw(SpriteBatch batch){

    }
}
