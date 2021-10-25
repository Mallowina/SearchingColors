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

public class MenuState implements Screen {
    private StartClass game;

    boolean clickSettings = false;
    boolean clickStart = false;

    /*Create textures*/
    private Texture background;
    private OrthographicCamera camera;

    /*Add music*/
    private Music music;

    /****************************/
    public Stage stage;
    private Viewport viewport;

    Label startLabel;

    private BitmapFont font; //** same as that used in Tut 7 **//
    private TextureAtlas buttonsAtlas; //** image of buttons **//
    private Skin buttonSkin; //** images are used as skins of the button **//
    private TextButton buttonSettings; //** the button - the only actor in program **//
    private TextButton buttonStart; //** the button - the only actor in program **//
    /*****************************/

    public MenuState(StartClass game) {
        System.out.println("MenuState start");

        this.game = game;

        background = new Texture("images/main_menu_bg.png");

        camera = new OrthographicCamera();
        camera.setToOrtho(false, StartClass.V_WIDTH, StartClass.V_HEIGHT);

        music = StartClass.manager.get("audio/music/menu_theme.mp3", Music.class);
        music.setLooping(true);
        music.setVolume(150);
        music.play();

        /************************************************************************/
        viewport = new FitViewport(StartClass.V_WIDTH, StartClass.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, game.batch);

        Gdx.input.setInputProcessor(stage);

        font = new BitmapFont();

        buttonsAtlas = new TextureAtlas("images/buttons/packs/button_executed.pack");
        buttonSkin = new Skin();
        buttonSkin.addRegions(buttonsAtlas);
        TextButton.TextButtonStyle style1 = new TextButton.TextButtonStyle();
        style1.up = buttonSkin.getDrawable("settings");
        style1.font = font;

        buttonSettings = new TextButton("", style1);
        buttonSettings.setHeight(40);
        buttonSettings.setWidth(40);

        buttonsAtlas = new TextureAtlas("images/buttons/packs/button_main_menu.pack");
        buttonSkin = new Skin();
        buttonSkin.addRegions(buttonsAtlas);
        TextButton.TextButtonStyle style2 = new TextButton.TextButtonStyle();
        style2.up = buttonSkin.getDrawable("start");
        style2.font = font;

        buttonStart = new TextButton("", style2);
        buttonStart.setHeight(140);
        buttonStart.setWidth(160);

        buttonSettings.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("btn click");
                clickSettings = true;
            }
        });

        buttonStart.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickStart = true;
            }
        });


        /*BUTTONS POSITION*/
        buttonSettings.setPosition(StartClass.V_WIDTH - 70, StartClass.V_HEIGHT - 60);
        buttonStart.setPosition(StartClass.V_WIDTH / 2 - buttonStart.getWidth() / 2, 20);

        Group btn_menu_group = new Group();
        btn_menu_group.addActor(buttonSettings);
        btn_menu_group.addActor(buttonStart);


        stage.addActor(btn_menu_group);
    }





    public void update(float dt) {
        if (clickSettings) {
            game.setScreen(new SettingsState(game));
        } else if (clickStart) {
            game.setScreen(new LevelsState(game));
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

        game.batch.setProjectionMatrix(stage.getCamera().combined);
        stage.draw();

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
