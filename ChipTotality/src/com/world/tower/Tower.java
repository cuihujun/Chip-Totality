package com.world.tower;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.screen.GameStage;
import com.world.building.Building;
import com.world.ship.Ship;

//class for towers, which shoot immediately
public abstract class Tower extends Building {
	protected int hitpoints;
	Ship currentTarget;	
	private final int range;
	private int firePower;
	private float shootDelay;
	private float lastShoot;
	
	public Tower(int x, int y, int width, int height, int maxHitpoints, int range, float shootDelay) {
		super(x, y, width, height, maxHitpoints);
		this.range=range;
		this.shootDelay = shootDelay;
	}	
	
	public void act(float delta){
		lastShoot+=delta;
		if(lastShoot>shootDelay){
			lastShoot-=shootDelay;
			if (targetInRange())
				shoot();
			else
				findTarget();			
		}
	}

	public void shoot(){
		//TODO jakis dzwiek und wystrzal
		currentTarget.hitpoints-=firePower;
		if(currentTarget.hitpoints<=0)
			currentTarget.destroy();
			currentTarget=null;
	}
	
	private void findTarget(){
			for (Ship ship : GameStage.ships) {
				Vector2 coords = new Vector2(getX(), getY());	
				if(coords.dst(ship.getX(), ship.getY()) <=range)
					currentTarget=ship;			
			}		
	}
		
	private boolean targetInRange() {
		Vector2 coordsFloat = new Vector2(getX(), getY());
		return (currentTarget!=null && coordsFloat.dst(currentTarget.getX(), currentTarget.getY()) <=range);
	}
	
	
	



}
