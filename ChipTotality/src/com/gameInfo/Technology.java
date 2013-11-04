package com.gameInfo;

public enum Technology {
	techName0(0, 10),
	techName1(1, 150),
	techName2(2, 100),
	techName3(3, 350);
	
	public final int id;
	public float timeToResearch;
	
	Technology(int id, float timeToResearch){
		this.id=id;
		this.timeToResearch=timeToResearch;
	}
}
