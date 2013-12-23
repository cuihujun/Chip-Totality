package com.world.tower;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.gameInfo.Stats;
import com.screen.GameStage;
import com.world.building.Building;
import com.world.ship.Ship;

public abstract class Tower extends Building {
	Ship currentTarget;	
	private float lastShoot;
	
	public Tower(int x, int y) {
		super(x, y);
	}	
	
	public abstract void shoot();
	
	@Override
	public void act(float delta){
		lastShoot+=delta;
		if(lastShoot>getTowerStats().shootDelay){
			lastShoot-=getTowerStats().shootDelay;
			if (targetInRange())
				shoot();
			else
				findTarget();			
		}
	}
	
	
	private void findTarget(){
		for (Actor ship : GameStage.shipsGroup.getChildren()) {
			Vector2 coords = new Vector2(getX(), getY());	
			if(coords.dst(ship.getX(), ship.getY()) <=getTowerStats().range){
				currentTarget=(Ship)ship;
				return;
			}
		}
		currentTarget=null;
	}
		
	private boolean targetInRange() {
		Vector2 coordsFloat = new Vector2(getX(), getY());
		//target ship has no parent - he formally doesn't exist in the world
		return (currentTarget!=null &&currentTarget.hasParent() && coordsFloat.dst(currentTarget.getX(), currentTarget.getY()) <=getTowerStats().range);
	}
	
	private Stats.Towers getTowerStats(){
		return Stats.Towers.valueOf(getClass().getSimpleName());
	}
	
	



}
