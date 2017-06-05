package com.hk47.arcana.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.hk47.arcana.Mappers;
import com.hk47.arcana.components.MovementComponent;
import com.hk47.arcana.components.TransformComponent;

public class MovementSystem extends IteratingSystem {

    private static final Family family =
            Family.all(TransformComponent.class, MovementComponent.class).get();

    private Vector2 movementVector = new Vector2();

    public MovementSystem() {
        super(family);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transformComponent = Mappers.transform.get(entity);
        MovementComponent movementComponent = Mappers.movement.get(entity);

        // Calculate the change in velocity
        movementVector.set(movementComponent.acceleration).scl(deltaTime);
        movementComponent.velocity.add(movementVector);

        movementVector.set(movementComponent.velocity).scl(deltaTime);
        transformComponent.position.add(movementVector.x, movementVector.y, 0.0f);
    }
}
