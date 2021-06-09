package com.simple_game_studio.game.states.levels;

import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.simple_game_studio.game.Screens.Control;
import com.simple_game_studio.game.StartClass;
import com.simple_game_studio.game.tools.B2WorldCreator;
import com.simple_game_studio.game.tools.PlayerGestureDetector;
import com.simple_game_studio.game.tools.WorldContactListener;

import com.simple_game_studio.game.tools.LevelCreator;

public class state1 extends LevelCreator {

    public state1(StartClass game) {
        super(game);
        System.out.println("start lvl1");

        map = mapLoader.load("levels/lvl1/lvl1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1/StartClass.PPM);

        PlayerGestureDetector.now_deltaXLeft = false;
        PlayerGestureDetector.now_deltaXRight = false;

        new B2WorldCreator(world, map);

        control = new Control(game.batch);

        world.setContactListener(new WorldContactListener());
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    protected void handleInput(float dt) {
        super.handleInput(dt);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
