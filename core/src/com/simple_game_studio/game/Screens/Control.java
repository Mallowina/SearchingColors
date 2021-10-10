package com.simple_game_studio.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.simple_game_studio.game.StartClass;

public class Control implements Disposable{
    public Stage stage;
    private Viewport viewport;

    private BitmapFont font; //** same as that used in Tut 7 **//
    private TextureAtlas buttonsAtlas; //** image of buttons **//
    private Skin buttonSkin; //** images are used as skins of the button **//

    private TextButton btn_right;
    private TextButton btn_left;
    private TextButton btn_up;

    public static boolean RIGHT = false;
    public static boolean LEFT = false;
    public static boolean UP = false;

    private int BUTTON_SIZE = 80;

    public Control(SpriteBatch sb) {
        viewport = new FitViewport(StartClass.V_WIDTH, StartClass.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Gdx.input.setInputProcessor(stage);

        /**CREATING BUTTONS*/
        buttonsAtlas = new TextureAtlas("buttons/packs/button_move.pack"); //** button atlas image **//
        buttonSkin = new Skin();
        buttonSkin.addRegions(buttonsAtlas); //** skins for on and off **//
        font = new BitmapFont(); //** font **//

        /*button right*/
        TextButton.TextButtonStyle style_right = new TextButton.TextButtonStyle(); //** Button properties **//
        style_right.up = buttonSkin.getDrawable("btn_right");
        style_right.font = font;
        btn_right = new TextButton("", style_right); //** Button text and style **//

        /*button left*/
        TextButton.TextButtonStyle style_left = new TextButton.TextButtonStyle(); //** Button properties **//
        style_left.up = buttonSkin.getDrawable("btn_left");
        style_left.font = font;
        btn_left = new TextButton("", style_left); //** Button text and style **//


        /*button up*/
        TextButton.TextButtonStyle style_up = new TextButton.TextButtonStyle(); //** Button properties **//
        style_up.up = buttonSkin.getDrawable("btn_up");
        style_up.font = font;
        btn_up = new TextButton("", style_up); //** Button text and style **//

        btn_up.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!StartClass.STORY_STATE) UP = true;
            }
        });

        /*BUTTONS POSITION*/
        btn_up.setPosition(650, 20);
        btn_right.setPosition(150, 20);
        btn_left.setPosition(30, 20);

        /*BUTTONS SIZE*/
        btn_up.setWidth(BUTTON_SIZE);
        btn_up.setHeight(BUTTON_SIZE);
        btn_left.setWidth(BUTTON_SIZE);
        btn_left.setHeight(BUTTON_SIZE);
        btn_right.setWidth(BUTTON_SIZE);
        btn_right.setHeight(BUTTON_SIZE);

        Group buttons_move_group = new Group();
        buttons_move_group.addActor(btn_up);
        buttons_move_group.addActor(btn_left);
        buttons_move_group.addActor(btn_right);

        stage.addActor(buttons_move_group);
    }

    public void update(float dt) {
        if (btn_right.isPressed()) {
            RIGHT = true;
            LEFT = false;
        } else if (btn_left.isPressed()) {
            LEFT = true;
            RIGHT = false;
        }
        else {
            btn_up.getClickListener().inTapSquare();
            RIGHT = false;
            LEFT = false;
        }
    }


    @Override
    public void dispose() {
        stage.dispose();
        font.dispose();
        buttonsAtlas.dispose();
        buttonSkin.dispose();
    }
}
