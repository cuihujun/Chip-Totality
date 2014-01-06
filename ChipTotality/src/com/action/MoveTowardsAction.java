package com.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;

public class MoveTowardsAction extends Action {
	private boolean remove;	//remove from stage, when action is completed
	private float speed;
	private Vector2 direction;
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
		this.direction = direction;		
		//direction.nor();
	}
	
	@Override
	public boolean act(float delta) {
		
		//TODO powinno byc oki ale wyglada ze nieresetuje sie pool problem?
		if(timeElapsed==0) {
			getActor().setRotation(direction.angle()-90);
			//getActor().rotate(direction.angle()-90);
		}
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
