package game.board.entities.playerentities.building.towers;

import game.gamemanaging.Player;
import game.utils.Position;

/**
 * @author D'Andréa William
 */
public class NormalTower extends Tower {


    public NormalTower(Position position, Player owner, int price, int shootingRange, int simultaneousStrike, int damageToSoldiers) {
        super(position, owner, price, shootingRange, simultaneousStrike, damageToSoldiers);
    }

}
