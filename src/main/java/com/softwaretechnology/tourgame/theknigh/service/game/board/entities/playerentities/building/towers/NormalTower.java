package com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.building.towers;


import com.softwaretechnology.tourgame.theknigh.service.game.utils.Position;

/**
 * @author D'Andréa William
 */
public class NormalTower extends Tower {


    public NormalTower(Position position, float price, int shootingRange, int simultaneousStrike, float percentageRewardIfDestroyed) {
        super(position, price, shootingRange, simultaneousStrike, percentageRewardIfDestroyed);
    }
}
