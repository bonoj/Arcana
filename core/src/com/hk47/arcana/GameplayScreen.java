package com.hk47.arcana;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameplayScreen extends ScreenAdapter {

    SpriteBatch batch;

    @Override
    public void show() {

        batch = new SpriteBatch();
    }

        @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void dispose () {
        batch.dispose();
    }
}
