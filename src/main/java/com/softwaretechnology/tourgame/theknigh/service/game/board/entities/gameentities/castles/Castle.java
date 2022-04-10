package com.softwaretechnology.tourgame.theknigh.service.game.board.entities.gameentities.castles;

import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.gameentities.GameEntity;
import com.softwaretechnology.tourgame.theknigh.service.game.utils.Position;

/**
 * @author D'Andréa William
 *
 *
 * - Number of health points removed when a unit reaches the enemy castle
 * - Castel initial health points
 */
public class Castle extends GameEntity {

    private int healthPoint;
    private String name = "castle_entity";


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

    @Override
    public String getName() {
        return name;
    }

    public int removeHealthPoint(int healthPointsRemovedWhenSoldierReachCastle) {

        this.healthPoint = this.healthPoint - healthPointsRemovedWhenSoldierReachCastle;
        return this.healthPoint;

    }
}
