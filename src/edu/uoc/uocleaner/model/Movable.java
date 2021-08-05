package edu.uoc.uocleaner.model;

public interface Movable {
	/** 
	 * @interface Movable
	 * @author Aleix Sicilia Fuentes
	 * It describes speed and method moveTo
	 * @version 1.0 
	 */
	public int SPEED = 1;
	 public void moveTo(int row, int column) throws SpriteException;

}
