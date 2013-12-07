package com.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.res.Loader.AssetsLoader;
import com.screen.DiplomacyScreen;
import com.screen.GameScreen;
import com.screen.GameStage;
import com.screen.MainMenuScreen;
import com.screen.controller.CameraController;
import com.screen.controller.GameController;
import com.world.Asteroid;

 
public class ChipTotality extends Game {

	public SpriteBatch batch;
	public Asteroid asteroid;
	
	public  MainMenuScreen mainMenuScreen;
	public GameStage gameStage;
	public  GameScreen gameScreen;
	public  DiplomacyScreen diplomacyScreen;
	
	
	
	private  InputMultiplexer inputMultiplexer;
	public CameraController cameraController;
	public  GameController gameController;


	@Override
	public void create() {		
		batch = new SpriteBatch();
		asteroid = new Asteroid();
		AssetsLoader.loadAssets();
		//TODO loadingScreen
		AssetsLoader.finishLoading();//normlanie robi sie update i get progress na loading screenie 		
		AssetsLoader.createResourcesAfterLoad();
			
		mainMenuScreen =new MainMenuScreen(this);
		gameScreen = new GameScreen(this);
		diplomacyScreen= new DiplomacyScreen(this);
		gameStage=new GameStage(this);
			
		gameController=new GameController(this);
		cameraController= new CameraController(gameScreen.camera);
		inputMultiplexer= new InputMultiplexer();
		inputMultiplexer.addProcessor(gameScreen.gameScreenGUI.stage);
		inputMultiplexer.addProcessor(cameraController);
		inputMultiplexer.addProcessor(gameController);
		inputMultiplexer.addProcessor(gameStage);
		Gdx.input.setInputProcessor(inputMultiplexer);	
		
		this.setScreen(mainMenuScreen);
	}
	
	@Override
	public void render(){
		super.render();
	}
	
}