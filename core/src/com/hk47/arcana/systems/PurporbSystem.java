package com.hk47.arcana.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.hk47.arcana.components.MovementComponent;
import com.hk47.arcana.components.TransformComponent;

public class PurporbSystem extends IteratingSystem {

    private static final Family family =
            Family.all(TransformComponent.class, MovementComponent.class).get();

    private ComponentMapper<TransformComponent> transformMapper;
    private ComponentMapper<MovementComponent> movementMapper;

    public PurporbSystem() {
        super(family);

        transformMapper = ComponentMapper.getFor(TransformComponent.class);
        movementMapper = ComponentMapper.getFor(MovementComponent.class);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        // Do something to change the player's position or whatever.
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transformCom = transformMapper.get(entity);
        MovementComponent movementComponent = movementMapper.get(entity);

        // Handle player movement
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            movementComponent.velocity.x = 200;
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            movementComponent.velocity.x = -200;
        } else {
            movementComponent.velocity.x = -0;
        }

        // Handle player jumping
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (movementComponent.velocity.y == 0) {
                // Apply gravity, temporary solution
                movementComponent.acceleration.y = -1000;
                movementComponent.velocity.y = 400;
            }
        }

        // Handle player collision
        // Make "ground" solid (Temporary, replace with CollisionSystem)
        // Remove gravity (Temporary solution)
        if (transformCom.position.y < 100) {
            movementComponent.acceleration.y = 0;
            movementComponent.velocity.y = 0;
            transformCom.position.y = 100;
        }
    }
}
