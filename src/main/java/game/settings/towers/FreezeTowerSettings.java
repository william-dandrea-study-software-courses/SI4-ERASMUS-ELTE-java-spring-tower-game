package game.settings.towers;

/**
 * @author D'Andréa William
 */
public class FreezeTowerSettings extends TowerSettings {

    private final int numberOfSimultaneousStrikes;
    // Number of turns where the enemies are freezed
    private final int numberOfRoundsWhereTheSoldierInTheAreaAreFreeze;

    /**
     * Tower subtype Freeze tower settings
     * @param price
     * @param shootingRange
     * @param numberOfRoundsWhereTheSoldierInTheAreaAreFreeze
     * The frozen number of rounds of the soldier in the range
     */
    public FreezeTowerSettings(int price, int shootingRange, int numberOfSimultaneousStrikes, int numberOfRoundsWhereTheSoldierInTheAreaAreFreeze, int damageToSoldier) {
        super(price, shootingRange, damageToSoldier);
        this.numberOfSimultaneousStrikes = numberOfSimultaneousStrikes;
        this.numberOfRoundsWhereTheSoldierInTheAreaAreFreeze = numberOfRoundsWhereTheSoldierInTheAreaAreFreeze;
    }

    public int getNumberOfSimultaneousStrikes() {
        return numberOfSimultaneousStrikes;
    }

    public int getNumberOfRoundsWhereTheSoldierInTheAreaAreFreeze() {
        return numberOfRoundsWhereTheSoldierInTheAreaAreFreeze;
    }
}
