package game.board.entities.playerentities.building;

import game.board.entities.playerentities.PlayerEntity;
import game.utils.Position;

/**
 * @author D'Andréa William
 */
public class BuildingEntity extends PlayerEntity {

    // Percentage of initial construction cost for tower removal
    private float percentageRewardIfDestroyed;


    public BuildingEntity(Position position, float price, float percentageRewardIfDestroyed) {
        super(position, price);
        this.percentageRewardIfDestroyed = percentageRewardIfDestroyed;
    }

    public float getPercentageRewardIfDestroyed() {
        return percentageRewardIfDestroyed;
    }

    public void setPercentageRewardIfDestroyed(float percentageRewardIfDestroyed) {
        this.percentageRewardIfDestroyed = percentageRewardIfDestroyed;
    }


}
