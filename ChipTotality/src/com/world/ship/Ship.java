package com.world.ship;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.res.Loader.AssetsLoader;

public abstract class Ship extends Actor{
	public int hitpoints;
	
	Ship(int x, int y, int width, int height, int hitpoints){
		setBounds(x, y, width, height);
		this.hitpoints=hitpoints;
		
		addListener(new InputListener(){
			//TODO zrobic jakis sensowny element GUI, ktory by po kliknieciu na statek sie pojawial i dawal jakies info  o nim
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("down");
                return true;
        }
		});

	}
	
	
	public Texture getTexture() {
		return AssetsLoader.getTexture(this.getClass().getSimpleName());
	}
	
	public void destroy(){
		//TODO jakas eksplozja
		remove();
	}
	
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		Sprite spr = new Sprite(getTexture());	// Moze by warto wsadzic go na sta³e do klasy niz generowac co funkcje
		spr.setPosition(getX(),getY());
		spr.setRotation(getRotation());
		spr.draw(batch);
		//batch.draw(getTexture(), getX(), getY());
	}
	
	
	
	
	
}
