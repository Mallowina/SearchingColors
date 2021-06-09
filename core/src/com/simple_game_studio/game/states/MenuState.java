package com.simple_game_studio.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.simple_game_studio.game.StartClass;

public class MenuState implements Screen {
    private StartClass game;

    /*Create textures*/
    private Texture background;
    private OrthographicCamera camera;

    public MenuState(StartClass game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, StartClass.V_WIDTH, StartClass.V_HEIGHT);
        System.out.println("MenuState start");
        background = new Texture("main_menu_bg.png");
    }


    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            game.setScreen(new LevelsState(game));
        }
    }


    public void update(float dt) {
        handleInput();
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

        update(delta);
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
        background.dispose();
    }
}
