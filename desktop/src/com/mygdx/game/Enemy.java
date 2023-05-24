package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;

public abstract class Enemy extends Entity{

    public Enemy(int healthPoints, String sprite, Sound sound) {
        super(healthPoints, sprite, sound);
    }

    public void spawnPoint(){

    }

    public void checkCollision(PlayerProjectile bala){
        if(bala.getProjectile().overlaps(this.entityCoords)) {
            System.out.println("CHOCO EEEEEEE");
        }
    }
}
