package com.screen.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Rectangle;
import com.gameInfo.Coords;
import com.gameInfo.GameStateHolder;
import com.gameInfo.GameStateHolder.ChosenBuilding;
import com.gameInfo.GameStateHolder.Mode;
import com.main.ChipTotality;
import com.main.Settings;
import com.screen.GameScreen;
import com.screen.GameStage;
import com.screen.GUI.GameScreenGUI;
import com.world.Tile.TileType;
import com.world.building.Building;

public class GameController extends InputAdapter {
	final ChipTotality game;
	final GameScreen gameScreen;

	public GameController(final ChipTotality game, final GameScreen gameScreen) {
		this.game = game;
		this.gameScreen = gameScreen;
	}

	public Coords unprojectTile(float x, float y) {
		int tileX = (int) x / Settings.tileSize;
		int tileY = (int) y / Settings.tileSize;
		
		if (tileY >=Settings.tilesHorizontal || tileX >=Settings.tilesVertical || tileX <0 || tileY<0)
			return null;			
		else 
			return new Coords(tileX, tileY);
	}

	
	public boolean buildingPossibleHere(Building building){
		//check if building may be built on selected tiles
		for (int i =  building.coords.x; i <  building.coords.x+ building.size.x; i++) {
			for (int j =  building.coords.y; j < building.coords.y+ building.size.y; j++) {
				if (i>=Settings.tilesHorizontal || 
					j>=Settings.tilesVertical ||
					i<0 ||
					j<0 ||
					game.asteroid.worldGrid[i][j].tileType != TileType.free ||
					game.asteroid.worldGrid[i][j].building != null)
						return false;
			}
		}
		
		return true;
	}
	
	public boolean buildingPossibleHere(int x, int y, int width, int height){
		//check if building may be built on selected tiles
		for (int i =  x; i <  x+ width; i++) {
			for (int j =  y; j < y+ height; j++) {
				if (i>=Settings.tilesHorizontal || 
					j>=Settings.tilesVertical ||
					i<0 ||
					j<0 ||
					game.asteroid.worldGrid[i][j].tileType != TileType.free ||
					game.asteroid.worldGrid[i][j].building != null)
						return false;
			}
		}
		
		return true;
	}
	
	private void buildingPropertiesSwitch(Coords screenXY, Coords mapXY) {
		if(game.gameScreen.gameScreenGUI.getBuildingPropertiesTabInfo() == true) {
			Gdx.app.log("Properties tab", "Closed");
			game.gameScreen.gameScreenGUI.closeBuildingPropertiesTab();
		}
		for ( Building b : GameStage.buildings ) {
			if ( new Rectangle(40*b.coords.x, 40*b.coords.y, 40*b.size.x, 40*b.size.y).contains(mapXY.x, mapXY.y) == true
					&& gameScreen.gameScreenGUI.getBuildingPropertiesTabInfo() == false) {
				Gdx.app.log("Properties tab", "open");
				gameScreen.gameScreenGUI.openBuildingPropertiesTab(b, screenXY);
			}
		}
	}
	
	
	public void addBuilding(Building building) {
		
		if(!buildingPossibleHere(building))
			return;
		GameStage.buildings.add(building);
		gameScreen.gameStage.addActor(building);
		building.pay();
		building.doTask();
		// add reference to building for all tiles occupied by it
		for (int i =  building.coords.x; i < building.coords.x
				+ building.size.x; i++) {
			for (int j =  building.coords.y; j < building.coords.y
					+ building.size.y; j++) {
				game.asteroid.worldGrid[i][j].building = building;
			}
		}
		Gdx.app.log("building", building.toString() + " added at "
				+  building.coords.x + ", " +  building.coords.y);
		
		GameStateHolder.mode = Mode.NONE;
		GameStateHolder.chosenBuilding = GameStateHolder.ChosenBuilding.none;		
	}

	
	public void removeBuilding(Building building) {		
		building.destroy();
		GameStage.buildings.removeValue(building, true);
		// remove references from tiles
		for (int i =  building.coords.x; i < building.coords.x + building.size.x; i++) {
			for (int j =  building.coords.y; j < building.coords.y + building.size.y; j++) {
				game.asteroid.worldGrid[i][j].building = null;
			}
		}
	}
	
	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
		case Keys.F10:
			Settings.DEBUG = !Settings.DEBUG;
			Gdx.app.log("Debug rendering", "status changed too: "+ Settings.DEBUG);
			break;
		}

		return true;
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
		Gdx.app.log("touchUp", screenX + " " + screenY);
		Vector3 pos = new Vector3(screenX, screenY, 0);
		game.gameScreen.camera.unproject(pos);
		
		Coords tileClicked=unprojectTile(pos.x, pos.y);
		if (tileClicked!=null){
			if((GameStateHolder.mode == Mode.BUILDING) && (GameStateHolder.chosenBuilding!= ChosenBuilding.none)){	
				addBuilding(GameStateHolder.chosenBuilding.getChosenBuilding(tileClicked.x, tileClicked.y));			
			}
			if((GameStateHolder.mode == Mode.NONE)){
				buildingPropertiesSwitch(new Coords(screenX, screenY), new Coords((int)pos.x, (int)pos.y));
			}
		}
			
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}
	
	
	
	
	
	
}
