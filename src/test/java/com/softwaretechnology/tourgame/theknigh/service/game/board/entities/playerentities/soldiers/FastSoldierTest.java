package com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.soldiers;

import com.softwaretechnology.tourgame.theknigh.service.game.utils.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FastSoldierTest {
    FastSoldier fastSoldier = new FastSoldier(new Position(1,1), 10.0, 20, 2, 2);

    @BeforeEach
    void setUp() {

    }

    @Test
    void getNumberOfTileHeCanJumpTest(){
        assertEquals(2, this.fastSoldier.getNumberOfTileHeCanJump());
    }

    @Test
    void setNumberOfTileHeCanJumpTest(){
        this.fastSoldier.setNumberOfTileHeCanJump(3);
        assertEquals(3, this.fastSoldier.getNumberOfTileHeCanJump());
    }

}
