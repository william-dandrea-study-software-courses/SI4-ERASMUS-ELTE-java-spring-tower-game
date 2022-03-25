package game.settings.game;

/**
 * @author D'Andréa William
 */
public class MonsterSettings {

    private final int poppingMonsterAtEachNRounds;
    private final int roundsFrequencyOfPopping;

    /**
     * the monster settings
     * @param poppingMonsterAtEachNRounds
     * The number of monsters at a time on the board
     * @param roundsFrequencyOfPopping
     * Turn interval for monsters to arrive
     */
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
