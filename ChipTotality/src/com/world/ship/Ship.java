package com.world.ship;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.res.Loader.AssetsLoader;
import com.screen.StageObject;

public abstract class Ship extends StageObject{
	public int hitpoints;
	private Texture texture;
	
	Ship(int x, int y, int width, int height, int hitpoints){
		super(x, y, width, height);
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
	
	public Rectangle getBounds(){
		return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}
	
	
}
