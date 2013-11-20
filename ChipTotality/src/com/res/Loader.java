package com.res;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.ParticleEffectLoader.ParticleEffectParameter;
import com.badlogic.gdx.assets.loaders.TextureLoader.TextureParameter;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

public class Loader {
	
	
	
	public static class AssetsLoader {
		public static AssetManager manager=new AssetManager();
		public static TextureParameter textureParameter= new TextureParameter();
		
		
		private AssetsLoader(){
			AssetsLoader.textureParameter.magFilter = Texture.TextureFilter.Linear;
			AssetsLoader.textureParameter.minFilter = Texture.TextureFilter.Linear;
		}
		
		public static Texture getTexture(String name) {
			return manager.get(name + ".png", Texture.class);
		}

		
		public static TiledMap getTileMap() {
			return manager.get("MapData/testmap2.tmx");
		}

		public static Sound getSound(String name) {
			String fullName = "Sounds/" + name + ".mp3";
			if (manager.isLoaded(fullName)) {
				return manager.get(fullName, Sound.class);
			}
			return null;
		}

		static ParticleEffect getParticle(String name) {
			String fullName = "ParticleEffects/" + name + ".p";
			if (manager.isLoaded(fullName)) {
				return manager.get(fullName, ParticleEffect.class);
			}
			return null;
		}

		public static Music getMusic(String name) {
			String fullName = "Musics/" + name + ".mp3";
			if (manager.isLoaded(fullName)) {
				return manager.get(fullName, Music.class);
			}
			return null;
		}

		public static void finishLoading() {
			manager.finishLoading();
			update();
		}

		public float getProgress() {
			return manager.getProgress();
		}

		public static boolean update() {
			if (manager.update()) {
				return true;
			}

			return false;
		}

		public static void loadAssets() {

			manager.load("background.png", Texture.class, textureParameter);
			manager.load("TestBuilding1.png", Texture.class, textureParameter);
			manager.load("dipl_menu.png", Texture.class, textureParameter);
			manager.load("ashtar_Button_128.png", Texture.class,
					textureParameter);

			manager.setLoader(TiledMap.class, new TmxMapLoader(
					new InternalFileHandleResolver()));
			manager.load("MapData/testmap2.tmx", TiledMap.class);

			loadSounds();
			loadMusics();
			loadParticleEffects();

			manager.load("fonts/foncik.fnt", BitmapFont.class);
		}

		private static void loadSounds() {
			try {
				Element xmlRoot = new XmlReader().parse(Gdx.files
						.internal("Sounds/soundsList.xml"));
				Element sound;
				for (int i = 0; i < xmlRoot.getChildCount(); i++) {
					sound = xmlRoot.getChild(i);
					manager.load("Sounds/" + sound.getAttribute("name")
							+ ".mp3", Sound.class);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		private static void loadMusics() {
			try {
				Element xmlRoot = new XmlReader().parse(Gdx.files
						.internal("Musics/musicsList.xml"));
				Element music;
				for (int i = 0; i < xmlRoot.getChildCount(); i++) {
					music = xmlRoot.getChild(i);
					manager.load("Musics/" + music.getAttribute("name")
							+ ".mp3", Music.class);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private static void loadParticleEffects() {
			ParticleEffectParameter particleParameters = new ParticleEffectParameter();
			particleParameters.atlasFile = "ParticleEffectsPack/particleTexture.atlas";

			try {
				Element xmlRoot = new XmlReader().parse(Gdx.files
						.internal("ParticleEffects/effectsList.xml"));
				Element effect;
				for (int i = 0; i < xmlRoot.getChildCount(); i++) {
					effect = xmlRoot.getChild(i);
					manager.load(
							"ParticleEffects/" + effect.getAttribute("name")
									+ ".p", ParticleEffect.class,
							particleParameters);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public static BitmapFont getFont() {
			return manager.get("fonts/foncik.fnt");
		}

		public static void createResourcesAfterLoad() {
			getFont().getRegion().getTexture()
					.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}

	}
	
	
}
