package com.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.screen.DiplomacyScreen;
import com.screen.GameScreen;
import com.screen.LoadingScreen;
import com.screen.MainMenuScreen;
import com.world.Asteroid;

 
public class ChipTotality extends Game {

	public SpriteBatch batch;
	public ShapeRenderer shapeRenderer;
	public Asteroid asteroid;
	
	public  MainMenuScreen mainMenuScreen;
	public  DiplomacyScreen diplomacyScreen;
	public  GameScreen gameScreen;
	public  LoadingScreen loadingScreen;
	public  OrthographicCamera camera;	
		
	public  InputMultiplexer inputMultiplexer;	


	@Override
	public void create() {		
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();		
		camera = new OrthographicCamera();
				
		loadingScreen = new LoadingScreen(this);									
			
		inputMultiplexer= new InputMultiplexer();
		Gdx.input.setInputProcessor(inputMultiplexer);
		
		this.setScreen(loadingScreen);
	}
	
	@Override
	public void render(){
		super.render();		
	}
	
}