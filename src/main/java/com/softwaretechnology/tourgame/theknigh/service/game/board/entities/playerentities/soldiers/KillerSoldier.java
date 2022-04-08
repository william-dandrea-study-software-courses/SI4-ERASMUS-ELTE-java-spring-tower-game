package com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.soldiers;

import com.softwaretechnology.tourgame.theknigh.service.game.utils.Position;


/**
 * @author D'Andréa William
 */
public class KillerSoldier extends Soldier {

    // Number of damages inflicted to enemies each turn
    private int damageToSoldier;


    public KillerSoldier(Position position, double price, int healthPoint, int damageToSoldier) {
        super(position, price, healthPoint);
        this.damageToSoldier = damageToSoldier;
    }

    public int getDamageToSoldier() {
        return damageToSoldier;
    }

    public void setDamageToSoldier(int damageToSoldier) {
        this.damageToSoldier = damageToSoldier;
    }
}
