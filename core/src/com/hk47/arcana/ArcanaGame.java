package com.hk47.arcana;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class ArcanaGame extends ApplicationAdapter {
	SpriteBatch batch;
	GameObject object;

	TiledMap tiledMap;
	OrthographicCamera camera;
	TiledMapRenderer tiledMapRenderer;

	@Override
	public void create () {
		batch = new SpriteBatch();

		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);
		camera.update();

		tiledMap = new TmxMapLoader().load("levels\\testlvl.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

		TextManager.setSpriteBatch(batch);
		object = new GameObject("purporb.png", batch, 0, 0);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

		GameInput.update();
		object.updatePosition();
		camera.position.set(object.x, object.y, 0);
		camera.update();

		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();


		batch.begin();

		batch.setProjectionMatrix(camera.combined);

		object.draw();

		TextManager.draw("(" + object.x + "," + object.y + ")", camera);

		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}