package com.world;

import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.main.Settings;
import com.world.Tile.TileType;import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.res.Loader.AssetsLoader;import com.badlogic.gdx.graphics.g2d.SpriteBatch;import com.world.building.Building;
import com.world.spaceship.Shoot;
import com.world.spaceship.Spaceship;
import com.world.spaceship.WeakShip;

public class Asteroid {

	public final Tile[][] worldGrid; // worldGrind axis orientation:
										// ^
										// |
										// --->

	public Vector<Building> buildings;
	public Vector<Spaceship> spaceships;
	public Vector<Shoot>	shoots;
	
	public Asteroid() {
		buildings = new Vector<Building>();

		// load text file containing informations about tiles type
		FileHandle file = Gdx.files.internal("world.txt");
		String text = file.readString();
		String newText=text.replaceAll("\\s+", "");
		shoots = new Vector<Shoot>();

	
		spaceships = new Vector<Spaceship>();
		spaceships.add(new WeakShip(new Vector2(10, 10)));

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
