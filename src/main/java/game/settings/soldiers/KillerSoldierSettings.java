package game.settings.soldiers;

/**
 * @author D'Andréa William
 */
public class KillerSoldierSettings  extends SoldierSettings {

    private final int damagesInflictedToOtherSoldiers;

    /**
     * The killer soldier settings
     * @param price
     * @param initialHealthPoints
     * @param numberOfMovesAtEachRound
     * @param damagesInflictedToOtherSoldiers
     * the damage the killer soldier could make to other units
     */
    public KillerSoldierSettings(int price, int initialHealthPoints, int numberOfMovesAtEachRound, int damagesInflictedToOtherSoldiers) {
        super(price, initialHealthPoints, numberOfMovesAtEachRound);
        this.damagesInflictedToOtherSoldiers = damagesInflictedToOtherSoldiers;
    }

    public int getDamagesInflictedToOtherSoldiers() {
        return damagesInflictedToOtherSoldiers;
    }
}
