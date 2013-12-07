package com.screen;

import java.awt.Rectangle;
import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.main.ChipTotality;
import com.main.Settings;
import com.world.ship.TestShip1;
//class for ships, bullets, and buildings

public class GameStage extends Stage{
	final ChipTotality game;
	QuadTree quadTree;	
	public ArrayList<StageObject> allActors;		//all actors in the game
	
	public GameStage(ChipTotality game) {
		super(Settings.WIDTH, Settings.HEIGHT, true, game.batch);
		this.game=game;
		quadTree = new QuadTree(0, new Rectangle(0,0, Settings.tilesHorizontal*Settings.tileSize, Settings.tilesVertical*Settings.tileSize));
		allActors = new ArrayList<StageObject>();
		
		
		//TODO do usuniecia kiedystam
		TestShip1 ts1 = new TestShip1(300, 300);
		allActors.add(ts1);
		//addActor(allActors.get(0));
		//ts1.action.setDuration(10);
		//ts1.action.setPosition(1500, 1400);	
		//ts1.addAction(ts1.action);
		
		TestShip1 ts2 = new TestShip1(590, 300);
		//allActors.add(ts2);
		//addActor(allActors.get(1));
		
		TestShip1 ts3 = new TestShip1(1500, 1400);
	//	allActors.add(ts3);
		//addActor(allActors.get(2));
		
		
		///////////////////
		setCamera(game.gameScreen.camera);
		
	}
	
	public void resetTree(){
		quadTree.clear();
		quadTree.insert(allActors);
		
		//TODO zrobic budynki i pociski aktorami i je dodawac
	}
	
	
	
}
