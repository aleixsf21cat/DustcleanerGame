package edu.uoc.uocleaner.model;

public class VacuumException extends Exception {
	/** 
	 * @class VacuumException
	 * @author Aleix Sicilia Fuentes
	 * It describes each customed Exception per vacuum
	 * @version 1.0 
	 * @throws Different exception in function of exceeding maximum capacity, if the increment of capacity is negative, overflow or negative capacity
	 */
	private static final long serialVersionUID = 1L;

	public final static String ERROR_MAX_CAPACITY_VALUE = "[ERROR] The maximum capacity of the vacuum cleaner cannot be 0 or a negative value!!";
	public final static String ERROR_INC_CAPACITY_NEGATIVE_VALUE = "[ERROR] The increment of the capacity cannot be a negative value!!";
	public final static String ERROR_OVERFLOW_MAX_CAPACITY = "[ERROR] You are trying to overflow the maximum capacity of the vacuum";
	public final static String ERROR_CAPACITY_NEGATIVE_VALUE = "[ERROR] The capacity cannot be a negative value!!";
	
	


	public VacuumException() {
		super();
	}
	
	public VacuumException(String msg) {
		super(msg);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
