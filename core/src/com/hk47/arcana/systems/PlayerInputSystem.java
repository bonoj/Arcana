package com.hk47.arcana.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.hk47.arcana.Mappers;
import com.hk47.arcana.components.MovementComponent;
import com.hk47.arcana.components.TransformComponent;

public class PlayerInputSystem extends IteratingSystem {

    private static final Family FAMILY =
            Family.all(TransformComponent.class, MovementComponent.class).get();



    public PlayerInputSystem() {
        super(FAMILY);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        // This gets called after processEntity.
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        TransformComponent transformComponent = Mappers.transform.get(entity);
        MovementComponent movementComponent = Mappers.movement.get(entity);

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
                movementComponent.acceleration.y = -1500;
                movementComponent.velocity.y = 550;
            }
        }

        // Debug reset, DELETE ME! :)
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            transformComponent.position.x = 320;
            transformComponent.position.y = 400;
            movementComponent.velocity.y = 0;
            movementComponent.acceleration.y = -1500f;
        }

        // Touch controls to be implemented later.
//        if (Gdx.input.justTouched()) {
//            if (movementComponent.velocity.y == 0) {
//                // Apply gravity, temporary solution
//                movementComponent.acceleration.y = -1500;
//                movementComponent.velocity.y = 550;
//            }
//        }
//        if (Gdx.input.isTouched()){
//            movementComponent.velocity.x = 200;
//        }

        // Handle player collision
        // Make "ground" solid (Temporary, replace with CollisionSystem)
        // Remove gravity (Temporary solution)
//        if (transformComponent.position.y < 64) {
//            movementComponent.acceleration.y = 0;
//            movementComponent.velocity.y = 0;
//            transformComponent.position.y = 64;
//        }

//        Gdx.app.log("Player position", "X: " + transformComponent.position.x +
//                " Y: " + transformComponent.position.y);
    }
}
