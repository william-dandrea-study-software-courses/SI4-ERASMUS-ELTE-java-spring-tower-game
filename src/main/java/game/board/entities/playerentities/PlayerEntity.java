package game.board.entities.playerentities;

import game.board.entities.Entity;
import game.utils.Position;

/**
 * @author D'Andréa William
 */
public class PlayerEntity extends Entity {

    private double price;

    /**
     * PlayerEntity extends from Entity initiation
     * @param position
     * position of the entity
     * @param owner
     * owner of the entity
     * @param price
     * price of the entity to build & make
     */
    public PlayerEntity(Position position, String owner, double price) {
        super(position, owner);
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
