package com.hk47.arcana.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.hk47.arcana.components.PositionComponent;

public class PurporbSystem extends IteratingSystem {

    private static final Family family = Family.all(PositionComponent.class).get();

    private ComponentMapper<PositionComponent> pm;

    public PurporbSystem() {
        super(family);

        pm = ComponentMapper.getFor(PositionComponent.class);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        Gdx.app.log("ECStest","deltaTime is " + deltaTime);
        // Do something to change a purporb's position.
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent p = pm.get(entity);
        Gdx.app.log("ECStest","The purporb's position x is " + p.x);
        Gdx.app.log("ECStest","The purporb's position y is " + p.y);
    }
}
