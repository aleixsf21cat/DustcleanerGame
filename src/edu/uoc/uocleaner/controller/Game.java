package edu.uoc.uocleaner.controller;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import edu.uoc.uocleaner.model.Level;
import edu.uoc.uocleaner.model.LevelException;
import edu.uoc.uocleaner.model.Corridor;
import edu.uoc.uocleaner.model.Dirt;
import edu.uoc.uocleaner.model.Dumpster;
import edu.uoc.uocleaner.model.DustBall;
import edu.uoc.uocleaner.model.Sprite;
import edu.uoc.uocleaner.model.SpriteException;
import edu.uoc.uocleaner.model.Symbol;
import edu.uoc.uocleaner.model.Vacuum;
import edu.uoc.uocleaner.model.VacuumException;
import edu.uoc.uocleaner.model.Wall;

public class Game {
	/**
	 * Number of the current level.
	 */
	private int numLevel = 0;
	/**
	 * Maximum quantity of levels that the game has.
	 */
	private final int MAX_LEVELS;
	/**
	 * Amount of time that must elapse in order to decrease the field "time" of the level.
	 */
	private final int INTERVAL_TIME = 50;
	/**
	 * Field that is used to check if the "INTERVAL_TIME" has been elapsed.
	 */
	private int elapsedTime = 0;
	/**
	 * Folder name where the configuration/level files are.
	 */
	private String fileFolder;
	/**
	 * Vacuum cleaner object (player). 
	 */
	private Vacuum huocver;
	/**
	 * Level object that contains the information of the current level.
	 */
	private Level level;
	/**
	 * Game total score.
	 */
	private int totalScore = 0;
	/**
	 * Current level score.
	 */
	private int levelScore = 0;
	/**
	 * Game mode.
	 */
	GameMode gameMode;
	
	/**
	 * Constructor
	 * @param fileFolder Folder name where the configuration/level files are.
	 * @param gameMode Indicates if the game is played based on turns or time. 
	 * @throws FileNotFoundException When there is a problem opening the configuration file.
	 * @throws LevelException When there is a level exception/problem.
	 * @throws VacuumException When there is an exception/problem with the vacuum cleaner.
	 * @throws SpriteException When there is a Sprite exception/problem.
	 */
	public Game(String fileFolder, GameMode gameMode) throws FileNotFoundException, LevelException, VacuumException, SpriteException {
		setFileFolder(fileFolder);
		MAX_LEVELS = new File(fileFolder).list().length;	
		setGameMode(gameMode);		
	}
	
	/**
	 * Setter of the field "fileFolder".
	 * @param fileFolder Folder name where the configuration/level files are. 
	 */
	private void setFileFolder(String fileFolder) {
		this.fileFolder = fileFolder;
	}
	
	/**
	 * Getter of the field "fileFolder".
	 * @return Value of the field "fileFolder".
	 */
	private String getFileFolder() {
		return fileFolder;
	}
	
	/**
	 * Says if the game is finished (true) or not (false). The game is finished when the field "numLevel" is equals to "MAX_LEVELS".
	 * @return True if there are no more levels and thus the game is finished. Otherwise, false.
	 */
	private boolean isFinished() {
		if (numLevel>=MAX_LEVELS) {
			return true;
		} else {
		return false;
		}
	}
	
	/**
	 * Getter of the field "numLevel".
	 * @return Value of the field "numLevel".
	 */
	public int getNumLevel() {
		return numLevel;
	}
	
