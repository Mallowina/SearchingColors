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

public class LevelsScreen implements Disposable {
    public Stage stage;
    private Viewport viewport;

    private BitmapFont font;
    private TextureAtlas buttonsAtlas;
    private Skin buttonSkin;

    private TextButton btn1;
    private TextButton btn2;
    private TextButton btn3;
    private TextButton btn4;
    private TextButton btn5;
    private TextButton btn6;
    private TextButton btnBack;

    private int BUTTON_SIZE = 40;

    public LevelsScreen(SpriteBatch sb, final StartClass game) {
        viewport = new FitViewport(StartClass.V_WIDTH, StartClass.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Gdx.input.setInputProcessor(stage);

        font = new BitmapFont();
        buttonsAtlas = new TextureAtlas("images/buttons/packs/btn_change_lvl.pack");
        buttonSkin = new Skin();
        buttonSkin.addRegions(buttonsAtlas);

        /*button 1*/
        TextButton.TextButtonStyle style1 = new TextButton.TextButtonStyle(); //** Button properties **//
        style1.up = buttonSkin.getDrawable("lbl1");
        style1.font = font;
        btn1 = new TextButton("", style1); //** Button text and style **//

        /*button 2*/
        TextButton.TextButtonStyle style2 = new TextButton.TextButtonStyle(); //** Button properties **//
        style2.up = buttonSkin.getDrawable("lbl2");
        style2.font = font;
        btn2 = new TextButton("", style2); //** Button text and style **//

        /*button 3*/
        TextButton.TextButtonStyle style3 = new TextButton.TextButtonStyle(); //** Button properties **//
        style3.up = buttonSkin.getDrawable("lbl3");
        style3.font = font;
        btn3 = new TextButton("", style3); //** Button text and style **//

        /*button 4*/
        TextButton.TextButtonStyle style4 = new TextButton.TextButtonStyle(); //** Button properties **//
        style4.up = buttonSkin.getDrawable("lbl4");
        style4.font = font;
        btn4 = new TextButton("", style4); //** Button text and style **//

        /*button 5*/
        TextButton.TextButtonStyle style5 = new TextButton.TextButtonStyle(); //** Button properties **//
        style5.up = buttonSkin.getDrawable("lbl5");
        style5.font = font;
        btn5 = new TextButton("", style5); //** Button text and style **//

        /*button 6*/
        TextButton.TextButtonStyle style6 = new TextButton.TextButtonStyle(); //** Button properties **//
        style6.up = buttonSkin.getDrawable("lbl6");
        style6.font = font;
        btn6 = new TextButton("", style6); //** Button text and style **//



        /*button back*/
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

        btn1.setWidth(BUTTON_SIZE);
        btn1.setWidth(BUTTON_SIZE);
        btn1.setPosition(btn1.getWidth() + 100, StartClass.V_HEIGHT - btn1.getHeight() - 60);


        /**ON CLICKS**/
        btn1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.changeState(StartClass.PrevStates.STATE1);
            }
        });


        Group group = new Group();
        group.addActor(btnBack);
        group.addActor(btn1);

        stage.addActor(group);
    }

    @Override
    public void dispose() {

    }
}
