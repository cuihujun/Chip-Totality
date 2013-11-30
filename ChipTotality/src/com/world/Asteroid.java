package com.world;

import java.util.Vector;

import com.main.Settings;
import com.world.Tile.TileType;
import com.world.building.Building;

public class Asteroid {
	
	public final Tile[][] worldGrid; 	//worldGrind axis orientation:
										//^
										//|
										//--->

	public Vector<Building> buildings;
	
	public Asteroid(){
		buildings = new Vector<Building>();
		worldGrid = new Tile[Settings.tilesVertical][Settings.tilesHorizontal];
		for (int i=0; i<Settings.tilesVertical; i++) {
			for(int j=0; j<Settings.tilesHorizontal; j++){
				worldGrid[i][j]=new Tile(TileType.free);
			}
		}	
	}


}
