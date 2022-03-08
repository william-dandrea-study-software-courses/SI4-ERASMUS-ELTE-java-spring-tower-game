package game.settings.game;

/**
 * @author D'Andréa William
 */
public class GoldSettings {


    private final int initialAmountOfGold;

    // Amount of initial gold in each round
    private final int addedGoldAtEachRound;

    // Price of a gold mine
    private final int priceOfGoldMine;

    // Amount of gold distributed at the beginning of each round for a gold mine
    private final int addedGoldAtEachRoundWithGoldMine;

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
