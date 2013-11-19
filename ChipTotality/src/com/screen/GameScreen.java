package com.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.gameInfo.ChosenBuilding;
import com.gameInfo.GameStateHolder;
import com.main.ChipTotality;
import com.res.Textures;
import com.world.Asteroid;
import com.world.building.Base;
import com.world.building.Building;

public class GameScreen implements Screen, InputProcessor {

	final ChipTotality game;
	CameraController cameraController;

	Asteroid asteroid;

	DiplomacyScreen diplomacyScreen;
		
	Base base;
	GameScreen(ChipTotality gam) {
		Gdx.app.log("screen", "GameScreen set");
		game = gam;
		Gdx.input.setInputProcessor(this);
		cameraController = new CameraController();

		asteroid = new Asteroid();
		
		diplomacyScreen = new DiplomacyScreen(game, this);
		//base = new Base(1, 1, 1, 1);
		
		
	}

	@Override
	public void render(float delta) {
		//System.out.println(GameStateHolder.beings);
		//System.out.println(Upgrade.directConnection.isResearched);
		
		
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		cameraController.handleInput();
		
		game.batch.setProjectionMatrix(cameraController.camera.combined);

		game.batch.begin();
		game.batch.draw(Textures.worldBackground, 0, 0);
		for (Building buildings : asteroid.buildings)
			game.batch.draw(buildings.buildingTexture, buildings.coords.x, buildings.coords.y);

		game.batch.end();
		
	}



	
	@Override
	public boolean keyDown(int keycode) {		
		switch (GameStateHolder.mode) {
		case BUILDING:
			switch (keycode) {
										
			case Keys.F1:
				GameStateHolder.chosenBuilding = ChosenBuilding.testBuilding1;
				break;
				
			case Keys.ESCAPE:
				GameStateHolder.chosenBuilding = ChosenBuilding.none;
				GameStateHolder.mode = GameStateHolder.Mode.NONE;
				Gdx.app.log("buildingMode", "building mode:"+ GameStateHolder.mode.toString());
				break;
				
			default:
				break;
			}
			
			Gdx.app.log("building", "building chosen:"
					+ GameStateHolder.chosenBuilding.toString());
		
		case DIPLOMACY:
			break;

		case NONE:
			switch (keycode) {

			case Keys.B:
				GameStateHolder.mode = GameStateHolder.Mode.BUILDING;
				break;
			}

			break;
			
			
		default:
			Gdx.app.log("Default", "action");
		}
		
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
