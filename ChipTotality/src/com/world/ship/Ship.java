package com.world.ship;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.gameInfo.Stats;
import com.particles.EffectsManagerHolder.EffectTypes;
import com.particles.EffectsManagerHolder.EffectsManager;
import com.res.Loader.AssetsLoader;
import com.screen.GameStage;
import com.world.building.Building;

public abstract class Ship extends Actor{
	public enum CurrentAction{
		search,
		approach,
		shoot;
	}
	
	protected float lastShoot;
	public int hitpoints;
	private final Sprite sprite;
	protected Building currentTarget;
	Stats.Bullets bulletType;
	Stats.Ships shipType;
	CurrentAction currentAction;
	Vector2 direction = new Vector2(0,0);
	Vector2 targetCoords = new Vector2(0,0);
	
	Ship(int x, int y, Stats.Ships shipType){
		this.shipType = shipType;
		setBounds(x, y, getStats().width, getStats().height);
		this.hitpoints=getStats().maxHitpoints;		
		sprite = new Sprite(AssetsLoader.getTexture(this.getClass().getSimpleName()));	
		bulletType=Stats.Bullets.simpleBullet;
		currentAction = CurrentAction.search;
	}
	
	@Override
	public void act(float delta){
		super.act(delta);
		
		lastShoot+=delta;
		if(currentAction==CurrentAction.shoot && lastShoot>getStats().shootDelay){
			if(targetInRange()){
				shoot();
				lastShoot=0;
			}
			else{
				currentAction=CurrentAction.search;
			}
		}
		else if(currentAction==CurrentAction.approach){
			approach(getStats().range);
		}
		else if(currentAction==CurrentAction.search)
			searchClosestTarget();
		
	}
	
		
	public abstract void shoot();

	//looks for building to attack
	public void searchClosestTarget(){
		float shortestDistance=Float.POSITIVE_INFINITY;
		Vector2 shipsCoords = new Vector2(getX(), getY());
		
		for (Actor building : GameStage.buildingsGroup.getChildren()) {
			float distance = shipsCoords.dst(building.getX(), building.getY());
			if(distance < shortestDistance){
				shortestDistance=distance;
				currentTarget=(Building)building;			
			}
			//target in range - there's no need to look for another one
			if(shortestDistance<getStats().range){
				currentAction=CurrentAction.shoot;
				return;
			}
			
		}

		setCurrentAction(Ship.CurrentAction.approach);
	}
	
	
	//approach building which was previously searched
	public void approach(float distance){
		//required when there is no buildings in the game
		if(currentTarget==null){
			currentAction=CurrentAction.search;
			return;
		}
		
		direction.x = currentTarget.getX()-getX();
		direction.y =  currentTarget.getY()-getY();
		direction.nor();		
		targetCoords.x = currentTarget.getX();
		targetCoords.y =  currentTarget.getY();
		
		if(targetCoords.dst2(getX(), getY())<(distance*distance)){
			setCurrentAction(Ship.CurrentAction.shoot);
			return;
		}
		
		//building was destroyed
		if(currentTarget.hasParent()!=true){
			setCurrentAction(Ship.CurrentAction.search);
			return;
		}
					
		setX(getX()+direction.x*getStats().speed);
		setY(getY()+direction.y*getStats().speed);	
	}
	
	private boolean targetInRange() {
		Vector2 coordsFloat = new Vector2(getX(), getY());
		//target ship has no parent - he formally doesn't exist in the world
		return (currentTarget!=null &&currentTarget.hasParent() && coordsFloat.dst(currentTarget.getX(), currentTarget.getY()) <=getStats().range);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {		
		sprite.setPosition(getX(),getY());
		sprite.setRotation(getRotation());
		sprite.draw(batch);
	}
	
	public void destroy(){
		this.getStage().addActor(EffectsManager.getActor(EffectTypes.explosionMed, getX(), getY()));//TODO zaleznie od typu inny wybuch?;p wysordkowanie wynuchu?
		remove();
	}
	
	public Stats.Ships getStats(){
		//TODO String alocation aaaaaa in every action! very slow;]
		//return Stats.Ships.valueOf(this.getClass().getSimpleName());
		return shipType;
	}
	
	public void setCurrentAction(CurrentAction action){
		this.currentAction=action;
	}
	public void setCurrentTarget(Building target){
		currentTarget=target;
	}
	
}