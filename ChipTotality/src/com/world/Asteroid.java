package com.world;

import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.gameInfo.ChosenBuilding;
import com.gameInfo.GameStateHolder;
import com.res.TexturesHolder;
import com.world.building.Building;
import com.world.building.TestBuilding1;
import com.world.spaceships.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
public class Asteroid {
	public Polygon asteroidBounds;
	public Vector<Building> buildings;
	public Vector<Spaceship> spaceships;
	public Vector<Shoot> shoots;
	public Asteroid() {
		shoots = new Vector<Shoot>();
		buildings = new Vector<Building>();
		spaceships = new Vector<Spaceship>();
		asteroidBounds = new Polygon(new float []{	100, 100, 0,
													1000,  100, 0,
													1000,  1000, 0,
													100, 1000, 0										
		});
		
		Spaceship newship = new WeakShip(new Vector2(50,50));
		spaceships.add(newship);
		newship.giveTarget(new Vector2(600,400));
		
	}
	public void draw(SpriteBatch batch)
	{
		batch.draw(TexturesHolder.worldBackground,0,0);
		for (Building building : buildings)
			building.draw(batch);
		for (Spaceship spaceship : spaceships)	{
			spaceship.draw(batch);
			spaceship.run(0.01f,this);
		}
		for (Shoot shoot : shoots)	{
			shoot.draw(batch);
			shoot.run(0.01f,this);
		}
		 ControlObject();
	}
	private void ControlObject()
	{
		for (Shoot shoot : shoots)	{
			if(!asteroidBounds.getBoundingRectangle().overlaps(shoot.getBound()))	
			{
				shoots.remove(shoot);
				break;
			}	
		}
	}
	public void shoot() {
		spaceships.get(0).shoot();
	}
	public void build(float x, float y) 
	{
		// returns if the given coordinates are not contained by the asteroid
		
		if (!asteroidBounds.contains(x, y)) {
			Gdx.app.log("building", "asteroid does not contains these coords");
			return;
		}
		//checks which building was chosen by the player
		Building newBuilding;
		switch (GameStateHolder.chosenBuilding) {
		
		case none:
			return;
			
		case testBuilding1:
			newBuilding = new TestBuilding1(x, y);
			break;
			
		default:
			return;
		}
		//checks collisions between buildings
		for (Building building : buildings) {
			if (newBuilding.overlaps(building)) {
				Gdx.app.log("building", "building overlaps another one");
				return;
			}

		}
		//actually adds the building to the game
		buildings.add(newBuilding);
		Gdx.app.log("building", "building built:"
				+ GameStateHolder.chosenBuilding.toString());
		GameStateHolder.chosenBuilding = ChosenBuilding.none;
	}
}
