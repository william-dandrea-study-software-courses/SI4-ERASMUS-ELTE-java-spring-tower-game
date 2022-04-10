package game.settings.towers;

/**
 * @author D'Andréa William
 */
public class SniperTowerSettings extends TowerSettings {

    private final int numberOfSimultaneousStrikes;

    /**
     * Tower subtype Sniper tower settings
     * @param price
     * @param shootingRange
     * @param numberOfSimultaneousStrikes
     * Changing the number of simultaneous strikes
     * @param upgradeCost;
     */
    public SniperTowerSettings(int price, int shootingRange, int numberOfSimultaneousStrikes, int damageToSoldier, int upgradeCost) {
        super(price, shootingRange, damageToSoldier, upgradeCost);
        this.numberOfSimultaneousStrikes = numberOfSimultaneousStrikes;
    }

    public int getNumberOfSimultaneousStrikes() {
        return numberOfSimultaneousStrikes;
    }


}
