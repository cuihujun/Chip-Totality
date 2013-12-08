package com.world.tower;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
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

	public abstract boolean targetInRange();

	public abstract void shoot(Ship target);

	public abstract void findTarget(); 
	// TODO przerobic to na szukanie w drzewie czy cos
	/*
	 * public Ship findTarget() { for (Ship ship : GameStage.ships) {
	 * if(coordsFloat.dst2(ship.getX(), ship.getY()) <= range)
	 * currentTarget=ship; } return null; }
	 */

}
