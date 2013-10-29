package com.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.res.TexturesHolder;
import com.screen.MainMenuScreen;


public class ChipTotality extends Game {
	
	public Settings settings;
	public SpriteBatch batch;
	public TexturesHolder texturesHolder;

	@Override
	public void create() {
		settings = new Settings();
		
		texturesHolder = new TexturesHolder();
		batch = new SpriteBatch();
		this.setScreen(new MainMenuScreen(this));
	}
	
	public void render(){
		super.render();
	}
	
	
}
