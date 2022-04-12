package com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.building.towers;

import com.softwaretechnology.tourgame.theknigh.service.game.utils.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FreezeTowerTest {

    FreezeTower freezeTower = new FreezeTower(new Position(1,1), 50, 2, 2, 0.5F, 2);

    @BeforeEach
    void setUp() {

    }

    @Test
    void getNumberOfTurnWhereTheSoldierIsFreezeTest(){
        assertEquals(2, this.freezeTower.getNumberOfTurnWhereTheSoldierIsFreeze());
    }

    @Test
    void setNumberOfTurnWhereTheSoldierIsFreezeTest(){
        this.freezeTower.setNumberOfTurnWhereTheSoldierIsFreeze(3);
        assertEquals(3, this.freezeTower.getNumberOfTurnWhereTheSoldierIsFreeze());
    }
}
