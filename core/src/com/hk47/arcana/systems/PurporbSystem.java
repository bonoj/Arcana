package com.hk47.arcana.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.hk47.arcana.components.TransformComponent;

public class PurporbSystem extends IteratingSystem {

    private static final Family family = Family.all(TransformComponent.class).get();

    private ComponentMapper<TransformComponent> transformMapper;

    public PurporbSystem() {
        super(family);

        transformMapper = ComponentMapper.getFor(TransformComponent.class);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        // Do something to change the player's position or whatever.
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transformCom = transformMapper.get(entity);
        Gdx.app.log("ECStest",
                "The purporb's position is (" +
                        transformCom.position.x + "," +
                        transformCom.position.y + "," +
                        transformCom.position.z + ")");
    }
}
