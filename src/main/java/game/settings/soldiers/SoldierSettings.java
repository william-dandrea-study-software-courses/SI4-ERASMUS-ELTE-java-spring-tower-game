package game.settings.soldiers;

/**
 * @author D'Andréa William
 */
public class SoldierSettings {

    private final int price;
    private final int initialHealthPoints;
    private final int numberOfMovesAtEachRound;
    private int healthPoints;
    private int killRewards;
    /**
     * The general soldier settings of the game
     * @param price
     * The price of soldier
     * @param initialHealthPoints
     * The initial health points of one soldier
     * @param numberOfMovesAtEachRound
     * The move the soldier can make each round
     */
    public SoldierSettings(int price, int initialHealthPoints, int numberOfMovesAtEachRound, int killRewards) {
        this.price = price;
        this.initialHealthPoints = initialHealthPoints;
        this.healthPoints = initialHealthPoints;
        this.numberOfMovesAtEachRound = numberOfMovesAtEachRound;
        this.killRewards = killRewards;
    }

    public int getPrice() {
        return price;
    }

    public int getInitialHealthPoints() {
        return initialHealthPoints;
    }

    public int getNumberOfMovesAtEachRound() {
        return numberOfMovesAtEachRound;
    }

    public int getKillRewards() { return killRewards; }
}


