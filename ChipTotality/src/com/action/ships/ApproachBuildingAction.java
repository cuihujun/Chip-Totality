package com.action.ships;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.world.ship.Ship;


//action designed for ships, to approach building
public class ApproachBuildingAction extends Action {
	private float speed;
	private float distance;	

	private  Vector2 direction;
	private  Vector2 targetCoords;
	private Actor target;
	private Ship actionOwner;
	
	
	public ApproachBuildingAction(Ship actionOwner, Actor target, float speed, float distance){
		setParams(actionOwner, target, speed, distance);
	}
	
	public void setParams(Ship actionOwner, Actor target, float speed, float distance) {
		this.actionOwner=actionOwner;
		this.target=target;
		this.distance=distance;
		this.speed=speed;
		
		direction = new Vector2(target.getX()-actionOwner.getX(), target.getY()-actionOwner.getY());
		direction.nor();
		targetCoords = new Vector2(target.getX(), target.getY());
	
	}
	
	@Override
	public boolean act(float delta) {
		//aproaching completed 
		if(targetCoords.dst(getActor().getX(), getActor().getY())<distance){
			actionOwner.setCurrentAction(Ship.CurrentAction.shoot);
			return true;	
		}
		
		//building was destroyed
		if(target.hasParent()!=true){
			actionOwner.setCurrentAction(Ship.CurrentAction.search);
			return true;
		}
				
		
		getActor().setX(getActor().getX()+direction.x*speed);
		getActor().setY(getActor().getY()+direction.y*speed);		
		return false;
	}
	
}
