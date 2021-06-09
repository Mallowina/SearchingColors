package com.simple_game_studio.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.simple_game_studio.game.StartClass;

public class DesktopLauncher {


	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = StartClass.V_WIDTH;
		config.height = StartClass.V_HEIGHT;
		config.title = "Searching colors";
		new LwjglApplication(new StartClass(), config);
	}
}
