package com.simple_game_studio.game.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.simple_game_studio.game.screens.ControlScreen;
import com.simple_game_studio.game.sprites.Player;
import com.simple_game_studio.game.StartClass;

public abstract class LevelCreator implements Screen {
    protected StartClass game;
    protected ControlScreen controlScreen;

    protected OrthographicCamera camera;
    protected Viewport gamePort;

    //Tiled map variables
    protected TmxMapLoader mapLoader;
    protected TiledMap map;
    protected OrthogonalTiledMapRenderer renderer;

    //Box2d variables
    protected World world;
    protected Box2DDebugRenderer b2dr;

    //sprites
    protected Player player;

//    GestureDetector gestureDetector;


    public LevelCreator(StartClass game) {
        this.game = game;

        camera = new OrthographicCamera();
        gamePort = new FitViewport(StartClass.V_WIDTH /StartClass.PPM, StartClass.V_HEIGHT/StartClass.PPM, camera);
        camera.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);

        mapLoader = new TmxMapLoader();

        world = new World(new Vector2(0, -10), true);
        b2dr = new Box2DDebugRenderer();

        player = new Player(world);

//        gestureDetector = new GestureDetector(new PlayerGestureDetector(player));
//        Gdx.input.setInputProcessor(gestureDetector);

        controlScreen = new ControlScreen(game.batch);

    }

    @Override
    public void show() {

    }

    protected void handleInput(float dt) {
    }

    public void update(float dt) {
        handleInput(dt);

        world.step(1/60f, 6, 2);

        camera.position.x = player.b2body.getPosition().x;

        camera.update();
        renderer.setView(camera);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        //Render our game map
        renderer.render();

        //render our Box2DDebugLines
        b2dr.render(world, camera.combined);

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        player.draw(game.batch);
        game.batch.end();

        if (!StartClass.STORY_STATE) {
            controlScreen.update(delta);
            controlScreen.stage.draw();
        } else {
            /**Control buttons hide**/
            ControlScreen.UP = false;
            ControlScreen.LEFT = false;
            ControlScreen.RIGHT = false;
        }

        player.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
    }
}
