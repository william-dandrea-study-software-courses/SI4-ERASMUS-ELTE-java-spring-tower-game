package com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.building;

import com.softwaretechnology.tourgame.theknigh.service.game.utils.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Bai Zimo
 */
class BuildingEntityTest {

    BuildingEntity buildingEntity = new BuildingEntity(new Position(1,1), 20, 0.5F);

    @BeforeEach
    void setUp() {

    }

    @Test
    void getPercentageRewardIfDestroyedTest(){
        assertEquals(0.5F, this.buildingEntity.getPercentageRewardIfDestroyed());
    }

    @Test
    void setPercentageRewardIfDestroyedTest(){
        this.buildingEntity.setPercentageRewardIfDestroyed(0.6F);
        assertEquals(0.6F, this.buildingEntity.getPercentageRewardIfDestroyed());
    }

}
