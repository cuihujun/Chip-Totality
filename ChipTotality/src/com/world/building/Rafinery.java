package com.world.building;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.gameInfo.GameStateHolder;

public class Rafinery extends Building{
	private static int maxHitpoints=500;
	private static int cost = 100;
	private static int resourceDeliveryTime = 5;
	static int efficiency=2;
	
	private Task process;
	
	
	
	public Rafinery(float x, float y, int width, int height) {
		super(x, y, width, height);
		hitpoints=maxHitpoints;
		GameStateHolder.beings -= cost;
		
		process = new Task() {
		
			@Override
			public void run() {
				if(GameStateHolder.dirtyAcodin>=efficiency){
					GameStateHolder.dirtyAcodin-=efficiency;
					GameStateHolder.acodin+=efficiency;
				}				
			}	
		};
		Timer.schedule(process, resourceDeliveryTime);
	}
	
	@Override
	public void destroy(){
		super.destroy();
		process.cancel();
	}
	
}
