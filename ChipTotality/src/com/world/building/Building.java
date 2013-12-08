package com.world.building;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.gameInfo.Coords;
import com.main.Settings;
import com.res.Loader.AssetsLoader;

public abstract class Building extends Actor{
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
	
	
	public Building(int x, int y, int width, int height, int maxHP) {
		//rectangle bounds
		setBounds(x*Settings.tileSize, y*Settings.tileSize, width*Settings.tileSize, height*Settings.tileSize);
		//tile coords
		coords = new Coords(x, y);
		
		size = new Vector2(width, height);
		hitpoints=maxHP;
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
		remove();
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(getTexture(), getX(), getY());
	}
}
