package com.main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;


public class Main {
	public static void main(String[] args) {
		
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "ChipTotality";
		cfg.useGL20 = true;
		cfg.width = Settings.WIDTH;
		cfg.height = Settings.HEIGHT;
		cfg.resizable = true;
		new LwjglApplication(new ChipTotality(), cfg);	
		
	}
}
<<<<<<< HEAD
 
=======
 
>>>>>>> 5d92dc957f51a7f999b4229a43f19e3eb417e2e1
