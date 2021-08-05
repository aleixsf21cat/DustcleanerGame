package edu.uoc.uocleaner.model;

/** 
 * Vacuum
 * It describes each vacuum 
 * @author Aleix Sicilia
 * @version 1.0  
 */

public class Vacuum extends Sprite implements Movable{
	
	/** 
	 * @class Vacuum
	 * It describes each vacuum. Extended from Sprite and implemented from Movable.
	 * @author Aleix Sicilia
	 * @version 1.0  
	 */
	
	/**
	 * Maximum capacity per vacuum
	 */
	  private int MAX_CAPACITY;
	  /**
		 * Capacity per vacuum
		 */
	  private int capacity=0;
	  /**
		 * It describes under's Sprite
		 */
	  private Sprite under;
	  
	  
	  public Vacuum (int row, int column, int MAX_CAPACITY) throws VacuumException, SpriteException{
		  /**
			 * Parameterized constructor
			 * @param row Set the position (row)
			 * @param column Set the position (column)
			 * @param Symbol, sets the symbol for each element
			 * @param MAX_CAPACITY, it describes the maximum capacity per each vacuum
			 * @throws SpriteException
			 */
	      
	    super(row, column, Symbol.VACUUM);

	    if (MAX_CAPACITY<0) {
	      
	      throw new VacuumException(VacuumException.ERROR_MAX_CAPACITY_VALUE);
	    }
	    this.MAX_CAPACITY= MAX_CAPACITY;
		Sprite Corridor = new Corridor(row, column);
		setUnder(Corridor);
	      
	  }
	


	

	  public int getCapacity() {
		  /**
			 * Getter MaxCapacity per vacuum
			 */
		    
		    return capacity;
		  }

		    public void incCapacity (int inc) throws VacuumException {
		    	/**
				 * IncCapacity You can increase the capacity per vacuum
				 * @throws VacuumExcepcetion if capacity is a negative value or it overflows the maximum capacity.
				 */
		      
		      if (inc<0){
		      
		      throw new VacuumException(VacuumException.ERROR_INC_CAPACITY_NEGATIVE_VALUE);
		    }
		      else {
		      this.capacity=inc+capacity;
		      }
		       if(MAX_CAPACITY<capacity) {

		        throw new VacuumException(VacuumException.ERROR_OVERFLOW_MAX_CAPACITY);
		      }
		      
		    }
		  
		  
		  public void setCapacity(int capacity) throws VacuumException{
			  /**
				 * Setter MaxCapacity per vacuum
				 * @throws VacuumException if capacity is a negative value
				 */
		    
		    if(capacity<0) {
		      throw new VacuumException(VacuumException.ERROR_CAPACITY_NEGATIVE_VALUE);
		    }
		    this.capacity = capacity;
		  }

		  public void empty() throws VacuumException{
			  /**
				 * Empty it sets up capacity to zero if it is called
				 */
			  this.capacity = 0;
		  }
		  
		  public int getMaxCapacity() {
			  /**
				 * Getter MaxCapacity
				 */
			  
		    
		    return MAX_CAPACITY;
		  }
		  
		  public Sprite getUnder() {
			  /**
				 * getter Under
				 * No exception
				 */
		    return under;
		  }


		  public void setUnder(Sprite sprite) {
			  /**
				 * setter Under
				 * No exception
				 */
			this.under = sprite;
		  }
		  @Override
		  public void moveTo (int row, int column) throws SpriteException{
				/**
				 * @class moveTo, it says if an element can be moved to some position
				 * @param row Set the position (row)
				 * @param column Set the position (column)
				 * @throws SpriteException
				 */
			    
			    
			    if(row<0) {
			      throw new SpriteException(SpriteException.ERROR_INDEX_ROW_INCORRECT);
			    }{
			    under.setRow(row);
			    }
			  
			    if(column<0) {
			      throw new SpriteException(SpriteException.ERROR_INDEX_COLUMN_INCORRECT);
			    }{
			    setRow(row);
				setColumn(column);
			  }

		}
}

