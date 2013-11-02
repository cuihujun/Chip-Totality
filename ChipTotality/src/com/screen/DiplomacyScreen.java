package com.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.main.ChipTotality;
import com.res.TexturesHolder;

public class DiplomacyScreen implements Screen
{
	ChipTotality game;
	GameScreen previous;
	
	DiplomacyScreen(ChipTotality ct, GameScreen gscr)
	{
		game = ct;
		previous = gscr;
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
	public void render(float arg0) 
	{
		if(Gdx.input.isKeyPressed(Keys.ESCAPE) == true) {
			game.setScreen(previous);
		}
		game.batch.begin();
		game.batch.draw(TexturesHolder.diplomacyMenuBackground, 0, 0);
		game.batch.end();
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
		// TODO Auto-generated method stub
		
	}
	
}
