package com.mygdx.game;

import com.badlogic.gdx.Game;

public class Runner extends Game {

    @Override
    public void create() {
        setScreen(new MenuState());
    }
}
