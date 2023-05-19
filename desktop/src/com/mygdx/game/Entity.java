package com.mygdx.game;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import com.badlogic.gdx.graphics.Texture;

public abstract class Entity extends EntityGraphics {
    private int healthPoints;
    private Texture sprite;
    private Rectangle coords;
    private Sound sound;
    private SpriteBatch batch;

    public Entity() {
    }

    //public abstract void Movement{}


}
