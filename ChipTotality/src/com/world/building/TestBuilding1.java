package com.world.building;

import com.res.TexturesHolder;

public class TestBuilding1 extends Building {
	

	public TestBuilding1(float x, float y){
		super(x,y);
		bounds.height=60;
		bounds.width=100;
		buildingTexture=TexturesHolder.testBuilding1Texture;
	}
	
	
}
