package com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.soldiers;

import com.softwaretechnology.tourgame.theknigh.service.game.utils.Position;

/**
 * @author D'Andréa William
 */
public class FastSoldier extends Soldier {

    private int numberOfTileHeCanJump;

    public FastSoldier(Position position, double price, int healthPoint, int numberOfTileHeCanJump) {
        super(position, price, healthPoint);
        this.numberOfTileHeCanJump = numberOfTileHeCanJump;
    }

    public int getNumberOfTileHeCanJump() {
        return numberOfTileHeCanJump;
    }

    public void setNumberOfTileHeCanJump(int numberOfTileHeCanJump) {
        this.numberOfTileHeCanJump = numberOfTileHeCanJump;
    }
}
