package com.simple_game_studio.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.simple_game_studio.game.screens.LevelsScreen;
import com.simple_game_studio.game.StartClass;

public class LevelsState implements Screen {
    private StartClass game;
    private LevelsScreen levelsScreen;

    private OrthographicCamera camera;

    Texture background;

    public LevelsState(StartClass game) {
        System.out.println("start levels state");

        this.game = game;

        levelsScreen = new LevelsScreen(game.batch, game);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, StartClass.V_WIDTH, StartClass.V_HEIGHT);

        background = new Texture("images/levels/levels_menu_bg.png");
    }


    protected void handleInput() {

        if (Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
        }
    }


    public void update(float dt) {
//        handleInput();
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

        levelsScreen.stage.draw();

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
