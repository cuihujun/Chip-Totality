package com.world.building;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.gameInfo.GameStateHolder;

public class Temple extends Building implements Upgradeable {
	final static int width = 2;
	final static int height = 2;
	private static int maxHitpoints = 200;
	private static int cost = 20;
	private static int resourceDeliveryAmount = 5;
	private static int resourceDeliveryTime = 5;

	Task addResources;

	public Temple(){
		super(width, height);
	}
	
	public Temple(int x, int y) {
		super(x, y, width, height);
		hitpoints = maxHitpoints;

	}

	@Override
	public void destroy() {
		super.destroy();
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
		Timer.schedule(addResources, resourceDeliveryTime);
	}


	@Override
	public void dispose() {
		addResources.cancel();
		
	}

}
