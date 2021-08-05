package edu.uoc.uocleaner.model;



public class Corridor extends Sprite {
	/** 
	 * @class Corridor. 
	 * @author Aleix Sicilia Fuentes
	 * It describes each corridor element
	 * @version 1.0 
	 */
	
	

	public Corridor(int row, int column) throws SpriteException {
		/**
		 * Parameterized constructor
		 * @param row Set the position (row)
		 * @param column Set the position (column)
		 * @param Symbol, sets the symbol for each element
		 * No Exception 
		 */
		super(row, column, Symbol.CORRIDOR);
		
	}
	
	
	
	

}
