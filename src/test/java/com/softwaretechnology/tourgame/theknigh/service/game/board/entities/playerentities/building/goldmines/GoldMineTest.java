package com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.building.goldmines;

import com.softwaretechnology.tourgame.theknigh.service.game.utils.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoldMineTest {

    GoldMine goldMine = new GoldMine(new Position(1,1), 200, 30, 0.5F);

    @BeforeEach
    void setUp() {

    }

    @Test
    void getGoldDistributedAtEachRoundTest(){
        assertEquals(30, this.goldMine.getGoldDistributedAtEachRound());
    }

    @Test
    void setGoldDistributedAtEachRoundTest(){
        this.goldMine.setGoldDistributedAtEachRound(50);
        assertEquals(50, this.goldMine.getGoldDistributedAtEachRound());
    }

}
