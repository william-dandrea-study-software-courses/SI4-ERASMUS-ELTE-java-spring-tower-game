package game.board.entities.gameentities.castles;

import game.board.entities.gameentities.GameEntity;
import game.gamemanaging.Player;
import game.utils.Position;

/**
 * @author D'Andréa William
 *
 *
 * - Number of health points removed when a unit reaches the enemy castle
 * - Castel initial health points
 */
public class Castle extends GameEntity {

    private int healthPoint;
    private Player owner;

    public Castle(Position position, int healthPoint) {
        super(position);
        this.healthPoint = healthPoint;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public void gotInvaded(int damage){
        this.healthPoint -= damage;
    }

}
