package com.simple_game_studio.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.simple_game_studio.game.StartClass;
import com.simple_game_studio.game.states.LevelsState;
import com.simple_game_studio.game.states.SettingsState;

public class MenuScreen implements Disposable {
    public Stage stage;
    private Viewport viewport;

    Label startLabel;

    private BitmapFont font; //** same as that used in Tut 7 **//
    private TextureAtlas buttonsAtlas; //** image of buttons **//
    private Skin buttonSkin; //** images are used as skins of the button **//
    private TextButton buttonSettings; //** the button - the only actor in program **//
    private TextButton buttonStart; //** the button - the only actor in program **//

    public MenuScreen(SpriteBatch sb, final StartClass game) {

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
                game.setScreen(new SettingsState(game));
            }
        });

        buttonStart.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LevelsState(game));
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

    @Override
    public void dispose() {

    }
}
