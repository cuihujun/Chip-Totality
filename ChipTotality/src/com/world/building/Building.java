package com.world.building;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.gameInfo.Coords;
import com.res.Loader.AssetsLoader;

public abstract class Building {
<<<<<<< HEAD
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
=======
	public final Coords coords;	
	public final Vector2 size;
	
	protected int hitpoints;
	public Boolean researchReady;
	
	
	public abstract void pay();
	public abstract void doTask();
	public abstract void dispose();
	
	public Texture getTexture() {
		return AssetsLoader.getTexture(this.getClass().getSimpleName());
	}
	
	

	public Building(){	
		coords = new Coords(0, 0);
		size = new Vector2(0, 0);
	}
	
	public Building(int width, int height){
		coords = new Coords(0, 0);
		size = new Vector2(width, height);
	}
	
	public Building(int x, int y, int width, int height, int maxHP) {
		coords = new Coords(x, y);
		size = new Vector2(width, height);
		hitpoints=maxHP;
>>>>>>> 5d92dc957f51a7f999b4229a43f19e3eb417e2e1
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
