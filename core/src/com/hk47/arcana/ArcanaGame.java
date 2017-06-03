package com.hk47.arcana;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
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

	float width;
	float height;
	int levelPixelWidth;
	int levelPixelHeight;

	@Override
	public void create () {
		batch = new SpriteBatch();

		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);
		camera.update();

		tiledMap = new TmxMapLoader().load("levels\\testlvl.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

		TextManager.setSpriteBatch(batch);
		object = new GameObject("purporb.png", batch, width / 2, height / 2);

		MapProperties properties = tiledMap.getProperties();
		// Get level width and height in tiles
		int levelWidth = properties.get("width", Integer.class);
		int levelHeight = properties.get("height", Integer.class);
		int tilePixelWidth = properties.get("tilewidth", Integer.class);
		int tilePixelHeight = properties.get("tileheight", Integer.class);
		levelPixelWidth = levelWidth * tilePixelWidth;
		levelPixelHeight = levelHeight * tilePixelHeight;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

		GameInput.update();
		object.updatePosition();
		camera.position.x = Math.min(Math.max(object.x, width / 2), levelPixelWidth - (width / 2));
		camera.position.x = Math.min(Math.max(object.x, height / 2), levelPixelHeight - (height / 2));
		camera.update();

		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();


		batch.begin();

		batch.setProjectionMatrix(camera.combined);

		object.draw();

		//TextManager.draw("(" + object.x + "," + object.y + ")", camera);
		TextManager.draw("FPS: " + Gdx.graphics.getFramesPerSecond() + " Time: " + Time.time, camera);

		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}