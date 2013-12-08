package com.screen;


import java.util.ArrayList;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.main.ChipTotality;
import com.main.Settings;
import com.world.building.Building;
import com.world.ship.TestShip1;
//class for ships, bullets, and buildings

public class GameStage extends Stage{
	final ChipTotality game;
	QuadTree quadTree, bulletTree;	
	
	
	public GameStage(ChipTotality game) {
		super(Settings.WIDTH, Settings.HEIGHT, true, game.batch);
		this.game=game;
		quadTree = new QuadTree(0, new Rectangle(0,0, Settings.tilesHorizontal*Settings.tileSize, Settings.tilesVertical*Settings.tileSize));
		bulletTree = new QuadTree(0, new Rectangle(0,0, Settings.tilesHorizontal*Settings.tileSize, Settings.tilesVertical*Settings.tileSize));
		
		
		//TODO do usuniecia kiedystam
		TestShip1 ts1 = new TestShip1(300, 300);
		addActor(ts1);
		ts1.action.setDuration(4);
		ts1.action.setPosition(1500, 1400);	
		ts1.addAction(ts1.action);	
		TestShip1 ts2 = new TestShip1(590, 300);
		addActor(ts2);
		TestShip1 ts3 = new TestShip1(1500, 1400);
		addActor(ts3);
		
		
		///////////////////
		setCamera(game.gameScreen.camera);
		
	}
	
	
	public void checkCollisions(){
		quadTree.clear();
		quadTree.insert(getActors());
		ArrayList<Actor> returnObjects = new ArrayList<Actor>();
		for (Actor currentActor : game.gameStage.getActors()) {
			returnObjects.clear();
			returnObjects=game.gameStage.quadTree.retrieve(currentActor);
			for (Actor possiblyColliding : returnObjects) {
				Rectangle currBounds = new Rectangle(currentActor.getX(), currentActor.getY(), currentActor.getWidth(), currentActor.getHeight());
				Rectangle possBounds = new Rectangle(possiblyColliding.getX(), possiblyColliding.getY(), possiblyColliding.getWidth(), possiblyColliding.getHeight());
				if(currBounds.overlaps(possBounds))
					Gdx.app.log("collision", "collision beetween"+currentActor.getClass().getSimpleName()+" and "+ possiblyColliding.getClass().getSimpleName());					
			}
		}
	}
	
	public void checkBulletCollisions(Vector<Building> buildings){
		
	}
	
	
}
