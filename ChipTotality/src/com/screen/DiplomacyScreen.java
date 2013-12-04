package com.screen;

import com.badlogic.gdx.Screen;
import com.main.ChipTotality;
import com.res.Textures;
import com.screen.controller.DiplomacyController;

public class DiplomacyScreen implements Screen{
	ChipTotality game;
	DiplomacyController diplomacyController;
	
	
	public DiplomacyScreen(ChipTotality game){
		this.game = game;

	}

	@Override
	public void render(float arg0) {	

		game.batch.begin();
		game.batch.draw(Textures.get("dipl_menu"), 0, 0);
		game.batch.draw(Textures.get("ashtar_button_128"), 25, 25);
		game.batch.end();
	}
	

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause(){
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void dispose() {
		
	}

	
	
}
