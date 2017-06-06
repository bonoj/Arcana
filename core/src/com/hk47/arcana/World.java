package com.hk47.arcana;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.hk47.arcana.components.MovementComponent;
import com.hk47.arcana.components.TextureComponent;
import com.hk47.arcana.components.TransformComponent;

public class World {

    private Engine engine;

    public World (Engine engine) {
        this.engine = engine;
    }

    public void create() {
        Entity purporb = createPurporb(new Vector3(100, 100, 0));
    }

    private Entity createPurporb(Vector3 position) {
        Entity purporb = new Entity();

        // Will change with switch to PooledEngine
        engine.addEntity(purporb);

        purporb.add(new TransformComponent());
        purporb.add(new TextureComponent());
        purporb.add(new MovementComponent());

        TransformComponent transformComponent = purporb.getComponent(TransformComponent.class);
        transformComponent.position.set(position);

        TextureComponent textureComponent = purporb.getComponent(TextureComponent.class);
        textureComponent.texture = new Texture("purporb.png");

        MovementComponent movementComponent = purporb.getComponent(MovementComponent.class);
        movementComponent.velocity.set(0, 0);
        movementComponent.acceleration.set(0, 0);
        
        return purporb;
    }

    private void createCamera(Entity target) {
        Entity camera = new Entity();


    }
}
