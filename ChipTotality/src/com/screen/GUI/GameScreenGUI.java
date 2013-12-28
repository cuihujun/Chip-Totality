package com.screen.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
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
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.gameInfo.Coords;
import com.gameInfo.GameStateHolder;
import com.gameInfo.GameStateHolder.ChosenBuilding;
import com.gameInfo.GameStateHolder.Mode;
import com.main.ChipTotality;
import com.main.Settings;
import com.res.Loader.AssetsLoader;
import com.world.building.AcodinMine;
import com.world.building.Base;
import com.world.building.Building;
import com.world.building.HolyMountains;
import com.world.building.Rafinery;
import com.world.building.Temple;

public class GameScreenGUI {
	final ChipTotality game;	
	public Stage stage;
	
	private Label acodinLabel;
	private Label dirtyAcodinLabel;
	private Label beingsLabel;
	private Label fpsLabel;
	private Label buildingInfoLabel;
	
	private static boolean BuildingPropertiesTabOpen = false;
	
	private float acc=0;
	private final float ONE_SECOND = 1.0f;
	
	Table infoTable;	
	Table mainBuildingsTable;
	Table towersTable;
	Table confirmBuildTable;

	private class BuildingPropertiesTab extends Table {
		Building focusedBuilding;
		
		Label infoLabel;
		TextButton deleteButton;
		
		BuildingPropertiesTab() {
			Skin skn = AssetsLoader.getSkin();
			LabelStyle style = new LabelStyle();
			style.font = AssetsLoader.getFont();
			
			setFillParent(true);
			setSkin(skn);
			setSize(150, 250);
			setVisible(false);
			debug();
			
			infoLabel = new Label("", skn);
			infoLabel.setSize(300, 100);
			infoLabel.setWrap(true);
			row();
			
			deleteButton = new TextButton("Delete", skn);
			add(deleteButton);
			deleteButton.addListener(new ChangeListener() {
				public void changed(ChangeEvent event, Actor actor) {
					game.gameScreen.gameController.removeBuilding(focusedBuilding);
					BuildingPropertiesTabOpen = false;
					switchTooBuildings();
					setVisible(false);
				}
			});
			
			
			add(infoLabel).fill();
			
			layout();
			
			pack();
		}
		
		public void update(Building b, Coords c) {
			setPosition(c.x - Settings.VIEW_WIDTH/2,  Settings.VIEW_HEIGHT/2 - c.y);
			focusedBuilding = b;
			
			infoLabel.setText("Type: " + b.getClass().toString() + "\nHitpoints:" + b.getHP() + "\n");
			
			setVisible(true);
		}
	}
	
	public BuildingPropertiesTab buildingPropertiesTab;

	public GameScreenGUI(final ChipTotality game) {
		this.game = game;
		stage = new Stage(Settings.VIEW_WIDTH , Settings.VIEW_HEIGHT , true);

		//TODO background tabelek
		//table.add(new Image(skin.newDrawable("white", Color.RED))).size(64);
		createInfoTab();
		createTowersTab();
		createMainBuildingsTab();	
		buildingPropertiesTab = new BuildingPropertiesTab();

		stage.addActor(infoTable);
		stage.addActor(mainBuildingsTable);
		stage.addActor(buildingPropertiesTab);
		
		update(ONE_SECOND);
	}
	
	public boolean getBuildingPropertiesTabInfo() {
		return BuildingPropertiesTabOpen;
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
	
	/**
	 * opens properties tab for a building if no tab is currently opened
	 * @param b is a building properties of which will be displayed
	 * @param c are the view-relative coordinates of the cursor
	 */
	
	public void openBuildingPropertiesTab(Building b, Coords c) {
		if(GameScreenGUI.BuildingPropertiesTabOpen == false) {
			GameScreenGUI.BuildingPropertiesTabOpen = true;
			Gdx.app.log("BuildingPropertiesTab", "Properties opened");
			buildingPropertiesTab.update(b, c);
			stage.addActor(buildingPropertiesTab);
		}
	}
	
	public void closeBuildingPropertiesTab() {
		buildingPropertiesTab.setVisible(false);
		BuildingPropertiesTabOpen = false;
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
		
		//game.batch.begin();
		//AssetsLoader.getSprite("infoPanel").draw(game.batch);
		//AssetsLoader.getSprite("actionPanel").draw(game.batch);
		//game.batch.end();
		stage.draw();
		if (Settings.DEBUG) Table.drawDebug(stage);
	}

}