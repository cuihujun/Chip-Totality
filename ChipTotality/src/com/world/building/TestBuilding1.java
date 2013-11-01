package com.world.building;

import com.res.TexturesHolder;

public class TestBuilding1 extends Building {
	private final static int width = 64;
	private final static int height = 64;

	public TestBuilding1(float x, float y){
		super(x,y, width, height);
		buildingTexture=TexturesHolder.testBuilding1Texture;
	}
	
	
}
