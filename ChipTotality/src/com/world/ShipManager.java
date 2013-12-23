package com.world;

import java.util.Random;

import com.action.FollowAction;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.screen.GameStage;
import com.world.building.Building;
import com.world.ship.TestShip1;

public class ShipManager {
	private static int MAXSHIPS = 50;
	private final GameStage stage;
	private final Timer timGen = new Timer();
	private final Timer timUpd = new Timer();
		
	
	
	class GenTask extends Task {
		@Override
		public void run()
		{
			GenerateShips();
		
		}
	}
	class UpdTask extends Task {
		@Override
		public void run()
		{
			Update();
		}
	}
	public ShipManager(GameStage actStage)
	{
		stage = actStage;
		timGen.scheduleTask(new GenTask(), 1,1);
		timUpd.scheduleTask(new UpdTask(), 0.05f,0.05f);	
	}
	
	public void GenerateShips()
	{
		if(GameStage.shipsGroup.getChildren().size < MAXSHIPS) {
			Random gen = new Random();
			
			TestShip1 ship = new  TestShip1(gen.nextInt(1000),2000);
			//action.setDuration(20);
			Building building = stage.getRandBuilding();
			Vector2 targ = null;
			if(building != null){
			
				targ = new Vector2(building.getX(), building.getY());
			}
				
			else
				targ = new Vector2(ship.getX(),-1000);
			FollowAction action = new FollowAction(targ, 5);
			//ship.addAction(action);
			float dy  = targ.y- ship.getY();
			float dx = targ.x - ship.getX();

			float degree = (float)(Math.atan2(dy, dx) * (float)(180/3.14))-90;
			ship.setRotation(degree);
			
			
		GameStage.shipsGroup.addActor(ship);
			//stage.getShips().add(ship);
		}
	}
	public void Update() {
		removeLostShips();
	}
	public void removeLostShips()	// Moze byc uruchamiane co jakie 100 ms lub nawet sekunde, odpowiada za usuwanei statkow ze sceny, jesli wyjda poza ekran gry
	{

		for(Actor ship : GameStage.shipsGroup.getChildren())//TODO not safe iterating and removing form list(use iterator and remove)
		{
			if(ship.getX()+ship.getWidth() < -20 || ship.getY()+ship.getHeight() <  -20 || ship.getX() > 3000|| ship.getY() > 3000)
			{
				ship.remove();
				break;
			}
}
		}
	}
	
	

