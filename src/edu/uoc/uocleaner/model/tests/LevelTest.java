package edu.uoc.uocleaner.model.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.uoc.uocleaner.model.Corridor;
import edu.uoc.uocleaner.model.Level;
import edu.uoc.uocleaner.model.LevelException;
import edu.uoc.uocleaner.model.SpriteException;
import edu.uoc.uocleaner.model.VacuumException;

class LevelTest {
	private Level level;

	@BeforeEach
	void testLevel() {
		try {
			level = new Level("levels/level1.txt");
		} catch (FileNotFoundException | LevelException | VacuumException | SpriteException e) {			
			e.printStackTrace();
			fail("testLevel failed");
		}
	}

	@Test
	void testGet1DBoard() {
		StringBuilder str = new StringBuilder();
	    
	    level.get1DBoard().stream().forEach(c -> {
	    	str.append(c.toString());
	    });
		
	    assertEquals("·   D##  · ########· ##    ·· @####  ##   ·    # ··  ##  ##########  ##  ########### ##        # V     ·    #   #### #   ##   # ·    #   # ### ·### ·#   #     #",str.toString());
	}

	@Test
	void testGetCell() {
		assertEquals("D",level.getCell(0, 4).toString());
		assertEquals("D",level.getCell(-1, 4).toString()); //(0,4)
		assertEquals("·",level.getCell(0, -1).toString()); //(0,0)
		assertEquals("#",level.getCell(0, 80).toString()); //last sprite of the first row
		assertEquals("·",level.getCell(-20, -1).toString());//first sprite (0,0)
		assertEquals("#",level.getCell(100, 80).toString()); //last sprite of the last row
	}

	@Test
	void testSetCellSprite() {
		Corridor corridor;
		
		try{
			corridor = new Corridor(0,0);
			level.setCell(corridor);
			assertEquals(corridor,level.getCell(0,0));
			assertEquals(0,corridor.getRow());
			assertEquals(0,corridor.getColumn());
		} catch (SpriteException e) {			
			e.printStackTrace();
			fail("testLevel failed");
		}		
	}

	@Test
	void testSetCellIntIntSprite() {
		Corridor corridor;
		
		try{
			corridor = new Corridor(0,0);
			level.setCell(1,2,corridor);
			assertEquals(corridor,level.getCell(1,2));
			assertEquals(1,corridor.getRow());
			assertEquals(2,corridor.getColumn());
		} catch (SpriteException e) {			
			e.printStackTrace();
			fail("testSetCellIntIntSprite failed");
		}
	}

	@Test
	void testGetNumRows() {
		assertEquals(10,level.getNumRows());
	}

	@Test
	void testGetNumColumns() {
		assertEquals(16,level.getNumColumns());
	}

	@Test
	void testGetImageBackground() {
		assertEquals("level1.png",level.getImageBackground());
	}

	@Test
	void testGetTurns() {
		assertEquals(75,level.getTurns());
	}

	@Test
	void testDecTurns() {
		try {
			level.decTurns();
		} catch (LevelException e) {
			fail("testDecTurns failed - Case 1");
			e.printStackTrace();
		}
		assertEquals(74,level.getTurns());
		
		for(int i = level.getTurns(); i>0; i--) {
			try {
				level.decTurns();
			} catch (LevelException e) {				
				e.printStackTrace();
				fail("testDecTurns failed - Case 2");
			}
		}
		
		LevelException ex = assertThrows(LevelException.class, () -> level.decTurns());
		assertEquals(LevelException.ERROR_NUM_TURNS_INCORRECT,ex.getMessage());		
	}

	@Test
	void testGetTime() {
		assertEquals(200,level.getTime());
	}

	@Test
	void testDecTime() {
		try {
			level.decTime();
		} catch (LevelException e) {
			fail("testDecTime failed - Case 1");
			e.printStackTrace();
		}
		assertEquals(199,level.getTime());
		
		for(int i = level.getTime(); i>0; i--) {
			try {
				level.decTime();
			} catch (LevelException e) {				
				e.printStackTrace();
				fail("testDecTime failed - Case 2");
			}
		}
		
		LevelException ex = assertThrows(LevelException.class, () -> level.decTime());
		assertEquals(LevelException.ERROR_NUM_TIME_INCORRECT,ex.getMessage());	
	}

	@Test
	void testToString() {
		assertEquals(level.toString().lastIndexOf("\n"),level.toString().length()-1);
		assertEquals("·   D##  · ########· ##    ·· @####  ##   ·    # ··  ##  ##########  ##  ########### ##        # V     ·    #   #### #   ##   # ·    #   # ### ·### ·#   #     #",level.toString().replaceAll("\n|\r\n", ""));
	}

}
