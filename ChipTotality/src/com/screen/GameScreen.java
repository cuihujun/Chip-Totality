package com.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Vector3;
import com.main.ChipTotality;
import com.world.Asteroid;

public class GameScreen implements Screen{
	
	final ChipTotality game;
	CameraController cameraController;
	
	Asteroid asteroid;
	
	
	
	
	GameScreen(ChipTotality gam){
		Gdx.app.log("screen", "GameScreen set");
		game=gam;
		
		cameraController = new CameraController(game.settings);
		
		asteroid = new Asteroid();
	}
	
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		cameraController.handleInput();
		
		handleInput();
	
		game.batch.setProjectionMatrix(cameraController.camera.combined);
		game.batch.begin();
		game.batch.draw(game.texturesHolder.worldBackground, 0, 0);
		game.batch.end();
		
		
		
	}
	
	private void handleInput(){
		if (Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			cameraController.camera.unproject(touchPos);
			if(asteroid.asteroidBounds.contains(touchPos.x, touchPos.y))
				Gdx.app.log("input", touchPos.x+ "," +touchPos.y);
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
