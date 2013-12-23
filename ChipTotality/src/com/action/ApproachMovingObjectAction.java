package com.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.main.Settings;

//approach to the actor at the certain distance with constant speed
public class ApproachMovingObjectAction extends Action{	
	private float speed;
	float distance;	
	private boolean remove;	//remove actor from the stage, when the action is completed
	
	private float lastUpdateTime;
	private final Vector2 direction = new Vector2();
	private final Vector2 targetCoords = new Vector2();
	private Actor target;
	
	
	public ApproachMovingObjectAction(Actor target, float speed, float distance, boolean remove){
		setParams(target, speed, distance, remove);
	}
	
	public void setParams(Actor target, float speed, float distance, boolean remove) {
		this.target=target;
		this.distance=distance;
		this.remove=remove;
		this.speed=speed;
		
		targetCoords.x = target.getX();
		targetCoords.y = target.getY();
	}
	
	@Override
	public boolean act(float delta) {
		lastUpdateTime+=delta;
		
		
		if(targetCoords.dst(getActor().getX(), getActor().getY())<distance || target.hasParent()!=true){
			if(remove)
				getActor().remove();
			return true;	
		}
				
		if(lastUpdateTime>=Settings.UPDATE_TIME){
			lastUpdateTime-=Settings.UPDATE_TIME;
			updateDirection();
		}
		
		getActor().setX(getActor().getX()+direction.x*speed);
		getActor().setY(getActor().getY()+direction.y*speed);		
		return false;
	}
	
	
	private void updateDirection(){		
		targetCoords.x = target.getX();
		targetCoords.y = target.getY();
		direction.x = targetCoords.x-getActor().getX();
		direction.y = targetCoords.y-getActor().getY();			
		direction.nor();		
	}

}
