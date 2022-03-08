package game.board.entities.playerentities.building.towers;

import game.utils.Position;

/**
 * @author D'Andréa William
 */
public class FreezeTower extends Tower {

    private int numberOfTurnWhereTheSoldierIsFreeze;

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
}
