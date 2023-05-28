package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;

public class PlayerProjectile {
    private int number = 1;
    private boolean isFired;
    private Rectangle projectile;
    private boolean visibility;
    private Texture projectileTexture;
    private Texture numbersTexture;
    private TextureRegion[] bulletOfSets;
    private int id;

    public PlayerProjectile(int id) {
        loadTexture();
        this.id = id;

        //visibility = false;

        projectile = new Rectangle();
        //projectileRectangle.x = 0;
        //projectileRectangle.y = 1000000;
        //projectileRectangle.width = 32;
        //projectileRectangle.height = 32;
        isFired = false;

        numbersTexture = new Texture(Gdx.files.internal("numbers&shi.png"));

        bulletOfSets = new TextureRegion[9];
        bulletOfSets[0] = new TextureRegion(numbersTexture, 178, 258, 12, 30);
        bulletOfSets[1] = new TextureRegion(numbersTexture, 215, 258, 12, 30);
        bulletOfSets[2] = new TextureRegion(numbersTexture, 248, 258, 12, 30);
        bulletOfSets[3] = new TextureRegion(numbersTexture, 9, 299, 12, 30);
        bulletOfSets[4] = new TextureRegion(numbersTexture, 43, 299, 12, 30);
        bulletOfSets[5] = new TextureRegion(numbersTexture, 77, 299, 12, 30);
        bulletOfSets[6] = new TextureRegion(numbersTexture, 111, 299, 12, 30);
        bulletOfSets[7] = new TextureRegion(numbersTexture, 146, 299, 12, 30);
        bulletOfSets[8] = new TextureRegion(numbersTexture, 180, 299, 12, 30);
    }

    public int getNumber() {
        return this.number;
    }

    public void substractNumber() {
        if (!visibility) {
            number = number - 1;
            if (number < 1) {
                number = 9;
            }
        }
    }

    public void addNumber() {
        if (!visibility) {
            number = number + 1;

            if (number > 9) {
                number = 1;
            }
        }
    }
    
    public void draw(SpriteBatch batch) {
        if (isFired) {

            projectile.y += 850 * Gdx.graphics.getDeltaTime();
            batch.draw(bulletOfSets[number - 1], projectile.x, projectile.y);

            if (projectile.y > Gdx.graphics.getHeight()) {

                isFired = false;
                visibility = false;
            }
        }

        changeNumber();
    }


    public void setVisible(boolean value) {

        visibility = value;
    }

    public boolean getVisible() {

        return visibility;
    }

    public void shoot(float shipX, float shipY) {

        if (!isFired) {
            projectile.set(shipX + 30, shipY, 12, 30);
            isFired = true;
        }
    }

    public void loadTexture() {

        try
        {
            projectileTexture = new Texture(Gdx.files.internal("bala.png"));


        } catch (Exception e) {

            System.out.println("oh no hermano, es la bala");
            e.printStackTrace();
            System.exit(0);
           // result = true;
        }

        //return result;
    }

    public void changeNumber(){

        boolean qPressed = false;
        boolean ePressed = false;

        if(Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            qPressed = true;
            ePressed = false;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            ePressed = true;
            qPressed = false;
        }

        if(qPressed) {
            substractNumber();
        }
        if(ePressed) {
            addNumber();
        }
    }

    public void setFired(boolean value){
        isFired = value;
    }
    public boolean isFired() {
        return isFired;
    }

    public Rectangle getProjectile() {
        return projectile;
    }

    public void setCoords(float x,float y){
        projectile.x = x;
        projectile.y = y;
    }

    public int getId(){
        return id;
    }

}
