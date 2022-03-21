package game.gamemanaging;

import game.board.entities.gameentities.castles.Castle;
/**
 * @author D'Andréa William
 */
public class Player {

    private String name;
    private Castle castle;
    private int currentGold;
    private int goldGainedPerRound;

    public Player(String name, Castle castle, int currentGold, int goldGainedPerRound){
        this.name = name;
        this.castle = castle;
        this.currentGold = currentGold;
        this.goldGainedPerRound = goldGainedPerRound;
    }

    public String getName() { return name; }

    public Castle getCastle() { return castle; }

    public int getCurrentGold() { return currentGold; }

    public int getGoldGainedPerRound() { return goldGainedPerRound; }

    public Player getPlayer() { return this; }

    public void addGold(int gold) {
        currentGold += gold;
    }

}
