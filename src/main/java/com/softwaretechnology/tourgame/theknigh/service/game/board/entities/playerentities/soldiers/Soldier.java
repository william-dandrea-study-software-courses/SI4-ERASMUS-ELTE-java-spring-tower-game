package com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.soldiers;

import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.PlayerEntity;
import com.softwaretechnology.tourgame.theknigh.service.game.utils.Position;


/**
 * @author D'Andréa William
 */
public class Soldier extends PlayerEntity {

    private int healthPoint;
    private int numberOfMoveAtEachRound;
    private String name = "soldier_entity";


    public Soldier(Position position, double price, int healthPoint, int numberOfMoveAtEachRound) {
        super(position, price);
        this.healthPoint = healthPoint;
        this.numberOfMoveAtEachRound = numberOfMoveAtEachRound;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public void removeHealthPoints(int healthPoint) {
        this.healthPoint -= healthPoint;
    }

    public int getNumberOfMoveAtEachRound() {
        return numberOfMoveAtEachRound;
    }

    public void setNumberOfMoveAtEachRound(int numberOfMoveAtEachRound) {
        this.numberOfMoveAtEachRound = numberOfMoveAtEachRound;
    }

    @Override
    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "Soldier{" +
                "healthPoint=" + healthPoint +
                ", numberOfMoveAtEachRound=" + numberOfMoveAtEachRound +
                ", name='" + name + '\'' +
                ", position='" + getPosition() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Soldier soldier = (Soldier) o;

        if (healthPoint != soldier.healthPoint) return false;
        if (numberOfMoveAtEachRound != soldier.numberOfMoveAtEachRound) return false;
        return name != null ? name.equals(soldier.name) : soldier.name == null;
    }

    @Override
    public int hashCode() {
        int result = healthPoint;
        result = 31 * result + numberOfMoveAtEachRound;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
