package com.hk47.arcana;

import com.badlogic.ashley.core.ComponentMapper;
import com.hk47.arcana.components.CameraComponent;
import com.hk47.arcana.components.MovementComponent;
import com.hk47.arcana.components.TextureComponent;
import com.hk47.arcana.components.TransformComponent;
import com.hk47.arcana.components.WorldMapComponent;

public class Mappers {

    public static final ComponentMapper<MovementComponent> movement =
            ComponentMapper.getFor(MovementComponent.class);

    public static final ComponentMapper<TextureComponent> texture =
            ComponentMapper.getFor(TextureComponent.class);

    public static final ComponentMapper<TransformComponent> transform =
            ComponentMapper.getFor(TransformComponent.class);

    public static final ComponentMapper<CameraComponent> camera =
            ComponentMapper.getFor(CameraComponent.class);

    public static final ComponentMapper<WorldMapComponent> worldMap =
            ComponentMapper.getFor(WorldMapComponent.class);

}
