package game.settings.towers;

/**
 * @author D'Andréa William
 */
public class TowerSettings {

    private final int price;
    private final int shootingRange;
    private final int damageToSoldier;

    /**
     * General tower settings
     * @param price
     * Changing the price of the tower
     * @param shootingRange
     * Changing the shooting range
     * @param damageToSoldier
     * Damage to one enemy
     */
    public TowerSettings(int price, int shootingRange, int damageToSoldier) {
        this.price = price;
        this.shootingRange = shootingRange;
        this.damageToSoldier = damageToSoldier;
    }

    public int getPrice() {
        return price;
    }

    public int getShootingRange() {
        return shootingRange;
    }

    public int getDamageToSoldier() { return damageToSoldier; }

}
