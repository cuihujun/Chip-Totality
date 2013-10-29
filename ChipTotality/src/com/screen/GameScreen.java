package com.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.main.ChipTotality;

public class GameScreen implements Screen{
	
	final ChipTotality game;
	CameraController cameraController;
	
	
	GameScreen(ChipTotality gam){
		Gdx.app.log("screen", "GameScreen set");
		game=gam;
		
		cameraController = new CameraController(game.settings);
	}
	
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		cameraController.handleInput();
	
		game.batch.setProjectionMatrix(cameraController.camera.combined);
		game.batch.begin();
		game.batch.draw(game.texturesHolder.worldBackground, 0, 0);
		game.batch.end();
		
		
		
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
