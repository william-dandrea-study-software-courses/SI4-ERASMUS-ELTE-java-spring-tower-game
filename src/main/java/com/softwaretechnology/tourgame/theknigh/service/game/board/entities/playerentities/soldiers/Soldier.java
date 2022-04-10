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
}
