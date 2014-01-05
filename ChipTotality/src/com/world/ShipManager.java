package com.world;

import com.main.Settings;
import com.screen.GameStage;
import com.world.ship.TestShip1;

public class ShipManager {
	private float waveNumber = 10;
	private final float maxShips = 400;//TODO for testing;]
	private float currentNumberOfships = 0;
	private float lastWaveTime;
	private final float waveInterval=5;
	
	
	public void generateWave(){
		currentNumberOfships = GameStage.shipsGroup.getChildren().size;
		for(int i=0; i<waveNumber*waveNumber*2+10; i++){
			if(currentNumberOfships<maxShips){
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
	
	

