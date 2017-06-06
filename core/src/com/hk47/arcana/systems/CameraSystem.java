package com.hk47.arcana.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.hk47.arcana.Mappers;
import com.hk47.arcana.components.CameraComponent;
import com.hk47.arcana.components.TransformComponent;

public class CameraSystem extends IteratingSystem {

    private static final Family FAMILY =
            Family.all(CameraComponent.class).get();

    private OrthographicCamera camera;

    public CameraSystem(OrthographicCamera camera) {
        super(FAMILY);

        this.camera = camera;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        // CameraComponent cameraComponent = Mappers.camera.get(entity);
        TransformComponent transformComponent = Mappers.transform.get(entity);


        // TODO Give CameraSystem some idea about world coordinates?
       //  camera.position.x = Math.min(Math.max(transformComponent.position.x, width / 2), levelPixelWidth - (width / 2));
//		camera.position.y = Math.min(Math.max(object.y, height / 2), levelPixelHeight - (height / 2));



        camera.position.x = transformComponent.position.x;
        // camera.position.y = transformComponent.position.y;
        camera.update();

        Gdx.app.log("Camera position", "X: " + camera.position.x + " Y: " + camera.position.y);

//        if (cameraComponent.target == null) {
//            return;
//        }
//
//        TransformComponent transformComponent = Mappers.transform.get(cameraComponent.target);
//
//        if (transformComponent == null) {
//            return;
//        }

//        cameraComponent.camera.translate(
//                transformComponent.position.x, transformComponent.position.y);

//        cameraComponent.camera.position.x =
//                Math.max(cameraComponent.camera.position.x, transformComponent.position.x);
//
//        cameraComponent.camera.position.y =
//                Math.max(cameraComponent.camera.position.y, transformComponent.position.y);

//        Vector3 cameraVector = cameraComponent.camera.position;
//
//        cameraComponent.camera.position =
//                Math.max(cameraComponent.camera.position, transformComponent.position);
    }
}
