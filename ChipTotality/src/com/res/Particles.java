package com.res;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;

public class Particles {

	static public ParticleEffect get(String name) {
		return AssetsLoader.getInstance().getParticle(name);
	}
	
}