package com.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Vector3;
import com.gameInfo.ChosenBuilding;
import com.gameInfo.DiplomacySelection;
import com.gameInfo.GameStateHolder;
import com.main.ChipTotality;
import com.res.TexturesHolder;
import com.world.Asteroid;
import com.world.building.Building;
import com.world.building.TestBuilding1;

public class GameScreen implements Screen, InputProcessor {

	final ChipTotality game;
	CameraController cameraController;

	Asteroid asteroid;
	
	DiplomacyScreen diplomacyScreen;

	GameScreen(ChipTotality gam) {
		Gdx.app.log("screen", "GameScreen set");
		game = gam;
		Gdx.input.setInputProcessor(this);
		cameraController = new CameraController();

		asteroid = new Asteroid();
		
		diplomacyScreen = new DiplomacyScreen(game, this);

	}

	@Override
	public void render(float delta) 
	{
		if(GameStateHolder.mode == GameStateHolder.Mode.DIPLOMACY){
			game.setScreen(diplomacyScreen);
		}
		
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		cameraController.handleInput();

		handleMouseInput();

		game.batch.setProjectionMatrix(cameraController.camera.combined);

		game.batch.begin();
		game.batch.draw(TexturesHolder.worldBackground, 0, 0);
		for (Building buildings : asteroid.buildings)
			game.batch.draw(buildings.buildingTexture, buildings.coords.x,
					buildings.coords.y);

		game.batch.end();

	}

	private void handleMouseInput()
	{
		if (Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			cameraController.camera.unproject(touchPos);

			switch(GameStateHolder.mode){ 
			case BUILDING:
				if (GameStateHolder.chosenBuilding != ChosenBuilding.none) {
					build(touchPos.x, touchPos.y);
				}
				break;
			
			case DIPLOMACY:
				break;
			}
		}

	}

	// handles pressed down keys
	@Override
	public boolean keyDown(int keycode) {
		// switches buildingMode state
		GameStateHolder.Mode modeVar = GameStateHolder.mode;
		
		switch(keycode) {
		case Keys.F1:
			modeVar = GameStateHolder.Mode.BUILDING;
			break;
			
		case Keys.F2:
			modeVar = GameStateHolder.Mode.DIPLOMACY;
			break;
		}
		
		if(GameStateHolder.mode == GameStateHolder.Mode.NONE) {
			GameStateHolder.mode = modeVar;
		} else if(GameStateHolder.mode == modeVar) {
			GameStateHolder.mode = GameStateHolder.Mode.NONE;
			game.setScreen(this);
		}
		
		Gdx.app.log("Current mode:", GameStateHolder.mode.toString());
		
		//if buildingMode is activated, chose the building to be built
		
		switch (GameStateHolder.mode) {
		case BUILDING:
			switch (keycode) {
			
			case Keys.F1:
				GameStateHolder.chosenBuilding = ChosenBuilding.testBuilding1;
				break;
				
			case Keys.ESCAPE:
				GameStateHolder.chosenBuilding = ChosenBuilding.none;
				GameStateHolder.buildingMode = false;
				Gdx.app.log("buildingMode", "building mode:"
						+ GameStateHolder.buildingMode);
				break;
				
			default:
				break;
			}
			
			Gdx.app.log("building", "building chosen:"
					+ GameStateHolder.chosenBuilding.toString());
		
		case DIPLOMACY:
			break;
			
		default:
		}

		return false;
	}

	// Builds the building in the pointed location. Checks whether the location
	// is contained by the asteroid and collisions between other buildings
	
	private void build(float x, float y) 
	{
		// returns if the given coordinates are not contained by the asteroid
		
		if (!asteroid.asteroidBounds.contains(x, y)) {
			Gdx.app.log("building", "asteroid does not contains these coords");
			return;
		}
		//checks which building was chosen by the player
		Building newBuilding;
		switch (GameStateHolder.chosenBuilding) {
		
		case none:
			return;
			
		case testBuilding1:
			newBuilding = new TestBuilding1(x, y);
			break;
			
		default:
			return;
		}
		//checks collisions between buildings
		for (Building building : asteroid.buildings) {
			if (newBuilding.overlaps(building)) {
				Gdx.app.log("building", "building overlaps another one");
				return;
			}

		}
		//actually adds the building to the game
		asteroid.buildings.add(newBuilding);
		Gdx.app.log("building", "building built:"
				+ GameStateHolder.chosenBuilding.toString());
		GameStateHolder.chosenBuilding = ChosenBuilding.none;
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