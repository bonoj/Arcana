package com.hk47.arcana;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.hk47.arcana.components.CameraComponent;
import com.hk47.arcana.components.MovementComponent;
import com.hk47.arcana.components.TextureComponent;
import com.hk47.arcana.components.TransformComponent;

public class Level {

    public float levelPixelWidth;
    public float levelPixelHeight;

    private Engine engine;
    private OrthographicCamera camera;
    TiledMap tiledMap;
    TiledMapRenderer tiledMapRenderer;

    public Level(Engine engine, OrthographicCamera camera) {
        this.engine = engine;
        this.camera = camera;
    }

    public void create() {
        tiledMap = new TmxMapLoader().load("levels\\testlvl.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        MapProperties properties = tiledMap.getProperties();
        int levelWidth = properties.get("width", Integer.class); // Level width in tiles
        int levelHeight = properties.get("height", Integer.class); // Level height in tiles
        int tilePixelWidth = properties.get("tilewidth", Integer.class);
        int tilePixelHeight = properties.get("tileheight", Integer.class);
        levelPixelWidth = levelWidth * tilePixelWidth;
        levelPixelHeight = levelHeight * tilePixelHeight;

        createPurporb(new Vector3(320, 400, 0));
    }

    public void update() {
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
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

        // TODO Replace this with a GravityComponent. Or just set Level gravity if it will change.
        // TODO Remove it when not airborne, however.
        MovementComponent movementComponent = purporb.getComponent(MovementComponent.class);
        movementComponent.acceleration.y = -1500f;
        return purporb;
    }
}
