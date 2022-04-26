package com.softwaretechnology.tourgame.theknigh.service.game.board.entities.gameentities.castles;
import com.softwaretechnology.tourgame.theknigh.service.game.utils.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CastleTest {
    Castle castle = new Castle(new Position(1,1), 100);

    @BeforeEach
    void setUp() {

    }

    @Test
    void getHealthPoint(){
        assertEquals(100, this.castle.getHealthPoint());
    }

    @Test
    void setHealthPointTest(){
        this.castle.setHealthPoint(50);
        assertEquals(50, this.castle.getHealthPoint());
    }

    @Test
    void removeHealthPointTest(){
        this.castle.removeHealthPoint(5);
        assertEquals(95, this.castle.getHealthPoint());
    }
}
