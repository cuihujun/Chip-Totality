package com.world.building;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.gameInfo.GameStateHolder;

public class Base extends Building implements Upgradeable{
	final static int width=1;
	final static int height=1;
	static int maxHitpoints=500;
	private static int cost = 121;
	static int resourceDeliveryAmount = 7;
	static int resourceDeliveryTime = 5;
	
	
	private Task addResources;

	
	public Base(){
		super(width, height);
	}
	
	public Base(int x, int y) {
		super(x, y, width, height, maxHitpoints);		
	}
	

	@Override
	public void destroy(){
		super.destroy();
		addResources.cancel();
	}


	@Override
	public void pay() {
		GameStateHolder.beings -= cost;	
	}


	@Override
	public void doTask() {
		addResources = new Task() {
			@Override
			public void run() {
				GameStateHolder.beings += resourceDeliveryAmount;
			}
		};
		Timer.schedule(addResources, resourceDeliveryTime, resourceDeliveryTime);
		
	}



	@Override
	public void dispose() {
		addResources.cancel();
		
	}
	

		
	
	
}
