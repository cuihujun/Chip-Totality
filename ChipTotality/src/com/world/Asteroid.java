package com.world;

import java.util.Vector;

import com.badlogic.gdx.math.Polygon;
import com.world.building.Building;

public class Asteroid {
	public Polygon asteroidBounds;
	public Vector<Building> buildings;
	
	public Asteroid(){
		
		buildings = new Vector<Building>();
		asteroidBounds = new Polygon(new float []{	100, 100, 0,
													1000,  1000, 0,
													100,  1000, 0,
													1000, 100, 0										
		});
	}

}
