package com.world.tower;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.res.Loader.AssetsLoader;

public abstract class Bullet extends Actor{
	//klasa dla pociskow, ktore leca troche dluzej niz od razu
	private final Texture texture;
	
	public Bullet(float x, float y){
		setPosition(x, y);
		texture=getTexture();
		setWidth(texture.getWidth());
		setHeight(texture.getHeight());
		setBounds(x, y, getWidth(), getHeight());	
	}
	
	public Texture getTexture() {
		return AssetsLoader.getTexture(this.getClass().getSimpleName());
	}
	
	
}
	