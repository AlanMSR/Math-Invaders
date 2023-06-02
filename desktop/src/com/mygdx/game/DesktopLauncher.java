package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Files;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Math Invaders");

		int screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;

		// Set window size to match screen dimensions
		//int width = screenWidth;
		//int height = screenHeight;
		//config
		//config.setWindowedMode(width, height);
		config.setWindowSizeLimits(screenWidth, screenHeight, screenWidth, screenHeight);
		Gdx.files = new Lwjgl3Files();
		new Lwjgl3Application(new Runner(), config);
	}
}
