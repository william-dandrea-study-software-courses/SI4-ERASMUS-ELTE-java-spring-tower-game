package com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.building.towers;

import com.softwaretechnology.tourgame.theknigh.service.game.utils.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class NormalTowerTest {

    NormalTower normalTower = new NormalTower(new Position(1,1), 30, 2, 2, 0.5F);

    @BeforeEach
    void setUp() {

    }
}
