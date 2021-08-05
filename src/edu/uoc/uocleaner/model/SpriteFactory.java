package edu.uoc.uocleaner.model;

/** 
 * Sprite Simple Factory class.
 * @author David García Solórzano
 * @version 1.0  
 */
public abstract class SpriteFactory {
	
	/**
	 * 
	 * @param row Index of the row in which the sprite is.
	 * @param column Index of the column in which the sprite is.
	 * @param symbol Value of the enumeration called Symbol that corresponds to the sprite.
	 * @return Sprite object that is related to the "symbol".
	 * @throws VacuumException When the value for the vacuum cleaner's maximum capacity is zero or negative; 
	 * when the value for the vacuum cleaner's current capacity is negative or higher than the maximum capacity.
	 * @throws SpriteException When the index of the row or the column is incorrect.
	 */
	public static Sprite getSprite(int row, int column, Symbol symbol) throws VacuumException, SpriteException {
		
		switch(symbol) {
			case WALL:
					return new Wall(row,column);
			case DUSTBALL:
					return new DustBall(row,column);
			case DIRT:
					return new Dirt(row,column);
			case VACUUM:
					return new Vacuum(row,column,10);
			case DUMPSTER:
					return new Dumpster(row,column);
			default: // CORRIDOR and others
				return new Corridor(row,column);
		}		
	}
}