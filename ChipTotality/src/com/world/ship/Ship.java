package com.world.ship;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
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
		shoot,
		escape;
	}
	
	private int shots;
	protected float lastShoot;
	public int hitpoints;
	private final Sprite sprite;
	private final Sprite healthBarSprite;
	
	protected Building currentTarget;
	Stats.Bullets bulletType;
	Stats.Ships shipType;
	CurrentAction currentAction;

	Vector2 direction = new Vector2(0,0);
	Vector2 targetCoords = new Vector2(0,0);
	
	
	public abstract void shoot();
	Ship(int x, int y, Stats.Ships shipType){
		this.shipType=shipType;
		setBounds(x, y, getStats().width, getStats().height);
		setOrigin(getWidth()/2, getHeight()/2);
		this.hitpoints=getStats().maxHitpoints;		
		sprite = new Sprite(AssetsLoader.getTexture(this.getClass().getSimpleName()));	
		
		healthBarSprite = new Sprite(AssetsLoader.getTexture("healthbar"));
		healthBarSprite.setOrigin(getWidth()/2, getHeight()/2);
		
		bulletType=Stats.Bullets.simpleBullet;
		currentAction = CurrentAction.search;
	
		shots = new Integer(5);
	}
	
	
	
	@Override
	public void act(float delta){
		super.act(delta);
		
		lastShoot+=delta;
		
		
		if(getX()<-1000 || getY()<-1000 || getX() > 2000 || getY() > 2000)
			destroy();
		
		
		/*if(shots <= 0)
		{
			currentAction=CurrentAction.escape;
		}
		
		
		
		if(currentAction == CurrentAction.escape)
		{
			escape();
		}
		else */ //TODO chwilowo wylaczam ucieckze
		if(currentAction==CurrentAction.shoot && lastShoot>getStats().shootDelay){
			if(targetInRange()){
				shoot();
				--shots;
				lastShoot=0;
			}
			else{
				currentAction=CurrentAction.search;
			}
		}
		else if(currentAction==CurrentAction.approach){
			approach(getStats().range, delta);
		}
		else if(currentAction==CurrentAction.search)
			searchClosestTarget();
		
	}
	
	public void escape() {
		Vector2 direction = new Vector2(800-getX(), 500-getY());
		direction.nor();
		super.setRotation(direction.angle()+90);		
		setX(getX()+direction.x*(-getStats().speed));
		setY(getY()+direction.y*(-getStats().speed));	
	}
		
	
	
	
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
				currentAction=CurrentAction.approach;	//just approach to correct angle
				return;
			}	
			
		}
		setCurrentAction(Ship.CurrentAction.approach);
	}
	
	
	//approach building which was previously searched
	public void approach(float distance, float delta){	//ships stops when the distance is equal or shorter to passed argument
		//required when there is no buildings in the game
		if(currentTarget==null){
			currentAction=CurrentAction.search;
			return;
		}
		//angle beetween ship and target
		float angle = MathUtils.atan2(direction.y = (currentTarget.getY()-getY()), direction.x=(currentTarget.getX()-getX()))*MathUtils.radiansToDegrees;
		float deltaAngle = Math.abs(angle-getRotation());
		//angle too big to rotate instantly - rotate a part of the angle, and wait.
		if(deltaAngle>getStats().rotationSpeed/2){	//TODO nie wiem czy polowa obrotu na sekudne to dobra liczba
			if(angle>getRotation())
				rotate(getStats().rotationSpeed*delta);
			else rotate(-getStats().rotationSpeed*delta);
			return;
		}
		
		setRotation(angle); 
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
					
		setX(getX()+direction.x*getStats().speed*delta);
		setY(getY()+direction.y*getStats().speed*delta);		

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
		
		
		
		float healthPercentage = ((float)hitpoints)/getStats().maxHitpoints;		
		healthBarSprite.setScale(healthPercentage, 1);
		if(healthPercentage>=0.75)
			healthBarSprite.setColor(Color.GREEN);
		else if(healthPercentage>=0.5 && healthPercentage<0.75){
			healthBarSprite.setColor(Color.YELLOW);
			System.out.println("ddd");
		}
		else if(healthPercentage>=0.25 && healthPercentage < 0.5)
			healthBarSprite.setColor(Color.YELLOW);
		else healthBarSprite.setColor(Color.RED);
		healthBarSprite.setPosition(getMidX(), getMidY()+getHeight()/2.5f);
		healthBarSprite.draw(batch);
		
	}
	
	public void destroy(){
		this.getStage().addActor(EffectsManager.getActor(EffectTypes.explosionMed, getX(), getMidY()));//TODO zaleznie od typu inny wybuch?;p wysordkowanie wynuchu?
		remove();
	}
	
	public Stats.Ships getStats(){
		return shipType;
	}
	
	public void setCurrentAction(CurrentAction action){
		this.currentAction=action;
	}
	
	public void setCurrentTarget(Building target){
		currentTarget=target;
	}
	
	float getMidY() {
		return getY()+sprite.getHeight()/2;
	}
	float getMidX(){
		return getX()+sprite.getWidth()/2;
	}
	
}