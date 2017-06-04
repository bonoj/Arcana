package com.hk47.arcana;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hk47.arcana.components.TextureComponent;
import com.hk47.arcana.components.TransformComponent;
import com.hk47.arcana.systems.PurporbSystem;
import com.hk47.arcana.systems.RenderingSystem;

public class GameScreen extends ScreenAdapter {

    ArcanaGame game;

    Engine engine;

    SpriteBatch batch;

    public GameScreen (ArcanaGame game) {
        this.game = game;

        batch = new SpriteBatch();

        engine = new Engine();
        engine.addSystem(new PurporbSystem());
        engine.addSystem(new RenderingSystem(batch));

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

        TransformComponent t = purporb.getComponent(TransformComponent.class);
        t.position.set(30, 30, 0);

        TextureComponent tex = purporb.getComponent(TextureComponent.class);
        tex.texture = new Texture("purporb.png");

        engine.addEntity(purporb);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
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
