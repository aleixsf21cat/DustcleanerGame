package edu.uoc.uocleaner.model;

public class SpriteException extends Exception {
	/**
	 *  Sprite Exception
	 *  @throws ERROR_INDEX_ROW_INCORRECT per each negative index of row 
	 *  @throws ERROR_INDEX_COLUMN_INCORRECT per each negative index of row 
	 */ 
	private static final long serialVersionUID = 1L;

	public final static String ERROR_INDEX_ROW_INCORRECT = "[ERROR] The index of row cannot be negative";
	public final static String ERROR_INDEX_COLUMN_INCORRECT = "[ERROR] The index of column cannot be negative";		
	
	


	public SpriteException() {
		super();
	}
	
	public SpriteException(String msg) {
		super(msg);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
