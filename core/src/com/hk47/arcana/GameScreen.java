package com.hk47.arcana;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.hk47.arcana.systems.CameraSystem;
import com.hk47.arcana.systems.MovementSystem;
import com.hk47.arcana.systems.PlayerInputSystem;
import com.hk47.arcana.systems.RenderingSystem;

public class GameScreen extends ScreenAdapter {

    private ArcanaGame game;
    private SpriteBatch batch;
    private Engine engine;
    private Level level;

    private TiledMap tiledMap;
    private OrthographicCamera camera;
    private TiledMapRenderer tiledMapRenderer;

    private float width;
    private float height;
    private int levelPixelWidth;
    private int levelPixelHeight;

    public GameScreen (ArcanaGame game) {
        this.game = game;

        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false);

        engine = new Engine();

        level = new Level(engine, camera);

        engine.addSystem(new RenderingSystem(batch));
        engine.addSystem(new CameraSystem(camera));
        engine.addSystem(new MovementSystem());
        engine.addSystem(new PlayerInputSystem());

        width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();

		tiledMap = new TmxMapLoader().load("levels\\testlvl.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        MapProperties properties = tiledMap.getProperties();

        // Get level width and height in tiles
		int levelWidth = properties.get("width", Integer.class);
		int levelHeight = properties.get("height", Integer.class);
		int tilePixelWidth = properties.get("tilewidth", Integer.class);
		int tilePixelHeight = properties.get("tileheight", Integer.class);
		levelPixelWidth = levelWidth * tilePixelWidth;
		levelPixelHeight = levelHeight * tilePixelHeight;


        // Entity addid tion and removal listener!
//        Family family = Family.all(PositionComponent.class).get();
//        engine.addEntityListener(family, new EntityListener() {
//            @Override
//            public void entityAdded(Entity entity) {
//                Gdx.app.log("ECTest", "Added " + entity);
//            }
//
//            @Override
//            public void entityRemoved(Entity entity) {
//                Gdx.app.log("ECTest", "Removed " + entity);
//            }
//        });

        level.create();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        batch.setProjectionMatrix(camera.combined);
        engine.update(delta);

        super.render(delta);
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
    }
}
