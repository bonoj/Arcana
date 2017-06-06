package com.hk47.arcana.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.hk47.arcana.Mappers;
import com.hk47.arcana.components.TransformComponent;
import com.hk47.arcana.components.WorldMapComponent;

public class WorldMapSystem extends IteratingSystem {

    private static final Family FAMILY =
            Family.all(WorldMapComponent.class, TransformComponent.class).get();

    public WorldMapSystem() {
        super(FAMILY);

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        WorldMapComponent worldMapComponent = Mappers.worldMap.get(entity);
    }
}
