package com.world.tower;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;

public class MoveToAtConstSpeed extends Action{
	private float speed;
	private final float FRAMES_PER_SECOND=60;
	private final float UPDATE_TIME=1/FRAMES_PER_SECOND;
	private float acc;	
	private Vector2 target;
	private Vector2 direction;

	public MoveToAtConstSpeed(Vector2 target, float speed){
		super();		
		this.speed = speed;
		this.target = target;
	}
	@Override
	public boolean act(float delta) {
		boolean completed = false;
		acc+=delta;
		
		while (acc>UPDATE_TIME) {
			acc-=UPDATE_TIME;
			direction =  new Vector2(target.x-getActor().getX(),target.y-getActor().getY());
			direction.nor();						
			getActor().setX(getActor().getX()+direction.x*speed);
			getActor().setY(getActor().getY()+direction.y*speed);
			if (target.dst(new Vector2(getActor().getX(),getActor().getY()))<(speed+0.1)) completed = true;
		}
		return completed;
	}
	
	public void setTarget(Vector2 target){
		this.target = target;
	}

}