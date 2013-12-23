package com.world.building;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.gameInfo.GameStateHolder;

public class Rafinery extends Building{
	private static int resourceDeliveryTime = 5;
	static int efficiency=2;
	
	private Task process;
	

	
	public Rafinery(int x, int y) {
		super(x, y);	
	}
	
	@Override
	public void destroy(){
		process.cancel();
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
