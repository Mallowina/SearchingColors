package com.simple_game_studio.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.simple_game_studio.game.StartClass;

public class BackScreen implements Disposable {
    public Stage stage;
    private Viewport viewport;

    private BitmapFont font; //** same as that used in Tut 7 **//
    private TextureAtlas buttonsAtlas; //** image of buttons **//
    private Skin buttonSkin; //** images are used as skins of the button **//

    private TextButton btnBack; //** the button - the only actor in program **//

    public BackScreen(SpriteBatch sb, final StartClass game) {
        viewport = new FitViewport(StartClass.V_WIDTH, StartClass.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Gdx.input.setInputProcessor(stage);

        buttonsAtlas = new TextureAtlas("images/buttons/packs/button_executed.pack");
        buttonSkin = new Skin();
        buttonSkin.addRegions(buttonsAtlas);
        font = new BitmapFont();
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = buttonSkin.getDrawable("previous");
        style.font = font;

        btnBack = new TextButton("", style);
        btnBack.setHeight(40);
        btnBack.setWidth(40);
        btnBack.setPosition(btnBack.getWidth() - 20, StartClass.V_HEIGHT - btnBack.getHeight() - 20);

        btnBack.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("go to main menu");
                game.changeState(StartClass.PrevStates.MAIN_MENU);
            }
        });

        Group group = new Group();
        group.addActor(btnBack);

        stage.addActor(group);

    }

    @Override
    public void dispose() {

    }
}
