package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BasicEnemy extends Enemy{

    public BasicEnemy(int healthPoints, String sprite, Sound sound){
        super(healthPoints, sprite, sound);
    }
    public void movement(){
    }

    public void draw(SpriteBatch batch){

    }
}
