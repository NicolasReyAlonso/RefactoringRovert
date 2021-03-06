package com.codemanship.marsrover;

import org.junit.Test;
import refactoring.Rover;
import refactoring.Rover.Position;

import static org.junit.Assert.assertEquals;
import static refactoring.Rover.Heading.*;

public class Position_ {

	@Test
	public void should_calculate_forward_position() {
		assertEquals(new Position(-1,0), new Position(0,0).forward(North).forward(West).forward(South));
		assertEquals(new Position(0,1), new Position(0,0).forward(North));
		assertEquals(new Position(1,0), new Position(0,0).forward(East));
		assertEquals(new Position(-1,0), new Position(0,0).forward(West));
		assertEquals(new Position(0,-1), new Position(0,0).forward(South));
	}
	@Test
	public void should_calculate_backward_position() {
		assertEquals(new Position(0,-1), new Position(0,0).backward(North));
		assertEquals(new Position(-1,0), new Position(0,0).backward(East));
		assertEquals(new Position(1,0), new Position(0,0).backward(West));
		assertEquals(new Position(0,1), new Position(0,0).backward(South));
	}
}
