package com.hk47.arcana;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextManager {
    private static BitmapFont bitmapFont = new BitmapFont();
    private static SpriteBatch spriteBatchHandle;

    public static void setSpriteBatch(SpriteBatch batch) {
        spriteBatchHandle = batch;
    }

    public static void draw(CharSequence msg) {
        bitmapFont.draw(spriteBatchHandle, msg, 40, 40);
    }
}
