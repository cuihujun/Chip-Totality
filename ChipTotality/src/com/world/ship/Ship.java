package com.world.ship;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.res.Loader.AssetsLoader;

public abstract class Ship extends Actor{
	public int hitpoints;
	public Vector2 coords;
	
	Ship(float x, float y, int hitpoints){
		this.hitpoints=hitpoints;
		coords= new Vector2(x, y);
		
		addListener(new InputListener(){
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("down");
                return true;
        }
		});
		
		
		
		setWidth(getTexture().getWidth());
		setHeight( getTexture().getHeight());
		setBounds(x, y, getWidth(), getHeight());		
	}
	
	
	public Texture getTexture() {
		return AssetsLoader.getTexture(this.getClass().getSimpleName());
	}
	
	
	
}
