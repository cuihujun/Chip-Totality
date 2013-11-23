package com.screen;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Rectangle;
import com.gameInfo.DiplomacySelection;
import com.main.ChipTotality;
import com.res.Textures;

public class DiplomacyScreen implements Screen
{
	ChipTotality game;
	
	
	ArrayList<Rectangle> buttons;
	
	public DiplomacyScreen(ChipTotality ct)
	{
		game = ct;
		buttons = new ArrayList<Rectangle>();
		
		buttons.add(new Rectangle(25, 25, 128, 128));
	}

	@Override
	public void render(float arg0) 
	{	
		if(Gdx.input.isTouched()) {
			DiplomacySelection.modeSelected = checkModeClicked(Gdx.input.getX(), Gdx.input.getY());
		}
		
		game.batch.begin();
		game.batch.draw(Textures.get("dipl_menu"), 0, 0);
		game.batch.draw(Textures.get("ashtar_button_128"), 25, 25);
		game.batch.end();
	}
	
	public DiplomacySelection.ModeSelected checkModeClicked(int x, int y)
	{
		for(int i=0; i < buttons.size();i++) {
			if(buttons.get(i).contains(x,768 - y)) {
				Gdx.app.log("modeChange", "Mode changed to ASHTAR");
				return DiplomacySelection.ModeSelected.ASHTAR;
			}
		} return DiplomacySelection.ModeSelected.NONE;
	}

	@Override
	public void hide() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause()
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void resize(int arg0, int arg1) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() 
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void dispose() 
	{
		
	}

	
	
}
