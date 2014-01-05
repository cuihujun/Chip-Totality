package com.world.tower;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.gameInfo.Stats;
import com.screen.GameStage;
import com.world.building.Building;
import com.world.ship.Ship;

public abstract class Tower extends Building {
	protected Ship currentTarget;	
	protected float lastShoot;
	protected Stats.Bullets bulletType;
	protected Stats.Towers towerType;
	private Vector2 coords = new Vector2(0,0);
	private Vector2 coordsFloat = new Vector2(0,0);
	
	public Tower(int x, int y, Stats.Towers towerType) {
		super(x, y);
		this.towerType = towerType;
		bulletType=Stats.Bullets.simpleBullet;
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
		coords.x = getX();
		coords.y = getY();		
		for (Actor ship : GameStage.shipsGroup.getChildren()) {
			if(coords.dst2(ship.getX(), ship.getY()) <=(getTowerStats().range*getTowerStats().range)){		
				currentTarget=(Ship)ship;
				return;
			}
		}
		currentTarget=null;
	}
		
	private boolean targetInRange() {
		coordsFloat.x = getX();
		coordsFloat.y = getY();		
		//target ship has no parent - he formally doesn't exist in the world
		return (currentTarget!=null &&currentTarget.hasParent() && coordsFloat.dst2(currentTarget.getX(), currentTarget.getY()) <=(getTowerStats().range*getTowerStats().range));				
	}
	
	private Stats.Towers getTowerStats(){
		//TODO String alocation aaaaaa in every action! very slow;]
		//return Stats.Towers.valueOf(getClass().getSimpleName());
		return towerType;
	}
	
}
