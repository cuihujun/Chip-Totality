package com.main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;


public class Main 
{
	public static void main(String[] args) 
	{
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "ChipTotality";
		cfg.useGL20 = false;
		cfg.width = 1024;
		cfg.height = 768;
		
		//cfg.resizable = false;
		new LwjglApplication(new ChipTotality(), cfg);
		
		
	}
}
