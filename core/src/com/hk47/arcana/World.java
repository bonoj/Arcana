package com.hk47.arcana;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.hk47.arcana.components.CameraComponent;
import com.hk47.arcana.components.MovementComponent;
import com.hk47.arcana.components.TextureComponent;
import com.hk47.arcana.components.TransformComponent;
import com.hk47.arcana.components.WorldMapComponent;

public class World {

    private Engine engine;
    private OrthographicCamera camera;

    public World (Engine engine, OrthographicCamera camera) {
        this.engine = engine;
        this.camera = camera;
    }

    public void create() {
        Entity purporb = createPurporb(new Vector3(300, 300, 0));
        // Entity purporb2 = createPurporb(new Vector3(10, 7.5f, 0));
        // createCamera(purporb);


    }

    private Entity createWorldMap() {
        Entity worldMap = new Entity();
        engine.addEntity(worldMap);

        WorldMapComponent worldMapComponent = worldMap.getComponent(WorldMapComponent.class);
        worldMapComponent.tiledMap = new TmxMapLoader().load("levels\\testlvl.tmx");
        worldMapComponent.tiledMapRenderer = new OrthogonalTiledMapRenderer(worldMapComponent.tiledMap);

        return worldMap;
    }

    private void setChaseCamera(Entity entity) {

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
        // TODO Replace this with a GravityComponent. Or just set World gravity if it will change.
        // TODO Remove it when not airborne, however.
        movementComponent.acceleration.y = -1500f;
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
