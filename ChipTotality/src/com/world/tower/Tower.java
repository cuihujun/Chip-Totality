package com.world.tower;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.screen.GameStage;
import com.world.building.Building;
import com.world.ship.Ship;

public abstract class Tower extends Building {
	Ship currentTarget;	
	private final int range;
	private final float shootDelay;
	private float lastShoot;
	
	public Tower(int x, int y, int width, int height, int maxHitpoints, int range, float shootDelay) {
		super(x, y, width, height, maxHitpoints);
		this.range=range;
		this.shootDelay = shootDelay;
	}	
	
	public abstract void shoot();
	
	@Override
	public void act(float delta){
		lastShoot+=delta;
		if(lastShoot>shootDelay){
			lastShoot-=shootDelay;
			if (targetInRange())
				shoot();
			else
				findTarget();	
			//findTarget();
			//if(currentTarget!=null)
			//	shoot();
			
		}
	}
	
	
	private void findTarget(){
		for (Actor ship : GameStage.shipsGroup.getChildren()) {
			Vector2 coords = new Vector2(getX(), getY());	
			if(coords.dst(ship.getX(), ship.getY()) <=range){
				currentTarget=(Ship)ship;
				return;
			}
		}
		currentTarget=null;
	}
		
	private boolean targetInRange() {
		Vector2 coordsFloat = new Vector2(getX(), getY());
		//target ship has no parent - he formally doesn't exist in the world
		return (currentTarget!=null &&currentTarget.hasParent() && coordsFloat.dst(currentTarget.getX(), currentTarget.getY()) <=range);
	}
	
	
	



}
