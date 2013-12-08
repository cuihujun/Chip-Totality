package com.world.tower;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.screen.GameStage;
import com.world.building.Building;
import com.world.ship.Ship;

public abstract class Tower extends Building {
	Rectangle rangeRectangle; 	// quadTree accepts only rectangles, so the range
								// is passed to it as a rectangle around circle,
								// and then possible aims are reduced to the ones, which are in the range
								// circle
	protected int hitpoints;
	Ship currentTarget;
	private final Task shootTask;
	
	public Tower(int x, int y, int width, int height, int maxHitpoints, int range) {
		super(x, y, width, height, maxHitpoints);
		rangeRectangle = new Rectangle(getX() / 2, getY() / 2, range, range);
		shootTask = new Task() {
			@Override
			public void run() {
				if (targetInRange())
					shoot(currentTarget);
				else
					findTarget();
			}
		};
		Timer.schedule(shootTask, 1, 1);
	}

	abstract void shoot(Ship target);
	
	private void findTarget(){
		
			for (Ship ship : GameStage.ships) {
				Vector2 coords = new Vector2(getX(), getY());
				if(coords.dst2(ship.getX(), ship.getY()) <=rangeRectangle.getWidth())
					currentTarget=ship;					
			}
			
	}
		
	private boolean targetInRange() {
		Vector2 coordsFloat = new Vector2(getX(), getY());
		return (currentTarget!=null && coordsFloat.dst2(currentTarget.getX(), currentTarget.getY()) <=rangeRectangle.getWidth());
	}
	
	
	



}
