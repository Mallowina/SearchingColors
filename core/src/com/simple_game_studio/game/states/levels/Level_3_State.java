package com.simple_game_studio.game.states.levels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.simple_game_studio.game.tools.GameStateManager;
import com.simple_game_studio.game.tools.State;

public class Level_3_State extends State {
    Texture background;

    public Level_3_State(GameStateManager gsm) {
        super(gsm);
        System.out.println("start lvl3");
        camera.setToOrtho(false, 100, 100);
        background = new Texture("lbl3.png");
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);

        sb.begin();
        sb.draw(background, 0, 0);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
