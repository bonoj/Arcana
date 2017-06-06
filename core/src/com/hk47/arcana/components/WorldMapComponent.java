package com.hk47.arcana.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;

public class WorldMapComponent implements Component {
    public TiledMap tiledMap;
    public TiledMapRenderer tiledMapRenderer;
}
