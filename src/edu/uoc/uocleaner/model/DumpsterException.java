package edu.uoc.uocleaner.model;

public class DumpsterException extends Exception {
	/** 
	 * @class DumpsterException
	 * @author Aleix Sicilia Fuentes
	 * It describes each customed Exception per dumpster
	 * @version 1.0 
	 */
	private static final long serialVersionUID = 1L;

	public final static String ERROR_LOAD_NEGATIVE_VALUE = "[ERROR] Load cannot be negative";	
	
	


	public DumpsterException() {
		super();
	}
	
	public DumpsterException(String msg) {
		super(msg);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
