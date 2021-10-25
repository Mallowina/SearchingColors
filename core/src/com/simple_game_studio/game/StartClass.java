package com.simple_game_studio.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.simple_game_studio.game.states.LevelsState;
import com.simple_game_studio.game.states.MenuState;
import com.simple_game_studio.game.states.SettingsState;
import com.simple_game_studio.game.states.levels.State1;

public class StartClass extends Game {
	public static final int V_WIDTH = 750;
	public static final int V_HEIGHT = 423;
	public static final float PPM = 100;

	public static final short DEFAULT_BIT = 1;
	public static final short PLAYER_BIT = 2;
	public static final short MESSAGE_BIT = 4;
	public static final short GROUND_BIT = 8;
	public static final short DESTROYED_BIT = 16;
	public static final short SPIKE_BIT = 32;

	public static boolean STORY_STATE = false;

	public SpriteBatch batch;
	public static AssetManager manager;


	/**var to return on prev state **/
	public enum PrevStates {
		MAIN_MENU,
		SETTINGS_STATE,
		LEVELS_STATE,
		STATE1
	}

	public void changeState(PrevStates prev_state) {
		switch (prev_state) {
			case MAIN_MENU: {
				this.setScreen(new MenuState(this));
				break;
			}
			case SETTINGS_STATE: {
				this.setScreen(new SettingsState(this));
				break;
			}
			case LEVELS_STATE: {
				this.setScreen(new LevelsState(this));
				break;
			}
			case STATE1: {
				this.setScreen(new State1(this));
				break;
			}
		}
	}


	@Override
	public void create () {
		batch = new SpriteBatch();

		manager = new AssetManager();
		manager.load("audio/music/menu_theme.mp3", Music.class);
		manager.finishLoading();

		setScreen(new MenuState(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		manager.dispose();
	}
}
