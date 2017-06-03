package com.hk47.arcana;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class TextManager {
    private static BitmapFont bitmapFont = new BitmapFont();
    private static SpriteBatch spriteBatchHandle;

    public static void setSpriteBatch(SpriteBatch batch) {
        spriteBatchHandle = batch;
    }

    public static void draw(CharSequence msg, OrthographicCamera camera) {
        Vector3 position = new Vector3(20, 20, 0);
        camera.unproject(position);
        bitmapFont.draw(spriteBatchHandle, msg, position.x, position.y);
    }
}
