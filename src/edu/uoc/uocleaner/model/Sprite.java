package edu.uoc.uocleaner.model;

/** 
 * Sprite
 * @author Aleix Sicilia Fuentes
 * It describes each sprite / element
 * @version 1.0 
 */

public abstract class Sprite {
	
	
	/**
	 * Number of rows of the sprite.
	 */
	private int row;
	/**
	 * Number of columns of the sprite.
	 */
	
	private int column;
	public Symbol symbol;
	
	
	protected Sprite(int row, int column, Symbol symbol) throws SpriteException{
		/**
		 * Parameterized constructor
		 * @param row Set the position (row)
		 * @param column Set the position (column)
		 * @param Symbol, sets the symbol for each element
		 * @throws Exception 
		 */
		setRow(row);
		setColumn(column);
		setSymbol(symbol);
	}
	
	

	public int getRow() {
		/**
		 * Getter of row
		 * No exception
		 */
		return row;
	}
	public void setRow(int row) throws SpriteException {
		/**
		 * Setter of row
		 * @throws SpriteException, it sets up the exception in case the index is not correct
		 */
		if (row <0){
			throw new SpriteException(SpriteException.ERROR_INDEX_ROW_INCORRECT);
	}else {
		this.row = row;
	}
	}
	public int getColumn() {
		/**
		 * Getter of column
		 * No exception
		 */
		return column;
	}
	public void setColumn(int column) throws SpriteException {
		/**
		 * Setter of column
		 * @throws SpriteException, it sets up the exception in case the index is not correct
		 */
		if (column <0){
			throw new SpriteException(SpriteException.ERROR_INDEX_COLUMN_INCORRECT);
	}else {
		this.column = column;
	}
	}


	public Symbol getSymbol() {
		/**
		 * Getter of symbol, you can get the symbol per each element
		 * No exception
		 */
		return symbol;
	}

	public void setSymbol(Symbol symbol) {
		/**
		 * Setter of symbol
		 * No exception
		 */
		this.symbol = symbol;
	}

	@Override
	public boolean equals(Object obj) {
		/**
		 * Equals per element
		 * No exception
		 */
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sprite other = (Sprite) obj;
		if (column != other.column)
			return false;
		if (row != other.row)
			return false;
		if (symbol != other.symbol)
			return false;
		return true;
	}

	@Override
	public String toString() {
		/**
		 * It converts to string each kind of symbol
		 * No exception
		 */
		return getSymbol().toString();
	}
	
	
	

}
