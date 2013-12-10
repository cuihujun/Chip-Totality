package com.world.tower;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.res.Loader.AssetsLoader;
import com.world.ship.Ship;

public abstract class Bullet extends Actor{
	protected Ship target;
	private final float FRAMES_PER_SECOND=60f;
	private final float SPEED_IN_WORLD_UNITS;//per second
	private final float speed;

	public MoveToAtConstSpeed moveToAction;
	Texture bulletTexture;
	
	
	public Bullet(float x, float y, int width, int height, float speed, Ship target){
		setBounds(x, y, width, height);
		this.target=target;
		SPEED_IN_WORLD_UNITS=speed;
		this.speed = SPEED_IN_WORLD_UNITS/FRAMES_PER_SECOND;
		moveToAction = new MoveToAtConstSpeed(new Vector2(target.getX(),target.getY()),this.speed);
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
	