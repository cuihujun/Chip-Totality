package com.screen;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.main.ChipTotality;
import com.main.Settings;

public class GameStage {
	final ChipTotality game;
	public Stage stage;
	
	
	public GameStage(ChipTotality game) {
		this.game=game;
		stage=new Stage(Settings.WIDTH, Settings.HEIGHT, true, game.batch);		
	}
	
	
}
