package com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.soldiers;

import com.softwaretechnology.tourgame.theknigh.service.game.utils.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SoldierTest {
    Soldier soldier = new Soldier(new Position(1,1), 10.0, 20, 2);

    @BeforeEach
    void setUp() {

    }

    @Test
    void getHealthPointTest(){
        assertEquals(20, this.soldier.getHealthPoint());
    }

    @Test
    void setHealthPointTest(){
        this.soldier.setHealthPoint(30);
        assertEquals(30, this.soldier.getHealthPoint());
    }

    @Test
    void removeHealthPointsTest(){
        this.soldier.removeHealthPoints(10);
        assertEquals(10, this.soldier.getHealthPoint());
    }

    @Test
    void getNumberOfMoveAtEachRoundTest(){
        assertEquals(2, this.soldier.getNumberOfMoveAtEachRound());
    }

    @Test
    void setNumberOfMoveAtEachRoundTest(){
        this.soldier.setNumberOfMoveAtEachRound(3);
        assertEquals(3, this.soldier.getNumberOfMoveAtEachRound());
    }

    @Test
    void hashCodeTest(){
        assertEquals(19282+this.soldier.getName().hashCode(), this.soldier.hashCode());
    }
}
