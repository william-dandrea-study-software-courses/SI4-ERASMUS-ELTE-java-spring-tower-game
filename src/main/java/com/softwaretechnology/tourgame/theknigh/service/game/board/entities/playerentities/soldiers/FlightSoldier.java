package com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.soldiers;

import com.softwaretechnology.tourgame.theknigh.service.game.utils.Position;


/**
 * @author D'Andréa William
 */
public class FlightSoldier extends Soldier {

    private String name = "flight_soldier_entity";

    public FlightSoldier(Position position, double price, int healthPoint) {
        super(position, price, healthPoint);
    }

    @Override
    public String getName() {
        return name;
    }
}
