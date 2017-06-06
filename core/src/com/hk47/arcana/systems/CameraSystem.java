package com.hk47.arcana.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.hk47.arcana.Level;
import com.hk47.arcana.Mappers;
import com.hk47.arcana.components.CameraComponent;
import com.hk47.arcana.components.TransformComponent;

public class CameraSystem extends IteratingSystem {

    private static final Family FAMILY =
            Family.all(CameraComponent.class).get();

    private OrthographicCamera camera;
    private Level level;

    public CameraSystem(OrthographicCamera camera, Level level) {
        super(FAMILY);

        this.camera = camera;
        this.level = level;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transformComponent = Mappers.transform.get(entity);

        camera.position.x = transformComponent.position.x;
        // camera.position.y = transformComponent.position.y;

        // TODO Allow Level.class to handle adding and detaching camera component from player instead.
        if (camera.position.x - Gdx.graphics.getWidth() / 2 < 0) {
            camera.position.x = Gdx.graphics.getWidth() / 2;
        }

//        if (camera.position.x + width > level.levelPixelWidth) {
//            camera.position.x = level.levelPixelWidth - width / 2;
//        }

//        if (camera.position.y - Gdx.graphics.getHeight() / 2 < 0) {
//            camera.position.y = Gdx.graphics.getHeight() / 2;
//        }

        camera.update();
    }
}
