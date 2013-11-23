package com.screen.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.gameInfo.ChosenBuilding;
import com.gameInfo.GameStateHolder;
import com.main.ChipTotality;

public class GameController extends InputAdapter {
	final ChipTotality game;
	
	
	// TODO aaa dirty code
	private TiledMapTile selectTile;
	private TiledMapTile buldingTile;
	private Vector2 lastSel = new Vector2(0, 0);
	
	
	
	
	
	public GameController(final ChipTotality game) {
		this.game=game;
	}
	
	/*private void checkTileClicked(float x, float y) {
		int xx, yy;
		float tileHeightUnits = tileHeight * unitScale;
		float tileWidthUnits = tileSize * unitScale;

		// Gdx.app.log("World", "x,y : " + x + "," + y);
		// ze wzgledu na dziwne przesuniecie mapy... (tak by punkt 0,0 dla lewy
		// dolny róg)//TODO czy da sie wysrodkowac jakos mape? Tile editor? albo
		// sam renderer?
		y = y + (tilesCountVertical / 2 - 0.5f) * tileHeightUnits;

		// y = 20*tileHeightUnits-y; //(tak by punkt 0,0 dla lewy górny róg)
		// Gdx.app.log("World przesuniety", "x,y : " + x + "," + y);

		// y = (20*tileHeightUnits)/2-y; //(tak by punkt 0,0 na rodku mapy)
		// x = (20*tileWidthUnits)/2-x; //(tak by punkt 0,0 na rodku mapy)

		xx = (int) Math.floor((y + x / 2) / tileHeightUnits);
		yy = (int) Math.floor((y - x / 2) / tileHeightUnits);

		// do kordynatów w tilach z layera na mapie//TODO usunac te magick
		// numbery jakos;]
		xx = xx - 10;
		yy = Math.abs(yy - 9);

		// TODO zamieniony xx,yy? dirty code
		selectionLayer.setCell((int) lastSel.y, (int) lastSel.x, null);
		lastSel.x = xx;
		lastSel.y = yy;
		Cell cell = new Cell();
		if (GameStateHolder.mode == GameStateHolder.Mode.BUILDING) {
			//czy mozna budowac;]
			Cell freeCell = freeLayer.getCell(yy, xx);
			if ((freeCell!=null)&&(freeCell.getTile().getProperties().containsKey("free"))){
				cell.setTile(buldingTile);
				buldingsLayer.setCell(yy, xx, cell);	
				freeLayer.setCell(yy, xx, null);
			}
			else
			{
				
			}

		} else {
			cell.setTile(selectTile);
			selectionLayer.setCell(yy, xx, cell);
		}

		Sounds.play("Click");
		Gdx.app.log("Selected tile", "x,y : " + xx + "," + yy);
	}
	*/
	
	
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
		//checkTileClicked(pos.x, pos.y);
		return false;
	}
	
	
}
