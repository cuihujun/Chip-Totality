package com.gameInfo;

<<<<<<< HEAD
=======
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

>>>>>>> 5d92dc957f51a7f999b4229a43f19e3eb417e2e1
import com.badlogic.gdx.graphics.Texture;
import com.res.Loader.AssetsLoader;
import com.world.building.AcodinMine;
import com.world.building.Base;
import com.world.building.Building;
import com.world.building.HolyMountains;
import com.world.building.Rafinery;
import com.world.building.Temple;
<<<<<<< HEAD

=======
>>>>>>> 5d92dc957f51a7f999b4229a43f19e3eb417e2e1

/**
 * @author arap This class contains variables regarding current game state.
 * @param buildingMode
 *            flag useful to check if player wants to build something
 * @param chosenBuilding
 *            building chosen by player. Includes none
 * 
 */

<<<<<<< HEAD
public final class GameStateHolder 
{	
	//resources
	public static int dirtyAcodin=0;
	public static int acodin=0;
	public static int beings=0;
	
	
	//current gamemode
	public enum Mode {NONE, BUILDING, DIPLOMACY};
	
	
	//Building mode
	public static Mode mode = Mode.NONE;
	public static ChosenBuilding chosenBuilding = ChosenBuilding.none;

	
	
	public enum ChosenBuilding {
		none,
		AcodinMine,
		Base,
		HolyMountains,
		Rafinery,
		Temple;
		
		public Building getBuilding(int x, int y){
			switch(this){
			case AcodinMine:
				return new AcodinMine(x, y);
			case Base:
				return new Base(x,y);
			case HolyMountains:
				return new HolyMountains(x, y);
			case Rafinery:
				return new Rafinery(x, y);
			case Temple:
				return new Temple(x, y);
			default:
				return null;
			}		
		}
		public Texture getTexture(){
			switch(this){
			case AcodinMine:				
				return AssetsLoader.getTexture("AcodinMine");				
			case Base:
				return AssetsLoader.getTexture("TestBuilding1");
			case HolyMountains:
				return AssetsLoader.getTexture("HolyMountains");				
			case Rafinery:
				return AssetsLoader.getTexture("Rafinery");				
			case Temple:
				return AssetsLoader.getTexture("Temple");
			default:
				return null;
			}
		}
	};
	
	
=======
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
>>>>>>> 5d92dc957f51a7f999b4229a43f19e3eb417e2e1

	public enum ChosenBuilding {
		none(null), 
		AcodinMine(new AcodinMine()),
		Base(new Base()),
		HolyMountains(new HolyMountains()), 
		Rafinery(new Rafinery()),
		Temple(new Temple());

		Building building;

		ChosenBuilding(Building building) {
			this.building = building;
		}

		public int getBuildingWidth() {
			return (int) building.size.x;
		}

		public int getBuildingHeight() {
			return (int) building.size.y;
		}
		
		public Texture getTexture() {
			return AssetsLoader.getTexture(this.toString());
		}
		
		public Building getChosenBuilding(int x, int y){
			
			
			Class newBuildingClass = null;
			try {
				newBuildingClass = Class.forName("com.world.building."+GameStateHolder.chosenBuilding.toString());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
					
			
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
		}
		

	}
	
	
	
}
