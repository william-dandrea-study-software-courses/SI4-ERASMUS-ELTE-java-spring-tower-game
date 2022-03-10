package game.settings.soldiers;

/**
 * @author D'Andréa William
 */
public class KillerSoldierSettings  extends SoldierSettings {

    private final int damagesInflictedToOtherSoldiers;


    public KillerSoldierSettings(int price, int initialHealthPoints, int numberOfMovesAtEachRound, int damagesInflictedToOtherSoldiers) {
        super(price, initialHealthPoints, numberOfMovesAtEachRound);
        this.damagesInflictedToOtherSoldiers = damagesInflictedToOtherSoldiers;
    }

    public int getDamagesInflictedToOtherSoldiers() {
        return damagesInflictedToOtherSoldiers;
    }
}
