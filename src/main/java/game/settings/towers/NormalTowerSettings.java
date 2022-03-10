package game.settings.towers;

/**
 * @author D'Andréa William
 */
public class NormalTowerSettings extends TowerSettings {

    private final int numberOfSimultaneousStrikes;

    public NormalTowerSettings(int price, int shootingRange, int numberOfSimultaneousStrikes) {
        super(price, shootingRange);
        this.numberOfSimultaneousStrikes = numberOfSimultaneousStrikes;
    }

    public int getNumberOfSimultaneousStrikes() {
        return numberOfSimultaneousStrikes;
    }
}
