package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import javax.swing.event.CaretListener;

public class MenuState extends ScreenAdapter {
    private Stage stage;
    private Viewport viewport;
    private TextureAtlas atlas;
    protected Skin skin;
    private Table mainTable;

    public MenuState() {
        atlas = new TextureAtlas("uiskin.atlas");
        //atlas = new TextureAtlas("skin.atlas");
        skin = new Skin(Gdx.files.internal("uiskin.json"), atlas);
        //skin = new Skin(Gdx.files.internal("uiskin.json"));
        //skin.addRegions(atlas);
    }

    @Override
    public void show() {
        viewport =  new ExtendViewport(1280,720);
        stage = new Stage(viewport);

        mainTable = new Table();
        mainTable.setFillParent(true);

        stage.addActor(mainTable);

        addButton("Play").addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new GameplayScreen());
            }
        });

        addButton("Leaderboard").addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });

        addButton("Quit").addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // exit game/ guardar data para leaderboard
            }
        });

        Gdx.input.setInputProcessor(stage);
    }

    private TextButton addButton(String name) {
        TextButton button = new TextButton(name, skin);
        mainTable.add(button).width(400).height(80).padBottom(20);
        mainTable.row();
        return button;
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.2f, 0.6f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}