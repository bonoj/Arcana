package com.hk47.arcana;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.hk47.arcana.components.MovementComponent;
import com.hk47.arcana.components.TextureComponent;
import com.hk47.arcana.components.TransformComponent;
import com.hk47.arcana.systems.MovementSystem;
import com.hk47.arcana.systems.PurporbSystem;
import com.hk47.arcana.systems.RenderingSystem;

public class GameScreen extends ScreenAdapter {

    ArcanaGame game;

    Engine engine;

    World world;

    SpriteBatch batch;

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

        Entity purporb = new Entity();

        purporb.add(new TransformComponent());
        purporb.add(new TextureComponent());
        purporb.add(new MovementComponent());

        TransformComponent transformComponent = purporb.getComponent(TransformComponent.class);
        transformComponent.position.set(30.0f, 30.0f, 0f);

        TextureComponent textureComponent = purporb.getComponent(TextureComponent.class);
        textureComponent.texture = new Texture("purporb.png");

        MovementComponent movementComponent = purporb.getComponent(MovementComponent.class);
        movementComponent.velocity.set(0, 50);
        movementComponent.acceleration.set(0, -20);

        engine.addEntity(purporb);

        for (int i = 80; i < 500; i += 50) {
            engine.addEntity(createPurporb(new Vector3((float) i, 30, 0)));
        }
    }

    private Entity createPurporb(Vector3 position) {
        Entity purporb = new Entity();

        purporb.add(new TransformComponent());
        purporb.add(new TextureComponent());
        purporb.add(new MovementComponent());

        TransformComponent transformComponent = purporb.getComponent(TransformComponent.class);
        transformComponent.position.set(position);

        TextureComponent textureComponent = purporb.getComponent(TextureComponent.class);
        textureComponent.texture = new Texture("purporb.png");

        MovementComponent movementComponent = purporb.getComponent(MovementComponent.class);
        movementComponent.velocity.set(0, 50);
        movementComponent.acceleration.set(0, -20);

        return purporb;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        //engine.getSystem(PurporbSystem.class).update(delta);
        engine.update(delta);


        super.render(delta);
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
    }
}
