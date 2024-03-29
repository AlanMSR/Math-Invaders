package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;

public abstract class Enemy extends Entity{

    public Enemy(int healthPoints, String sprite, Sound sound) {
        super(healthPoints, sprite, sound);
    }



    abstract boolean checkCollision(Player player);
}
