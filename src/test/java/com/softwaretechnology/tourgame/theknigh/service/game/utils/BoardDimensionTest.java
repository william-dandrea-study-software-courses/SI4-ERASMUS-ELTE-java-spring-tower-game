package com.softwaretechnology.tourgame.theknigh.service.game.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Tian Zhenman
 */
class BoardDimensionTest {

    BoardDimension boardDimension = new BoardDimension(20,10);

    @BeforeEach
    void setUp() {
    }

    @Test
    void getLengthTest(){
        assertEquals(20, this.boardDimension.getLength());
    }

    @Test
    void getWidthTest(){
        assertEquals(10, this.boardDimension.getWidth());
    }
}
