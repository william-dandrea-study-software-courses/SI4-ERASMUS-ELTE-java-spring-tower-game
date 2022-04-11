package com.softwaretechnology.tourgame.theknigh.service.game.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Bai Zimo
 */
public class PositionTest {

    @BeforeEach
    void setUp() {



    }

    @Test
    void getXTest(){
        Position position = new Position(1,1);
        assertEquals(1, position.getX());
    }

    @Test
    void getYTest(){
        Position position = new Position(1,1);
        assertEquals(1, position.getY());
    }

    @Test
    void setXTest(){
        Position position = new Position(1,1);
        position.setX(10);
        assertEquals(10, position.getX());
    }

    @Test
    void setYTest(){
        Position position = new Position(1,1);
        position.setY(10);
        assertEquals(10, position.getY());
    }

    @Test
    void hashCodeTest(){
        Position position = new Position(1,1);
        assertEquals(32, position.hashCode());
    }

    @Test
    void toStringTest(){
        Position position = new Position(1,1);
        assertEquals("Position{x=1, y=1}", position.toString());
    }

}
