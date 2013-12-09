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
	private int speed;
	public MoveToAction moveToAction;
	
	public Bullet(float x, float y, int width, int height, int speed, Ship target){
		setBounds(x, y, width, height);
		this.target=target;
		moveToAction = new MoveToAction();
		moveToAction.setInterpolation(Interpolation.pow2);
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
		moveToAction.setPosition(target.getX(), target.getY());
		Vector2 coords = new Vector2(getX(), getY());
		//moveToAction.setDuration((coords.dst2(target.getX(), target.getY()))/speed);
		moveToAction.setDuration(5);
		
		
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
	