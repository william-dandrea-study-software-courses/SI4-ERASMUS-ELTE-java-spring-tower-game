package game.board.entities.playerentities.building.towers;

import game.utils.Position;

/**
 * @author D'Andréa William
 */
public class FreezeTower extends Tower {

    private int numberOfTurnWhereTheSoldierIsFreeze;

    public FreezeTower(Position position, float price, int shootingRange, int simultaneousStrike, int damageToSoldiers, int refundPrice, int upgradePrice, float percentageRewardIfDestroyed, int numberOfTurnWhereTheSoldierIsFreeze) {
        super(position, price, shootingRange, simultaneousStrike, damageToSoldiers, refundPrice, upgradePrice, percentageRewardIfDestroyed);

        this.numberOfTurnWhereTheSoldierIsFreeze = numberOfTurnWhereTheSoldierIsFreeze;
    }


    public int getNumberOfTurnWhereTheSoldierIsFreeze() {
        return numberOfTurnWhereTheSoldierIsFreeze;
    }

    public void setNumberOfTurnWhereTheSoldierIsFreeze(int numberOfTurnWhereTheSoldierIsFreeze) {
        this.numberOfTurnWhereTheSoldierIsFreeze = numberOfTurnWhereTheSoldierIsFreeze;
    }
}
