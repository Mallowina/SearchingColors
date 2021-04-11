package com.simple_game_studio.game.states.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.simple_game_studio.game.sprites.Player;
import com.simple_game_studio.game.tools.GameStateManager;
import com.simple_game_studio.game.tools.PlayerGestureDetector;
import com.simple_game_studio.game.tools.State;

public class Level_1_State extends State {
    private Texture background;

    private Player player;

    GestureDetector gestureDetector;

    public Level_1_State(GameStateManager gsm) {
        super(gsm);
        System.out.println("start lvl1");
        camera.setToOrtho(false, 100, 100);
        background = new Texture("levels/lvl1/lbl1.png");

        player = new Player(50, 0);

        gestureDetector = new GestureDetector(new PlayerGestureDetector(player));
        Gdx.input.setInputProcessor(gestureDetector);
    }

    @Override
    protected void handleInput() {}

    @Override
    public void update(float dt) {
        player.update(dt);
        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);

        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(player.getPlayer(), player.getPosition().x, player.getPosition().y);
        sb.end();


    }

    @Override
    public void dispose() {
        background.dispose();
        player.dispose();
    }
}
