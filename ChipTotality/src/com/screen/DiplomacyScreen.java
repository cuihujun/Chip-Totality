package com.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Rectangle;
import com.gameInfo.DiplomacySelection;
import com.main.ChipTotality;
import com.res.TexturesHolder;

import java.util.ArrayList;

public class DiplomacyScreen implements Screen
{
	ChipTotality game;
	GameScreen previous;
	
	ArrayList<Rectangle> buttons;
	
	DiplomacyScreen(ChipTotality ct, GameScreen gscr)
	{
		game = ct;
		previous = gscr;
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
		game.batch.draw(TexturesHolder.diplomacyMenuBackground, 0, 0);
		game.batch.draw(TexturesHolder.ashtarButton, 25, 25);
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
