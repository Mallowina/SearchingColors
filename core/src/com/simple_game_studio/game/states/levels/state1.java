package com.simple_game_studio.game.states.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.simple_game_studio.game.Screens.Control;
import com.simple_game_studio.game.Sprites.Spike;
import com.simple_game_studio.game.StartClass;
import com.simple_game_studio.game.tools.B2WorldCreator;
import com.simple_game_studio.game.tools.WorldContactListener;

import com.simple_game_studio.game.tools.LevelCreator;

public class state1 extends LevelCreator {

    private Spike spike;

    public state1(StartClass game) {
        super(game);
        System.out.println("start lvl1");

        map = mapLoader.load("levels/lvl1/lvl1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1/StartClass.PPM);
        new B2WorldCreator(world, map);
        control = new Control(game.batch);
        world.setContactListener(new WorldContactListener());

        spike = new Spike(world, map, 50, 180);
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



        spike.update(dt);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        b2dr.render(world, camera.combined);
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        spike.draw(game.batch);
        game.batch.end();
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
