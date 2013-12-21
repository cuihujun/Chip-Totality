package com.world.building;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.gameInfo.GameStateHolder;

public class AcodinMine extends Building{
	final static int width=2;
	final static int height=2;
	private static int maxHitpoints=200;
	private static int cost = 20;
	private static int resourceDeliveryAmount = 7;
	private static int resourceDeliveryTime = 5;

	private Task addResources;
	
	

	
	public AcodinMine(int x, int y) {
		super(x, y, width, height, maxHitpoints);	
	}
	
	@Override
	public void destroy(){
		addResources.cancel();
	}

	@Override
	public void pay() {
		GameStateHolder.beings -= cost;
		
	}

	@Override
	public void doTask() {
		addResources=new Task() {
			
			@Override
			public void run() {
				GameStateHolder.dirtyAcodin+=resourceDeliveryAmount;
			}
		};
		Timer.schedule(addResources, resourceDeliveryTime, resourceDeliveryTime);	
	}


}
