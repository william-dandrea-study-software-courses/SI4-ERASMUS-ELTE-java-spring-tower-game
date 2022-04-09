package game.board.entities.playerentities.building.goldmines;

import game.board.entities.playerentities.building.BuildingEntity;
import game.gamemanaging.Player;
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
     */
    public GoldMine(Position position, Player owner, int price, int goldDistributedAtEachRound) {
        super(position, owner, price);
        this.goldDistributedAtEachRound = goldDistributedAtEachRound;
    }

    public int getGoldDistributedAtEachRound() {
        return goldDistributedAtEachRound;
    }

    public void setGoldDistributedAtEachRound(int goldDistributedAtEachRound) {
        this.goldDistributedAtEachRound = goldDistributedAtEachRound;
    }
}
