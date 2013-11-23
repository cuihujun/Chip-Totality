package com.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.main.ChipTotality;

public class MainMenuScreen implements Screen{
	
	final ChipTotality game;
	public Ticker ticker;
	
	
	public MainMenuScreen(ChipTotality gam) {
		Gdx.app.log("screen", "MainMenuScreen set");
		game=gam;
		ticker = new Ticker(50, 50, 10, 120);
		ticker.addMessage("petycja: nie bedziemy cipowani");
		ticker.addMessage("kochajcie sie i lubcie sie");
		ticker.addMessage("nie nie dajcie sie zacipowac");
		ticker.addMessage("usuwanie zel w sobie");
		ticker.addMessage("kochajcie ziemie ona Panstwa tez kocha");
		ticker.addMessage("jaszczur piekielny = smierc");
		ticker.addMessage("witam i pozdrawiam, astar seran");
		ticker.addMessage("stara ziem niewolnictwo cipowe wiezienie");
	}
	

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		ticker.draw(game.batch, 0);
		game.batch.end();
		
		if(Gdx.input.isTouched()){
			game.setScreen(game.gameScreen);
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
