package com.hk47.arcana.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.hk47.arcana.Mappers;
import com.hk47.arcana.components.MovementComponent;
import com.hk47.arcana.components.TransformComponent;

public class MovementSystem extends IteratingSystem {

    private static final Family FAMILY =
            Family.all(TransformComponent.class, MovementComponent.class).get();

    private Vector2 momentumVector = new Vector2();

    public MovementSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transformComponent = Mappers.transform.get(entity);
        MovementComponent movementComponent = Mappers.movement.get(entity);

        momentumVector.set(movementComponent.acceleration).scl(deltaTime);
        movementComponent.velocity.add(momentumVector);

        momentumVector.set(movementComponent.velocity).scl(deltaTime);
        transformComponent.position.add(momentumVector.x, momentumVector.y, 0.0f);
    }
}
