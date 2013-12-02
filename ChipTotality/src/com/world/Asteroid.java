package com.world;

import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.main.Settings;
import com.world.Tile.TileType;
import com.world.building.Building;

public class Asteroid {

	public final Tile[][] worldGrid; // worldGrind axis orientation:
										// ^
										// |
										// --->

	public Vector<Building> buildings;

	public Asteroid() {
		buildings = new Vector<Building>();

		// load text file containing informations about tiles type
		FileHandle file = Gdx.files.internal("world.txt");
		String text = file.readString();
		String newText=text.replaceAll("\\s+", "");
		
		worldGrid = new Tile[Settings.tilesVertical][Settings.tilesHorizontal];  		
		
		int index = 0;
		while (index < Settings.tilesHorizontal*Settings.tilesVertical) {
			TileType tileType;
			switch (newText.charAt(index)) {
			case '#':
				tileType = TileType.free;
				break;
			case '@':
				tileType = TileType.blocked;
				break;
			case '*':
				tileType  =TileType.acodinField;
				break;
			default:
				tileType = TileType.free;
			}
			
			worldGrid[index % Settings.tilesHorizontal][Settings.tilesVertical- 1 - index / 40] = new Tile(tileType);
			index++;
		}

	}

}
