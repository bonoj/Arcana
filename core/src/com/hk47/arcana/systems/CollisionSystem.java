package com.hk47.arcana.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.hk47.arcana.Mappers;
import com.hk47.arcana.components.TransformComponent;

public class CollisionSystem extends IteratingSystem {

    private static final Family FAMILY = Family.all(TransformComponent.class).get();

    public CollisionSystem() {
        super(FAMILY);
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transformComponent = Mappers.transform.get(entity);

        // Check for collisions!
    }
}
