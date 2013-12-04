package com.world;


public class Tile {
	//free- available for building. blocked not available for building
	public enum TileType{acodinField, free, blocked}; 
	public final TileType tileType;
	public Object building;
	
	public Tile(TileType type){
		tileType=type;
	}
}


