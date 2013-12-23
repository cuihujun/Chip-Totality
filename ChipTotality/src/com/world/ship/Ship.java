package com.world.ship;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.gameInfo.Stats;
import com.res.Loader.AssetsLoader;

public abstract class Ship extends Actor{
	private float lastShoot;
	public int hitpoints;
	private Sprite sprite;
	public Actor currentTarget;
	
	
	Ship(int x, int y){
		setBounds(x, y, getStats().width, getStats().height);
		this.hitpoints=getStats().maxHitpoints;
		
		addListener(new InputListener(){
			//TODO zrobic jakis sensowny element GUI, ktory by po kliknieciu na statek sie pojawial i dawal jakies info  o nim
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("down");
                return true;
        }
		});
		
		sprite = new Sprite(AssetsLoader.getTexture(this.getClass().getSimpleName()));				
	}
	
	public abstract void shoot();
	
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
	
	public Stats.Ships getStats(){
		return Stats.Ships.valueOf(this.getClass().getSimpleName());
	}
}