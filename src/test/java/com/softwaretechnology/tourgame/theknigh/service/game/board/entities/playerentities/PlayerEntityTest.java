package com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities;

import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.PlayerEntity;
import com.softwaretechnology.tourgame.theknigh.service.game.utils.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerEntityTest {
    PlayerEntity playerEntity = new PlayerEntity(new Position(1,1), 10.0);

    @BeforeEach
    void setUp() {

    }

    @Test
    void getPriceTest(){
        assertEquals(10.0, this.playerEntity.getPrice());
    }

    @Test
    void setPriceTest(){
        this.playerEntity.setPrice(20.0);
        assertEquals(20.0, this.playerEntity.getPrice());
    }

}