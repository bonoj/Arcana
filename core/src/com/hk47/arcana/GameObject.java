package com.hk47.arcana;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameObject {
    public float x = 0;
    public float y = 0;

    private Texture texture;
    private SpriteBatch spriteBatchHandle;

    public GameObject(String texturePath, SpriteBatch batch, float posX, float posY) {
        texture = new Texture(texturePath);
        spriteBatchHandle = batch;
        x = posX;
        y = posY;
    }

    public void updatePosition() {
        int speed = 5;

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            x -= speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            x += speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            y -= speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            y += speed;
        }
    }

    public void draw() {
        spriteBatchHandle.draw(texture, x, y);
    }
}
