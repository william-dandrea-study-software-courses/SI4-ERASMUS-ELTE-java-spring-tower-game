package com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities;


import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.Entity;
import com.softwaretechnology.tourgame.theknigh.service.game.utils.Position;

/**
 * @author D'Andréa William
 */
public class PlayerEntity extends Entity {

    private double price;

    @Override
    public String getName() {
        return name;
    }

    private String name = "player_entity";

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
