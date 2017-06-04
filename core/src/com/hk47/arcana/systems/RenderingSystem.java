package com.hk47.arcana.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.hk47.arcana.components.TextureComponent;
import com.hk47.arcana.components.TransformComponent;

public class RenderingSystem extends IteratingSystem {

    static final float PIXELS_TO_METERS = 1.0f / 32.0f;

    private static final Family family = Family.all(TransformComponent.class, TextureComponent.class).get();

    private SpriteBatch batch;
    private Array<Entity> renderQueue;


    private ComponentMapper<TextureComponent> textureMapper;
    private ComponentMapper<TransformComponent> transformMapper;

    public RenderingSystem(SpriteBatch batch) {
        super(family);

        textureMapper = ComponentMapper.getFor(TextureComponent.class);
        transformMapper = ComponentMapper.getFor(TransformComponent.class);

        renderQueue = new Array<Entity>();

        this.batch = batch;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        batch.begin();

        for (Entity entity : renderQueue) {

            TextureComponent tex = textureMapper.get(entity);

            if (tex.texture == null) {
                continue;
            }

            TransformComponent t = transformMapper.get(entity);

            batch.draw(tex.texture, t.position.x, t.position.y);
        }

        batch.end();
        renderQueue.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }
}
