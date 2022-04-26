package com.softwaretechnology.tourgame.theknigh.service.game.settings.soldiers;

/**
 * @author D'Andréa William
 */
public class SoldierSettings {

    private final int price;
    private final int initialHealthPoints;
    private final int numberOfMovesAtEachRound;

    public SoldierSettings(int price, int initialHealthPoints, int numberOfMovesAtEachRound) {
        this.price = price;
        this.initialHealthPoints = initialHealthPoints;
        this.numberOfMovesAtEachRound = numberOfMovesAtEachRound;
    }

    public int getPrice() {
        return price;
    }

    public int getInitialHealthPoints() {
        return initialHealthPoints;
    }

    public int getNumberOfMovesAtEachRound() {
        return numberOfMovesAtEachRound;
    }
}
