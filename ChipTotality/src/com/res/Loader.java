package com.res;

import java.io.IOException;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.ParticleEffectLoader.ParticleEffectParameter;
import com.badlogic.gdx.assets.loaders.TextureLoader.TextureParameter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.main.Settings;

public class Loader {
			
	public static class AssetsLoader {
		
		public static AssetManager manager=new AssetManager();
		public static TextureParameter textureParameter= new TextureParameter();		
		private static HashMap<String, Sprite> sprites;
		

		public static TextureRegion getBuildingIcon(String name){			
			TextureAtlas atlas = manager.get("IconsPack/iconsPack.atlas", TextureAtlas.class);			
			return atlas.findRegion(name);
		}		
		
		public static Sprite getSprite(String name){
			return sprites.get(name);
		}
		
		public static Texture getTexture(String name){
			return manager.get(name + ".png", Texture.class);
		}
		
		public static TiledMap getTileMap(){
			return manager.get("MapData/test_ortho2.tmx");
		}	
							
		public static Sound getSound(String name){
			String fullName = "Sounds/"+name+".mp3";
			if (manager.isLoaded(fullName)){
				return manager.get(fullName, Sound.class);
			}
			return null;
		}
			
		public static Skin getSkin(){
			return manager.get("uiSkin/uiskin.json", Skin.class);		
		}	
		
		static ParticleEffect getParticle(String name){			
			String fullName = "ParticleEffects/"+name+".p";
			if (manager.isLoaded(fullName)){
				return manager.get(fullName, ParticleEffect.class);
			}
			return null;				
		}		
		
		public static Music getMusic(String name){
			String fullName = "Musics/"+name+".mp3";
			if (manager.isLoaded(fullName)){
				return manager.get(fullName, Music.class);
			}
			return null;
		}	

		public static void finishLoading() {
			manager.finishLoading();
			update();
		}
		
		public static float getProgress() {
			return manager.getProgress();
		}
		
		public static boolean update() {
			return manager.update();					
		}
					
		public static void loadAssets() {
			
			manager.load("background.png", Texture.class, textureParameter);			
			manager.load("dipl_menu.png", Texture.class, textureParameter);
			manager.load("ashtar_button_128.png", Texture.class, textureParameter);
			manager.load("tabBackground.png", Texture.class, textureParameter);
			
			
			manager.load("Meteorite.png", Texture.class, textureParameter);
			
	
			
			//buldings
			manager.load("AcodinMine.png", Texture.class, textureParameter);
			manager.load("Base.png", Texture.class, textureParameter);
			manager.load("HolyMountains.png", Texture.class, textureParameter);
			manager.load("Rafinery.png", Texture.class, textureParameter);
			manager.load("Temple.png", Texture.class, textureParameter);
			
			//icons
			manager.load("IconsPack/iconsPack.atlas", TextureAtlas.class);			
			
			//skin do menu
			manager.load("uiSkin/uiskin.json",Skin.class);
					
			//loading mapy
			/*TmxMapLoader.Parameters para = new TmxMapLoader.Parameters();
			para.generateMipMaps = true;		
			para.textureMagFilter = Texture.TextureFilter.Nearest;
			para.textureMinFilter = Texture.TextureFilter.Nearest;
			TmxMapLoader tmxLoader = new TmxMapLoader(new InternalFileHandleResolver());	
			manager.setLoader(TiledMap.class, tmxLoader);
			manager.load("MapData/test_ortho2.tmx", TiledMap.class, para);
			manager.load("MapData/tree2-final.png", Texture.class, textureParameter);*/
								
			loadSounds();
			loadMusics();
			loadParticleEffects();
							
			manager.load("fonts/foncik.fnt", BitmapFont.class);
		}
		
		private static void loadSounds(){
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
		
		private static void loadMusics(){
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
		
		private static void loadParticleEffects(){
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
		
		public static BitmapFont getFont(){
			return manager.get("fonts/foncik.fnt");
		}
		
		public static void createResourcesAfterLoad(){
			getFont().getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
			
			recreateAfterResize();	
		}
		
		public static void recreateAfterResize(){
			Texture backTab = manager.get("tabBackground.png", Texture.class);			
			sprites = new HashMap<String, Sprite>();
			
			Sprite infoPanel = new Sprite(backTab);
			infoPanel.setSize(Settings.VIEW_WIDTH, Settings.HEIGHT*0.05f);
			infoPanel.setPosition(-200, -300);
			sprites.put("infoPanel", infoPanel);			
			
			Sprite actionPanel = new Sprite(backTab);
			actionPanel.setSize(Settings.VIEW_WIDTH, Settings.HEIGHT*0.12f);
			actionPanel.setPosition(-200, -200);			
			sprites.put("actionPanel", actionPanel);
			
		}
		
		private AssetsLoader() {
			textureParameter.magFilter = Texture.TextureFilter.Linear;
			textureParameter.minFilter = Texture.TextureFilter.Linear;				
		}

	}
	
	
}
