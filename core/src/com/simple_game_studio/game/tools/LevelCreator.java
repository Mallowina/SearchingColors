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
import com.simple_game_studio.game.screens.StoryScreen;
import com.simple_game_studio.game.sprites.Player;
import com.simple_game_studio.game.StartClass;
import com.simple_game_studio.game.states.GameOverState;

public abstract class LevelCreator implements Screen {
    protected StartClass game;
    protected ControlScreen controlScreen;
    protected StoryScreen storyScreen;

    protected OrthographicCamera camera;
    protected Viewport gamePort;

    //Tiled map variables
    protected TmxMapLoader mapLoader;
    protected TiledMap map;
    protected OrthogonalTiledMapRenderer renderer;

    //Box2d variables
    protected World world;
    protected Box2DDebugRenderer b2dr;
    protected B2WorldCreator creator;

    //sprites
    protected Player player;

//    GestureDetector gestureDetector;


    public LevelCreator(StartClass game) {
        this.game = game;
        world = new World(new Vector2(0, -10), true);
        b2dr = new Box2DDebugRenderer();

        camera = new OrthographicCamera();
        gamePort = new FitViewport(StartClass.V_WIDTH /StartClass.PPM, StartClass.V_HEIGHT/StartClass.PPM, camera);
        camera.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2 + 0.5f, 0);

        mapLoader = new TmxMapLoader();
        player = new Player(world);
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

        if (player.currentState != Player.State.DEAD)
            if (player.b2body.getPosition().x > 4)
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
        b2dr.setDrawBodies(false);

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        player.draw(game.batch);
        game.batch.end();

        if (!StartClass.STORY) {
            controlScreen.update(delta);
            controlScreen.stage.draw();
            player.update(delta);
        } else {
            storyScreen.stage.draw();
            storyScreen.update(delta);
        }

        if (gameOver()) {
            game.setScreen(new GameOverState(game));
            dispose();
        }

    }

    public boolean gameOver(){
        if (player.currentState == Player.State.DEAD && player.getStateTimer() > 2) {
            return true;
        } else return false;
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
