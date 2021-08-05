package edu.uoc.uocleaner.model;



public class Dumpster extends Sprite {
	/** 
	 * @class Dumpster. 
	 * @author Aleix Sicilia Fuentes
	 * It describes each dumpster
	 * @version 1.0 
	 */
	private int load = 0;

	public Dumpster(int row, int column) throws SpriteException {
		/**
		 * Parameterized constructor
		 * @param row Set the position (row)
		 * @param column Set the position (column)
		 * @param Symbol, sets the symbol for each element
		 * @throws SpriteException
		 */
		super(row, column, Symbol.DUMPSTER);
	}

	public int getLoad() {
		/**
		 * Getter Load
		 */
		return load;
	}

	public void setLoad(int load) {
		/**
		 * Setter Load
		 */
		this.load = load;
	}
	
	public void addLoad(int load) throws DumpsterException {
		/**
		 * Add Load, it adds each element of Dirt (TOTAL LOAD)
		 * @throws DumpsterException
		 */
		if (load < 0) {
			throw new DumpsterException(DumpsterException.ERROR_LOAD_NEGATIVE_VALUE);
		} else {
		this.load += load;
		
		}
		}
	

}
