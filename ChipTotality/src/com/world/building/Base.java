package com.world.building;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.gameInfo.GameStateHolder;

public class Base extends Building{
	static int resourceDeliveryAmount = 7;
	static int resourceDeliveryTime = 5;
	
	
	private Task addResources;

	
	public Base(int x, int y) {
		super(x, y);		
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

	
	
}
