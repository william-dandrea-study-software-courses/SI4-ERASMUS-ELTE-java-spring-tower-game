package game.board.entities.playerentities.building.towers;

import game.utils.Position;

/**
 * @author D'Andréa William
 */
public class FreezeTower extends Tower {

    private int numberOfTurnWhereTheSoldierIsFreeze;

    /**
     * The freeze tower entity extended from Tower
     * @param position
     * @param price
     * @param shootingRange
     * @param simultaneousStrike
     * @param damageToSoldiers
     * @param upgradePrice
     * @param percentageRewardIfDestroyed
     * @param numberOfTurnWhereTheSoldierIsFreeze
     * number of turn where the soldier in the range is frozen
     */
    public FreezeTower(Position position, float price, int shootingRange, int simultaneousStrike, int damageToSoldiers, int upgradePrice, float percentageRewardIfDestroyed, int numberOfTurnWhereTheSoldierIsFreeze) {
        super(position, owner, price, shootingRange, simultaneousStrike, damageToSoldiers, upgradePrice, percentageRewardIfDestroyed);

        this.numberOfTurnWhereTheSoldierIsFreeze = numberOfTurnWhereTheSoldierIsFreeze;
    }


    public int getNumberOfTurnWhereTheSoldierIsFreeze() {
        return numberOfTurnWhereTheSoldierIsFreeze;
    }

    public void setNumberOfTurnWhereTheSoldierIsFreeze(int numberOfTurnWhereTheSoldierIsFreeze) {
        this.numberOfTurnWhereTheSoldierIsFreeze = numberOfTurnWhereTheSoldierIsFreeze;
    }
}
