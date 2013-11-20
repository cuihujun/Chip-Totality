package com.res;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.res.Loader.AssetsLoader;

public class Particles {

	static public ParticleEffect get(String name) {
		return AssetsLoader.getParticle(name);
	}
	
}