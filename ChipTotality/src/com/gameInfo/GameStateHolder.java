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
	
	public static Mode mode = Mode.NONE;
	public static ChosenBuilding chosenBuilding = ChosenBuilding.none;
	
	public static boolean[] technologiesDiscovered = new boolean[Technology.values().length];
	public static boolean researching=false;
	public static float researchTimeElapsed=0;
	public static Technology toResearch = Technology.techName0;
	
}
