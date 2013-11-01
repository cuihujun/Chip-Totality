package com.world.building;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

 public abstract class Building {
	public final Vector2 coords;
	public Texture buildingTexture;
	protected Rectangle bounds;
	
	public Building(float x, float y, int width, int height){
		coords = new Vector2(x, y);
		bounds=new Rectangle(x-(0.5f*width), y-(0.5f*height), width, height);
	}

	public boolean overlaps(Building building){
		return this.bounds.overlaps(building.bounds);
	}
	
	
	
}
