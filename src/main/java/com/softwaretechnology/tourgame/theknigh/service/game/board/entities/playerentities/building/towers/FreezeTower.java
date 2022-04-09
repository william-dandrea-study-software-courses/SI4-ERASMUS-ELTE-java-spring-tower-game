package com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.building.towers;


import com.softwaretechnology.tourgame.theknigh.service.game.utils.Position;

/**
 * @author D'Andréa William
 */
public class FreezeTower extends Tower {

    private int numberOfTurnWhereTheSoldierIsFreeze;
    private String name = "freeze_tower_entity";

    public FreezeTower(Position position, float price, int shootingRange, int simultaneousStrike, float percentageRewardIfDestroyed, int numberOfTurnWhereTheSoldierIsFreeze) {
        super(position, price, shootingRange, simultaneousStrike, percentageRewardIfDestroyed);

        this.numberOfTurnWhereTheSoldierIsFreeze = numberOfTurnWhereTheSoldierIsFreeze;
    }


    public int getNumberOfTurnWhereTheSoldierIsFreeze() {
        return numberOfTurnWhereTheSoldierIsFreeze;
    }

    public void setNumberOfTurnWhereTheSoldierIsFreeze(int numberOfTurnWhereTheSoldierIsFreeze) {
        this.numberOfTurnWhereTheSoldierIsFreeze = numberOfTurnWhereTheSoldierIsFreeze;
    }

    @Override
    public String getName() {
        return name;
    }
}
