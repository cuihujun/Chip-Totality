package com.world;

import java.util.Vector;



import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.res.Loader.AssetsLoader;
import com.world.building.Building;
import com.world.spaceship.Shoot;
import com.world.spaceship.Spaceship;
import com.world.spaceship.WeakShip;

public class Asteroid {
	public Vector<Building> buildings;
	public Vector<Spaceship> spaceships;
	public Vector<Shoot>	shoots;
	
	private final TiledMap tiledMap;
	//public TiledMapTileLayer freeLayer;
	///private final TiledMapTileLayer selectionLayer;
	///private final TiledMapTileLayer buldingsLayer;
	
	

	
	
	public Asteroid(){
		buildings = new Vector<Building>();
		tiledMap=AssetsLoader.getTileMap();
		shoots = new Vector<Shoot>();

	
		spaceships = new Vector<Spaceship>();
		spaceships.add(new WeakShip(new Vector2(10, 10)));

		//freeLayer = (TiledMapTileLayer) tiledMap.getLayers().get("FreeSpace");
	}
	public TiledMap getTiledMap(){
		return tiledMap;
	}
	public void dispose(){
		tiledMap.dispose();

	}
	public void draw(SpriteBatch batch)
	{
		for (Spaceship spaceship : spaceships)	{
			spaceship.draw(batch);
			spaceship.run(0.01f,this);
		}
		for(Shoot shoot : shoots)
		{
			shoot.draw(batch);
			shoot.run(0.01f, this);
		}
	}
}
