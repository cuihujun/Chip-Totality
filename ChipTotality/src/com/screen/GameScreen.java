package com.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Vector3;
import com.main.ChipTotality;
import com.res.TexturesHolder;
import com.world.Asteroid;
import com.world.building.Building;
import com.world.building.TestBuilding1;

public class GameScreen implements Screen, InputProcessor{
	
	final ChipTotality game;
	CameraController cameraController;
	
	Asteroid asteroid;
	
	
	
	
	GameScreen(ChipTotality gam){
		Gdx.app.log("screen", "GameScreen set");
		game=gam;
		Gdx.input.setInputProcessor(this);
		cameraController = new CameraController();
		
		asteroid = new Asteroid();
		asteroid.buildings.add(new TestBuilding1(300, 300));
	}
	
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		cameraController.handleInput();
		
		handleMouseInput();
	
		game.batch.setProjectionMatrix(cameraController.camera.combined);
		
		
		
		game.batch.begin();
		game.batch.draw(TexturesHolder.worldBackground, 0, 0);	
		for (Building buildings : asteroid.buildings) 
			game.batch.draw(buildings.buildingTexture, buildings.coords.x, buildings.coords.y );
			
		
		game.batch.end();
		
		
		
	}
	
	private void handleMouseInput(){
		if (Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			cameraController.camera.unproject(touchPos);
			if(asteroid.asteroidBounds.contains(touchPos.x, touchPos.y))
				Gdx.app.log("input", touchPos.x+ "," +touchPos.y);
		}
		

	}
	
	//handles pressed down keys 
	@Override
	public boolean keyDown(int keycode) {
		
		return false;
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

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}