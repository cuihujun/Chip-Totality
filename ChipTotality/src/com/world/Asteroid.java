package com.world;

import java.util.Vector;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.res.Loader.AssetsLoader;
import com.world.building.Building;

public class Asteroid {
	public Vector<Building> buildings;
	
	
	private final TiledMap tiledMap;
	//public TiledMapTileLayer freeLayer;
	///private final TiledMapTileLayer selectionLayer;
	///private final TiledMapTileLayer buldingsLayer;
	
	
	
	public Asteroid(){
		buildings = new Vector<Building>();
		tiledMap=AssetsLoader.getTileMap();
		
		//freeLayer = (TiledMapTileLayer) tiledMap.getLayers().get("FreeSpace");
	}
	public TiledMap getTiledMap(){
		return tiledMap;
	}
	public void dispose(){
		tiledMap.dispose();
	}
}
