package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.graphics.Texture;

public abstract class Entity {
    private int healthPoints;
    protected Texture sprite;
    protected Rectangle entityCoords;
    private Sound sound;
    private SpriteBatch batch;
    private boolean isAlive = true;

    public Entity(){

    }

    public Entity(int healthPoints, String sprite, Sound sound) {
        this.healthPoints = healthPoints;
        this.sound = sound;
        entityCoords = new Rectangle();

        loadTexture(sprite);
    }

    public void loseHealthPoints(int damage){
        this.healthPoints -= damage;
    }

    public void isStillAlive() {
        if (healthPoints <= 0) {
            isAlive = false;
        }
    }

    public void loadTexture(String path) {
        try
        {
            sprite = new Texture(Gdx.files.internal(path));

        } catch (Exception e) {
            System.out.println("oh no hermano, entidad");
            e.printStackTrace();
        }
    }

    abstract void movement();

    abstract void draw(SpriteBatch batch);

    public void setCoords(float x, float y){
        this.entityCoords.x = x;
        this.entityCoords.y = y;
    }
}
