package edu.uoc.uocleaner.model.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import edu.uoc.uocleaner.model.DustBall;
import edu.uoc.uocleaner.model.Level;
import edu.uoc.uocleaner.model.LevelException;
import edu.uoc.uocleaner.model.SpriteException;
import edu.uoc.uocleaner.model.VacuumException;

@TestInstance(Lifecycle.PER_CLASS)

class DustBallTest {
	
	private DustBall dustball;
	
	@BeforeEach
	void testDustBall() {
		try {
			dustball = new DustBall(8,5);			
		} catch (SpriteException e) {
			e.printStackTrace();
			fail("testDustBall failed");
		}
	}

	@Test
	void testMoveTo() {
		try {
			dustball.moveTo(1, 1);
		} catch (SpriteException e) {			
			e.printStackTrace();
			fail("testMoveTo failed - Case 1");
		}
		
		SpriteException ex = assertThrows(SpriteException.class, () -> dustball.moveTo(-1, 1));
		assertEquals(SpriteException.ERROR_INDEX_ROW_INCORRECT,ex.getMessage());
		assertEquals(1,dustball.getRow());
		
		ex = assertThrows(SpriteException.class, () -> dustball.moveTo(1, -1));
		assertEquals(SpriteException.ERROR_INDEX_COLUMN_INCORRECT,ex.getMessage());
		assertEquals(1,dustball.getColumn());		
	}



	
	@Test
	void testMoveRandomly() {
		try {
			Level level = new Level("levels/level1.txt");
			
			//Test 1
			
			dustball = (DustBall) level.getCell(1,14);			
			assertTrue(dustball.moveRandomly(level).stream().allMatch(p -> check(p,
					new ArrayList<int[]>(Arrays.asList(new int[][]{new int[]{1,13}, new int[]{2,14}}))					
					)));			
			
			//Test 2
			
			level.setCell(3,0,dustball);
			
			assertTrue(dustball.moveRandomly(level).stream().allMatch(p -> check(p,
					new ArrayList<int[]>(Arrays.asList(new int[][]{new int[]{3,1}}))					
					)));
			
			//Test 3
			
			level.setCell(9,3,dustball);
			
			assertTrue(dustball.moveRandomly(level).stream().allMatch(p -> check(p,
					new ArrayList<int[]>(Arrays.asList(new int[][]{new int[]{9,4},new int[]{8,3}}))					
					)));	
		
			//Test 4
			
			level.setCell(6,0,dustball);
			
			assertTrue(dustball.moveRandomly(level).stream().allMatch(p -> check(p,
					new ArrayList<int[]>(Arrays.asList(new int[][]{}))					
					)));
			
			//Test 5
			
			level.setCell(1,10,dustball);
			
			assertTrue(dustball.moveRandomly(level).stream().allMatch(p -> check(p,
					new ArrayList<int[]>(Arrays.asList(new int[][]{new int[]{1,11},new int[]{0,10},new int[] {1,9},new int[] {2,10}}))					
					)));	
		
	} catch (FileNotFoundException | LevelException | VacuumException | SpriteException e) {			
		e.printStackTrace();
		fail("testMoveRandomly failed");
	}
	}
	
	/**
	 * Helper method for testMoveRandomly
	 * @param p Element/Movement to check
	 * @param validMoves ArrayList with the valid moves that the dustball can do according to the test
	 * @return true if p is in validMoves
	 */
	private boolean check(int[]p,ArrayList<int[]> validMoves) {				
		for(var v : validMoves) {					
			if((v[0] == p[0]) && (v[1] == p[1])) return true;
		}
		
		return false;	
	}

	@Test
	void testGetScore() {
		assertEquals(5,dustball.getScore());
	}	
}