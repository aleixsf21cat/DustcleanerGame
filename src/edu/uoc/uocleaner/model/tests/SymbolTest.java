package edu.uoc.uocleaner.model.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.uoc.uocleaner.model.Symbol;

class SymbolTest {

	@Test
	void testGetName() {
		assertEquals(Symbol.CORRIDOR, Symbol.getName(' '));
		assertEquals(Symbol.DIRT, Symbol.getName('·'));
		assertEquals(Symbol.DUMPSTER, Symbol.getName('D'));
		assertEquals(Symbol.DUSTBALL, Symbol.getName('@'));
		assertEquals(Symbol.VACUUM, Symbol.getName('V'));
		assertEquals(Symbol.WALL, Symbol.getName('#'));
	}

	@Test
	void testGetAscii() {
		assertEquals(' ',Symbol.CORRIDOR.getAscii());
		assertEquals('·',Symbol.DIRT.getAscii());
		assertEquals('D',Symbol.DUMPSTER.getAscii());
		assertEquals('@',Symbol.DUSTBALL.getAscii());
		assertEquals('V',Symbol.VACUUM.getAscii());
		assertEquals('#',Symbol.WALL.getAscii());
	}

	@Test
	void testGetImage() {
		assertEquals("",Symbol.CORRIDOR.getImage());
		assertEquals("dirt.png",Symbol.DIRT.getImage());
		assertEquals("dumpster.png",Symbol.DUMPSTER.getImage());
		assertEquals("dustball.png",Symbol.DUSTBALL.getImage());
		assertEquals("huocver.png",Symbol.VACUUM.getImage());
		assertEquals("",Symbol.WALL.getImage());
	}

	@Test
	void testToString() {
		assertEquals(" ",Symbol.CORRIDOR.toString());
		assertEquals("·",Symbol.DIRT.toString());
		assertEquals("D",Symbol.DUMPSTER.toString());
		assertEquals("@",Symbol.DUSTBALL.toString());
		assertEquals("V",Symbol.VACUUM.toString());
		assertEquals("#",Symbol.WALL.toString());
	}

}
