package com.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.main.ChipTotality;

public class MainMenuScreen implements Screen{
	
	final ChipTotality game;
	
	
	public MainMenuScreen(ChipTotality gam) {
		Gdx.app.log("screen", "MainMenuScreen set");
		game=gam;
		
	}
	

	@Override
	public void render(float delta) {
		
		if(Gdx.input.isTouched()){
			game.setScreen(new GameScreen(game));
			dispose();
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
}
