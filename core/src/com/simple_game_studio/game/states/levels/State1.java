package com.simple_game_studio.game.states.levels;

import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.simple_game_studio.game.screens.ControlScreen;
import com.simple_game_studio.game.screens.StoryScreen;
import com.simple_game_studio.game.sprites.Spike;
import com.simple_game_studio.game.StartClass;
import com.simple_game_studio.game.tools.B2WorldCreator;
import com.simple_game_studio.game.tools.WorldContactListener;
import com.simple_game_studio.game.tools.LevelCreator;

public class State1 extends LevelCreator {
    public State1(StartClass game) {
        super(game);
        map = mapLoader.load("images/levels/lvl1/lvl1.tmx");
        world.setContactListener(new WorldContactListener());

        renderer = new OrthogonalTiledMapRenderer(map, 1/StartClass.PPM);
        controlScreen = new ControlScreen(game.batch);
        creator = new B2WorldCreator(world, map);
        storyScreen = new StoryScreen(game.batch, "story1");
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
        for (Spike spike : creator.getSpikes()) {
            spike.update(dt);
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        b2dr.render(world, camera.combined);
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        for (Spike spike : creator.getSpikes()) {
            spike.draw(game.batch);
        }
        game.batch.end();
//        storyScreen.stage.draw();
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
