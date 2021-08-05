package edu.uoc.uocleaner.model.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import edu.uoc.uocleaner.model.Dumpster;
import edu.uoc.uocleaner.model.DumpsterException;
import edu.uoc.uocleaner.model.SpriteException;
import edu.uoc.uocleaner.model.Symbol;

@TestInstance(Lifecycle.PER_CLASS)

class DumpsterTest {

	private Dumpster dumpster;
	
	@BeforeEach
	void testDumpster() {
		try {
			dumpster = new Dumpster(0,1);			
		} catch (SpriteException e) {
			e.printStackTrace();
			fail("testDumpster failed");
		}
	}

	@Test
	void testGetLoad() {
		assertEquals(0,dumpster.getLoad());
	}

	@Test
	void testAddLoad() {
		DumpsterException ex = assertThrows(DumpsterException.class, () -> dumpster.addLoad(-20));
		assertEquals(DumpsterException.ERROR_LOAD_NEGATIVE_VALUE,ex.getMessage());
		
		try {
			dumpster.addLoad(5);
		} catch (DumpsterException e1) {			
			e1.printStackTrace();
			fail("testAddLoad failed - Case 1");
		}
		assertEquals(5,dumpster.getLoad());
		
		try {
			dumpster.addLoad(8);
		} catch (DumpsterException e) {
			e.printStackTrace();
			fail("testAddLoad failed - Case 2");
		}
		assertEquals(13,dumpster.getLoad());
	}	

	@Test
	void testGetRow() {
		assertEquals(0,dumpster.getRow());
	}

	@Test
	void testSetRow() {
		try {
			dumpster.setRow(1);
			assertEquals(1,dumpster.getRow());
		} catch (SpriteException e) {			
			e.printStackTrace();
			fail("testSetRow failed");
		}
							
		SpriteException ex = assertThrows(SpriteException.class, () -> dumpster.setRow(-10));
		assertEquals(SpriteException.ERROR_INDEX_ROW_INCORRECT,ex.getMessage());
		assertEquals(1,dumpster.getRow());
	}

	@Test
	void testGetColumn() {
		assertEquals(1,dumpster.getColumn());
	}

	@Test
	void testSetColumn() {
		try {
			dumpster.setColumn(1);
			assertEquals(1,dumpster.getColumn());
		} catch (SpriteException e) {			
			e.printStackTrace();
			fail("testSetColumn failed");
		}
							
		SpriteException ex = assertThrows(SpriteException.class, () -> dumpster.setColumn(-10));
		assertEquals(SpriteException.ERROR_INDEX_COLUMN_INCORRECT,ex.getMessage());
		assertEquals(1,dumpster.getColumn());
	}

	@Test
	void testGetSymbol() {
		assertEquals(Symbol.DUMPSTER,dumpster.getSymbol());
	}

	@Test
	void testEqualsObject() {
		Dumpster dumpsterEquals = null;
		
		try {
			dumpsterEquals = new Dumpster(dumpster.getRow(),dumpster.getColumn());
			assertTrue(dumpster.equals(dumpsterEquals));
		} catch (SpriteException e) {			
			e.printStackTrace();
			fail("testEqualsObject failed - Case 1");
		}
		
		try {
			dumpsterEquals.setRow(dumpsterEquals.getRow()+1);
			assertFalse(dumpster.equals(dumpsterEquals));
		} catch (SpriteException e) {
			e.printStackTrace();
			fail("testEqualsObject failed - Case 2");
		}
		
		try {
			dumpsterEquals.setRow(dumpster.getRow());
			dumpsterEquals.setColumn(dumpsterEquals.getColumn()+1);
			assertFalse(dumpster.equals(dumpsterEquals));
		} catch (SpriteException e) {
			e.printStackTrace();
			fail("testEqualsObject failed - Case 3");
		}
	}

	@Test
	void testToString() {
		assertEquals("D",dumpster.toString());
	}
}