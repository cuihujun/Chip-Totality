package com.world;

import com.main.Settings;
import com.screen.GameStage;
import com.world.ship.TestShip1;

public class ShipManager {
	private float lastWaveTime;
	private final float waveInterval=45;
	
	
	
	public void act(float delta){
		lastWaveTime+=delta;
		if(lastWaveTime>waveInterval){
			generateWave();
		}
		
	}
	
	public void generateWave(){
		for(int i=0; i<10; i++){
			GameStage.shipsGroup.addActor(new TestShip1(100+(150*i), Settings.tilesVertical*Settings.tileSize));
		}
		lastWaveTime=0;
	}
	
	public void spawnMotherShip(){
		
	}
}
	
	

