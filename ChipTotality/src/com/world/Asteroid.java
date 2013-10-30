package com.world;

import java.util.Vector;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

public class Asteroid {
	public Polygon asteroidBounds;
	Vector<Rectangle> buildings;
	
	public Asteroid(){
		asteroidBounds = new Polygon(new float []{	50, 50, 0,
													150,  50, 0,
													50,  150, 0,
													150, 150, 0										
		});
	}
	
}
