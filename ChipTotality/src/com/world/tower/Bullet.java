package com.world.tower;

import com.badlogic.gdx.math.Vector2;

public abstract class Bullet {
	//klasa dla pociskow, ktore leca troche dluzej niz od razu
	Vector2 coords;
	
	public Bullet(float x, float y){
		coords.x=x;
		coords.y=y;
		
	}
	
	
}
