package game.board.entities.playerentities.building.goldmines;

import game.board.entities.playerentities.building.BuildingEntity;
import game.utils.Position;

/**
 * @author D'Andréa William
 *
 * - Price of a gold mine
 * - Amount of gold distributed at the beginning of each round for a gold mine
 */
public class GoldMine extends BuildingEntity {

    // Amount of gold distributed at the beginning of each round for a gold mine
    private int goldDistributedAtEachRound;

    /**
     * The goldmine entity extended from BuildingEntity
     * @param position
     * @param price
     * @param goldDistributedAtEachRound
     * the gold gain for the owner each round
     * @param percentageRewardIfDestroyed
     */
    public GoldMine(Position position, float price, int goldDistributedAtEachRound, float percentageRewardIfDestroyed) {
        super(position, price, percentageRewardIfDestroyed);
        this.goldDistributedAtEachRound = goldDistributedAtEachRound;
    }

    public int getGoldDistributedAtEachRound() {
        return goldDistributedAtEachRound;
    }

    public void setGoldDistributedAtEachRound(int goldDistributedAtEachRound) {
        this.goldDistributedAtEachRound = goldDistributedAtEachRound;
    }
}
