package game.board.entities.playerentities.building;

import game.board.entities.playerentities.PlayerEntity;
import game.gamemanaging.Player;
import game.utils.Position;

/**
 * @author D'Andréa William
 */
public class BuildingEntity extends PlayerEntity {

    /**
     * Building entity extended from PlayerEntity
     * @param position
     * @param owner
     * @param price
     */
    public BuildingEntity(Position position, Player owner, int price) {
        super(position, owner, price);
    }


}
