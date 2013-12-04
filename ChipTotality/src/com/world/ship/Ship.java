package com.world.ship;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.res.Loader.AssetsLoader;

public abstract class Ship extends Image{
	public int hitpoints;
	public Texture texture;
	
	Ship(float x, float y, int hitpoints){
		this.hitpoints=hitpoints;
		
		addListener(new InputListener(){
			//TODO zrobic jakis sensowny element GUI, ktory by po kliknieciu na statek sie pojawial i dawal jakies info  o nim
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("down");
                return true;
        }
		});
		
		texture=getTexture();
		setWidth(getTexture().getWidth());
		setHeight( getTexture().getHeight());
		setBounds(x, y, getWidth(), getHeight());	
		
	}
	
	
	public Texture getTexture() {
		return AssetsLoader.getTexture(this.getClass().getSimpleName());
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(texture, getX(), getY());
		
	}
	
	
}
