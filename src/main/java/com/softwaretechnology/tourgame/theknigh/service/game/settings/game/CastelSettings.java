package com.softwaretechnology.tourgame.theknigh.service.game.settings.game;

/**
 * @author D'Andréa William
 */
public class CastelSettings {

    // Castel initial health points
    private final int initialHealthPoints;

    // Number of health points removed when a unit reaches the enemy castle
    private final int healthPointsRemovedWhenSoldierReachCastle;

    public CastelSettings(int initialHealthPoints, int healthPointsRemovedWhenSoldierReachCastle) {
        this.initialHealthPoints = initialHealthPoints;
        this.healthPointsRemovedWhenSoldierReachCastle = healthPointsRemovedWhenSoldierReachCastle;
    }

    public int getInitialHealthPoints() {
        return initialHealthPoints;
    }

    public int getHealthPointsRemovedWhenSoldierReachCastle() {
        return healthPointsRemovedWhenSoldierReachCastle;
    }
}
