package edu.uoc.uocleaner.model.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import edu.uoc.uocleaner.model.Corridor;
import edu.uoc.uocleaner.model.SpriteException;
import edu.uoc.uocleaner.model.Symbol;

@TestInstance(Lifecycle.PER_CLASS)

class CorridorTest {

	private Corridor corridor;
	
	@BeforeEach
	void testCorridor() {
		try {
			corridor = new Corridor(0,1);			
		} catch (SpriteException e) {
			e.printStackTrace();
			fail("testCorridor failed");
		}
	}
	
	
	@Test
	void testGetRow() {
		assertEquals(0,corridor.getRow());
	}

	@Test
	void testSetRow() {
		try {
			corridor.setRow(1);
			assertEquals(1,corridor.getRow());
		} catch (SpriteException e) {			
			e.printStackTrace();
			fail("testSetRow failed");
		}
							
		SpriteException ex = assertThrows(SpriteException.class, () -> corridor.setRow(-10));
		assertEquals(SpriteException.ERROR_INDEX_ROW_INCORRECT,ex.getMessage());
		assertEquals(1,corridor.getRow());		
	}

	@Test
	void testGetColumn() {
		assertEquals(1,corridor.getColumn());
	}

	@Test
	void testSetColumn() {
		try {
			corridor.setColumn(1);
			assertEquals(1,corridor.getColumn());
		} catch (SpriteException e) {			
			e.printStackTrace();
			fail("testSetColumn failed");
		}
							
		SpriteException ex = assertThrows(SpriteException.class, () -> corridor.setColumn(-10));
		assertEquals(SpriteException.ERROR_INDEX_COLUMN_INCORRECT,ex.getMessage());
		assertEquals(1,corridor.getColumn());
	}

	@Test
	void testGetSymbol() {
		assertEquals(Symbol.CORRIDOR,corridor.getSymbol());
	}

	@Test
	void testEqualsObject() {
		Corridor corridorEquals = null;
		
		try {
			corridorEquals = new Corridor(corridor.getRow(),corridor.getColumn());
			assertTrue(corridor.equals(corridorEquals));
		} catch (SpriteException e) {			
			e.printStackTrace();
			fail("testEqualsObject failed - Case 1");
		}
		
		try {
			corridorEquals.setRow(corridorEquals.getRow()+1);
			assertFalse(corridor.equals(corridorEquals));
		} catch (SpriteException e) {
			e.printStackTrace();
			fail("testEqualsObject failed - Case 2");
		}
		
		try {
			corridorEquals.setRow(corridor.getRow());
			corridorEquals.setColumn(corridorEquals.getColumn()+1);
			assertFalse(corridor.equals(corridorEquals));
		} catch (SpriteException e) {
			e.printStackTrace();
			fail("testEqualsObject failed - Case 3");
		}		
	}

	@Test
	void testToString() {
		assertEquals(" ",corridor.toString());
	}
}