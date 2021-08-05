package edu.uoc.uocleaner.model;

public class Wall extends Sprite{
	/** 
	 * @class Wall. 
	 * @author Aleix Sicilia Fuentes
	 * It describes each wall element
	 * @version 1.0 
	 */
	public Wall(int row, int column) throws SpriteException {
		/**
		 * Parameterized constructor
		 * @param row Set the position (row)
		 * @param column Set the position (column)
		 * @param Symbol, sets the symbol for each element
		 * No Exception 
		 */
		super(row, column, Symbol.WALL);
		// TODO Auto-generated constructor stub
	}
	

}
