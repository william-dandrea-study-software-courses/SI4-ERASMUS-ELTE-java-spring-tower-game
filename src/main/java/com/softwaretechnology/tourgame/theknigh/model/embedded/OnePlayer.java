package com.softwaretechnology.tourgame.theknigh.model.embedded;

import lombok.Data;

/**
 * @author D'Andréa William
 */

@Data
public class OnePlayer {

    private int playerNumber;

    public OnePlayer(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }
}
