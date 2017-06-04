package com.hk47.arcana;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Game;
import com.hk47.arcana.components.PositionComponent;

public class AshleyArcanaGame extends Game {

    @Override
    public void create() {
        Engine engine = new Engine();

        Entity entity = new Entity();

        engine.addEntity(entity);

        entity.add(new PositionComponent());

    }

    @Override
    public void render() {
        super.render();


    }
}
