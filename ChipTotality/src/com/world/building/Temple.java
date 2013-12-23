package com.world.building;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.gameInfo.GameStateHolder;

public class Temple extends Building  {
	private static int resourceDeliveryAmount = 5;
	private static int resourceDeliveryTime = 5;

	Task addResources;


	public Temple(int x, int y) {
		super(x, y);
	}

	@Override
	public void destroy() {
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



}
