package com.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.res.AssetsLoader;
import com.screen.MainMenuScreen;


public class ChipTotality extends Game {

	public SpriteBatch batch;

	@Override
	public void create() {	
		batch = new SpriteBatch();
		AssetsLoader.getInstance().loadAssets();
		AssetsLoader.getInstance().finishLoading();//normlanie robi sie update i get progress na loading screenie TODO loadingScreen
		AssetsLoader.getInstance().createResourcesAfterLoad();				
		this.setScreen(new MainMenuScreen(this));
	}
	
	public void render(){
		super.render();
	}
	
}