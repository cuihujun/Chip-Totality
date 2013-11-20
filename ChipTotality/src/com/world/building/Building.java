package com.world.building;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Building {
	public final Vector2 coords;
	public Texture buildingTexture;
	protected Rectangle bounds;

	protected int hitpoints;
	Boolean isResearching = true;

	public Building(float x, float y, int width, int height) {
		coords = new Vector2(x, y);
		bounds = new Rectangle(x - (0.5f * width), y - (0.5f * height), width,
				height);
	}

	public boolean overlaps(Building building) {
		return this.bounds.overlaps(building.bounds);
	}
	public void draw(SpriteBatch batch)
	{
		batch.draw(buildingTexture, 0, 0);
	}
	public abstract void run(float deltaTime); //Time in ms
	

	/**
	 * Conducts research. Note that you have to make sure, if the exact upgrade
	 * is available for the building, for your own.
	 * 
	 * 
	 */
	//public void research(Upgrade upgrade) {
	//	Upgrade.scheduleResearch(upgrade, this);
//}

	public void destroy() {
		buildingTexture.dispose();
	}

}
