package com.softwaretechnology.tourgame.theknigh.service.game.board.entities;

import com.softwaretechnology.tourgame.theknigh.service.game.utils.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntityTest {
    Entity entity = new Entity(new Position(1,1));

    @BeforeEach
    void setUp() {

    }

    @Test
    void getPositionTest(){
        assertEquals(new Position(1,1), this.entity.getPosition());
    }

    @Test
    void setPositionTest(){
        this.entity.setPosition(new Position(1,2));
        assertEquals(new Position(1,2), this.entity.getPosition());
    }

    @Test
    void getNameTest(){
        assertEquals("entity", this.entity.getName());
    }
}
