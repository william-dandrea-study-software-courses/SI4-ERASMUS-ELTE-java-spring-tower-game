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

    public GoldMine(Position position, float price, int goldDistributedAtEachRound, float percentageRewardIfDestroyed, int healthPoint) {
        super(position, price, percentageRewardIfDestroyed, healthPoint);
        this.goldDistributedAtEachRound = goldDistributedAtEachRound;
    }

    public int getGoldDistributedAtEachRound() {
        return goldDistributedAtEachRound;
    }

    public void setGoldDistributedAtEachRound(int goldDistributedAtEachRound) {
        this.goldDistributedAtEachRound = goldDistributedAtEachRound;
    }
}
