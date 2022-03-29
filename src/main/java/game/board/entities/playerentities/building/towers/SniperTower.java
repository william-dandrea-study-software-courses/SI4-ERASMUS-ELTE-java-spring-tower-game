package game.board.entities.playerentities.building.towers;

import game.utils.Position;

/**
 * @author D'Andréa William
 */
public class SniperTower extends Tower{

    /**
     * The sniper tower extended from Tower
     * @param position
     * @param price
     * @param shootingRange
     * @param simultaneousStrike
     * @param damageToSoldiers
     * @param upgradePrice
     * upgrade price of the tower
     * @param percentageRewardIfDestroyed
     */
    public SniperTower(Position position, float price, int shootingRange, int simultaneousStrike, int damageToSoldiers, int refundPrice, int upgradePrice, float percentageRewardIfDestroyed) {
        super(position, owner, price, shootingRange, simultaneousStrike, damageToSoldiers, upgradePrice, percentageRewardIfDestroyed);
    }
}
