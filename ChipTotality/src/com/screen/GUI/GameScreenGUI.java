package com.screen.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
<<<<<<< HEAD
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
=======
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
>>>>>>> 5d92dc957f51a7f999b4229a43f19e3eb417e2e1
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.gameInfo.GameStateHolder;
import com.gameInfo.GameStateHolder.ChosenBuilding;
import com.gameInfo.GameStateHolder.Mode;
import com.main.ChipTotality;
import com.main.Settings;
import com.res.Loader.AssetsLoader;

public class GameScreenGUI {
	final ChipTotality game;	
	public Stage stage;
	
	private Label acodinLabel;
	private Label dirtyAcodinLabel;
	private Label beingsLabel;
	private Label fpsLabel;
<<<<<<< HEAD
=======
	private Label buildingInfoLabel;
>>>>>>> 5d92dc957f51a7f999b4229a43f19e3eb417e2e1
	
	private float acc=0;
	private final float ONE_SECOND = 1.0f;
	
	Table infoTable;	
	Table mainBuildingsTable;
	Table towersTable;
	Table confirmBuildTable;
	
<<<<<<< HEAD
=======
	
>>>>>>> 5d92dc957f51a7f999b4229a43f19e3eb417e2e1

	public GameScreenGUI(final ChipTotality game) {
		this.game = game;
		stage = new Stage(Settings.VIEW_WIDTH , Settings.VIEW_HEIGHT , true);
				
		//TODO background tabelek
		//table.add(new Image(skin.newDrawable("white", Color.RED))).size(64);
		createInfoTab();
		createTowersTab();
		createMainBuildingsTab();	
						
		stage.addActor(infoTable);
		stage.addActor(mainBuildingsTable);
		
		update(ONE_SECOND);
	}
	
<<<<<<< HEAD
=======

>>>>>>> 5d92dc957f51a7f999b4229a43f19e3eb417e2e1
	private void createTowersTab(){
		Skin skin = AssetsLoader.getSkin();
		
		LabelStyle style = new LabelStyle();
		style.font = AssetsLoader.getFont();
		
					
		towersTable = new Table();
		towersTable.setFillParent(true);
		towersTable.setSkin(skin);
		towersTable.debug();
		towersTable.center().bottom();
		
		TextButton buildingsButton = new TextButton("Buildings", skin);		
		buildingsButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				switchTooBuildings();	    				
<<<<<<< HEAD
			}
		});		
		towersTable.add(buildingsButton).top().fill();
		
		for(ChosenBuilding buildingType: ChosenBuilding.values() ){
			if (buildingType!=ChosenBuilding.none){
				final String buildingTypeString = buildingType.toString();
				
				TextureRegion region = AssetsLoader.getBuildingIcon(buildingTypeString);
	    		ImageButtonStyle imageStyle = new ImageButtonStyle(skin.get(ButtonStyle.class));   	
	    		imageStyle.imageUp = new TextureRegionDrawable(region);
	    		imageStyle.imageDown = new TextureRegionDrawable(region);
	    		ImageButton iconButton = new ImageButton(imageStyle);
	    			    		
	    			    	
	    		iconButton.addListener(new ChangeListener() {
	    			@Override
	    			public void changed(ChangeEvent event, Actor actor) {
	    				GameStateHolder.mode = Mode.BUILDING;
	    				GameStateHolder.chosenBuilding = ChosenBuilding.valueOf(buildingTypeString);	    				
	    			}
	    		});
	    			    		
	    		Label buildingNameLabel = new Label(buildingTypeString, skin);	    		
	    		iconButton.add(buildingNameLabel).bottom();
	    		towersTable.add(iconButton).top();
			}
=======
			}
		});		
		towersTable.add(buildingsButton).top().fill();
		
		for(ChosenBuilding buildingType: ChosenBuilding.values() ){
			if (buildingType!=ChosenBuilding.none){
				final String buildingTypeString = buildingType.toString();
				
				TextureRegion region = AssetsLoader.getBuildingIcon(buildingTypeString);
	    		ImageButtonStyle imageStyle = new ImageButtonStyle(skin.get(ButtonStyle.class));   	
	    		imageStyle.imageUp = new TextureRegionDrawable(region);
	    		imageStyle.imageDown = new TextureRegionDrawable(region);
	    		ImageButton iconButton = new ImageButton(imageStyle);
	    			    		
	    			    	
	    		iconButton.addListener(new ChangeListener() {
	    			@Override
	    			public void changed(ChangeEvent event, Actor actor) {
	    				GameStateHolder.mode = Mode.BUILDING;
	    				GameStateHolder.chosenBuilding = ChosenBuilding.valueOf(buildingTypeString);	    				
	    			}
	    		});
	    			    		
	    		Label buildingNameLabel = new Label(buildingTypeString, skin);	    		
	    		iconButton.add(buildingNameLabel).bottom();
	    		towersTable.add(iconButton).top();
			}
>>>>>>> 5d92dc957f51a7f999b4229a43f19e3eb417e2e1
		}
		
		towersTable.pack();
		
	}
	
	private void switchTooTowers(){		
		stage.clear();
		stage.addActor(infoTable);
		stage.addActor(towersTable);
	}
	
	private void switchTooBuildings(){		
		stage.clear();
		stage.addActor(infoTable);
		stage.addActor(mainBuildingsTable);
	}
	
	private void createInfoTab(){
		Skin skin = AssetsLoader.getSkin();
<<<<<<< HEAD
		
		LabelStyle style = new LabelStyle();
		style.font = AssetsLoader.getFont();		
		
		infoTable = new Table();
		infoTable.setFillParent(true);
		infoTable.setSkin(skin);			
		infoTable.debug();
		infoTable.top().right();				
		
=======
		
		LabelStyle style = new LabelStyle();
		style.font = AssetsLoader.getFont();		
		
		infoTable = new Table();
		infoTable.setFillParent(true);
		infoTable.setSkin(skin);			
		infoTable.debug();
		infoTable.top().right();				
		
>>>>>>> 5d92dc957f51a7f999b4229a43f19e3eb417e2e1
		acodinLabel = new Label("Acodin : ", skin);
		acodinLabel.setStyle(style);		
		acodinLabel.setAlignment(Align.top | Align.left);		

		beingsLabel = new Label("Beings : ", skin);
		beingsLabel.setStyle(style);		
		beingsLabel.setAlignment(Align.top | Align.left);
		
		dirtyAcodinLabel = new Label("Dirty Acodin : ", skin);
		dirtyAcodinLabel.setStyle(style);		
		dirtyAcodinLabel.setAlignment(Align.top | Align.left);
		
		fpsLabel = new Label("Fps : ", skin);
		fpsLabel.setStyle(style);		
		fpsLabel.setAlignment(Align.top | Align.left);
<<<<<<< HEAD
			
		infoTable.add(fpsLabel).left();
		
		
		infoTable.add(acodinLabel).top().right();
		infoTable.add(beingsLabel).top().right();
		infoTable.add(dirtyAcodinLabel).top().right();		
		
		
		infoTable.pack();		
	}
	
	private void createMainBuildingsTab(){
		Skin skin = AssetsLoader.getSkin();
		
		LabelStyle style = new LabelStyle();
		style.font = AssetsLoader.getFont();
		
					
		mainBuildingsTable = new Table();
		mainBuildingsTable.setFillParent(true);
		mainBuildingsTable.setSkin(skin);
		mainBuildingsTable.debug();
		mainBuildingsTable.center().bottom();
		
		TextButton towersButton = new TextButton("Towers", skin);		
		towersButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				switchTooTowers();	    				
			}
		});		
		mainBuildingsTable.add(towersButton).top().fill();		
		
		for(ChosenBuilding buildingType: ChosenBuilding.values() ){
			if (buildingType!=ChosenBuilding.none){
				final String buildingTypeString = buildingType.toString();
				
				TextureRegion region = AssetsLoader.getBuildingIcon(buildingTypeString);
	    		ImageButtonStyle imageStyle = new ImageButtonStyle(skin.get(ButtonStyle.class));    	
	    		imageStyle.imageUp = new TextureRegionDrawable(region);
	    		imageStyle.imageDown = new TextureRegionDrawable(region);
	    		ImageButton iconButton = new ImageButton(imageStyle);
	    			    		
	    			    	
	    		iconButton.addListener(new ChangeListener() {
	    			@Override
	    			public void changed(ChangeEvent event, Actor actor) {
	    				GameStateHolder.mode = Mode.BUILDING;
	    				GameStateHolder.chosenBuilding = ChosenBuilding.valueOf(buildingTypeString);	    				
	    			}
	    		});
	    			    		
	    		Label buildingNameLabel = new Label(buildingTypeString, skin);	    		
	    		iconButton.add(buildingNameLabel).bottom();
	    		mainBuildingsTable.add(iconButton).top();
			}
=======
		
		
		buildingInfoLabel= new Label("ds", skin);
		buildingInfoLabel.setStyle(style);			
		buildingInfoLabel.setAlignment(Align.top | Align.left);
		
		infoTable.add(buildingInfoLabel).top().left();
		infoTable.add(fpsLabel).left();
		infoTable.add(acodinLabel).top().right();
		infoTable.add(beingsLabel).top().right();
		infoTable.add(dirtyAcodinLabel).top().right();		
		
		
		infoTable.pack();		
	}
	
	private void createMainBuildingsTab(){
		Skin skin = AssetsLoader.getSkin();
		
		LabelStyle style = new LabelStyle();
		style.font = AssetsLoader.getFont();
		
					
		mainBuildingsTable = new Table();
		mainBuildingsTable.setFillParent(true);
		mainBuildingsTable.setSkin(skin);
		mainBuildingsTable.debug();
		mainBuildingsTable.center().bottom();
		
		TextButton towersButton = new TextButton("Towers", skin);		
		towersButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				switchTooTowers();	    				
			}
		});		
		mainBuildingsTable.add(towersButton).top().fill();		
		
		for(ChosenBuilding buildingType: ChosenBuilding.values() ){
			if (buildingType!=ChosenBuilding.none){
				final String buildingTypeString = buildingType.toString();
				
				TextureRegion region = AssetsLoader.getBuildingIcon(buildingTypeString);
	    		ImageButtonStyle imageStyle = new ImageButtonStyle(skin.get(ButtonStyle.class));    	
	    		imageStyle.imageUp = new TextureRegionDrawable(region);
	    		imageStyle.imageDown = new TextureRegionDrawable(region);
	    		ImageButton iconButton = new ImageButton(imageStyle);
	    			    		
	    			    	
	    		iconButton.addListener(new ChangeListener() {
	    			@Override
	    			public void changed(ChangeEvent event, Actor actor) {
	    				GameStateHolder.mode = Mode.BUILDING;
	    				GameStateHolder.chosenBuilding = ChosenBuilding.valueOf(buildingTypeString);	    				
	    			}
	    		});
	    			    		
	    		Label buildingNameLabel = new Label(buildingTypeString, skin);	    		
	    		iconButton.add(buildingNameLabel).bottom();
	    		mainBuildingsTable.add(iconButton).top();
			}
			
>>>>>>> 5d92dc957f51a7f999b4229a43f19e3eb417e2e1
		}
		
		mainBuildingsTable.pack();
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
			fpsLabel.setText("Fps : " + Gdx.graphics.getFramesPerSecond() + "         ");
		}
	}
	
	public void render(float delta){
		update(delta);
		
<<<<<<< HEAD
		game.batch.begin();
		AssetsLoader.getSprite("infoPanel").draw(game.batch);
		AssetsLoader.getSprite("actionPanel").draw(game.batch);
		game.batch.end();
=======
		//game.batch.begin();
		//AssetsLoader.getSprite("infoPanel").draw(game.batch);
		//AssetsLoader.getSprite("actionPanel").draw(game.batch);
		//game.batch.end();
>>>>>>> 5d92dc957f51a7f999b4229a43f19e3eb417e2e1
		stage.draw();
		if (Settings.DEBUG) Table.drawDebug(stage);
	}

}
