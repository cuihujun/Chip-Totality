package com.world.tower;

import com.action.FollowAction;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.main.Settings;
import com.res.Loader.AssetsLoader;
import com.world.ship.Ship;

public abstract class Rocket extends Actor{
	protected Ship target;

	private final float SPEED_IN_WORLD_UNITS;//per second
	private final float speed;

	public FollowAction moveToAction;
	Texture bulletTexture;
	
	
	public Rocket(float x, float y, int width, int height, float speed, Ship target){
		setBounds(x, y, width, height);
		this.target=target;
		SPEED_IN_WORLD_UNITS=speed;
		this.speed = SPEED_IN_WORLD_UNITS/Settings.FRAMES_PER_SECOND;
		moveToAction = new FollowAction(new Vector2(target.getX(),target.getY()),this.speed);
		updateAction();
		addAction(moveToAction);
		moveToAction.updateDirection();
		bulletTexture = AssetsLoader.getTexture(this.getClass().getSimpleName());
	}
	
	public Texture getTexture() {
		return bulletTexture;
	}
	
	public void updateAction(){
		if(target==null)
			return;
		moveToAction.setTarget(new Vector2(target.getX(), target.getY()));
	}
	
	public void setTarget(Ship target){
		this.target=target;
	}
	
	@Override
	public void act(float delta){
		updateAction();
		super.act(delta);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(getTexture(), getX(), getY());	
	}
	
	
	
	
}
	