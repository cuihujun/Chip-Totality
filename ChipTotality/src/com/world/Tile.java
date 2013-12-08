package com.world;

import com.world.building.Building;


public class Tile {
	//free- available for building. blocked not available for building
	public enum TileType{acodinField, free, blocked}; 
	public final TileType tileType;
	public Building building;
	
	public Tile(TileType type){
		tileType=type;
	}
}


