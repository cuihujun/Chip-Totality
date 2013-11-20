package com.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.res.Loader.AssetsLoader;
import com.screen.MainMenuScreen;


public class ChipTotality extends Game {

	public SpriteBatch batch;
	
	@Override
	public void create() {	
		batch = new SpriteBatch();
		
		AssetsLoader.loadAssets();
		//TODO loadingScreen
		AssetsLoader.finishLoading();//normlanie robi sie update i get progress na loading screenie 		
		AssetsLoader.createResourcesAfterLoad();				
		this.setScreen(new MainMenuScreen(this));
	}
	
	public void render(){
		super.render();
	}
	
}