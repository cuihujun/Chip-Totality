package com.world.tower;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.res.Loader.AssetsLoader;

public abstract class Bullet extends Actor{
	int typeBullet; // Wszystkie ponizej 100 naleza do wie¿ 
	public Bullet(float x, float y, int width, int height){
		setBounds(x, y, width, height);
	}
	
	public Texture getTexture() {
		return AssetsLoader.getTexture(this.getClass().getSimpleName());
	}
	
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {	// Co to parentalpha ?
		batch.draw(getTexture(), getX(), getY());	
	}
	
}
	