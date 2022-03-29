package game.board.entities.playerentities.building.towers;

import game.utils.Position;

/**
 * @author D'Andréa William
 */
public class NormalTower extends Tower {


    public NormalTower(Position position, float price, int shootingRange, int simultaneousStrike, int damageToSoldiers, int refundPrice, int upgradePrice, float percentageRewardIfDestroyed) {
        super(position, owner, price, shootingRange, simultaneousStrike, damageToSoldiers, upgradePrice, percentageRewardIfDestroyed);
    }
}
