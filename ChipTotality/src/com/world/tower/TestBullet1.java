package com.world.tower;

import com.world.ship.Ship;


public class TestBullet1 extends Bullet {	
	public static int speed;
	public static int width = 20;
	public static int height = 20;
	public TestBullet1(float x, float y, Ship target) {
		super(x, y, width, height, speed, target);
	}
	
	

}
