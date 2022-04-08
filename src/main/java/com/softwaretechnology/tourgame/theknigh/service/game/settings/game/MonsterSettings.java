package com.softwaretechnology.tourgame.theknigh.service.game.settings.game;

/**
 * @author D'Andréa William
 */
public class MonsterSettings {

    // Number of monsters on the map
    private final int poppingMonsterAtEachNRounds;

    // Turn interval for monsters to arrive
    private final int roundsFrequencyOfPopping;

    public MonsterSettings(int poppingMonsterAtEachNRounds, int roundsFrequencyOfPopping) {
        this.poppingMonsterAtEachNRounds = poppingMonsterAtEachNRounds;
        this.roundsFrequencyOfPopping = roundsFrequencyOfPopping;
    }

    public int getPoppingMonsterAtEachNRounds() {
        return poppingMonsterAtEachNRounds;
    }

    public int getRoundsFrequencyOfPopping() {
        return roundsFrequencyOfPopping;
    }
}
