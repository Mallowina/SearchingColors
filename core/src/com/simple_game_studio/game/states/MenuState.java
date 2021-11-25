package com.simple_game_studio.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.simple_game_studio.game.StartClass;
import com.simple_game_studio.game.screens.MenuScreen;
import com.simple_game_studio.game.tools.AppPreferences;

public class MenuState implements Screen {
    private StartClass game;
    private MenuScreen menuScreen;
    private AppPreferences appPreferences;

    /*Create textures*/
    private Texture background;
    private OrthographicCamera camera;

    /*Add music*/
    private Music music;

    public MenuState(StartClass game) {
        System.out.println("MenuState start");

        this.game = game;
        appPreferences = new AppPreferences();
        menuScreen = new MenuScreen(game.batch, game);

        background = new Texture("images/main_menu_bg.png");

        camera = new OrthographicCamera();
        camera.setToOrtho(false, StartClass.V_WIDTH, StartClass.V_HEIGHT);

        music = StartClass.manager.get("audio/music/menu_theme.mp3", Music.class);

        if (appPreferences.isMusicEnabled()) {
            music.setLooping(true);
            music.setVolume(appPreferences.getMusicVolume());
            music.play();
        }
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

        menuScreen.stage.draw();
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
