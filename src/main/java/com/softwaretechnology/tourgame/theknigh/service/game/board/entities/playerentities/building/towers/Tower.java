package com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.building.towers;


import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.building.BuildingEntity;
import com.softwaretechnology.tourgame.theknigh.service.game.utils.Position;

/**
 * @author D'Andréa William
 *
 * - Percentage of initial construction cost for tower removal
 */
public class Tower extends BuildingEntity {

    private int shootingRange;
    private int simultaneousStrike;
    private String name = "tower_entity";
    private int level;



    public Tower(Position position, float price, int shootingRange, int simultaneousStrike, float percentageRewardIfDestroyed) {
        super(position, price, percentageRewardIfDestroyed);

        this.shootingRange = shootingRange;
        this.simultaneousStrike = simultaneousStrike;
        this.level = 1;
    }

    public int getShootingRange() {
        return shootingRange;
    }

    public void setShootingRange(int shootingRange) {
        this.shootingRange = shootingRange;
    }

    public int getSimultaneousStrike() {
        return simultaneousStrike;
    }

    public void setSimultaneousStrike(int simultaneousStrike) {
        this.simultaneousStrike = simultaneousStrike;
    }

    public void increaseSimultaneousStrike() {
        this.level += 1;
        this.simultaneousStrike += 1;
    }

    @Override
    public String getName() {
        return name;
    }
}
