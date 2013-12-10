package com.world;

import java.util.Random;

import com.main.Settings;
import com.screen.GameStage;
import com.world.building.Building;
import com.world.ship.Ship;
import com.world.ship.TestShip1;

public class ShipManager {
	private static int MAXSHIPS = 50;
	private final GameStage stage;
	
	public ShipManager(GameStage actStage)
	{
		stage = actStage;
	}
	
	public void GenerateShips()
	{
		if(stage.getShip().size() < MAXSHIPS) {
			Random gen = new Random();
			TestShip1 ship = new  TestShip1(gen.nextInt()%Settings.WIDTH, Settings.HEIGHT-50);
			//ship.action.setDuration(3);
			Building building = stage.getRandBuilding();
			
			stage.addActor(ship);
			stage.getShip().add(ship);
		}
	}
	public void removeLostShips()	// Moze byc uruchamiane co jakie 100 ms lub nawet sekunde, odpowiada za usuwanei statkow ze sceny, jesli wyjda poza ekran gry
	{
		for(Ship ship : stage.getShip())
		{
			if(ship.getX()+ship.getWidth() < -20 || ship.getY()+ship.getHeight() <  -20 || ship.getX() > Settings.WIDTH || ship.getY() > Settings.HEIGHT)
			{
				ship.remove();
				stage.getShip().remove(ship);
			}
		}
	}
}
