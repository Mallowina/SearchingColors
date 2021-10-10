package com.simple_game_studio.game.states;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.simple_game_studio.game.Screens.BackScreen;
import com.simple_game_studio.game.StartClass;

public class SettingsState implements Screen {
    private StartClass game;
    private OrthographicCamera camera;
    private BackScreen backScreen;

    Texture background;

    public SettingsState(StartClass game) {
        System.out.println("start settings state");

        this.game = game;

        backScreen = new BackScreen(game.batch, game);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, StartClass.V_WIDTH, StartClass.V_HEIGHT);

        background = new Texture("levels/levels_menu_bg.png");
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

        backScreen.update(delta);
        backScreen.stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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
