package com.world.tower;

import com.world.ship.Ship;

public class TestRocket1 extends Rocket {
	public static int speed = 500;
	public static int width = 20;
	public static int height = 20;

	public TestRocket1(float x, float y, Ship target) {
		super(x, y, width, height, speed, target);
	}
}
