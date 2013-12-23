package com.gameInfo;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.res.Loader.AssetsLoader;
import com.world.building.AcodinMine;
import com.world.building.Base;
import com.world.building.Building;
import com.world.building.HolyMountains;
import com.world.building.Rafinery;
import com.world.building.Temple;
import com.world.tower.VioletGun;
import com.world.tower.YellowGun;


/**
 * @author arap This class contains variables regarding current game state.
 * @param buildingMode
 *            flag useful to check if player wants to build something
 * @param chosenBuilding
 *            building chosen by player. Includes none
 */

public final class GameStateHolder {
	// resources
	public static int dirtyAcodin = 0;
	public static int acodin = 0;
	public static int beings = 0;

	// current gamemode
	public enum Mode {
		NONE, BUILDING, DIPLOMACY
	};

	// Building mode
	public static Mode mode = Mode.NONE;
	public static ChosenBuilding chosenBuilding = ChosenBuilding.none;

	public enum ChosenBuilding {
		none(), 
		AcodinMine(),
		Base(),
		HolyMountains(), 
		Rafinery(),
		Temple(),
		YellowGun(),
		VioletGun();

		Building buildingSample;
		TextureRegion region;

		ChosenBuilding() {
			//this.buildingSample = building;
			region = AssetsLoader.getBuilding(this.toString());
		}
		
		public int getBuildingWidth(){
			return Stats.Buildings.valueOf(this.name()).width;
		}
		public int getBuildingHeight(){
			return Stats.Buildings.valueOf(this.name()).height;
		}
		
		public TextureRegion getTextureRegion() {
			return region;			
		}
		
		public Building getChosenBuilding(int x, int y){
			
			switch(this){
			case AcodinMine:
				return new AcodinMine(x, y);
			case Base: 
				return new Base(x, y);
			case HolyMountains:
				return new HolyMountains(x, y);
			case Rafinery:
				return new Rafinery(x, y);
			case Temple:
				return new Temple(x, y);
			case YellowGun:
				return new YellowGun(x, y);
			case VioletGun:
				return new VioletGun(x, y);
			default:
				return null;
			}
			
			
			/*Class newBuildingClass = null;
			newBuildingClass = buildingSample.getClass();
					
			
			Constructor con = null;
			try {
				con = newBuildingClass.getConstructor(int.class, int.class);
			} catch (NoSuchMethodException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			}
			
			
			
			Object newBuildingObject=null;
			try {
				newBuildingObject = con.newInstance(x, y);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			return (Building)newBuildingObject;
			*/
		}
		

	}
	
	
	
}
