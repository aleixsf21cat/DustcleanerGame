package edu.uoc.uocleaner.model;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;






/** 
 * Level/Room class. 
 * @author Aleix Sicilia Fuentes
 * Game about a cleaner with different level that has to clean different rooms
 * @version 1.0 
 */
public class Level{
	/**
	 * Number of rows of the board.
	 */
	private static int numRows;
	/**
	 * Number of columns of the board.
	 */
	private static int numColumns;
	/**
	 * Representation of the 2D board.
	 */
	private Sprite[][] board;
	/**
	 * Name of the background image for the GUI app.
	 */
	private String imageBackground;
	/**
	 * Number of turns which the player has in order to beat the level.
	 */
	private int turns;	
	/**
	 * Maximum time which the player has in order to beat the level.
	 */
	private int time;
		
	/**
	 * Constructor 
	 * @param fileName Name of the configuration file which has all the information of the level.
	 * @throws FileNotFoundException When it is impossible to open the configuration file.
	 * @throws LevelException When the number of rows or columns are zero or negative; 
	 * when the number of turns or time is negative; when there is not only one vacuum cleaner; when there are not dumpsters; 
	 * when there are not dirts or dustballs.
	 * @throws VacuumException When the value for the vacuum cleaner's capacity is zero or negative.
	 * @throws SpriteException When the index of either the row or the column is incorrect.
	 */
	public Level(String fileName) throws FileNotFoundException, LevelException, VacuumException, SpriteException {
		char[] line = null;
		boolean isDumpster = false, isDirt = false;
		int numVacuums = 0;		
		
		Scanner sc = new Scanner(new File(fileName));

		// find the number of rows and columns         
        setNumRows(Integer.parseInt(sc.nextLine()));
        setNumColumns(Integer.parseInt(sc.nextLine()));
        setImageBackground(sc.nextLine());
        setTurns(Integer.parseInt(sc.nextLine()));
        setTime(Integer.parseInt(sc.nextLine()));
        
        board = new Sprite[numRows][numColumns];           
        for (int row = 0; row < numRows; row++) {
        	line = sc.nextLine().toCharArray();        	
			for (int column = 0; column < numColumns; column++) {				
				board[row][column] = SpriteFactory.getSprite(row,column,Symbol.getName(line[column]));				
				if(board[row][column] instanceof Dirt) isDirt = true;
				if(board[row][column] instanceof Vacuum) numVacuums++;
				if(board[row][column] instanceof Dumpster) isDumpster = true;								
			}	
			
        }        
        
        sc.close();       
                
        if(numVacuums!=1) {
        	throw new LevelException(LevelException.ERROR_NUM_VACUUMS);
        }
        
        if(!isDumpster) {
        	throw new LevelException(LevelException.ERROR_NO_DUMPSTERS);
        }
        
        if(!isDirt) {
        	throw new LevelException(LevelException.ERROR_NO_DIRT);
        }        
	}

	public static int getNumRows() {
		/**
		 * getter NumsRows
		 */
		
		return numRows;
	}

	public void setNumRows(int numRows) throws LevelException {
		/**
		 * setter NumsRows
		 * @throws LevelException if numRows is not correct (less or equal to zero)
		 */
		if (numRows <= 0){
			throw new LevelException(LevelException.ERROR_NUM_ROWS_INCORRECT);
	}else {
		this.numRows = numRows;
	}
	}

	public static int getNumColumns() {
		/**
		 * getter NumsColumns
		 */
		return numColumns;
	}

	public void setNumColumns(int numColumns) throws LevelException {
		/**
		 * setter NumsColumns
		 * @throws LevelException if numColumns is not correct (less or equal to zero)
		 */
		if (numColumns <= 0){
			throw new LevelException(LevelException.ERROR_NUM_COLUMNS_INCORRECT);
	}else {
		this.numColumns = numColumns;
	}
	}

	public String getImageBackground() {
		/**
		 * It converts to string getImageBackground
		 */
		return imageBackground;
	}

	public void setImageBackground(String imageBackground) {
		/**
		 * Setter ImageBackground
		 */
		this.imageBackground = imageBackground;
	}

	public int getTurns() {
		/**
		 * Getter number of turns
		 */
		return turns;
	}

	public void setTurns(int turns) throws LevelException {
		/**
		 * Setter number of turns
		 * @throws LevelException if number of turns is negative
		 */
		if (turns <0){
			throw new LevelException(LevelException.ERROR_NUM_TURNS_INCORRECT);
	}else {
		this.turns = turns;
	}
	}
	
	public void decTurns() throws LevelException {
		/**
		 * DecTurns() it extracts 1 per each turn you play
		 * @throws LevelException if number of turns is negative
		 */
		
		this.turns = turns-1;
	 if (this.turns <0){
		throw new LevelException(LevelException.ERROR_NUM_TURNS_INCORRECT);
	}
	}
	
	public void decTime() throws LevelException {
		/**
		 * DecTime() it extracts 1 per each time you play
		 * @throws LevelException if number of time is negative
		 */
		
		this.time = time-1;
		if (this.time <0){
		throw new LevelException(LevelException.ERROR_NUM_TIME_INCORRECT);
	}
	}

	public int getTime() {
		/**
		 * getter Time
		 */
		return time;
	}

	public void setTime(int time) throws LevelException {
		/**
		 * Setter Time
		 * @throws LevelException if number of time is negative
		 */
		if (time <0){
			throw new LevelException(LevelException.ERROR_NUM_TIME_INCORRECT);
	}else {
		this.time = time;
	}	
	}

	
	
	public List<Sprite> get1DBoard() {
		/**
		 * @class get1DBoard()
		 * You get a list of all 1D elements
		 * No exception
		*/
		ArrayList<Sprite> DBlist = new ArrayList<Sprite>();
		for (int i=0;i<board.length;i++) {
			for (int j=0;j<board[0].length;j++) {
				DBlist.add(board[i][j]);
			}
		}
		return DBlist;
	}
	

	
	
	public void setCell(Sprite sprite) throws SpriteException{
		  
		  board[sprite.getRow()][sprite.getColumn()] = sprite;
		 }
	
	public void setCell(int row, int column, Sprite sprite) throws SpriteException {
		/**
		 * Setter Cell
		 * You look up per each row and column
		 */
		
			  sprite.setRow(row);
			  sprite.setColumn(column);
			  board[sprite.getRow()][sprite.getColumn()] = sprite;
		}
		
		

	public Sprite getCell(int row, int column) {
		  return board[putRowInRange(row)][putColumnInRange(column)];
		  
		 }
	
	public int putRowInRange(int row) {
		/**
		 * It fixes the row if it is not in range
		 */
		if (row<0) {
			return 0;
		}
		else if (row>Level.numRows) {
			return (numRows-1);
		}
		return row;
	}
	
	public int putColumnInRange(int column) {
		/**
		 * It fixes the column if it is not in range
		 */
		if (column<0) {
			return 0;
		}
		else if (column>Level.numColumns) {
			return (numColumns-1);
		}
		return column;
	}
	@Override
	public String toString() {
		
		/**
		 * It converts board to string
		 */
		String res = "";
		for (int i=0;i<board.length;i++) {
			for (int j=0;j<board[0].length;j++) {
				res+=(board[i][j]);
			}
		
	}
		return res+"\n\n";
}
}
	
	
	
	
