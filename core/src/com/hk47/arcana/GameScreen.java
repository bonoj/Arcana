package com.hk47.arcana;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hk47.arcana.systems.CameraSystem;
import com.hk47.arcana.systems.MovementSystem;
import com.hk47.arcana.systems.PlayerInputSystem;
import com.hk47.arcana.systems.RenderingSystem;

public class GameScreen extends ScreenAdapter {

    private ArcanaGame game;
    private SpriteBatch batch;
    private Engine engine;
    private OrthographicCamera camera;
    private Level level;

    public GameScreen (ArcanaGame game) {
        this.game = game;

        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false);

        engine = new Engine();

        level = new Level(engine, camera);
        level.create();

        engine.addSystem(new RenderingSystem(batch));
        engine.addSystem(new CameraSystem(camera, level));
        engine.addSystem(new MovementSystem());
        engine.addSystem(new PlayerInputSystem());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        level.update();
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


// TODO Adding Listeners
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
