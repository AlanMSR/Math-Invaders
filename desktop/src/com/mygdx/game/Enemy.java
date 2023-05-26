package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public abstract class Enemy extends Entity{

    public Enemy(int healthPoints, String sprite, Sound sound) {
        super(healthPoints, sprite, sound);
    }

    public void spawnPoint(){

    }

    abstract void checkCollision(PlayerProjectile bala);
}
