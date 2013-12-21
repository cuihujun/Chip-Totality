package com.world.tower;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.world.ship.Ship;

public class Bullet extends Actor{
	final int firePower;
	Texture texture;
	
	public Bullet(float x, float y, int width, int height, Texture texture, int firePower){
		setBounds(x, y, width, height);
		this.firePower=firePower;
		this.texture=texture;
		
	}
	
	public void explode(Ship target){
		target.hitpoints-=firePower;
		if(target.hitpoints<=0)
			target.destroy();
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(texture, getX(), getY());	
	}
	

}
