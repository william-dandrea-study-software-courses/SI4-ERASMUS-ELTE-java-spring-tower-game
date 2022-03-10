package game.settings.towers;

/**
 * @author D'Andréa William
 */
public class SniperTowerSettings extends TowerSettings {

    private final int numberOfSimultaneousStrikes;

    public SniperTowerSettings(int price, int shootingRange, int numberOfSimultaneousStrikes) {
        super(price, shootingRange);
        this.numberOfSimultaneousStrikes = numberOfSimultaneousStrikes;
    }

    public int getNumberOfSimultaneousStrikes() {
        return numberOfSimultaneousStrikes;
    }
}
