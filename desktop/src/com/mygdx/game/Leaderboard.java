package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Leaderboard extends ScreenAdapter {
    private SpriteBatch batch;
    private Stage stage;
    private Viewport viewport;
    private TextureAtlas atlas;
    protected Skin skin;
    private Table mainTable;
    private BitmapFont font;
    private List<Integer> scores;
    List<Integer> sortedScores;

    public Leaderboard() {
        batch = new SpriteBatch();
        atlas = new TextureAtlas("uiskin.atlas");
        skin = new Skin(Gdx.files.internal("uiskin.json"), atlas);
        font = new BitmapFont();
        scores = ScoreManager.loadScores();
        sortedScores = scores.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    @Override
    public void show() {
        viewport =  new ExtendViewport(1280,720);
        stage = new Stage(viewport);

        mainTable = new Table();
        mainTable.setFillParent(true);

        stage.addActor(mainTable);

        addButton("Back").addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(MenuState.getInstance());
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

        batch.begin();
        // hasta arriba?
        font.draw(batch, "Scores:", 10, Gdx.graphics.getHeight() - 10);
        float y = Gdx.graphics.getHeight() - 30; // y inicial para socores

        for (int i = 0; i < 10; i++) {
            int score = sortedScores.get(i);
            font.draw(batch, "Top " + (i + 1) + ": " + score, 10, y);
            y -= 20;
        }
        batch.end();

        stage.act();

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
