package com.simple_game_studio.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.simple_game_studio.game.states.MenuState;
import com.simple_game_studio.game.tools.GameStateManager;

public class StartClass extends ApplicationAdapter {
	private GameStateManager gsm;
	public static final int WIDTH = 900;
	public static final int HEIGHT = 500;

	SpriteBatch batch;



	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		System.out.println("dispose StartClass");
		Gdx.gl.glClearColor(1, 0, 0, 1);
		/*Starting MenuState*/
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();

	}
}
