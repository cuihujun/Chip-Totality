package com.res;

import com.badlogic.gdx.audio.Sound;

public class Sounds {
	static private float volume = 0.5f;

	static public void play(String name) {
		Sound sound = AssetsLoader.getInstance().getSound(name);
		if (sound != null) {
			sound.play(volume);			
		}
	}
	
	static public void loop(String name) {
		Sound sound = AssetsLoader.getInstance().getSound(name);
		if (sound != null) {			
			sound.loop(volume);
		}
	}	

	static public void setVolume(float volume) {
		Sounds.volume = volume;
	}

	static public float getVolume() {
		return Sounds.volume;
	}
	
}
