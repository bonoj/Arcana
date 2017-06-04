package com.hk47.arcana;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.physics.box2d.World;

import box2dLight.Light;
import box2dLight.RayHandler;

public class ArcanaGame extends Game {
	SpriteBatch batch;
	GameObject object;
	Player player;

	TiledMap tiledMap;
	OrthographicCamera camera;
	TiledMapRenderer tiledMapRenderer;

	float width;
	float height;
	int levelPixelWidth;
	int levelPixelHeight;

	World world;
	RayHandler rayHandler;
	Light light;



	@Override
	public void create () {

		setScreen(new GameScreen(this));








//
//		// Sound test
//		SoundManager.playMusic("Winds Of Stories.ogg", 0.1f);
//
//		batch = new SpriteBatch();
//
//		width = Gdx.graphics.getWidth();
//		height = Gdx.graphics.getHeight();
//
//		camera = new OrthographicCamera();
//		camera.setToOrtho(false, width, height);
//		camera.update();
//
//		tiledMap = new TmxMapLoader().load("levels\\testlvl.tmx");
//		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
//
//		TextManager.setSpriteBatch(batch);
//		object = new GameObject("purporb.png", batch, width / 2, height / 2);
//
//		world = new World(new Vector2(0f, -9.8f), false);
//
//		player = new Player(new Texture("purporb.png"), 300, 400);
//
//		MapProperties properties = tiledMap.getProperties();
//
//		// Get level width and height in tiles
//		int levelWidth = properties.get("width", Integer.class);
//		int levelHeight = properties.get("height", Integer.class);
//		int tilePixelWidth = properties.get("tilewidth", Integer.class);
//		int tilePixelHeight = properties.get("tileheight", Integer.class);
//		levelPixelWidth = levelWidth * tilePixelWidth;
//		levelPixelHeight = levelHeight * tilePixelHeight;
//
//		rayHandler = new RayHandler(world);
//		rayHandler.setAmbientLight(0.1f, 0.1f, 0.1f, 0.25f);
//		light = new PointLight(rayHandler, 1000, Color.BLUE, 200, 100, 200);
//		light = new PointLight(rayHandler, 1000, Color.YELLOW, 200, 300, 200);
//		light = new PointLight(rayHandler, 1000, Color.PURPLE, 200, 500, 200);
	}

//	@Override
//	public void render () {
//		Gdx.gl.glClearColor(0, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
//
//		GameInput.update();
//		object.updatePosition();
//		player.update();
//		camera.position.x = Math.min(Math.max(object.x, width / 2), levelPixelWidth - (width / 2));
//		camera.position.x = Math.min(Math.max(object.x, height / 2), levelPixelHeight - (height / 2));
//		camera.update();
//
//		tiledMapRenderer.setView(camera);
//		tiledMapRenderer.render();
//
//
//		batch.begin();
//
//		batch.setProjectionMatrix(camera.combined);
//
//		player.draw(batch);
//
//		object.draw();
//
//		//TextManager.draw("(" + object.x + "," + object.y + ")", camera);
//		TextManager.draw("FPS: " + Gdx.graphics.getFramesPerSecond() + " Time: " + Time.time, camera);
//
//		batch.end();
//
//		//rayHandler.setCombinedMatrix(camera.combined.cpy());
//		// rayHandler.updateAndRender();
//	}
//
//	@Override
//	public void dispose () {
//		batch.dispose();
//		SoundManager.dispose();
//		light.dispose();
//	}
}