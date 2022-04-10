package game.board.entities.playerentities.soldiers;

import game.gamemanaging.Player;
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
     * @param owner
     * @param price
     * @param healthPoint
     * @param numberOfMoveAtEachRound
     * @param killRewards
     * @param damageToSoldier
     * damage made to the other soldier per time
     */
    public KillerSoldier(Position position, Player owner, int price, int healthPoint, int numberOfMoveAtEachRound, int killRewards, int damageToSoldier) {
        super(position, owner, price, healthPoint, numberOfMoveAtEachRound, killRewards);
        this.damageToSoldier = damageToSoldier;
    }


    public int getDamageToSoldier() {
        return damageToSoldier;
    }

    public void setDamageToSoldier(int damageToSoldier) {
        this.damageToSoldier = damageToSoldier;
    }
}
