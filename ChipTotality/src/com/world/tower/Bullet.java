package com.world.tower;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.res.Loader.AssetsLoader;

public class Bullet extends Actor{
	

	
	Bullet(float x, float y, int width, int height){
		setBounds(x, y, width, height);
		
	}
	

	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(getTexture(), getX(), getY());	
	}
	
	public Texture getTexture() {
		return AssetsLoader.getTexture(this.getClass().getSimpleName());
	}
}
