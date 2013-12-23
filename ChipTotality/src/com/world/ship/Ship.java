package com.world.ship;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.res.Loader.AssetsLoader;

public abstract class Ship extends Actor{
	public int hitpoints;
	private Sprite sprite;
	
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
		
		sprite = new Sprite(getTexture());				
	}
	
	
	public Texture getTexture() {
		return AssetsLoader.getTexture(this.getClass().getSimpleName());
	}
	
	public void destroy(){
		//TODO jakas eksplozja
		remove();
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {		
		sprite.setPosition(getX(),getY());
		sprite.setRotation(getRotation());
		sprite.draw(batch);
	}
	
}