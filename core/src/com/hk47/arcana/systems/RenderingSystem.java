package com.hk47.arcana.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.hk47.arcana.Mappers;
import com.hk47.arcana.components.TextureComponent;
import com.hk47.arcana.components.TransformComponent;

public class RenderingSystem extends IteratingSystem {

    private static final Family FAMILY =
            Family.all(
                    TransformComponent.class,
                    TextureComponent.class
            ).get();

    private SpriteBatch batch;
    private Array<Entity> renderQueue;

    public RenderingSystem(SpriteBatch batch) {
        super(FAMILY);

        renderQueue = new Array<Entity>();

        this.batch = batch;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        batch.begin();

        for (Entity entity : renderQueue) {

            TextureComponent textureCom = Mappers.texture.get(entity);

            if (textureCom.texture == null) {
                continue;
            }

            TransformComponent transformCom = Mappers.transform.get(entity);

            batch.draw(
                    textureCom.texture,
                    transformCom.position.x,
                    transformCom.position.y);
        }

        batch.end();
        renderQueue.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }
}
