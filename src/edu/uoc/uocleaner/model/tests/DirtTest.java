package edu.uoc.uocleaner.model.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import edu.uoc.uocleaner.model.Dirt;
import edu.uoc.uocleaner.model.SpriteException;
import edu.uoc.uocleaner.model.Symbol;
import edu.uoc.uocleaner.model.Wall;

@TestInstance(Lifecycle.PER_CLASS)

class DirtTest {
	
	private Dirt dirt;

	@BeforeEach
	void testDirtIntInt() {
		try {
			dirt = new Dirt(8,5);			
		} catch (SpriteException e) {
			e.printStackTrace();
			fail("testDirtIntInt failed");
		}
	}

	@Test
	void testGetScore() {
		assertEquals(1,dirt.getScore());
	}

	@Test
	void testGetRow() {
		assertEquals(8,dirt.getRow());
	}

	@Test
	void testSetRow() {
		try {
			dirt.setRow(1);
			assertEquals(1,dirt.getRow());
		} catch (SpriteException e) {			
			e.printStackTrace();
			fail("testSetRow failed");
		}
							
		SpriteException ex = assertThrows(SpriteException.class, () -> dirt.setRow(-10));
		assertEquals(SpriteException.ERROR_INDEX_ROW_INCORRECT,ex.getMessage());
		assertEquals(1,dirt.getRow());
	}

	@Test
	void testGetColumn() {
		assertEquals(5,dirt.getColumn());
	}

	@Test
	void testSetColumn() {
		try {
			dirt.setColumn(1);
			assertEquals(1,dirt.getColumn());
		} catch (SpriteException e) {			
			e.printStackTrace();
			fail("testSetColumn failed");
		}
							
		SpriteException ex = assertThrows(SpriteException.class, () -> dirt.setColumn(-10));
		assertEquals(SpriteException.ERROR_INDEX_COLUMN_INCORRECT,ex.getMessage());
		assertEquals(1,dirt.getColumn());
	}

	@Test
	void testGetSymbol() {
		assertEquals(Symbol.DIRT,dirt.getSymbol());
	}

	@Test
	void testEqualsObject() {
		Dirt dirtEquals = null;
		
		try {
			dirtEquals = new Dirt(dirt.getRow(),dirt.getColumn());
			assertTrue(dirt.equals(dirtEquals));
		} catch (SpriteException e) {			
			e.printStackTrace();
			fail("testEqualsObject failed - Case 1");
		}
		
		try {
			dirtEquals.setRow(dirtEquals.getRow()+1);
			assertFalse(dirt.equals(dirtEquals));
		} catch (SpriteException e) {
			e.printStackTrace();
			fail("testEqualsObject failed - Case 2");
		}
		
		try {
			dirtEquals.setRow(dirt.getRow());
			dirtEquals.setColumn(dirtEquals.getColumn()+1);
			assertFalse(dirt.equals(dirtEquals));
		} catch (SpriteException e) {
			e.printStackTrace();
			fail("testEqualsObject failed - Case 3");
		}
		
		try {
			Wall wall = new Wall(dirt.getRow(),dirt.getColumn());
			assertFalse(dirt.equals(wall));
		} catch (SpriteException e) {			
			e.printStackTrace();
			fail("testEqualsObject failed - Case 3");
		}
	}

	@Test
	void testToString() {
		assertEquals("·",dirt.toString());
	}
}