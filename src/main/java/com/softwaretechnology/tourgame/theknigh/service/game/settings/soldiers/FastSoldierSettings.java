package com.softwaretechnology.tourgame.theknigh.service.game.settings.soldiers;

/**
 * @author D'Andréa William
 */
public class FastSoldierSettings extends SoldierSettings {

    private final int numberOfTileHeCanJump;

    public FastSoldierSettings(int price, int initialHealthPoints, int numberOfMovesAtEachRound, int numberOfTileHeCanJump) {
        super(price, initialHealthPoints, numberOfMovesAtEachRound);
        this.numberOfTileHeCanJump = numberOfTileHeCanJump;
    }

    public int getNumberOfTileHeCanJump() {
        return numberOfTileHeCanJump;
    }


}
