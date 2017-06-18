package com.hk47.arcana.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.math.Vector2;
import com.hk47.arcana.Level;
import com.hk47.arcana.Mappers;
import com.hk47.arcana.components.MovementComponent;
import com.hk47.arcana.components.TransformComponent;

import java.util.ArrayList;

public class CollisionSystem extends IteratingSystem {

    private static final Family FAMILY =
            Family.all(TransformComponent.class, MovementComponent.class).get();

    private ArrayList<ArrayList<Vector2>> platforms;

    public CollisionSystem(Level level) {
        super(FAMILY);

        MapLayer collisionObjectLayer = level.tiledMap.getLayers().get("collisionObjects");
        MapObjects objects = collisionObjectLayer.getObjects();


        platforms = new ArrayList<ArrayList<Vector2>>();

        for (MapObject object : objects) {
            ArrayList<Vector2> platform = new ArrayList<Vector2>();

            PolylineMapObject polyline = (PolylineMapObject) object;

            float[] vertices = polyline.getPolyline().getTransformedVertices();
//            for (float vertex : vertices) {
//                Gdx.app.log("Vertex at: ", "" + vertex);
//            }
            for (int i = 0; i < vertices.length - 1; i += 2) {
                platform.add(new Vector2(vertices[i], vertices[i + 1]));

//                if (i % 2 == 0) {
//                    Gdx.app.log("X: ", vertices[i] + "");
//                } else {
//                    Gdx.app.log("Y: ", vertices[i] + "");
//                }
            }
            platforms.add(platform);
        }

//        Gdx.app.log("Platforms: ", "" + platforms.size());
//        Gdx.app.log("Platform 1 start: ", "" + platforms.get(0).get(0).x + "," + platforms.get(0).get(0).y);
//        Gdx.app.log("Platform 1 end: ", "" + platforms.get(0).get(platforms.get(0).size() - 1).x + "," + platforms.get(0).get(platforms.get(0).size() - 1).y);
//        Gdx.app.log("Platform 2 start: ", "" + platforms.get(1).get(0).x + "," + platforms.get(1).get(0).y);
//        Gdx.app.log("Platform 2 end: ", "" + platforms.get(1).get(platforms.get(1).size() - 1).x + "," + platforms.get(1).get(platforms.get(1).size() - 1).y);
//        Gdx.app.log("Platform 3 start: ", "" + platforms.get(2).get(0).x + "," + platforms.get(2).get(0).y);
//        Gdx.app.log("Platform 3 end: ", "" + platforms.get(2).get(platforms.get(2).size() - 1).x + "," + platforms.get(2).get(platforms.get(2).size() - 1).y);
//        Gdx.app.log("Platform 3 start: ", "" + platforms.get(2).get(0).x + "," + platforms.get(2).get(0).y);
    }

    private ArrayList<Vector2> currentPlatform = null;

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transformComponent = Mappers.transform.get(entity);
        MovementComponent movementComponent = Mappers.movement.get(entity);


        // Handling level collision pre-Box2D, this will all change

        // Temporarily testing state here.
        final int falling = 2;
        final int jumping = 1;
        final int walking = 0;
        int state = 2;

        // Pre PlayerStateSystem or GravitySystem jump test, will be removed!
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            state = 1;
            currentPlatform = null;
        }

        // Debug reset, DELETE ME! :)
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            transformComponent.position.x = 320;
            transformComponent.position.y = 400;
            movementComponent.velocity.y = 0;
            movementComponent.acceleration.y = -1500f;
        }

        if (currentPlatform == null) {
            int j = 0;
            for (ArrayList<Vector2> platform : platforms) {
                Gdx.app.log("", j++ + ": platY: " + platform.get(0).y + " playerY: " + transformComponent.position.y);
                if (transformComponent.position.y >= platform.get(0).y) {
                    if (transformComponent.position.x >= platform.get(0).x && transformComponent.position.x <= platform.get(platform.size() - 1).x) {
                        Gdx.app.log("", "Platform created.");
                        currentPlatform = platform;
                    }
                }
            }
        }

        if (currentPlatform != null) {
            if (transformComponent.position.x < currentPlatform.get(0).x || transformComponent.position.x > currentPlatform.get(currentPlatform.size() - 1).x) {
                currentPlatform = null;
                Gdx.app.log("", "Platform destroyed.");
                if (state != 1) {
                    state = 2;
                    movementComponent.acceleration.y = -1500f;
                }
                return;
            }

            if (state != 0 && transformComponent.position.y < currentPlatform.get(0).y) {

                // Player landed on a platform
                state = 0;
                movementComponent.acceleration.y = 0;
                movementComponent.velocity.y = 0;
                transformComponent.position.y = currentPlatform.get(0).y;
        }





//                    if (state != 1) {
//                        state = 2;
//                        // Player is no longer on platform and is not jumping, so apply gravity
//                        movementComponent.acceleration.y = -1500f;
//
//                    }


        }

    }
}
//                    if (y < currentPlatform.get(0).y) {
//
//                        // Player landed on a platform
//                        state = 0;
//                        movementComponent.acceleration.y = 0;
//                        movementComponent.velocity.y = 0;
//                        transformComponent.position.y = platform.get(0).y;
//                    }
//                } else {
//                    if (state != 1) {
//                        state = 2;
//                    }
////                    if (state != 1) {
////                        state = 2;
////                        // Player is no longer on platform and is not jumping, so apply gravity
////                        movementComponent.acceleration.y = -1500f;
////                    }
//                }
//            }
//            if (state == 2) {
//                // Player is no longer on platform and is not jumping, so apply gravity
////                        movementComponent.acceleration.y = -1500f;
//            }
//
//
//        }
//
////        if (transformComponent.position.y < 64) {
////            movementComponent.acceleration.y = 0;
////            movementComponent.velocity.y = 0;
////            transformComponent.position.y = 64;
////        }
//    }
//}
