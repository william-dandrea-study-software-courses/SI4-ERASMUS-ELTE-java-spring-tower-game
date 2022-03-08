package game.board.entities.gameentities.castles;

import game.board.entities.Entity;
import game.board.entities.gameentities.GameEntity;
import game.utils.Position;

/**
 * @author D'Andréa William
 *
 *
 * - Number of health points removed when a unit reaches the enemy castle
 * - Castel initial health points
 */
public class Castle extends GameEntity {


    public Castle(Position position) {
        super(position);
    }
}
