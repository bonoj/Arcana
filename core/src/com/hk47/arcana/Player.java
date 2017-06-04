package com.hk47.arcana;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Player extends Sprite {

    private float momentum = 0;
    private Vector2 gravityVector = new Vector2(0,-1);
    private Vector2 momentumVector = new Vector2(0, 0);
    private Vector2 jumpVector = new Vector2(0,1);

    public Player(Texture texture, float x, float y) {
        this.setTexture(texture);
        this.setPosition(x, y);
    }

    public void update() {

        int speed = 5;

        float frames = Gdx.graphics.getFramesPerSecond();
        frames = (frames == 0) ? 60 : frames;
        float gravity = 19.6f / frames;

        if (this.getY() <= 64) {
            momentumVector.y = 0;
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                momentumVector.y = gravity * 25;
            }
        } else {
            //momentum = Math.min(gravity * frames, momentum + gravity);
            momentumVector.y -= gravity;
        }
        
        momentumVector.x = GameInput.keyForce.x * speed;

//        this.translateX(gravityVector.x * momentum);
//        this.translateY(gravityVector.y * momentum);
        this.translateX(momentumVector.x);
        this.translateY(momentumVector.y);
        this.setY(Math.max(this.getY(), 64));
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(this.getTexture(), this.getX(), this.getY());
    }
}
