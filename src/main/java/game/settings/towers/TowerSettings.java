package game.settings.towers;

/**
 * @author D'Andréa William
 */
public class TowerSettings {

    private final int price;
    private final int shootingRange;
    private final int damageToSoldier;
    private final int upgradeCost;

    /**
     * General tower settings
     * @param price
     * Changing the price of the tower
     * @param shootingRange
     * Changing the shooting range
     * @param damageToSoldier
     * Damage to one enemy
     * @param upgradeCost
     */
    public TowerSettings(int price, int shootingRange, int damageToSoldier, int upgradeCost) {
        this.price = price;
        this.shootingRange = shootingRange;
        this.damageToSoldier = damageToSoldier;
        this.upgradeCost = upgradeCost;
    }

    public int getPrice() {
        return price;
    }

    public int getShootingRange() {
        return shootingRange;
    }

    public int getDamageToSoldier() { return damageToSoldier; }

    public int getUpgradeCost() { return upgradeCost; }

}
