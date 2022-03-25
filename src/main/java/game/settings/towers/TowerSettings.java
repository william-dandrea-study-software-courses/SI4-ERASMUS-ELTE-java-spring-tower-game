package game.settings.towers;

/**
 * @author D'Andréa William
 */
public class TowerSettings {

    private final int price;
    private final int shootingRange;

    /**
     * General tower settings
     * @param price
     * Changing the price of the tower
     * @param shootingRange
     * Changing the shooting range
     */
    public TowerSettings(int price, int shootingRange) {
        this.price = price;
        this.shootingRange = shootingRange;
    }

    public int getPrice() {
        return price;
    }

    public int getShootingRange() {
        return shootingRange;
    }
}
