package com.main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;


public class Main {
	public static void main(String[] args) {
		
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "ChipTotality";
		cfg.useGL20 = true;
		cfg.width = Settings.WIDTH;
<<<<<<< HEAD
		cfg.height = Settings.HEIGHT;
		
		
		cfg.resizable = false;
		new LwjglApplication(new ChipTotality(), cfg);	
		
=======
		cfg.height = Settings.HEIGHT;			
		cfg.resizable = true;
		new LwjglApplication(new ChipTotality(), cfg);			
>>>>>>> upstream/master
	}
}
