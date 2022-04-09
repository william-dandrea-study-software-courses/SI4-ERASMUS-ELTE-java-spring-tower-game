package com.softwaretechnology.tourgame.theknigh.service.game.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author D'Andréa William
 */
class RadiusTest {

    @BeforeEach
    void setUp() {



    }

    @Test
    void generateRows() {

        Radius radius = new Radius(1, 0, 0);
        assertEquals(List.of(1), radius.generateRows());

        radius = new Radius(2, 0, 0);
        assertEquals(List.of(1,3,1), radius.generateRows());

        radius = new Radius(3, 0, 0);
        assertEquals(List.of(1,3, 5, 3, 1), radius.generateRows());

        radius = new Radius(4, 0, 0);
        assertEquals(List.of(1,3, 5, 7, 5,  3, 1), radius.generateRows());
    }


    @Test
    void getPositionsWhenZeroTest() {

        Radius radius = new Radius(1, 0, 0);
        assertEquals(List.of(new Position(0,0)) , radius.getPositionsWhenZero());


        radius = new Radius(2, 0, 0);
        assertEquals(List.of(new Position(0,-1), new Position(-1,0), new Position(0,0),new Position(1,0),new Position(0,1)) , radius.getPositionsWhenZero());
    }


    @Test
    void getPositionsTest() {

        Radius radius = new Radius(1, 2, 4);
        assertEquals(List.of(new Position(2,4)) , radius.getPositions());


        radius = new Radius(2, 2, 3);
        assertEquals(List.of(new Position(2,2), new Position(1,3), new Position(2,3),new Position(3,3),new Position(2,4)) , radius.getPositions());
    }
}
