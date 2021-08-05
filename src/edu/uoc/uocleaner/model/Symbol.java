package edu.uoc.uocleaner.model;

/** 
 * Symbol enumeration
 * It describes each element with each character ASCII and its respective image
 * @author Aleix Sicilia
 * @version 1.0  
 */

public enum Symbol {
	/**
	 * Enum of symbol
	 * @param ascii it sets up the character (ASCII)
	 * @param image it sets up each image
	 * No exception
	 */
	DUMPSTER('D',"dumpster.png"),
	DUSTBALL('@',"dustball.png"),
	VACUUM('V',"huocver.png"),
	DIRT('·',"dirt.png"),
	WALL('#',""),
	CORRIDOR(' ',"");
	
	public char ascii;
	public String image;
	
	
	
	Symbol(char ascii, String image) {
		/**
		 * Parameterized constructor
		 * @param ascii it sets up the character (ASCII)
		 * @param image it sets up each image
		 * No exception
		 */
		 this.ascii=ascii;
		 this.image=image;
	}
	@Override
	public String toString()
	   {
		/**
		 * toString
		 * It converts to string the characters ASCII
		 * No exception
		 */
		  String text=Character.toString(ascii);
	      return text; //This will return its symbol
	   }

	public static Symbol getName(char ascii) {
		/**
		 * getName
		 * You get the name of each element per symbol
		 * No exception
		 */
		switch(ascii) {
		case 'D':
				return DUMPSTER;
		case '@':
				return DUSTBALL;
		case 'V':
				return VACUUM;
		case '·':
				return DIRT;
		case '#':
				return WALL;
		default: // CORRIDOR and others
			return CORRIDOR;
	}
		
		}

	public String getImage() {
		/**
		 * getImage()
		 * You get the name of each image per symbol
		 * No exception
		 */
		return image;
	}
	
	public char getAscii() {
		/**
		 * getAscii()
		 * You get the name of each character ASCII per symbol
		 * No exception
		 */
		return ascii;
	}
	}
