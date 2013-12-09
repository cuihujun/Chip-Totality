package com.world.tower;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.res.Loader.AssetsLoader;
import com.world.ship.Ship;

public abstract class Bullet extends Actor{
	protected Ship target;
	private final float FRAMES_PER_SECOND=60f;
	private final float SPEED_IN_WORLD_UNITS=200f;//per second
	private final float speed = SPEED_IN_WORLD_UNITS/FRAMES_PER_SECOND;
	//public MoveToAction moveToAction;
	public MoveToAtConstSpeed moveToAction;
	
	
	
	
	public Bullet(float x, float y, int width, int height, int speed, Ship target){
		setBounds(x, y, width, height);
		this.target=target;
		//moveToAction = new MoveToAction();
		//moveToAction.setInterpolation(Interpolation.linear);
		moveToAction = new MoveToAtConstSpeed(new Vector2(target.getX(),target.getY()),this.speed);
		updateAction();
		addAction(moveToAction);
	}
	
	public Texture getTexture() {
		return AssetsLoader.getTexture(this.getClass().getSimpleName());
	}
	
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(getTexture(), getX(), getY());	
	}
	
	public void updateAction(){
		if(target==null)
			return;
		moveToAction.setTarget(new Vector2(target.getX(), target.getY()));
		//Vector2 coords = new Vector2(getX(), getY());
		//moveToAction.setDuration(coords.dst(target.getX(), target.getY())/UNITS_PER_SECOND_FACTOR);
		
		//moveToAction.setDuration((coords.dst2(target.getX(), target.getY()))/speed);
		//moveToAction.setDuration(5);
	}
	
	public void setTarget(Ship target){
		this.target=target;
	}
	
	@Override
	public void act(float delta){
		updateAction();
		super.act(delta);
		
	}
}
	