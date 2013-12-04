package com.world.tower;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.gameInfo.Coords;
import com.main.Settings;
import com.res.Loader.AssetsLoader;
import com.world.ship.Ship;

public abstract class Tower {
	public final Coords coords;
	public final Vector2 coordsFloat;
	
	float shootDelay;
	final Vector2 size;
	int hitpoints;
	Ship currentTarget;
	private final Task shootTask;
	
	
	public Tower(int x, int y, int width, int height, int maxHP) {
		coords = new Coords(x, y);
		size = new Vector2(width, height);
		coordsFloat=new Vector2(Settings.tileSize/2*x, Settings.tileSize/2*y);
		hitpoints=maxHP;
		
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
	
	
	public Texture getTexture() {
		return AssetsLoader.getTexture(this.getClass().getSimpleName());
	}
	
	
	
	public abstract boolean targetInRange();
	public abstract void shoot(Ship target);
	public abstract Ship findTarget();
	
	
	
}
