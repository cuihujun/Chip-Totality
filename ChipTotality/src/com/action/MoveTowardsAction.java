package com.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;

public class MoveTowardsAction extends Action {
	private boolean remove;	//remove from stage, when action is completed
	private float speed;
	private Vector2 direction = new Vector2();
	private float lifeTime;
	private float timeElapsed;
	
	public MoveTowardsAction(Vector2 direction, float speed, float lifeTime, boolean remove){
		setParams(direction, speed, lifeTime, remove);
	}
		
	public MoveTowardsAction() {}

	public void setParams(Vector2 direction, float speed, float lifeTime, boolean remove) {
		this.remove=remove;
		this.speed=speed;
		this.lifeTime=lifeTime;
		this.direction = new Vector2(direction);
		direction.nor();
	}
	
	@Override
	public boolean act(float delta) {
		timeElapsed+=delta;
		if(timeElapsed>=lifeTime){
			if(remove)
				getActor().remove();
			return true;	
		}
			
		getActor().setX(getActor().getX()+direction.x*speed);
		getActor().setY(getActor().getY()+direction.y*speed);	
		
		
		return false;
	}

}
