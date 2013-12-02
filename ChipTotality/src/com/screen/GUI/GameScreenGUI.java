package com.screen.GUI;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.gameInfo.GameStateHolder;
import com.main.ChipTotality;
import com.main.Settings;
import com.res.Loader.AssetsLoader;

public class GameScreenGUI {
	final ChipTotality game;	
	public Stage stage;
	
	private Label acodinLabel;
	private Label dirtyAcodinLabel;
	private Label beingsLabel;
	
	private float acc=0;
	private final float ONE_SECOND = 1.0f;
	

	public GameScreenGUI(final ChipTotality game) {
		this.game = game;
		stage = new Stage(Settings.VIEW_WIDTH , Settings.VIEW_HEIGHT , true);
		Skin skin = AssetsLoader.getSkin();
			
		Table actionTable = new Table();
		actionTable.setFillParent(true);
		actionTable.setSkin(skin);			
		actionTable.debug();
		
		
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
		actionTable.center().bottom();
		actionTable.add(buildingButton);

		



		Table infoTable = new Table();
		infoTable.setFillParent(true);
		infoTable.setSkin(skin);			
		infoTable.debug();
		infoTable.top().right();
		
		LabelStyle style = new LabelStyle();
		style.font = AssetsLoader.getFont();		
		
		acodinLabel = new Label("Acodin : ", skin);
		acodinLabel.setStyle(style);		
		acodinLabel.setAlignment(Align.top | Align.left);		

		beingsLabel = new Label("Beings : ", skin);
		beingsLabel.setStyle(style);		
		beingsLabel.setAlignment(Align.top | Align.left);
		
		dirtyAcodinLabel = new Label("Dirty Acodin : ", skin);
		dirtyAcodinLabel.setStyle(style);		
		dirtyAcodinLabel.setAlignment(Align.top | Align.left);	
			
		
		infoTable.add(acodinLabel).top().right();
		infoTable.add(beingsLabel).top().right();
		infoTable.add(dirtyAcodinLabel).top().right();
		
		infoTable.pack();
		actionTable.pack();
		stage.addActor(infoTable);
		stage.addActor(actionTable);
		
		update(ONE_SECOND);
	}
	
	void dispose(){
		stage.dispose();
	}
	
	private void update(float delta){
		acc+=delta;
		if (acc>ONE_SECOND){
			acc-=ONE_SECOND;
			acodinLabel.setText("Acodin: " + GameStateHolder.acodin + " ");
			beingsLabel.setText("Beings: " + GameStateHolder.beings + " ");
			dirtyAcodinLabel.setText("Dirty Acodin: " + GameStateHolder.dirtyAcodin + " ");
		}
	}
	
	public void render(float delta){
		update(delta);
		
		game.batch.begin();
		AssetsLoader.getSprite("infoPanel").draw(game.batch);
		AssetsLoader.getSprite("actionPanel").draw(game.batch);
		game.batch.end();
		stage.draw();
		if (Settings.DEBUG) Table.drawDebug(stage);
	}

}
