package com.res;

import java.io.IOException;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader.TextureParameter;
import com.badlogic.gdx.assets.loaders.ParticleEffectLoader.ParticleEffectParameter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;


public class AssetsLoader {
	private static AssetsLoader loader;

	private AssetManager manager = new AssetManager();	
	private TextureParameter textureParameter = new TextureParameter();
	
	public static AssetsLoader getInstance() {
		if(loader == null)
			loader = new AssetsLoader();
		return loader;
	}
	
	public Texture getTexture(String name){
		return manager.get(name + ".png", Texture.class);
	}
						
	public Sound getSound(String name){
		String fullName = "Sounds/"+name+".mp3";
		if (manager.isLoaded(fullName)){
			return manager.get(fullName, Sound.class);
		}
		return null;
	}
	
	
	ParticleEffect getParticle(String name){			
		String fullName = "ParticleEffects/"+name+".p";
		if (manager.isLoaded(fullName)){
			return manager.get(fullName, ParticleEffect.class);
		}
		return null;				
	}		
	
	public Music getMusic(String name){
		String fullName = "Musics/"+name+".mp3";
		if (manager.isLoaded(fullName)){
			return manager.get(fullName, Music.class);
		}
		return null;
	}	

	public void finishLoading() {
		manager.finishLoading();
		update();
	}
	
	public float getProgress() {
		return manager.getProgress();
	}
	
	public boolean update() {
		if (manager.update()) {						
			return true;
		}
		
		return false;
	}
				
	public void loadAssets() {
		
		manager.load("background.png", Texture.class, textureParameter);
		manager.load("TestBuilding1.png", Texture.class, textureParameter);
		manager.load("dipl_menu.png", Texture.class, textureParameter);
		manager.load("ashtar_Button_128.png", Texture.class, textureParameter);
							
		loadSounds();
		loadMusics();
		loadParticleEffects();
						
		manager.load("fonts/foncik.fnt", BitmapFont.class);
	}
	
	private void loadSounds(){
		try {
			Element xmlRoot = new XmlReader().parse(Gdx.files.internal("Sounds/soundsList.xml"));
			Element sound;			 
			for (int i = 0; i < xmlRoot.getChildCount(); i++) {
				sound =  xmlRoot.getChild(i);
				manager.load("Sounds/" + sound.getAttribute("name") + ".mp3", Sound.class);			
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
	}
	
	private void loadMusics(){
		try {
			Element xmlRoot = new XmlReader().parse(Gdx.files.internal("Musics/musicsList.xml"));
			Element music;			 
			for (int i = 0; i < xmlRoot.getChildCount(); i++) {
				music =  xmlRoot.getChild(i);
				manager.load("Musics/" + music.getAttribute("name") + ".mp3", Music.class);			
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	private void loadParticleEffects(){
		ParticleEffectParameter particleParameters = new ParticleEffectParameter();
		particleParameters.atlasFile = "ParticleEffectsPack/particleTexture.atlas";
		
		try {
			Element xmlRoot = new XmlReader().parse(Gdx.files.internal("ParticleEffects/effectsList.xml"));
			Element effect;			 
			for (int i = 0; i < xmlRoot.getChildCount(); i++) {
				effect =  xmlRoot.getChild(i);
				manager.load("ParticleEffects/" + effect.getAttribute("name") + ".p", ParticleEffect.class, particleParameters);				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}					
	}
	
	public BitmapFont getFont(){
		return manager.get("fonts/foncik.fnt");
	}
	
	public void createResourcesAfterLoad(){												
		getFont().getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);			
	}
	
	private AssetsLoader() {
		textureParameter.magFilter = Texture.TextureFilter.Linear;
		textureParameter.minFilter = Texture.TextureFilter.Linear;
	}
}