package com.simple_game_studio.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.simple_game_studio.game.StartClass;
import com.simple_game_studio.game.states.levels.state1;

public class LevelsState implements Screen {
    private StartClass game;

    private OrthographicCamera camera;

    Texture background;
    Texture btn1;
    Texture btn2;
    Texture btn3;
    Texture btn4;
    Texture btn5;
    Texture btn6;

    Rectangle lbl1;
    Rectangle lbl2;
    Rectangle lbl3;
    Rectangle lbl4;
    Rectangle lbl5;
    Rectangle lbl6;

    public LevelsState(StartClass game) {
        this.game = game;
        System.out.println("start levels state");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 900, 500);

        background = new Texture("levels/levels_menu_bg.png");
        btn1 = new Texture("levels/lvl1/lbl1.png");
        btn2 = new Texture("levels/lvl2/lbl2.png");
        btn3 = new Texture("levels/lvl3/lbl3.png");
        btn4 = new Texture("levels/lvl4/lbl4.png");
        btn5 = new Texture("levels/lvl5/lbl5.png");
        btn6 = new Texture("levels/lvl6/lbl6.png");

        lbl1 = new Rectangle(200, 300, 100, 100);
        lbl2 = new Rectangle(350, 300, 100, 100);
        lbl3 = new Rectangle(500, 300, 100, 100);
        lbl4 = new Rectangle(200, 100, 100, 100);
        lbl5 = new Rectangle(350, 100, 100, 100);
        lbl6 = new Rectangle(500, 100, 100, 100);
    }


    protected void handleInput() {

        if (Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);

            if ((touchPos.x >= lbl1.x && touchPos.x <= lbl1.x + 100) && (touchPos.y >= lbl1.y && touchPos.y <= lbl1.y + 100))
               game.setScreen(new state1(game));
//            else if ((touchPos.x >= lbl2.x && touchPos.x <= lbl2.x + 100) && (touchPos.y >= lbl2.y && touchPos.y <= lbl2.y + 100))
//                gsm.set(new Level_2_State(gsm));
//            else if ((touchPos.x >= lbl3.x && touchPos.x <= lbl3.x + 100) && (touchPos.y >= lbl3.y && touchPos.y <= lbl3.y + 100))
//                gsm.set(new Level_3_State(gsm));
//            else if ((touchPos.x >= lbl4.x && touchPos.x <= lbl4.x + 100) && (touchPos.y >= lbl4.y && touchPos.y <= lbl4.y + 100))
//                gsm.set(new Level_4_State(gsm));
//            else if ((touchPos.x >= lbl5.x && touchPos.x <= lbl5.x + 100) && (touchPos.y >= lbl5.y && touchPos.y <= lbl5.y + 100))
//                gsm.set(new Level_5_State(gsm));
//            else if ((touchPos.x >= lbl6.x && touchPos.x <= lbl6.x + 100) && (touchPos.y >= lbl6.y && touchPos.y <= lbl6.y + 100))
//                gsm.set(new Level_6_State(gsm));
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
        game.batch.draw(btn1, lbl1.x, lbl1.y);
        game.batch.draw(btn2, lbl2.x, lbl2.y);
        game.batch.draw(btn3, lbl3.x, lbl3.y);
        game.batch.draw(btn4, lbl4.x, lbl4.y);
        game.batch.draw(btn5, lbl5.x, lbl5.y);
        game.batch.draw(btn6, lbl6.x, lbl6.y);
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
        btn1.dispose();
        btn2.dispose();
        btn3.dispose();
        btn4.dispose();
        btn5.dispose();
        btn6.dispose();
    }
}
