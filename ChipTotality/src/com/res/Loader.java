package com.res;

import java.io.IOException;
//import net.dermetfan.utils.libgdx.graphics.AnimatedSprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.ParticleEffectLoader.ParticleEffectParameter;
import com.badlogic.gdx.assets.loaders.TextureLoader.TextureParameter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.particles.EffectsManagerHolder.EffectsManager;

public class Loader {
			
	public static class AssetsLoader {
		
		public static AssetManager manager=new AssetManager();
		public static TextureParameter textureParameter= new TextureParameter();
		private static ArrayMap<String, Animation> objectsAnimations = new ArrayMap<String, Animation>();
		//public static AnimatedSprite animatedSprite;
		public static Sprite backgroundSprite;
		

		public static TextureRegion getBuildingIcon(String name){			
			TextureAtlas atlas = manager.get("IconsPack/iconsPack.atlas", TextureAtlas.class);			
			return atlas.findRegion(name);
		}
		
		public static TextureRegion getBuilding(String name){			
			TextureAtlas atlas = manager.get("BuildingsPack/buildingsPack.atlas", TextureAtlas.class);			
			return atlas.findRegion(name);
		}		
		
		public static Animation getObjectAnimation(String name){
			return objectsAnimations.get(name);
		}					
		
		public static Texture getTexture(String name){								
			return manager.get(name + ".png", Texture.class);
		}
		
		public static Texture getTextureJPG(String name){								
			return manager.get(name + ".jpg", Texture.class);
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
		
		public static ParticleEffect getParticle(String name){			
			String fullName = "ParticleEffects/"+name+".p";
			if (manager.isLoaded(fullName)){
				ParticleEffect effectNew = manager.get(fullName, ParticleEffect.class);				
				return effectNew;
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
			
			//manager.load("background.png", Texture.class, textureParameter);
			//manager.load("starsSeamless.png", Texture.class, textureParameter);
							  
			//manager.load("starSpaceTile.png", Texture.class, textureParameter);
			//manager.load("blackDreams.png", Texture.class, textureParameter);
			manager.load("dipl_menu.png", Texture.class, textureParameter);
			manager.load("strangeSpace.jpg", Texture.class, textureParameter);		
			
			
			manager.load("Meteorite.png", Texture.class, textureParameter);

			//bullets
			manager.load("TestBullet1.png", Texture.class, textureParameter);
			manager.load("TestRocket1.png", Texture.class, textureParameter);
			//ships
			manager.load("TestShip1.png", Texture.class, textureParameter);
			//buldings
			manager.load("BuildingsPack/buildingsPack.atlas", TextureAtlas.class);
			
			//explosions
			manager.load("ExplosionsPack/explosionsPack.atlas", TextureAtlas.class);
	
			//towers
			manager.load("TestTower1.png", Texture.class, textureParameter);
			
			//icons
			manager.load("IconsPack/iconsPack.atlas", TextureAtlas.class);		
			
			//skin do menu
			manager.load("uiSkin/uiskin.json",Skin.class);
													
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
			
			backgroundSprite = new Sprite(getTextureJPG("strangeSpace"));
			EffectsManager.init();
			recreateAfterResize(1280,720);	
		}
		
		public static void recreateAfterResize(float w, float h){
			backgroundSprite.setSize(w, w*backgroundSprite.getRegionHeight()/backgroundSprite.getRegionWidth());
			backgroundSprite.setPosition(-backgroundSprite.getWidth()/2, -backgroundSprite.getHeight()/2);
			
			//niekoniecznie tu w sumie wystarczy pewnie raz;]
			if (manager.isLoaded("ExplosionsPack/explosionsPack.atlas")){
				TextureAtlas locationObjectsAnimetedAtals = manager.get("ExplosionsPack/explosionsPack.atlas", TextureAtlas.class);
				
				Array<AtlasRegion> foundRegions;
				Array<AtlasRegion> regions = locationObjectsAnimetedAtals.getRegions();
				float frameTime = 0;
				String objName = "explosionTest"; 
					
				foundRegions = new Array<AtlasRegion>();																		
				for (int i = 0, n = regions.size; i < n; i++) {
					AtlasRegion region = regions.get(i);
					if (region.name.contains(objName)) foundRegions.add(new AtlasRegion(region));
				}															
															
				if (foundRegions.size > 0){
					frameTime = 1.0f/foundRegions.size;
					objectsAnimations.put(objName, new Animation(frameTime, foundRegions,Animation.LOOP));//TODO do zmainy z loop na jednorazowe i po usuniecie 
				}
			}				
			
			//animatedSprite = new AnimatedSprite(objectsAnimations.get("explosionTest"));
			
			
		}
		
		private AssetsLoader() {
			//textureParameter.magFilter = Texture.TextureFilter.MipMapNearestNearest;
			//textureParameter.minFilter = Texture.TextureFilter.MipMapNearestNearest;
			Texture.setAssetManager(manager);
			textureParameter.genMipMaps = true;
			textureParameter.magFilter = Texture.TextureFilter.MipMapNearestNearest;
			textureParameter.magFilter = Texture.TextureFilter.MipMapNearestNearest;			
		}

	}
	
	
}
