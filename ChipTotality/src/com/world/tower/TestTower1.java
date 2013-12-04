package com.world.tower;

import com.screen.GameStage;
import com.world.ship.Ship;

public class TestTower1 extends Tower{
	final static int width = 2;
	final static int height = 2;
	static int firepower=5;
	static int maxHitpoints=150;
	static float range=1150;
	static float shootDelay=1;
	
	
	public TestTower1(int x, int y) {
		super(x, y, width, height, maxHitpoints);
	}
	
	
	
	@Override
	public boolean targetInRange() {
		if(currentTarget!=null && coordsFloat.dst2(currentTarget.getX(), currentTarget.getY()) <=range){
			return true;
		}
		return false;
	}

	
	@Override
	public void shoot(Ship target) {
		target.hitpoints-=firepower;
		if(target.hitpoints<=0){
			//TODO remove ship from stage	
			currentTarget=null;
		}
	}

	
	@Override
	public Ship findTarget() {
		for (Ship ship : GameStage.ships) {
			if(coordsFloat.dst2(ship.getX(), ship.getY()) <= range)
				currentTarget=ship;
		}	
		return null;
	}



	@Override
	public void pay() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void doTask() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	

}
