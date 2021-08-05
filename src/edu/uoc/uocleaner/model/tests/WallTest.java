package edu.uoc.uocleaner.model.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import edu.uoc.uocleaner.model.Wall;
import edu.uoc.uocleaner.model.SpriteException;
import edu.uoc.uocleaner.model.Symbol;

@TestInstance(Lifecycle.PER_CLASS)

class WallTest {

	private Wall wall;
	
	@BeforeEach
	void testWall() {
		try {
			wall = new Wall(0,1);			
		} catch (SpriteException e) {
			e.printStackTrace();
			fail("testWall failed");
		}
	}

	@Test
	void testGetRow() {
		assertEquals(0,wall.getRow());
	}

	@Test
	void testSetRow() {
		try {
			wall.setRow(1);
			assertEquals(1,wall.getRow());
		} catch (SpriteException e) {			
			e.printStackTrace();
			fail("testSetRow failed");
		}
							
		SpriteException ex = assertThrows(SpriteException.class, () -> wall.setRow(-10));
		assertEquals(SpriteException.ERROR_INDEX_ROW_INCORRECT,ex.getMessage());
		assertEquals(1,wall.getRow());	
	}

	@Test
	void testGetColumn() {
		assertEquals(1,wall.getColumn());
	}

	@Test
	void testSetColumn() {
		try {
			wall.setColumn(1);
			assertEquals(1,wall.getColumn());
		} catch (SpriteException e) {			
			e.printStackTrace();
			fail("testSetColumn failed");
		}
							
		SpriteException ex = assertThrows(SpriteException.class, () -> wall.setColumn(-10));
		assertEquals(SpriteException.ERROR_INDEX_COLUMN_INCORRECT,ex.getMessage());
		assertEquals(1,wall.getColumn());
	}

	@Test
	void testGetSymbol() {
		assertEquals(Symbol.WALL,wall.getSymbol());
	}

	@Test
	void testEqualsObject() {
		Wall wallEquals = null;
		
		try {
			wallEquals = new Wall(wall.getRow(),wall.getColumn());
			assertTrue(wall.equals(wallEquals));
		} catch (SpriteException e) {			
			e.printStackTrace();
			fail("testEqualsObject failed - Case 1");
		}
		
		try {
			wallEquals.setRow(wallEquals.getRow()+1);
			assertFalse(wall.equals(wallEquals));
		} catch (SpriteException e) {
			e.printStackTrace();
			fail("testEqualsObject failed - Case 2");
		}
		
		try {
			wallEquals.setRow(wall.getRow());
			wallEquals.setColumn(wallEquals.getColumn()+1);
			assertFalse(wall.equals(wallEquals));
		} catch (SpriteException e) {
			e.printStackTrace();
			fail("testEqualsObject failed - Case 3");
		}
	}

	@Test
	void testToString() {
		assertEquals("#",wall.toString());
	}
}