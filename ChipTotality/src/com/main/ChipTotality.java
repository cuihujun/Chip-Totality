package com.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.res.Loader.AssetsLoader;
import com.screen.DiplomacyScreen;
import com.screen.GameScreen;
import com.screen.MainMenuScreen;


public class ChipTotality extends Game {

	public SpriteBatch batch;
	
	public MainMenuScreen mainMenuScreen;
	public GameScreen gameScreen;
	public DiplomacyScreen diplomacyScreen;
	
	@Override
	public void create() {	
		batch = new SpriteBatch();
		
		AssetsLoader.loadAssets();
		//TODO loadingScreen
		AssetsLoader.finishLoading();//normlanie robi sie update i get progress na loading screenie 		
		AssetsLoader.createResourcesAfterLoad();
		
		diplomacyScreen= new DiplomacyScreen(this);
		gameScreen = new GameScreen(this);
		mainMenuScreen=new MainMenuScreen(this);
		
		this.setScreen(mainMenuScreen);
	}
	
	public void render(){
		super.render();
	}
	
}