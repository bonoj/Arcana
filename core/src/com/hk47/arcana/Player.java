package com.hk47.arcana;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Player extends Sprite {

    private float momentum = 0;
    private Vector2 gravityVector = new Vector2(0,-1);

    public Player(Texture texture, float x, float y) {
        this.setTexture(texture);
        this.setPosition(x, y);
    }

    public void update() {
        // Gravity temporarily handled here.
        float frames = Gdx.graphics.getFramesPerSecond();
        frames = (frames == 0) ? 60 : frames;
        float gravity = 20f / frames;
        momentum = Math.min(gravity * frames, momentum + gravity);

        this.translateX(gravityVector.x * momentum);
        this.translateY(gravityVector.y * momentum);
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(this.getTexture(), this.getX(), this.getY());
    }
}
