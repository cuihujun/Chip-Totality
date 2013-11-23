package com.world.building;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.gameInfo.GameStateHolder;

public class Base extends Building implements Upgradeable{
	static int maxHitpoints=500;
	private static int cost = 100;
	static int resourceDeliveryAmount = 5;
	static int resourceDeliveryTime = 5;
	
	
	private Task addResources;

	
	
	public Base(float x, float y, int width, int height) {
		super(x, y, width, height);
		GameStateHolder.beings -= cost;
		hitpoints=maxHitpoints;
		
		addResources = new Task() {
			@Override
			public void run() {
				GameStateHolder.beings += resourceDeliveryAmount;
			}
		};
		Timer.schedule(addResources, resourceDeliveryTime, resourceDeliveryTime);

	}
	

	@Override
	public void destroy(){
		super.destroy();
		addResources.cancel();
	}
	

		
	
	
}
