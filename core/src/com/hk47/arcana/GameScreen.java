package com.hk47.arcana;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hk47.arcana.systems.MovementSystem;
import com.hk47.arcana.systems.PurporbSystem;
import com.hk47.arcana.systems.RenderingSystem;

public class GameScreen extends ScreenAdapter {

    private ArcanaGame game;
    private Engine engine;
    private World world;
    private SpriteBatch batch;

    public GameScreen (ArcanaGame game) {
        this.game = game;

        batch = new SpriteBatch();

        engine = new Engine();

        world = new World(engine);

        engine.addSystem(new PurporbSystem());
        engine.addSystem(new RenderingSystem(batch));
        engine.addSystem(new MovementSystem());

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

        float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();

        world.create();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        engine.update(delta);

        super.render(delta);
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
    }
}
