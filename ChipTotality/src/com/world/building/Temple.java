package com.world.building;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.gameInfo.GameStateHolder;

public class Temple extends Building implements Upgradeable{
	private static int maxHitpoints=200;
	private static int cost = 20;
	private static int resourceDeliveryAmount = 5;
	private static int resourceDeliveryTime = 5;
	
	Task addResources;
	
	public Temple(float x, float y, int width, int height) {
		super(x, y, width, height);		
		hitpoints=maxHitpoints;
		GameStateHolder.beings -= cost;
		
		addResources=new Task() {
			
			@Override
			public void run() {
				GameStateHolder.beings+=resourceDeliveryAmount;
			}
		};
		Timer.schedule(addResources, resourceDeliveryTime);
	}
	
	
	@Override
	public void destroy(){
		super.destroy();	
	}



	
}