	/**
	 * Increases the total score, resets the level score, checks if there is a new level and loads the level.
	 * @return True if there is a next level and it has been loaded.
	 * @throws FileNotFoundException When there is a problem opening the configuration file.
	 * @throws LevelException When there is a level exception/problem.
	 * @throws VacuumException When there is an exception/problem with the vacuum cleaner.
	 * @throws SpriteException When there is a Sprite exception/problem.
	 */
	public boolean nextLevel() throws FileNotFoundException, LevelException, VacuumException, SpriteException {
		  this.incTotalScore(this.getLevelScore());
		  this.resetLevelScore();
		  if(this.isFinished()) {
			  return false;
		  }else {
			  this.numLevel=this.getNumLevel()+1;
			  this.loadLevel();
			  return true;
		  } 
		 }
	
	
	/**
	 * Loads a new level, creating a level object and a vacuum object. 
	 * @throws FileNotFoundException When there is a problem opening the configuration file.
	 * @throws LevelException When there is a level exception/problem.
	 * @throws VacuumException When there is an exception/problem with the vacuum cleaner.
	 * @throws SpriteException When there is a Sprite exception/problem.
	 */
	private void loadLevel() throws FileNotFoundException, LevelException, VacuumException, SpriteException {
		level = new Level(getFileFolder()+"level"+numLevel+".txt");
		huocver =  (Vacuum) level.get1DBoard().stream().filter(p -> p instanceof Vacuum).findAny().get();		
	}
	
	/**
	 * Resets the level score and load the current level again.
	 * @throws FileNotFoundException When there is a problem opening the configuration file.
	 * @throws LevelException When there is a level exception/problem.
	 * @throws VacuumException When there is an exception/problem with the vacuum cleaner.
	 * @throws SpriteException When there is a Sprite exception/problem.
	 */
	public void reload() throws FileNotFoundException, LevelException, VacuumException, SpriteException {		
		resetLevelScore();		
		loadLevel();
	}
	
	/**
	 * Checks if the level is beaten, i.e. zero dirts and dustballs, and the vacuum is empty.
	 * @return true if this level is beaten, otherwise false.
	 */
	public boolean isLevelBeaten() {
		if (level.get1DBoard().stream().filter(p -> (p instanceof DustBall || p instanceof Dirt)).collect(Collectors.toList()).size()>0 || huocver.getCapacity()>0)	{
			return false;
		}
		return true;
	}
	
	/**
	 * Returns a String with the board of the current level in textual format.
	 * @return Text-based board of the current level.
	 */
	public String getBoardText() {
		return level.toString();
	}
	
	/**
	 * Getter of the field "level".
	 * @return Object of the level which is being played currently. 
	 */
	public Level getLevel() {
		return level;
	}
	
	/**
	 * Returns the value of the background image of the current level.
	 * @return Value of the the background image of the current level.
	 */
	public String getLevelBackground() {
		return level.getImageBackground();
	}
	
	/**
	 * Returns the ratio capacity/MAX_CAPACITY.
	 * @return Ratio between vacuum cleaner's current capacity and vacuum cleaner's maximum capacity.
	 */
	public double getVacuumRatioCapacity() {		
		return (double)huocver.getCapacity()/(double)huocver.getMaxCapacity();
	}
	
	/**
	 * Getter of the field "totalScore".
	 * @return Value of the field "totalScore".
	 */
	public int getTotalScore() {
		return totalScore;
	}
	
	/**
	 * Adds "inc" to the field "totalScore".
	 * @param inc Amount to add to "totalScore".
	 */
	private void incTotalScore(int inc) {
		totalScore += inc;
	}
	
	/**
	 * Getter of the field "levelScore".
	 * @return Value of the field "levelScore".
	 */
	public int getLevelScore() {
		return levelScore;
	}
	
	/**
	 * Increases the field "levelScore", "inc".
	 * @param inc Amount to add to the field "levelScore".
	 */
	private void incLevelScore(int inc) {
		levelScore += inc;
	}
	
	/**
	 * Sets the field "levelScore" to zero.
	 */
	private void resetLevelScore() {
		levelScore = 0;
	}
	
	/**
	 * Getter of the field "time".
	 * @return Value of the field "time".
	 */
	public int getTime() {
		return level.getTime();
	}
	
	/**
	 * Getter of the field "turns".
	 * @return Value of the field "turns".
	 */
	public int getTurns() {
		return level.getTurns();
	}
	
