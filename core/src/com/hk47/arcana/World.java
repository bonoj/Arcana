package com.hk47.arcana;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.hk47.arcana.components.CameraComponent;
import com.hk47.arcana.components.MovementComponent;
import com.hk47.arcana.components.TextureComponent;
import com.hk47.arcana.components.TransformComponent;

public class World {

    private Engine engine;

    public World (Engine engine, OrthographicCamera camera) {
        this.engine = engine;
    }

    public void create() {
        Entity purporb = createPurporb(new Vector3(100, 100, 0));
        // Entity purporb2 = createPurporb(new Vector3(10, 7.5f, 0));
        // createCamera(purporb);
    }

    private Entity createPurporb(Vector3 position) {
        Entity purporb = new Entity();

        // Will change with switch to PooledEngine
        engine.addEntity(purporb);

        purporb.add(new TransformComponent());
        purporb.add(new TextureComponent());
        purporb.add(new MovementComponent());
        purporb.add(new CameraComponent());

        TransformComponent transformComponent = purporb.getComponent(TransformComponent.class);
        transformComponent.position.set(position);

        TextureComponent textureComponent = purporb.getComponent(TextureComponent.class);
        textureComponent.texture = new Texture("purporb.png");

        MovementComponent movementComponent = purporb.getComponent(MovementComponent.class);
        movementComponent.velocity.set(0, 0);
        movementComponent.acceleration.set(0, 0);

        // CameraComponent cameraComponent = purporb.getComponent(CameraComponent.class);
        // cameraComponent.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        return purporb;
    }

//    private void createCamera(Entity target) {
//        Entity camera = new Entity();
//        engine.addEntity(camera);
//
//        CameraComponent cameraComponent = new CameraComponent();
//        cameraComponent.camera = engine.getSystem(RenderingSystem.class).getCamera();
//        cameraComponent.target = target;
//        Gdx.app.log("Camera at ", "X: " +
//                cameraComponent.camera.position.x + " Y: " + cameraComponent.camera.position.y);
//    }
}
