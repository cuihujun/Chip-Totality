package com.screen.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;
import com.gameInfo.Coords;
import com.gameInfo.GameStateHolder;
import com.gameInfo.GameStateHolder.ChosenBuilding;
import com.gameInfo.GameStateHolder.Mode;
import com.main.ChipTotality;
import com.main.Settings;
import com.screen.GameScreen;
import com.screen.GameStage;
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
		for (int i =  building.coords.x; i <  building.coords.x+ building.getStats().width; i++) {
			for (int j =  building.coords.y; j < building.coords.y+ building.getStats().height; j++) {
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
	
	
	public void addBuilding(Building building) {
		
		if(!buildingPossibleHere(building))
			return;
		GameStage.buildingsGroup.addActor(building);
		building.pay();
		building.doTask();
		
		// add reference to building for all tiles occupied by it
		for (int i =  building.coords.x; i < building.coords.x
				+ building.getStats().width; i++) {
			for (int j =  building.coords.y; j < building.coords.y
					+ building.getStats().height; j++) {
				game.asteroid.worldGrid[i][j].building = building;
			}
		}
		Gdx.app.log("building", building.toString() + " added at "
				+  building.coords.x + ", " +  building.coords.y);
		
		GameStateHolder.mode = Mode.NONE;
		GameStateHolder.chosenBuilding = GameStateHolder.ChosenBuilding.none;		
	}

	
	private void removeBuilding(Building building) {		
		building.destroy();
		building.remove();
		// remove references from tiles
		for (int i =  building.coords.x; i < building.coords.x + building.getStats().width; i++) {
			for (int j =  building.coords.y; j < building.coords.y + building.getStats().height; j++) {
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
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Vector3 pos = new Vector3(screenX, screenY, 0);
		game.gameScreen.camera.unproject(pos);
				
		
		Coords tileClicked=unprojectTile(pos.x, pos.y);
		if (tileClicked!=null){
			if((GameStateHolder.mode == Mode.BUILDING) && (GameStateHolder.chosenBuilding!= ChosenBuilding.none)){	
				addBuilding(GameStateHolder.chosenBuilding.getChosenBuilding(tileClicked.x, tileClicked.y));			
			}
			
			if((GameStateHolder.mode == Mode.NONE)){
				
			}
		}
			
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}
	
	
	
	
	
	
}
