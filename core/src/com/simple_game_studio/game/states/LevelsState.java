package com.simple_game_studio.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.simple_game_studio.game.screens.BackScreen;
import com.simple_game_studio.game.screens.LevelsScreen;
import com.simple_game_studio.game.StartClass;

public class LevelsState implements Screen {
    private StartClass game;
    private LevelsScreen levelsScreen;
    private BackScreen backScreen;

    private OrthographicCamera camera;

    Texture background;

    public LevelsState(StartClass game) {
        System.out.println("start levels state");

        this.game = game;

        levelsScreen = new LevelsScreen(game.batch, game);
        backScreen = new BackScreen(game.batch, game);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, StartClass.V_WIDTH, StartClass.V_HEIGHT);

        background = new Texture("images/levels/levels_menu_bg.png");

        Gdx.input.setInputProcessor(new InputMultiplexer());

        InputMultiplexer inputMultiplexer = (InputMultiplexer) Gdx.input.getInputProcessor();
        inputMultiplexer.addProcessor(0, levelsScreen.stage);
        inputMultiplexer.addProcessor(1, backScreen.stage);
    }

    public void update(float dt) {

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(background, 0, 0);
        game.batch.end();

        levelsScreen.stage.getViewport().apply();
        levelsScreen.stage.act();
        levelsScreen.stage.draw();

        backScreen.stage.getViewport().apply();
        backScreen.stage.act();
        backScreen.stage.draw();

//        update(delta);
    }

    @Override
    public void resize(int width, int height) {
        levelsScreen.stage.getViewport().update(width, height, true);
        backScreen.stage.getViewport().update(width, height, false);
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
        background.dispose();
    }
}
