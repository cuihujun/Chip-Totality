package com.world.tower;

import com.world.ship.Ship;

public class VioletGun extends Tower{
	final static int width = 1;
	final static int height = 1;
	static int firepower=10;
	static int maxHitpoints=150;
	static int range=2000;
	static float shootDelay=1;
	
	public VioletGun(int x, int y) {
		super(x, y, width, height, maxHitpoints, range);
	}
	
		

	@Override
	public void shoot(Ship target) {
		target.hitpoints-=firepower;
		if(target.hitpoints<=0){
			target.remove();
			currentTarget=null;
		}
	}

	@Override
	public void pay() {	
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

