package com.particles;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.res.Loader.AssetsLoader;

public class ParticleEffectActor extends Actor {
	ParticleEffect effect;

	public ParticleEffectActor(ParticleEffect effect, float x, float y) {
		this.effect = effect;
		effect.setPosition(x, y);
		effect.allowCompletion();
		effect.start();
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		effect.draw(batch);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		effect.update(delta);		
		if (effect.isComplete()) {
			AssetsLoader.particlePoolExplosion.free((PooledEffect) effect);
			remove();
		}
	}

	public ParticleEffect getEffect() {
		return effect;
	}
}