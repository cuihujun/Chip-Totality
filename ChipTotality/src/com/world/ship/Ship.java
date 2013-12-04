package com.world.ship;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.res.Loader.AssetsLoader;

public abstract class Ship {
	public int hitpoints;
	public Vector2 coords;
	
	Ship(float x, float y, int hitpoints){
		this.hitpoints=hitpoints;
		coords= new Vector2(x, y);
	}
	
	
	public Texture getTexture() {
		return AssetsLoader.getTexture(this.getClass().getSimpleName());
	}
	
	
	
}
