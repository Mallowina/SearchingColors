package com.simple_game_studio.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.simple_game_studio.game.tools.GameStateManager;
import com.simple_game_studio.game.tools.State;

public class MenuState extends State {
    /*Create textures*/
    private Texture background;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, 1920, 1080);
        System.out.println("MenuState start");
        background = new Texture("main_menu_bg.png");
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
