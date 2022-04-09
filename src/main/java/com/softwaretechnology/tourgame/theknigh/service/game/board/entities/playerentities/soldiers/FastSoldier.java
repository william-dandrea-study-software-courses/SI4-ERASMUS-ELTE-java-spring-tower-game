package com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.soldiers;

import com.softwaretechnology.tourgame.theknigh.service.game.settings.soldiers.FastSoldierSettings;
import com.softwaretechnology.tourgame.theknigh.service.game.utils.Position;

/**
 * @author D'Andréa William
 */
public class FastSoldier extends Soldier {

    private int numberOfTileHeCanJump;
    private String name = "fast_soldier_entity";

    public FastSoldier(Position position, double price, int healthPoint, int numberOfTileHeCanJump) {
        super(position, price, healthPoint);
        this.numberOfTileHeCanJump = numberOfTileHeCanJump;
    }

    public FastSoldier(Position position, FastSoldierSettings fastSoldierSettings) {
        super(position, fastSoldierSettings.getPrice(), fastSoldierSettings.getInitialHealthPoints());
        this.numberOfTileHeCanJump = fastSoldierSettings.getNumberOfTileHeCanJump();
    }

    public int getNumberOfTileHeCanJump() {
        return numberOfTileHeCanJump;
    }

    public void setNumberOfTileHeCanJump(int numberOfTileHeCanJump) {
        this.numberOfTileHeCanJump = numberOfTileHeCanJump;
    }

    @Override
    public String getName() {
        return name;
    }
}