	/**
	 * Getter of the field "gameMode";
	 * @return Value of the field "gameMode".
	 */
	private GameMode getGameMode() {
		return gameMode;
	}
	
	/**
	 * Setter of the field "gameMode"
	 * @param gameMode Value of the field "gameMode"
	 */
	private void setGameMode(GameMode gameMode) {
		this.gameMode = gameMode;
	}
	
	/**
	 * Given a movement in row axis (dRow) and in column axis (dCol), moves the vacuum cleaner. Moreover cleans the dirt.
	 * @param dRow Amount to move the vacuum cleaner in the row axis.
	 * @param dCol Amount to move the vacuum cleaner in the column axis.
	 * @throws VacuumException When there is an exception/problem with the vacuum cleaner.
	 * @throws SpriteException When there is a Sprite exception/problem.
	 */
	public void moveVacuum(int dRow, int dCol) throws VacuumException, SpriteException {
		int row = huocver.getRow();
		int col = huocver.getColumn();
		
		if(!(row+dRow<0 || col+dCol<0 || row+dRow>=level.getNumRows() || col+dCol>=level.getNumColumns())) {
			Sprite nextSprite = level.getCell(row + dRow, col + dCol);
	    	
	    	if (!(nextSprite instanceof Wall)){
	    		level.setCell(row, col, huocver.getUnder()); 
	    		
	    		//we store the sprite in order to repaint it after passing its position
	    		huocver.setUnder(nextSprite);
	    		
	    		if(nextSprite instanceof Dirt) {    			
	    			if(((Dirt)nextSprite).getScore()+huocver.getCapacity() <= huocver.getMaxCapacity()) {
	    				//If we don't overload the capacity with the next dirt, we can clean it
	    				huocver.incCapacity(((Dirt)nextSprite).getScore());
	    				//After cleaning, there isn't dirt, there is a clean corridor :)
	    				huocver.setUnder(new Corridor(row,col));
	    			}    			
	    		}else if(nextSprite instanceof Dumpster) {
	    			incLevelScore(huocver.getCapacity());
	    			huocver.empty();    			
	    		}
	    		
	    		huocver.moveTo(row + dRow, col + dCol);
	    		level.setCell(huocver);    		
	    	}
		}		
	}
	
	/**
	 * Updates the state of the current level, e.g. decreases time/turns, move dustballs, etc.	
	 * @throws LevelException When the turns/time is negative.
	 * @throws SpriteException 
	 */
	public void update() throws LevelException, SpriteException{
		//Move all the dustballs
		moveDustBalls();		
		//Update time
		if(getGameMode().equals(GameMode.TIME)){
			if(elapsedTime == INTERVAL_TIME) {
				level.decTime();
				elapsedTime = 0;
			}else {
				elapsedTime++;
			}
		}else {
			level.decTurns();
		}
	}
	
	/**
	 * Moves all the dustballs that are in the level/room.	  
	 * @throws SpriteException 
	 */
	private void moveDustBalls() throws SpriteException{	
		List<Sprite> llista =  level.get1DBoard().stream().filter(p -> (p instanceof DustBall)).collect(Collectors.toList());	
		for(int i = 0; i<llista.size();i++) {
			DustBall b = (DustBall) llista.get(i);
			b.moveRandomly(level);
		} 
	}	
	
	/**
	 * List of sprites which are visible in a GUI app.
	 * @return List of sprites which are visible (i.e. Dumpster, Dirt and Vacuum), i.e. they have an image.
	 */
	public List<Sprite> getVisibleSprites(){
		return level.get1DBoard().stream().filter(p -> (p instanceof Dumpster || p instanceof Dirt || p instanceof Vacuum)).collect(Collectors.toList());
	}
	
	/**
	 * Checks if the turns or the time is up. It depends on the game mode.
	 * @return True if the turns or time are equal to zero, depending on the game mode.
	 */	
	public boolean timesUp() {
		if(getGameMode().equals(GameMode.TIME)) {
			return getTime() == 0;
		}else {
			return getTurns() == 0;
		}
	}	
}