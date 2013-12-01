package com.screen.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.gameInfo.GameStateHolder;
import com.gameInfo.GameStateHolder.ChosenBuilding;
import com.main.ChipTotality;
import com.main.Settings;
import com.world.Tile.TileType;
import com.world.building.Building;

public class GameController extends InputAdapter {
	final ChipTotality game;

	public GameController(final ChipTotality game) {
		this.game = game;

	}

	public Vector2 unprojectTile(float x, float y) {
		int tileX = (int) x / Settings.tileSize;
		int tileY = (int) y / Settings.tileSize;
		
		if (tileY >=Settings.tilesHorizontal || tileX >=Settings.tilesVertical || tileX <0 || tileY<0)
			return null;			
		else 
			return new Vector2(tileX, tileY);
	}

	private void addBuilding(Building building) {
		
		// check tile types and buildings
		for (int i = (int) building.coords.x; i < (int) building.coords.x+ building.size.x; i++) {
			for (int j = (int) building.coords.y; j < building.coords.y+ building.size.y; j++) {
				if (game.asteroid.worldGrid[i][j].tileType != TileType.free || game.asteroid.worldGrid[i][j].building != null)
					return;
			}
		}

		game.asteroid.buildings.add(building);
		building.pay();
		building.doTask();
		// add reference to building for all tiles occupied by it
		for (int i = (int) building.coords.x; i < building.coords.x
				+ building.size.x; i++) {
			for (int j = (int) building.coords.y; j < building.coords.y
					+ building.size.y; j++) {
				game.asteroid.worldGrid[i][j].building = building;
			}
		}
		Gdx.app.log("building", building.toString() + " added at "
				+ (int) building.coords.x + ", " + (int) building.coords.y);
	}

	
	private void removeBuilding(Building building) {
		game.asteroid.buildings.remove(building);
		// remove references from tiles
		for (int i = (int) building.coords.x; i < building.coords.x + building.size.x; i++) {
			for (int j = (int) building.coords.y; j < building.coords.y + building.size.y; j++) {
				game.asteroid.worldGrid[i][j].building = null;
			}
		}
	}

	@Override
	public boolean keyDown(int keycode) {
		switch (GameStateHolder.mode) {
		case BUILDING:
			switch (keycode) {

			case Keys.F1:
				GameStateHolder.chosenBuilding = ChosenBuilding.Base;
				break;

			case Keys.ESCAPE:
				GameStateHolder.chosenBuilding = GameStateHolder.ChosenBuilding.none;
				GameStateHolder.mode = GameStateHolder.Mode.NONE;
				Gdx.app.log("buildingMode", "building mode:"
						+ GameStateHolder.mode.toString());
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
			case Keys.D:
				GameStateHolder.mode = GameStateHolder.Mode.DIPLOMACY;
				game.setScreen(game.diplomacyScreen);
				break;
			}

			break;

		default:
			Gdx.app.log("Default", "action");
		}

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Vector3 pos = new Vector3(screenX, screenY, 0);
		game.gameScreen.camera.unproject(pos);
		
		Vector2 tileClicked=unprojectTile(pos.x, pos.y);
		if (tileClicked!=null)
			addBuilding(GameStateHolder.chosenBuilding.getBuilding((int) tileClicked.x, (int) tileClicked.y));

		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}
	
	
	
	
	
	
}
