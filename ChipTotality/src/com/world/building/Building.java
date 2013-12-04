package com.world.building;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public abstract class Building {
	public final Vector2 coords;	//left bottom corner of the building
	public final Vector2 size;		//width and height of the building, in tiles; e.g. size(3,2) looks like [][][]
									//																		[][][]
	protected int hitpoints;
	public Boolean researchReady;
	
	protected int cost;
	
	
	
	public abstract void pay();
	public abstract void doTask();
	public abstract Texture getTexture();
	
	
	public Building(int x, int y, int width, int height) {
		coords = new Vector2(x, y);
		size = new Vector2(width, height);
		setResearchingFlag(this);
	}

	void setResearchingFlag(Building building){
		if(building instanceof Upgradeable)
			researchReady=true;
		else 
			researchReady=false;			
		
	}
	
	
	/**
	 * Conducts research. Note that you have to make sure, whether the exact upgrade
	 * is available for the building, for your own.
	 * 
	 * 
	 */
	public void research(Upgrade upgrade) {
		Upgrade.scheduleResearch(upgrade, this);
	}

	public void destroy() {
		
	}

}
