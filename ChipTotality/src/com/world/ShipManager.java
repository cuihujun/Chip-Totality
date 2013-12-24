package com.world;

import com.main.Settings;
import com.screen.GameStage;
import com.world.ship.TestShip1;

public class ShipManager {
	private float waveNumber = 1;
	private float maxShips = 100;//TODO for testing;]
	private float currentNumberOfships = 0;
	private float lastWaveTime;
	private final float waveInterval=30;
	
	
	
	public void act(float delta){
		lastWaveTime+=delta;
		if(lastWaveTime>waveInterval){
			generateWave();
		}
		
	}
	
	public void generateWave(){
		currentNumberOfships = GameStage.shipsGroup.getChildren().size;
		for(int i=0; i<waveNumber*3+5; i++){
			if(currentNumberOfships<=maxShips){
				GameStage.shipsGroup.addActor(new TestShip1(100+(150*i), Settings.tilesVertical*Settings.tileSize));
				currentNumberOfships++;
			}
		}
		waveNumber++;
		lastWaveTime=0;
	}
	
	public void update(float delta){
		lastWaveTime+=delta;
		if (lastWaveTime>waveInterval){
			generateWave();
			lastWaveTime = 0;
		}
	}
	
	public void spawnMotherShip(){
		
	}
}
	
	

