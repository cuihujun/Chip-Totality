package com.gameInfo;

/**
 * @author arap
 * This class contains variables regarding current game state.
 * @param buildingMode flag useful to check if player wants to build something
 * @param chosenBuilding building chosen by player. Includes none
 *
 */

public final class GameStateHolder 
{	
	public enum Mode {NONE, BUILDING, DIPLOMACY};
	
	//list of modes
	public static boolean buildingMode = false;
	public static boolean diplomacyMode = false;
	
	public static Mode mode = Mode.NONE;
	
	//build mode-specific variables
	public static ChosenBuilding chosenBuilding=ChosenBuilding.none;

	//diplomacy mode-specific variables

	
}
