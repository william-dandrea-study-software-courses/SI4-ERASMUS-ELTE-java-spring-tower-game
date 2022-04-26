package com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.building.towers;


import com.softwaretechnology.tourgame.theknigh.service.game.settings.towers.SniperTowerSettings;
import com.softwaretechnology.tourgame.theknigh.service.game.utils.Position;

/**
 * @author D'Andréa William
 */
public class SniperTower extends Tower{

    private String name = "sniper_tower_entity";

    public SniperTower(Position position, float price, int shootingRange, int simultaneousStrike, float percentageRewardIfDestroyed) {
        super(position, price, shootingRange, simultaneousStrike, percentageRewardIfDestroyed);
    }

    public SniperTower(Position position, SniperTowerSettings sniperTowerSettings) {
        super(position, sniperTowerSettings.getPrice(), sniperTowerSettings.getShootingRange(), sniperTowerSettings.getNumberOfSimultaneousStrikes(), .5F);
    }

    @Override
    public String getName() {
        return name;
    }
}
