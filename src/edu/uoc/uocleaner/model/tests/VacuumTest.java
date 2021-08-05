package edu.uoc.uocleaner.model.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import edu.uoc.uocleaner.model.Corridor;
import edu.uoc.uocleaner.model.SpriteException;
import edu.uoc.uocleaner.model.Symbol;
import edu.uoc.uocleaner.model.Vacuum;
import edu.uoc.uocleaner.model.VacuumException;

@TestInstance(Lifecycle.PER_CLASS)
class VacuumTest {

	private Vacuum vacuum;
	
	@BeforeEach
	void testVacuum() {
		
		VacuumException ex = assertThrows(VacuumException.class, () -> new Vacuum(0,1, -20));
		assertEquals(VacuumException.ERROR_MAX_CAPACITY_VALUE,ex.getMessage());
				
		try {
			vacuum = new Vacuum(0,1, 20);			
		} catch (VacuumException e) {
			e.printStackTrace();
			fail("testVacuum failed - Case 1");
		} catch (SpriteException e) {
			e.printStackTrace();
			fail("testVacuum failed - Case 2");
		}		
	}

	@Test
	void testGetCapacity() {
		assertEquals(0,vacuum.getCapacity());
	}

	@Test
	void testIncCapacity() {
		assertEquals(0,vacuum.getCapacity());
		try {
			vacuum.incCapacity(10);
		}catch (VacuumException e) {			
			e.printStackTrace();
			fail("testIncCapacity failed - Case 1");
		}
		
		assertEquals(10,vacuum.getCapacity());
		
		try {
			vacuum.incCapacity(10);
			assertEquals(20,vacuum.getCapacity());
		} catch (VacuumException e) {			
			e.printStackTrace();
			fail("testIncCapacity failed - Case 2");
		}
		
		VacuumException ex = assertThrows(VacuumException.class, () -> vacuum.incCapacity(1));
		assertEquals(VacuumException.ERROR_OVERFLOW_MAX_CAPACITY,ex.getMessage());
		
		ex = assertThrows(VacuumException.class, () -> vacuum.incCapacity(-10));
		assertEquals(VacuumException.ERROR_INC_CAPACITY_NEGATIVE_VALUE,ex.getMessage());		
	}

	@Test
	void testGetUnder() {
		assertEquals(" ",vacuum.getUnder().toString());
	}

	@Test
	void testSetUnder() {
		Corridor c = null;
		
		try {
			c = new Corridor(0,1);
		} catch (SpriteException e) {			
			e.printStackTrace();
			fail("testSetUnder failed - Case 1");
		}
		
		vacuum.setUnder(c);
		assertEquals(c,vacuum.getUnder());
		
		vacuum.setUnder(null);
		assertEquals(null,vacuum.getUnder());
	}

	@Test
	void testEmpty() {
		try {
			vacuum.incCapacity(5);
		} catch (VacuumException e) {			
			e.printStackTrace();
			fail("testEmpty failed - Case 1");
		}
		assertEquals(5,vacuum.getCapacity());
		
		try {
			vacuum.incCapacity(15);
		} catch (VacuumException e) {		
			e.printStackTrace();
			fail("testEmpty failed - Case 2");
		}
		assertEquals(20,vacuum.getCapacity());
		
		try {
			vacuum.empty();
		} catch (VacuumException e) {
			e.printStackTrace();
			fail("testEmpty failed - Case 3");
		}
		
		assertEquals(0,vacuum.getCapacity());
	}

	@Test
	void testGetMaxCapacity() {
		assertEquals(20,vacuum.getMaxCapacity());
	}

	@Test
	void testMoveTo() {
		try {
			vacuum.moveTo(1, 1);
		} catch (SpriteException e) {			
			e.printStackTrace();
			fail("testMoveTo failed - Case 1");
		}
		
		SpriteException ex = assertThrows(SpriteException.class, () -> vacuum.moveTo(-1, 1));
		assertEquals(SpriteException.ERROR_INDEX_ROW_INCORRECT,ex.getMessage());
		assertEquals(1,vacuum.getRow());
		
		ex = assertThrows(SpriteException.class, () -> vacuum.moveTo(1, -1));
		assertEquals(SpriteException.ERROR_INDEX_COLUMN_INCORRECT,ex.getMessage());
		assertEquals(1,vacuum.getColumn());		
	}	

	@Test
	void testGetRow() {
		assertEquals(0,vacuum.getRow());
	}

	@Test
	void testSetRow() {
		try {
			vacuum.setRow(1);
			assertEquals(1,vacuum.getRow());
		} catch (SpriteException e) {			
			e.printStackTrace();
			fail("testSetRow failed");
		}
							
		SpriteException ex = assertThrows(SpriteException.class, () -> vacuum.setRow(-10));
		assertEquals(SpriteException.ERROR_INDEX_ROW_INCORRECT,ex.getMessage());
		assertEquals(1,vacuum.getRow());
	}

	@Test
	void testGetColumn() {
		assertEquals(1,vacuum.getColumn());
	}

	@Test
	void testSetColumn() {
		try {
			vacuum.setColumn(1);
			assertEquals(1,vacuum.getColumn());
		} catch (SpriteException e) {			
			e.printStackTrace();
			fail("testSetColumn failed");
		}
							
		SpriteException ex = assertThrows(SpriteException.class, () -> vacuum.setColumn(-10));
		assertEquals(SpriteException.ERROR_INDEX_COLUMN_INCORRECT,ex.getMessage());
		assertEquals(1,vacuum.getColumn());
	}

	@Test
	void testGetSymbol() {
		assertEquals(Symbol.VACUUM,vacuum.getSymbol());
	}

	@Test
	void testEqualsObject() {
		Vacuum vacuumEquals = null;
		
		try {
			vacuumEquals = new Vacuum(vacuum.getRow(),vacuum.getColumn(),20);
			assertTrue(vacuum.equals(vacuumEquals));
		} catch (VacuumException e) {			
			e.printStackTrace();
			fail("testEqualsObject failed - Case 1");
		} catch (SpriteException e) {			
			e.printStackTrace();
			fail("testEqualsObject failed - Case 2");
		}
		
		try {
			vacuumEquals.setRow(vacuumEquals.getRow()+1);
			assertFalse(vacuum.equals(vacuumEquals));
		} catch (SpriteException e) {
			e.printStackTrace();
			fail("testEqualsObject failed - Case 3");
		}
		
		try {
			vacuumEquals.setRow(vacuum.getRow());
			vacuumEquals.setColumn(vacuumEquals.getColumn()+1);
			assertFalse(vacuum.equals(vacuumEquals));
		} catch (SpriteException e) {
			e.printStackTrace();
			fail("testEqualsObject failed - Case 3");
		}
	}

	@Test
	void testToString() {
		assertEquals("V",vacuum.toString());
	}
}