package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class PlayerProjectile {

    private int number;
    private boolean visibility;
    private Texture projectileTexture;
    private Array<PlayerProjectile> projectile;

    private Rectangle projectileRectangle;

    public PlayerProjectile() {

        //projectile = new Array<PlayerProjectile>;

        loadTexture();

        visibility = false;

        projectileRectangle = new Rectangle();
        projectileRectangle.x = 0;
        projectileRectangle.y = 1000000;
        projectileRectangle.width = 32;
        projectileRectangle.height = 32;

    }

    public int getNumber() {

        return this.number;
    }

    public int changeNumber() {
        return this.number;
    }

    public void draw(SpriteBatch batch) {

        projectileRectangle.y += 820 * Gdx.graphics.getDeltaTime();
        batch.draw(projectileTexture, projectileRectangle.x, projectileRectangle.y);

    }

    public void setVisible(boolean value) {

        visibility = value;
    }

    public boolean getVisible() {

        return visibility;
    }

    public void shoot(float shipX, float shipY) {

        if (visibility) {

            projectileRectangle.x = shipX + 8;
            projectileRectangle.y = shipY;
            System.out.println(projectileRectangle.y);

            visibility = false;
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
}
