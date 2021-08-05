package edu.uoc.uocleaner.model;

import java.util.ArrayList;
import java.util.Random;

public class DustBall extends Dirt implements Movable {
	
	/** 
	 * @class Dustball
	 * @author Aleix Sicilia Fuentes
	 * It describes each dustball
	 * @version 1.0 
	 */
	
	public DustBall(int row, int column) throws SpriteException {
		/**
		 * Parameterized constructor
		 * @param row Set the position (row)
		 * @param column Set the position (column)
		 * @throws SpriteException 
		 */
		super(row, column, Symbol.DUSTBALL, 5);
		setScore(getScore());
	}

	


	
	
	  public ArrayList<int[]> moveRandomly (Level level) throws SpriteException {
		  /**
			 * @class moveRandomly
			 * It moves randomly the dustball if the movemenent is valid and a random value is greater than 0.75
			 * @throws SpriteException
			 */
			ArrayList<int[]> movement = new ArrayList<int[]>();
			
			int up[] = {this.getRow() + SPEED, this.getColumn()};
			int down[] = {this.getRow() - SPEED, this.getColumn()};
			int left[] = {this.getRow(), this.getColumn() - SPEED};
			int right[] = {this.getRow(), this.getColumn() + SPEED};
			
			if(validMove(level,up[0],up[1])) movement.add(up);
			if(validMove(level,down[0],down[1])) movement.add(down);
			if(validMove(level,left[0],left[1])) movement.add(left);
			if(validMove(level,right[0],right[1])) movement.add(right);
			
			
			if ((Math.random() >= 0.75) && (movement.size() > 0)) {
				Random rand = new Random();
				int nextPosition = rand.nextInt(movement.size());
				
				level.setCell(this.getRow(), this.getColumn(), 
						new Dirt(this.getRow(), this.getColumn()));
				
				moveTo(movement.get(nextPosition)[0],
						(movement.get(nextPosition)[1]));
				
				level.setCell(movement.get(nextPosition)[0],
						movement.get(nextPosition)[1],
						this);
				
			}
			
			return movement;
			
			
			
			
		}
		
		private boolean validMove(Level level, int row, int column) {
			/**
			 * @class validMove
			 * It indicates what kind of position is valid
			 * No exception
			 */
			boolean IsMovement = false;
			
			if ((row >= 0) && ((row) <= Level.getNumRows()-1) && 
					(column >= 0) && ((column) <= Level.getNumColumns()-1) &&
					(level.getCell(row, column).getSymbol() != Symbol.WALL) &&
					(level.getCell(row, column).getSymbol() != Symbol.VACUUM) &&
					(level.getCell(row, column).getSymbol() != Symbol.DUSTBALL) &&
					(level.getCell(row, column).getSymbol() != Symbol.DUMPSTER)) {
				IsMovement = true;
				
				
			}
			
			return IsMovement;
					
		}

		@Override
		public void moveTo(int row, int column) throws SpriteException {
			/**
			 * @class moveTo, it says if an element can be moved to some position
			 * @param row Set the position (row)
			 * @param column Set the position (column)
			 * @throws SpriteException
			 */
		    
			if (row < 0) {
				throw new SpriteException(SpriteException.ERROR_INDEX_ROW_INCORRECT);
			} else if (column < 0) {
				throw new SpriteException(SpriteException.ERROR_INDEX_COLUMN_INCORRECT);
				
			}
			else {
				this.setRow(row);
				this.setColumn(column);
			}
			
			
		}


	
	
}

