package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class PlayerProjectile {

    private int number;

    private boolean isFired;

    private Rectangle projectile;

    private boolean visibility;
    private Texture projectileTexture;

    private Rectangle projectileRectangle;

    public PlayerProjectile() {


        loadTexture();

        //visibility = false;

        projectile = new Rectangle();
        //projectileRectangle.x = 0;
        //projectileRectangle.y = 1000000;
        //projectileRectangle.width = 32;
        //projectileRectangle.height = 32;
        isFired = false;

    }

    public int getNumber() {

        return this.number;
    }

    public int changeNumber() {
        return this.number;
    }

    public void draw(SpriteBatch batch) {

        if (isFired) {
            projectile.y += 820 * Gdx.graphics.getDeltaTime();
            batch.draw(projectileTexture, projectile.x, projectile.y);
            if (projectile.y > Gdx.graphics.getHeight()) {
                isFired = false;
                visibility = false;
            }
        }
    }


    public void setVisible(boolean value) {

        visibility = value;
    }

    public boolean getVisible() {

        return visibility;
    }

    public void shoot(float shipX, float shipY) {

        if (!isFired) {
            projectile.set(shipX + 8, shipY, 32, 32);
            isFired = true;
        }
    }

    public void loadTexture() {

        //boolean result = false;

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

    public void projectileMovement() {

    }

    public boolean isFired() {
        return isFired;
    }

    public Rectangle getProjectile() {
        return projectile;
    }

}
