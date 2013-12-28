package com.particles;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.particles.EffecteManagerHolder.EffectTypes;
import com.particles.EffecteManagerHolder.EffectsManager;

public class ParticleEffectActor extends Actor {
	ParticleEffect effect;
	EffectTypes effectType;

	public ParticleEffectActor(EffectTypes type, ParticleEffect effect, float x, float y) {
		this.effect = effect;
		this.effectType = type;
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
			EffectsManager.freeEffect(effectType, (PooledEffect)effect);
			//AssetsLoader.particlePoolExplosion.free((PooledEffect) effect);
			remove();
		}
	}

	public ParticleEffect getEffect() {
		return effect;
	}
}