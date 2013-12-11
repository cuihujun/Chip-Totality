package com.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.main.Settings;

public class FollowAction extends Action{
	private final float speed;
	private final float UPDATE_TIME=1/Settings.FRAMES_PER_SECOND;
	private final float UPDATE_DIRECTION_TIME=UPDATE_TIME*(Settings.FRAMES_PER_SECOND/3);
	private float acc;
	private float accDirection;
	private Vector2 target;
	private Vector2 direction;

	public FollowAction(Vector2 target, float speed){
		super();		
		this.speed = speed;
		this.target = target;
		direction = new Vector2(target.x,target.y);
		direction.nor();
	}
	
	@Override
	public boolean act(float delta) {		
		acc+=delta;
		accDirection+=delta;
		if(accDirection>UPDATE_DIRECTION_TIME){
			updateDirection();			
		}
		
		while (acc>UPDATE_TIME) {
			acc-=UPDATE_TIME;						
			getActor().setX(getActor().getX()+direction.x*speed);
			getActor().setY(getActor().getY()+direction.y*speed);			
		}
		
		return false;
	}
	
	public void setTarget(Vector2 target){
		this.target = target;
	}
	
	public void updateDirection(){		
		direction.x = target.x-getActor().getX();
		direction.y = target.y-getActor().getY();			
		direction.nor();		
	}

}