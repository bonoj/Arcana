package com.hk47.arcana.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.hk47.arcana.Mappers;
import com.hk47.arcana.components.CameraComponent;
import com.hk47.arcana.components.TransformComponent;

public class CameraSystem extends IteratingSystem {

    private static final Family FAMILY =
            Family.all(CameraComponent.class).get();

    private CameraSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        CameraComponent cameraComponent = Mappers.camera.get(entity);

        if (cameraComponent.target == null) {
            return;
        }

        TransformComponent transformComponent = Mappers.transform.get(cameraComponent.target);

        if (transformComponent == null) {
            return;
        }

        cameraComponent.camera.position.x =
                Math.max(cameraComponent.camera.position.x, transformComponent.position.x);
    }
}
