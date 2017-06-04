package com.hk47.arcana;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.hk47.arcana.components.PositionComponent;
import com.hk47.arcana.systems.PurporbSystem;

public class GameScreen extends ScreenAdapter {

    ArcanaGame game;

    Engine engine;

    public GameScreen (ArcanaGame game) {
        this.game = game;

        engine = new Engine();
        engine.addSystem(new PurporbSystem());

        Family family = Family.all(PositionComponent.class).get();
        engine.addEntityListener(family, new EntityListener() {
            @Override
            public void entityAdded(Entity entity) {
                Gdx.app.log("ECTest", "Added " + entity);
            }

            @Override
            public void entityRemoved(Entity entity) {
                Gdx.app.log("ECTest", "Removed " + entity);
            }
        });

        Entity entity = new Entity();

        engine.addEntity(entity);

        entity.add(new PositionComponent());
    }

    @Override
    public void render(float delta) {

        engine.getSystem(PurporbSystem.class).update(delta);

        super.render(delta);
    }
}
