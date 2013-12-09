package com.screen;


import java.util.ArrayList;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.main.ChipTotality;
import com.main.Settings;
import com.world.ShipManager;
import com.world.building.Building;
import com.world.ship.Ship;
import com.world.ship.TestShip1;
import com.world.tower.Bullet;
import com.world.tower.TestBullet1;
//class for ships, bullets, and buildings

public class GameStage extends Stage{
	final ChipTotality game;

	
	public static Vector<Ship> ships;

	QuadTree<Actor> quadTree; 
	QuadTree<Bullet> bulletTreeTower, bulletTreeShip;	
	ArrayList<Bullet> listOfBulletTower, listOfBulletShip;	// Bullets from towers, bullets from ships

	
	ShipManager shipMgr;
	public GameStage(ChipTotality game) {
		super(Settings.WIDTH, Settings.HEIGHT, true, game.batch);
		this.game=game;
		quadTree = new QuadTree<Actor>(0, new Rectangle(0,0, Settings.tilesHorizontal*Settings.tileSize, Settings.tilesVertical*Settings.tileSize));
		bulletTreeTower = new QuadTree<Bullet>(0, new Rectangle(0,0, Settings.tilesHorizontal*Settings.tileSize, Settings.tilesVertical*Settings.tileSize));
		bulletTreeShip = new QuadTree<Bullet>(0, new Rectangle(0,0, Settings.tilesHorizontal*Settings.tileSize, Settings.tilesVertical*Settings.tileSize));
		shipMgr = new ShipManager(this);
		
		//TODO usunac po testach
		TestShip1 ts1 = new TestShip1(300, 300);
		addActor(ts1);
		ts1.action.setDuration(10);
		ts1.action.setInterpolation(Interpolation.exp10);
		ts1.action.setPosition(1500, 1400);	
		ts1.addAction(ts1.action);	
		
		TestShip1 ts2 = new TestShip1(590, 300);
		addActor(ts2);
		TestShip1 ts3 = new TestShip1(1500, 1400);
		addActor(ts3);
		
		TestBullet1 tb1 = new TestBullet1(800, 330, ts1);	
		addActor(tb1);
		
		///////////////////
		setCamera(game.gameScreen.camera);
		
	}
	
	
	public void checkCollisions(){
		quadTree.clear();
		quadTree.insert(getActors());
		ArrayList<Actor> returnObjects = new ArrayList<Actor>();
		for (Actor currentActor : game.gameStage.getActors()) {
			returnObjects.clear();
			returnObjects=game.gameStage.quadTree.retrieve(currentActor);
			for (Actor possiblyColliding : returnObjects) {
				Rectangle currBounds = new Rectangle(currentActor.getX(), currentActor.getY(), currentActor.getWidth(), currentActor.getHeight());
				Rectangle possBounds = new Rectangle(possiblyColliding.getX(), possiblyColliding.getY(), possiblyColliding.getWidth(), possiblyColliding.getHeight());
				if(currBounds.overlaps(possBounds))
					Gdx.app.log("collision", "collision beetween"+currentActor.getClass().getSimpleName()+" and "+ possiblyColliding.getClass().getSimpleName());					
			}
		}
	}
	
	public void checkBulletCollisions(Vector<Building> buildings){
		for(Building building : buildings)
		{
			ArrayList<Bullet> bulls = bulletTreeShip.retrieve(building);
			for(Bullet b : bulls)
			{
				if((new Rectangle(b.getX(), b.getY(), b.getWidth(), b.getHeight())).overlaps(new Rectangle(building.getX(),  building.getY(),  building.getWidth(),  building.getHeight())))  
				{
					
					//	building.health-=b.damage;
					//TODO Jakas animacja wybuchu
					bulls.remove(b);
					b.remove();
				}
			}
		}
	}
	public void checkBulletCollisionsShips(Vector<Ship> ships) {
		for(Ship ship : ships)	{
			ArrayList<Bullet> bulls = bulletTreeTower.retrieve(ship);
			for(Bullet b : bulls)
			{
				if((new Rectangle(b.getX(), b.getY(), b.getWidth(), b.getHeight())).overlaps(new Rectangle(ship.getX(),  ship.getY(),  ship.getWidth(),  ship.getHeight())))  
				{
					
					//	ship.health-=b.damage;
					//TODO Jakas animacja wybuchu
					bulls.remove(b);
					b.remove();
				}
			}
		}
	}
	public void Update()
	{
		bulletTreeTower.clear();
		bulletTreeShip.clear();
	
		for(Bullet b : listOfBulletTower) {
			bulletTreeTower.insert(b);
		}
			
		for(Bullet b : listOfBulletShip) {
			bulletTreeShip.insert(b);
		}
		final int licz = 0; // Liczni
		
		
	}
	public void AddBullet(Bullet  bull, boolean fromShip) {
		if(fromShip)
		{
			listOfBulletShip.add(bull);
		}
		else
		{
			listOfBulletTower.add(bull);
		}
		addActor(bull);
	}
	

	public Building getRandBuilding() {
		for(int x = 0; x < Settings.tilesVertical; x++)
			for(int y = 0; y < Settings.tilesHorizontal; y++)
				if(game.asteroid.worldGrid[x][y].building != null)
				{
					return game.asteroid.worldGrid[x][y].building;
				}
		return null;			
	}
	public Vector<Ship> getShip()
	{
		return ships;
	}

}
