package com.screen;

import java.util.Vector;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.main.ChipTotality;
import com.main.Settings;
import com.world.ship.Ship;
import com.world.ship.TestShip1;
//class for ships, bullets, and buildings

public class GameStage extends Stage{
	final ChipTotality game;
	public static Vector<Ship> ships;
	
	public GameStage(ChipTotality game) {
		super(Settings.WIDTH, Settings.HEIGHT, true, game.batch);
		this.game=game;
		
		ships = new Vector<Ship>();
		ships.add(new TestShip1(300, 300));
		addActor(ships.get(0));
		setCamera(game.gameScreen.camera);
		
	}
	
	
	
	
}
