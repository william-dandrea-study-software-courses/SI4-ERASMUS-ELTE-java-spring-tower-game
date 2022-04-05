package game.board.entities.playerentities.building.towers;

import game.gamemanaging.Player;
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
     */
    public SniperTower(Position position, Player owner, float price, int shootingRange, int simultaneousStrike, int damageToSoldiers) {
        super(position, owner, price, shootingRange, simultaneousStrike, damageToSoldiers);
    }
}
