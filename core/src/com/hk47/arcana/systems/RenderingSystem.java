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
            Family.all(TransformComponent.class, TextureComponent.class).get();

//    private static final float PIXELS_PER_METER = 32.0f; // Tile size
//    private static final float FRUSTUM_WIDTH = Gdx.graphics.getWidth() / PIXELS_PER_METER;
//    private static final float FRUSTUM_HEIGHT = Gdx.graphics.getHeight() / PIXELS_PER_METER;
//
//    private static final float PIXELS_TO_METERS = 1.0f / PIXELS_PER_METER;
//
//    private static Vector2 meterDimensions = new Vector2();
//    private static Vector2 pixelDimensions = new Vector2();
//
//    public static Vector2 getScreenSizeInMeters(){
//        meterDimensions.set(FRUSTUM_WIDTH * PIXELS_TO_METERS,
//                FRUSTUM_HEIGHT * PIXELS_TO_METERS);
//        return meterDimensions;
//    }
//
//    public static Vector2 getScreenSizeInPixels(){
//        pixelDimensions.set(FRUSTUM_WIDTH,FRUSTUM_HEIGHT);
//        return pixelDimensions;
//    }
//
//    public static float convertPixelsToMeters(float pixelValue){
//        return pixelValue * PIXELS_TO_METERS;
//    }

    private SpriteBatch batch;
    private Array<Entity> renderQueue;

//    private OrthographicCamera camera;

    public RenderingSystem(SpriteBatch batch) {
        super(FAMILY);

        renderQueue = new Array<Entity>();

        this.batch = batch;

         // Create and center camera with dimensions equal to the screen size in meters
//        camera = new OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
//        camera.setToOrtho(false, FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
//        camera.position.set(FRUSTUM_WIDTH / 2.0f, FRUSTUM_HEIGHT / 2.0f, 0);


//        camera = new OrthographicCamera();
//        camera.setToOrtho(false);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

//        camera.update();
//        Gdx.app.log("Camera at ", "X: " + camera.position.x + " Y: " + camera.position.y);
//
//        batch.setProjectionMatrix(camera.combined);
        // batch.enableBlending();
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

//    public OrthographicCamera getCamera() {
//        return camera;
//    }
}
