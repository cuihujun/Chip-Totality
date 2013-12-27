package com.particles;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.res.Loader.AssetsLoader;

public class EffecteManagerHolder {
	
	public enum EffectTypes{
		explosion(200,1000),
		explosionMed(100,500);
		
		int initialCapacity;
		int maxCapacity;
		
		EffectTypes(int init, int max){
			initialCapacity = init;
			maxCapacity = max;
		}	
	}
	
	public static class EffectsManager{
				
		private static HashMap<EffectTypes, ParticleEffectPool> particlePools;				
				
		public static void init(){
			
			particlePools = new HashMap<EffectTypes, ParticleEffectPool>();
			for(EffectTypes effectType : EffectTypes.values()){		
				ParticleEffectPool pool  = new ParticleEffectPool(AssetsLoader.getParticle(effectType.name()), effectType.initialCapacity , effectType.maxCapacity);
				particlePools.put(effectType, pool);							
			}
		}
		
		public static ParticleEffect getEffect(EffectTypes type){
			return particlePools.get(type).obtain();
		}
		
		public static void freeEffect(EffectTypes type, PooledEffect effect){
			particlePools.get(type).free(effect);
		}
		
		public static ParticleEffectActor getActor(EffectTypes type, float x, float y){
			//TODO Pool this too;]
			return new ParticleEffectActor(type, getEffect(type),x,y);
		}
	}
}
