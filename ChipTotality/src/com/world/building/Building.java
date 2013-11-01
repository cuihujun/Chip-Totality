package com.world.building;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

 public abstract class Building {
	public Vector2 coords;
	public Texture buildingTexture;
	protected Rectangle bounds;
	
	public Building(float x, float y){
		coords = new Vector2(x, y);
		bounds = new Rectangle();
		bounds.height=0;
		bounds.width=0;
	}

	public boolean overlaps(Building building)
	{
		return this.bounds.overlaps(building.bounds);
	}
	
	
	
}
