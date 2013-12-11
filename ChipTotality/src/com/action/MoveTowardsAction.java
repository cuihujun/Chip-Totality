package com.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;

public class MoveTowardsAction extends Action {
	private final float speed;
	private final Vector2 direction;
	private final float lifeTime;
	private float timeElapsed;
	
	
	public MoveTowardsAction(Vector2 direction, float speed, float lifeTime) {
		super();
		this.speed=speed;
		this.lifeTime=lifeTime;
		this.direction=new Vector2(direction.x, direction.y);
		direction.nor();
		
	}
	
	@Override
	public boolean act(float delta) {
		if(timeElapsed>=lifeTime)
			return true;
		
		
		timeElapsed+=delta;
		getActor().setX(getActor().getX()+direction.x*speed);
		getActor().setY(getActor().getY()+direction.y*speed);	
				
		return false;
	}

}
