package game.board.entities.playerentities;

import game.board.entities.Entity;
import game.utils.Position;

/**
 * @author D'Andréa William
 */
public class PlayerEntity extends Entity {

    private double price;

    public PlayerEntity(Position position, double price) {
        super(position);
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
