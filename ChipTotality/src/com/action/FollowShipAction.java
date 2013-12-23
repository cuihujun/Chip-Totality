package com.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.main.Settings;
import com.world.ship.Ship;

public class FollowShipAction extends Action{
	private final boolean remove;	//remove from stage, when action is completed
	private final float speed;
	private final Ship target;
	private final Vector2 direction;
	private final float lifeTime;
	private float timeElapsed;
	private float lastUpdateTime;
	
	public FollowShipAction(Ship target, float speed, float lifeTime, boolean remove) {
		this.target=target;
		this.remove=remove;
		this.speed=speed;
		this.lifeTime=lifeTime;
		direction=new Vector2();
	}
	
	@Override
	public boolean act(float delta) {
		timeElapsed+=delta;
		lastUpdateTime+=delta;
		if(timeElapsed>=lifeTime){
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
		direction.x = target.getX()-getActor().getX();
		direction.y = target.getY()-getActor().getY();			
		direction.nor();	
	}

}
