package com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.building.towers;


import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.building.BuildingEntity;
import com.softwaretechnology.tourgame.theknigh.service.game.utils.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Bai Zimo
 */
class TowerTest {

    Tower tower = new Tower(new Position(1,1), 20, 2, 2, 0.5F);

    @BeforeEach
    void setUp() {

    }

    @Test
    void getShootingRangeTest(){
        assertEquals(2, this.tower.getShootingRange());
    }

    @Test
    void setShootingRangeTest(){
        this.tower.setShootingRange(3);
        assertEquals(3, this.tower.getShootingRange());
    }

    @Test
    void getSimultaneousStrikeTest(){
        assertEquals(2, this.tower.getSimultaneousStrike());
    }

    @Test
    void setSimultaneousStrikeTest(){
        this.tower.setSimultaneousStrike(3);
        assertEquals(3, this.tower.getSimultaneousStrike());
    }

    @Test
    void increaseSimultaneousStrikeTest(){
        this.tower.setSimultaneousStrike(3);
        assertEquals(3, this.tower.getSimultaneousStrike());
    }

}

