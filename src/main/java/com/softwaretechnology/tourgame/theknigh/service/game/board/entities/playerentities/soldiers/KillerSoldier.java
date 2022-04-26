package com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.soldiers;

import com.softwaretechnology.tourgame.theknigh.service.game.settings.soldiers.KillerSoldierSettings;
import com.softwaretechnology.tourgame.theknigh.service.game.utils.Position;


/**
 * @author D'Andréa William
 */
public class KillerSoldier extends Soldier {

    // Number of damages inflicted to enemies each turn
    private int damageToSoldier;
    private String name = "killer_soldier_entity";


    public KillerSoldier(Position position, double price, int healthPoint, int damageToSoldier, int numberOfMoveAtEachRound) {
        super(position, price, healthPoint, numberOfMoveAtEachRound);
        this.damageToSoldier = damageToSoldier;
    }

    public KillerSoldier(Position position, KillerSoldierSettings killerSoldierSettings) {
        super(position, killerSoldierSettings.getPrice(), killerSoldierSettings.getInitialHealthPoints(), killerSoldierSettings.getNumberOfMovesAtEachRound());
        this.damageToSoldier = killerSoldierSettings.getDamagesInflictedToOtherSoldiers();
    }

    public int getDamageToSoldier() {
        return damageToSoldier;
    }

    public void setDamageToSoldier(int damageToSoldier) {
        this.damageToSoldier = damageToSoldier;
    }

    @Override
    public String getName() {
        return name;
    }


}
