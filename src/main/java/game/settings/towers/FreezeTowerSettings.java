package game.settings.towers;

/**
 * @author D'Andréa William
 */
public class FreezeTowerSettings extends TowerSettings {

    // Number of turns where the enemies are freezed
    private final int numberOfRoundsWhereTheSoldierInTheAreaAreFreeze;

    public FreezeTowerSettings(int price, int shootingRange, int numberOfRoundsWhereTheSoldierInTheAreaAreFreeze) {
        super(price, shootingRange);
        this.numberOfRoundsWhereTheSoldierInTheAreaAreFreeze = numberOfRoundsWhereTheSoldierInTheAreaAreFreeze;
    }

    public int getNumberOfRoundsWhereTheSoldierInTheAreaAreFreeze() {
        return numberOfRoundsWhereTheSoldierInTheAreaAreFreeze;
    }
}
