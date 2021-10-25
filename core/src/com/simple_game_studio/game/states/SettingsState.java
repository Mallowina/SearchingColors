package com.simple_game_studio.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.simple_game_studio.game.StartClass;
import com.simple_game_studio.game.screens.BackScreen;
import com.simple_game_studio.game.screens.SettingsScreen;

public class SettingsState implements Screen {
    private StartClass game;
    private SettingsScreen settingsScreen;
    private BackScreen backScreen;

    private OrthographicCamera camera;

    Texture background;

    public SettingsState(StartClass game) {
        System.out.println("start settings state");

        this.game = game;
        settingsScreen = new SettingsScreen(game.batch);
        backScreen = new BackScreen(game.batch, game);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, StartClass.V_WIDTH, StartClass.V_HEIGHT);

        background = new Texture("images/main_menu_bg.png");

        Gdx.input.setInputProcessor(new InputMultiplexer());

        InputMultiplexer inputMultiplexer = (InputMultiplexer) Gdx.input.getInputProcessor();
        inputMultiplexer.addProcessor(0, settingsScreen.stage);
        inputMultiplexer.addProcessor(1, backScreen.stage);

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

//        game.batch.setProjectionMatrix(settingsScreen.stage.getCamera().combined);

        settingsScreen.stage.getViewport().apply();
        settingsScreen.stage.act();
        settingsScreen.stage.draw();

        backScreen.stage.getViewport().apply();
        backScreen.stage.act();
        backScreen.stage.draw();


    }


    @Override
    public void resize(int width, int height) {
        settingsScreen.stage.getViewport().update(width, height, true);
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

    }
}
