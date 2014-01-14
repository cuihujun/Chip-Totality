package com.world;

import java.util.Random;

import com.main.Settings;
import com.screen.GameStage;
import com.world.ship.TestShip1;

public class ShipManager {
	private float waveNumber = 1;
	private final float maxShips = 300;//TODO for testing;]
	private float currentNumberOfships = 0;
	private float lastWaveTime;
	private final float waveInterval=20;
	
	
	public void generateWave(){
		currentNumberOfships = GameStage.shipsGroup.getChildren().size;
		for(int i=0; i<waveNumber*3+5; i++){
			Random generator = new Random();
			int direction = generator.nextInt(2);
			if(currentNumberOfships<maxShips){
				GameStage.shipsGroup.addActor(new TestShip1(100+(150*i), -800+direction*(800+Settings.tilesVertical*Settings.tileSize)));
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
	
	

