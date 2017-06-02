package com.hk47.arcana;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hk47.arcana.entities.Explosion;
import com.hk47.arcana.utilities.Assets;

public class GameplayScreen extends ScreenAdapter {

    SpriteBatch batch;
    private Explosion explosion;

    @Override
    public void show() {
        AssetManager am = new AssetManager();
        Assets.instance.init(am);

        batch = new SpriteBatch();
    }

        @Override
    public void render (float delta) {
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            // TODO Hack in an explosion animation.
    }

    @Override
    public void dispose () {
        batch.dispose();
        Assets.instance.dispose();
    }
}
