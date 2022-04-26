package com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.building.towers;


import com.softwaretechnology.tourgame.theknigh.service.game.settings.towers.NormalTowerSettings;
import com.softwaretechnology.tourgame.theknigh.service.game.utils.Position;

/**
 * @author D'Andréa William
 */
public class NormalTower extends Tower {

    private String name = "normal_tower_entity";

    public NormalTower(Position position, float price, int shootingRange, int simultaneousStrike, float percentageRewardIfDestroyed) {
        super(position, price, shootingRange, simultaneousStrike, percentageRewardIfDestroyed);
    }

    public NormalTower(Position position, NormalTowerSettings normalTowerSettings) {
        super(position, normalTowerSettings.getPrice(), normalTowerSettings.getShootingRange(), normalTowerSettings.getNumberOfSimultaneousStrikes(), .5F);
    }

    @Override
    public String getName() {
        return name;
    }
}
