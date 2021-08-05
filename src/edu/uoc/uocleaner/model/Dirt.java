package edu.uoc.uocleaner.model;

public class Dirt extends Sprite {
	/** 
	 * @class Dirt. 
	 * @author Aleix Sicilia Fuentes
	 * It describes each dirt element (it includes dustball)
	 * @version 1.0 
	 */
	private int score = 1;
	public Dirt(int row, int column) throws SpriteException {
		
		/**
		 * Parameterized constructor
		 * @param row Set the position (row)
		 * @param column Set the position (column)
		 * @param Symbol, sets the symbol for each element
		 * @throws SpriteException
		 */
		super(row, column, Symbol.DIRT);
		// TODO Auto-generated constructor stub
	}
	protected Dirt(int row, int column, Symbol symbol, int score) throws SpriteException {
		/**
		 * Parameterized constructor
		 * @param row Set the position (row)
		 * @param column Set the position (column)
		 * @param Symbol, sets the symbol for each element
		 * @param score, sets the score per each element
		 * @throws SpriteException
		 */
		super(row, column, Symbol.DUSTBALL);
		setScore(score);
		// TODO Auto-generated constructor stub
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score=score;
	}
	
	
}
