package game.settings.soldiers;

/**
 * @author D'Andréa William
 */
public class SoldierSettings {

    private final int price;
    private final int initialHealthPoints;
    private final int numberOfMovesAtEachRound;
    private int healthPoints;
    /**
     * The general soldier settings of the game
     * @param price
     * The price of soldier
     * @param initialHealthPoints
     * The initial health points of one soldier
     * @param numberOfMovesAtEachRound
     * The move the soldier can make each round
     */
    public SoldierSettings(int price, int initialHealthPoints, int numberOfMovesAtEachRound) {
        this.price = price;
        this.initialHealthPoints = initialHealthPoints;
        this.healthPoints = initialHealthPoints;
        this.numberOfMovesAtEachRound = numberOfMovesAtEachRound;
        
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

}


