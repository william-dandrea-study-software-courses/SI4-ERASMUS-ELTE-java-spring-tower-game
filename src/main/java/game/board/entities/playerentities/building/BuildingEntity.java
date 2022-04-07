package game.board.entities.playerentities.building;

import game.board.entities.playerentities.PlayerEntity;
import game.gamemanaging.Player;
import game.utils.Position;

/**
 * @author D'Andréa William
 */
public class BuildingEntity extends PlayerEntity {

    // Percentage of initial construction cost for tower removal
    private float percentageRewardIfDestroyed;

    /**
     * Building entity extended from PlayerEntity
     * @param position
     * @param owner
     * @param price
     * @param percentageRewardIfDestroyed
     * The destroy-reward (to enemy) or recycle-reward (to us)
     */
    public BuildingEntity(Position position, Player owner, float price, float percentageRewardIfDestroyed) {
        super(position, owner, price);
        this.percentageRewardIfDestroyed = percentageRewardIfDestroyed;
    }

    public float getPercentageRewardIfDestroyed() {
        return percentageRewardIfDestroyed;
    }

    public void setPercentageRewardIfDestroyed(float percentageRewardIfDestroyed) {
        this.percentageRewardIfDestroyed = percentageRewardIfDestroyed;
    }


}
