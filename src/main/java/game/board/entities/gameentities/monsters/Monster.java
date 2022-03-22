package game.board.entities.gameentities.monsters;

import game.board.entities.Entity;
import game.board.entities.gameentities.GameEntity;
import game.utils.Position;

/**
 * @author D'Andréa William
 *
 * - Turn interval for monsters to arrive
 * - Number of monsters on the map
 */
public class Monster extends GameEntity {

    private int damage;
    private int speed;
    public Monster(Position position, int damage, int speed) {

        super(position);
        this.damage = damage;
        this.speed = speed;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
