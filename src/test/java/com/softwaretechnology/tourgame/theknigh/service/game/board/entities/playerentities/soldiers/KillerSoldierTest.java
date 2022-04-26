package com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.soldiers;

import com.softwaretechnology.tourgame.theknigh.service.game.utils.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KillerSoldierTest {
    KillerSoldier killerSoldier = new KillerSoldier(new Position(1,1), 10.0, 20, 2, 2);

    @BeforeEach
    void setUp() {

    }

    @Test
    void getDamageToSoldierTest(){
        assertEquals(2, this.killerSoldier.getDamageToSoldier());
    }

    @Test
    void setDamageToSoldierTest(){
        this.killerSoldier.setDamageToSoldier(3);
        assertEquals(3, this.killerSoldier.getDamageToSoldier());
    }

}
