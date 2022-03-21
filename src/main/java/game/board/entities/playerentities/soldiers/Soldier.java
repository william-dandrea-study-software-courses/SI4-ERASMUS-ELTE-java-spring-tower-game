package game.board.entities.playerentities.soldiers;

import game.board.entities.playerentities.PlayerEntity;
import game.utils.Position;

/**
 * @author D'Andréa William
 */
public class Soldier extends PlayerEntity {

    private int healthPoint;
    private int numberOfMoveAtEachRound;
    private int damageToCastle;


    public Soldier(Position position, double price, int healthPoint, int damageToCastle) {
        super(position, price);
        this.healthPoint = healthPoint;
        this.damageToCastle = damageToCastle;
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

    public int getDamageToCastle() { return damageToCastle; }

    public void setNumberOfMoveAtEachRound(int numberOfMoveAtEachRound) {
        this.numberOfMoveAtEachRound = numberOfMoveAtEachRound;
    }
}
