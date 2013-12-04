package com.world;

<<<<<<< HEAD
import com.world.building.Building;
=======
>>>>>>> 5d92dc957f51a7f999b4229a43f19e3eb417e2e1

public class Tile {
	//free- available for building. blocked not available for building
	public enum TileType{acodinField, free, blocked}; 
	public final TileType tileType;
<<<<<<< HEAD
	public Building building;
=======
	public Object building;
>>>>>>> 5d92dc957f51a7f999b4229a43f19e3eb417e2e1
	
	public Tile(TileType type){
		tileType=type;
	}
}


