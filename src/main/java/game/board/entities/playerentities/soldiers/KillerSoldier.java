package game.board.entities.playerentities.soldiers;

import game.utils.Position;

/**
 * @author D'Andréa William
 */
public class KillerSoldier extends Soldier {

    // Number of damages inflicted to enemies each turn
    private int damageToSoldier;

    /**
     * Killer soldier entity extended from Soldier
     * @param position
     * @param price
     * @param healthPoint
     * @param damageToSoldier
     * damage made to the other soldier per time
     */
    public KillerSoldier(Position position, String owner, double price, int healthPoint, int numberOfMoveAtEachRound, int damageToSoldier) {
        super(position, owner, price, healthPoint, numberOfMoveAtEachRound);
        this.damageToSoldier = damageToSoldier;
    }

    public int getDamageToSoldier() {
        return damageToSoldier;
    }

    public void setDamageToSoldier(int damageToSoldier) {
        this.damageToSoldier = damageToSoldier;
    }
}
