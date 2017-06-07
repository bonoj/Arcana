# Arcana

Multiplatform - An entity-component system based 2D platformer powered by libGDX, Ashley ECS, and Tiled.

#### Engine
Currently using Ashley's **Engine** class. Will be migrating to **PooledEngine** in the near future, once the **AnimationSystem** has been implemented.

#### Entities
Entities do not have their own classes. They are added to the engine within the **Level** class.

#### Components
Components are essentially just specialized data containers. To create a player, for instance, simply create an entity and add a **CameraComponent**, **MovementComponent**, **TransformComponent**, and **TextureComponent**.

Currently implemented:
1. CameraComponent - Does not store a camera. Instead, tags an entity for focus from the **GameScreen**'s **Orthographic Camera**.
2. MovementComponent - Stores vectors for an entity's velocity and acceleration.
3. TransformComponent - Stores vectors for an entity's position and scale and a value for the entity's angle of rotation.
4. TextureComponent - Stores an entity's texture.

#### Systems
Systems handle the logic for each entity. An **IteratingSystem** is used to cycle through all entities to which the system applies. **Mappers** supply access to the components within the processEntity method. Systems are added to the engine in the **GameScreen** class, but can be added and removed as necessary within the **Level** class through the engine reference.

Currently implemented:
1. CameraSystem - Controls the camera's position to the position of the entity.
2. MovementSystem - Controls the entity's momentum over time by applying velocity and acceleration to the entity's position.
3. PlayerInputSytem - Listens for key presses (and screen touches) to modify an entity's position and movement as necessary.
4. RenderingSystem - Updates the **GameScreen**'s **SriteBatch** by iterating through its entity renderQueue.

#### Levels
The **Level** class adds and removes entities (and systems) from the engine as needed and also loads the level's **TiledMap** and **TiledMapRenderer**.
