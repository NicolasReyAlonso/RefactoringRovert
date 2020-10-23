package com.codemanship.marsrover;

import org.junit.Test;
import refactoring.Rover.*;

import static org.junit.Assert.*;
import static refactoring.Rover.Heading.*;


public class Rover_ {

/*    @Test
    public void turnRightNtoE(){
        Rover rover = new Rover("N", 5, 5);
        rover.go("R");
        assertEquals("E", rover.getFacing());
        assertEquals(5, rover.getPosition()[0]);
        assertEquals(5, rover.getPosition()[1]);
    }

    @Test
    public void turnRightEtoS(){
        Rover rover = new Rover("E", 5, 5);
        rover.go("R");
        assertEquals("S", rover.getFacing());
    }

    @Test
    public void turnRightStoW(){
        Rover rover = new Rover("S", 5, 5);
        rover.go("R");
        assertEquals("W", rover.getFacing());
    }

    @Test
    public void turnRightWtoN(){
        Rover rover = new Rover("W", 5, 5);
        rover.go("R");
        assertEquals("N", rover.getFacing());
    }

    @Test
    public void turnLeftNtoW(){
        Rover rover = new Rover("N", 5, 5);
        rover.go("L");
        assertEquals("W", rover.getFacing());
    }

    @Test
    public void turnLeftWtoS(){
        Rover rover = new Rover("W", 5, 5);
        rover.go("L");
        assertEquals("S", rover.getFacing());
    }

    @Test
    public void turnLeftStoE(){
        Rover rover = new Rover("S", 5, 5);
        rover.go("L");
        assertEquals("E", rover.getFacing());
    }

    @Test
    public void turnLeftEtoN(){
        Rover rover = new Rover("E", 5, 5);
        rover.go("L");
        assertEquals("N", rover.getFacing());
    }

    @Test
    public void moveFowardFacingN(){
        Rover rover = new Rover("N", 5, 5);
        rover.go("F");
        assertArrayEquals(new int[]{5, 6}, rover.getPosition());
    }

    @Test
    public void moveFowardFacingE(){
        Rover rover = new Rover("E", 5, 5);
        rover.go("F");
        assertArrayEquals(new int[]{6, 5}, rover.getPosition());
    }

    @Test
    public void moveFowardFacingS(){
        Rover rover = new Rover("S", 5, 5);
        rover.go("F");
        assertArrayEquals(new int[]{5, 4}, rover.getPosition());
    }

    @Test
    public void moveFowardFacingW(){
        Rover rover = new Rover("W", 5, 5);
        rover.go("F");
        assertArrayEquals(new int[]{4, 5}, rover.getPosition());
    }

    @Test
    public void moveBackFacingN(){
        Rover rover = new Rover("N", 5, 5);
        rover.go("B");
        assertArrayEquals(new int[]{5, 4}, rover.getPosition());
    }

    @Test
    public void moveBackFacingE(){
        Rover rover = new Rover("E", 5, 5);
        rover.go("B");
        assertArrayEquals(new int[]{4, 5}, rover.getPosition());
    }

    @Test
    public void moveBackFacingS(){
        Rover rover = new Rover("S", 5, 5);
        rover.go("B");
        assertArrayEquals(new int[]{5, 6}, rover.getPosition());
    }

    @Test
    public void moveBackFacingW(){
        Rover rover = new Rover("W", 5, 5);
        rover.go("B");
        assertArrayEquals(new int[]{6, 5}, rover.getPosition());
    }

    @Test
    public void executesSequenceOfInstructions(){
        Rover rover = new Rover("N", 5, 5);
        rover.go("RFF");
        assertEquals("E", rover.getFacing());
        assertArrayEquals(new int[]{7, 5}, rover.getPosition());
    }*/
    @Test
    public void could_be_initialized_with_legacy_constructor() {
        assertThat(new Rover("N", 5, 5).heading()).isEqualTo(North);
        assertThat(new Rover("North", 5, 5).heading()).isEqualTo(North);
        assertThat(new Rover("North", 5, 5).position()).isEqualTo(new Position(5,5));
    }

    @Test
    public void could_be_initialized_using_new_constructors() {
        assertThat(new Rover(North, new Position(4,4)).position()).isEqualTo(new Position(4,4));
        assertThat(new Rover(North, 4, 4).position()).isEqualTo(new Position(4,4));
    }

    @Test
    public void could_turn_left() {
        Rover rover = new Rover(North, new Position(3, 3));
        rover.go(Left);
        assertThat(rover.heading()).isEqualTo(West);
        assertThat(rover.position()).isEqualTo(new Position(3,3));
    }

    @Test
    public void could_turn_right() {
        Rover rover = new Rover(East, new Position(5, 1));
        rover.go(Right);
        assertThat(rover.heading()).isEqualTo(South);
        assertThat(rover.position()).isEqualTo(new Position(5,1));
    }

    @Test
    public void could_go_forward() {
        Rover rover = new Rover(South, new Position(-1, 1));
        rover.go(Forward);
        assertThat(rover.heading()).isEqualTo(South);
        assertThat(rover.position()).isEqualTo(new Position(-1,0));
    }

    @Test
    public void could_go_backward() {
        Rover rover = new Rover(West, new Position(-4, 4));
        rover.go(Backward);
        assertThat(rover.heading()).isEqualTo(West);
        assertThat(rover.position()).isEqualTo(new Position(-3,4));
    }

    @Test
    public void could_execute_many_orders() {
        Rover rover = new Rover(West, new Position(3, 1));
        rover.go(Backward, Left, Forward, Right, Forward);
        assertThat(rover.heading()).isEqualTo(West);
        assertThat(rover.position()).isEqualTo(new Position(3,0));
    }

    @Test
    public void could_execute_legacy_instructions() {
        Rover rover = new Rover(West, new Position(3, 1));
        rover.go("BLFRF");
        assertThat(rover.heading()).isEqualTo(West);
        assertThat(rover.position()).isEqualTo(new Position(3,0));
    }


    @Test
    public void could_ignore_legacy_instructions() {
        Rover rover = new Rover(West, new Position(3, 1));
        rover.go("BL*FRF");
        assertThat(rover.heading()).isEqualTo(West);
        assertThat(rover.position()).isEqualTo(new Position(3,0));
    }

}
