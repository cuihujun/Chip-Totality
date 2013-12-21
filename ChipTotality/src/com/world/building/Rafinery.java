package com.world.building;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.gameInfo.GameStateHolder;

public class Rafinery extends Building{
	final static int width=3;
	final static int height=3;
	private static int maxHitpoints=500;
	private static int cost = 100;
	private static int resourceDeliveryTime = 5;
	static int efficiency=2;
	
	private Task process;
	

	
	public Rafinery(int x, int y) {
		super(x, y, width, height, maxHitpoints);	
	}
	
	@Override
	public void destroy(){
		process.cancel();
	}

	@Override
	public void pay() {
		GameStateHolder.beings -= cost;
		
	}

	@Override
	public void doTask() {
		process = new Task() {
			@Override
			public void run() {
				if(GameStateHolder.dirtyAcodin>=efficiency){
					GameStateHolder.dirtyAcodin-=efficiency;
					GameStateHolder.acodin+=efficiency;
				}				
			}	
		};
		Timer.schedule(process, resourceDeliveryTime, resourceDeliveryTime);
		
	}


	
}
