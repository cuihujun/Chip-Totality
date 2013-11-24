package com.screen.GUI;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.gameInfo.GameStateHolder;
import com.main.ChipTotality;
import com.main.Settings;
import com.res.Loader.AssetsLoader;

public class GameScreenGUI {
	final ChipTotality game;	
	public Stage stage;

	public GameScreenGUI(final ChipTotality game) {
		this.game = game;
		stage = new Stage(Settings.VIEW_WIDTH , Settings.VIEW_HEIGHT , true);
		Skin skin = AssetsLoader.getSkin();
			
		Table mainTable = new Table();
		mainTable.setFillParent(true);
		mainTable.setSkin(skin);	
		stage.addActor(mainTable);
        

        
		Button buildingButton = new TextButton(" Bulding \n Mode ", skin);	
		buildingButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (GameStateHolder.mode != GameStateHolder.Mode.BUILDING) {
					GameStateHolder.mode = GameStateHolder.Mode.BUILDING;
					//TODO game.gameScreen.asteroid.freeLayer.setVisible(true);
				} else {
					GameStateHolder.mode = GameStateHolder.Mode.NONE;
					//TODO game.gameScreen.asteroid.freeLayer.setVisible(false);
				}

			}
		});
		mainTable.add(buildingButton);
		
		
			
	}
	
	void dispose(){
		stage.dispose();
	}

}
