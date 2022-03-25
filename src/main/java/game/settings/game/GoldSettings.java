package game.settings.game;

/**
 * @author D'Andréa William
 */
public class GoldSettings {


    private final int initialAmountOfGold;
    private final int addedGoldAtEachRound;
    private final int priceOfGoldMine;
    private final int addedGoldAtEachRoundWithGoldMine;

    /**
     * The gold related settings
     * @param initialAmountOfGold
     * The initial amount of gold of each player
     * @param addedGoldAtEachRound
     * Amount of initial gold in each round
     * @param priceOfGoldMine
     * The price of a goldmine
     * @param addedGoldAtEachRoundWithGoldMine
     * Amount of gold distributed at the beginning of each round for a gold mine
     */
    public GoldSettings(int initialAmountOfGold, int addedGoldAtEachRound, int priceOfGoldMine, int addedGoldAtEachRoundWithGoldMine) {
        this.initialAmountOfGold = initialAmountOfGold;
        this.addedGoldAtEachRound = addedGoldAtEachRound;
        this.priceOfGoldMine = priceOfGoldMine;
        this.addedGoldAtEachRoundWithGoldMine = addedGoldAtEachRoundWithGoldMine;
    }

    public int getInitialAmountOfGold() {
        return initialAmountOfGold;
    }

    public int getAddedGoldAtEachRound() {
        return addedGoldAtEachRound;
    }

    public int getPriceOfGoldMine() {
        return priceOfGoldMine;
    }

    public int getAddedGoldAtEachRoundWithGoldMine() {
        return addedGoldAtEachRoundWithGoldMine;
    }
}
