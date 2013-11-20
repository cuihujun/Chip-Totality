package com.res;

import com.badlogic.gdx.audio.Music;
import com.res.Loader.AssetsLoader;

public class Musics {
	
	static private float volume = 0.5f;
	static Music lastMusic;
	
	public static float getVolume() {
		return volume;
	}

	static public void loop(String name) {
		Music music = AssetsLoader.getMusic(name);
		if (music != null) {	
			music.setVolume(volume);
			music.setLooping(true);
			music.play();
			lastMusic = music;
		}
	}		
	
	static public void play(String name) {
		Music music = AssetsLoader.getMusic(name);
		if (music != null) {
			music.setVolume(volume);			
			music.setLooping(false);
			music.play();
			lastMusic = music;
		}
	}	
	
	static public void setVolume(float volume) {
		Musics.volume = volume;
	}	
	
	static public void stop() {
		lastMusic.stop();
	}

}
