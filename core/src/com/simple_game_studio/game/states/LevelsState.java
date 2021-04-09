package com.simple_game_studio.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.simple_game_studio.game.StartClass;
import com.simple_game_studio.game.states.levels.Level_1_State;
import com.simple_game_studio.game.states.levels.Level_2_State;
import com.simple_game_studio.game.states.levels.Level_3_State;
import com.simple_game_studio.game.states.levels.Level_4_State;
import com.simple_game_studio.game.states.levels.Level_5_State;
import com.simple_game_studio.game.states.levels.Level_6_State;
import com.simple_game_studio.game.tools.GameStateManager;
import com.simple_game_studio.game.tools.State;

public class LevelsState extends State {

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

    int count  = 0;

    public LevelsState(GameStateManager gsm) {
        super(gsm);
        System.out.println("start levels state");
        camera.setToOrtho(false, StartClass.WIDTH, StartClass.HEIGHT);

        background = new Texture("levels_menu_bg.png");
        btn1 = new Texture("lbl4.png");
        btn2 = new Texture("lbl5.png");
        btn3 = new Texture("lbl6.png");
        btn4 = new Texture("lbl1.png");
        btn5 = new Texture("lbl2.png");
        btn6 = new Texture("lbl3.png");

        lbl1 = new Rectangle(200, 100, 100, 100);
        lbl2 = new Rectangle(350, 100, 100, 100);
        lbl3 = new Rectangle(500, 100, 100, 100);
        lbl4 = new Rectangle(200, 300, 100, 100);
        lbl5 = new Rectangle(350, 300, 100, 100);
        lbl6 = new Rectangle(500, 300, 100, 100);
    }

    @Override
    protected void handleInput() {

        if (Gdx.input.isTouched()) {
            count++;
            if (count > 5) {
                int x = Gdx.input.getX();
                int y = Gdx.input.getY();
                System.out.println("x: " + x + "\ny: " + y);
                if ((x >= lbl1.x && x <= lbl1.x + 100) && (y >= lbl1.y && y <= lbl1.y + 100))
                    gsm.set(new Level_1_State(gsm));
                else if ((x >= lbl2.x && x <= lbl2.x + 100) && (y >= lbl2.y && y <= lbl2.y + 100))
                    gsm.set(new Level_2_State(gsm));
                else if ((x >= lbl3.x && x <= lbl3.x + 100) && (y >= lbl3.y && y <= lbl3.y + 100))
                    gsm.set(new Level_3_State(gsm));
                else if ((x >= lbl4.x && x <= lbl4.x + 100) && (y >= lbl4.y && y <= lbl4.y + 100))
                    gsm.set(new Level_4_State(gsm));
                else if ((x >= lbl5.x && x <= lbl5.x + 100) && (y >= lbl5.y && y <= lbl5.y + 100))
                    gsm.set(new Level_5_State(gsm));
                else if ((x >= lbl6.x && x <= lbl6.x + 100) && (y >= lbl6.y && y <= lbl6.y + 100))
                    gsm.set(new Level_6_State(gsm));
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);

        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(btn1, lbl1.x, lbl1.y);
        sb.draw(btn2, lbl2.x, lbl2.y);
        sb.draw(btn3, lbl3.x, lbl3.y);
        sb.draw(btn4, lbl4.x, lbl4.y);
        sb.draw(btn5, lbl5.x, lbl5.y);
        sb.draw(btn6, lbl6.x, lbl6.y);
        sb.end();
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
