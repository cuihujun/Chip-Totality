package com.world.tower;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.main.Settings;
import com.world.building.Building;
import com.world.ship.Ship;

public abstract class Tower extends Building{
	public final Vector2 coordsFloat;

	protected int hitpoints;
	Ship currentTarget;
	private final Task shootTask;
	
	
	public Tower(int x, int y, int width, int height, int maxHitpoints) {
		super(x, y, width, height, maxHitpoints);
		coordsFloat=new Vector2(Settings.tileSize/2*x, Settings.tileSize/2*y);
		
		shootTask = new Task() {			
			@Override
			public void run() {		
				if(targetInRange())
					shoot(currentTarget);	
				else
					findTarget();		
			}
		};
		Timer.schedule(shootTask, 1, 1);
	}
	
	
	
	
	public abstract boolean targetInRange();
	public abstract void shoot(Ship target);
	public abstract Ship findTarget();
	
	
	
}
