package com.screen.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.gameInfo.GameStateHolder;
import com.gameInfo.GameStateHolder.ChosenBuilding;
import com.gameInfo.GameStateHolder.Mode;
import com.main.ChipTotality;
import com.main.Settings;
import com.res.Loader.AssetsLoader;
import com.screen.GameStage;

public class GameScreenGUI {
	final ChipTotality game;	
	public Stage stage;
	public GameStage gameStage;
	
	private Label acodinLabel;
	private Label dirtyAcodinLabel;
	private Label beingsLabel;
	private Label fpsLabel;
	private Label buildingInfoLabel;
	
	private Label shipsLabel;
	
	
	private float acc=0;
	private final float ONE_SECOND = 1.0f;
	
	Table infoTable;	
	Table mainBuildingsTable;
	Table towersTable;
	Table confirmBuildTable;
	
	

	public GameScreenGUI(final ChipTotality game, final GameStage gameStage) {
		this.game = game;
		this.gameStage = gameStage;
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
			}
		});		
		towersTable.add(buildingsButton).top().fill();
		
		Array<String> towerNames = new Array<String>();
		towerNames.add("VioletGun");
		towerNames.add("YellowGun");
		
		for (String towerName : towerNames) {
			final String buildingTypeString = towerName;

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
		
		LabelStyle style = new LabelStyle();
		style.font = AssetsLoader.getFont();		
		
		infoTable = new Table();
		infoTable.setFillParent(true);
		infoTable.setSkin(skin);			
		infoTable.debug();
		infoTable.top().right();				
		
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
		
		shipsLabel = new Label("Ships Number:", skin);
		shipsLabel.setStyle(style);		
		shipsLabel.setAlignment(Align.top | Align.left);		
					
		buildingInfoLabel= new Label("ds", skin);
		buildingInfoLabel.setStyle(style);			
		buildingInfoLabel.setAlignment(Align.top | Align.left);
				
		infoTable.add(fpsLabel).left();		
		infoTable.add(shipsLabel).left();
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
		
		//TODO skad lista budynków mo¿e ustalone wed³ug lvl-a? fali albo cus;] moze byc w pliku zewnetrznym xml i ladowane tutaj
		Array<String> towerNames = new Array<String>();
		towerNames.add("AcodinMine");
		towerNames.add("Base");
		towerNames.add("HolyMountains");
		towerNames.add("Rafinery");
		towerNames.add("Temple");		
		
		for (String towerName : towerNames) {
			final String buildingTypeString = towerName;

			TextureRegion region = AssetsLoader.getBuildingIcon(buildingTypeString);
			ImageButtonStyle imageStyle = new ImageButtonStyle(skin.get(ButtonStyle.class));
			imageStyle.imageUp = new TextureRegionDrawable(region);
			imageStyle.imageDown = new TextureRegionDrawable(region);
			ImageButton iconButton = new ImageButton(imageStyle);

			iconButton.addListener(new ChangeListener() {
				@Override
				public void changed(ChangeEvent event, Actor actor) {
					GameStateHolder.mode = Mode.BUILDING;
					GameStateHolder.chosenBuilding = ChosenBuilding
							.valueOf(buildingTypeString);
				}
			});

			Label buildingNameLabel = new Label(buildingTypeString, skin);
			iconButton.add(buildingNameLabel).bottom();
			mainBuildingsTable.add(iconButton).top();

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
			shipsLabel.setText("Ships number: " + gameStage.shipsGroup.getChildren().size + "       ");
		}
	}
	
	public void render(float delta){
		update(delta);
		
		//game.batch.begin();
		//AssetsLoader.getSprite("infoPanel").draw(game.batch);
		//AssetsLoader.getSprite("actionPanel").draw(game.batch);
		//game.batch.end();
		stage.draw();
		if (Settings.DEBUG) Table.drawDebug(stage);
	}

}