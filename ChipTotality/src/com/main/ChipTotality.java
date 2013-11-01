package com.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.screen.MainMenuScreen;


public class ChipTotality extends Game {

	public SpriteBatch batch;

	@Override
	public void create() {
	
		batch = new SpriteBatch();
		this.setScreen(new MainMenuScreen(this));
	}
	
	public void render(){
		super.render();
	}
	
}