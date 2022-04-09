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

    /**
     * The player class
     * @param name
     * Name of the player
     * @param castle
     * The castle belongs to the player
     * @param currentGold
     * The current gold holding by the player
     * @param goldGainedPerRound
     * The amount of gold gained per round by the player
     */
    public Player(String name, Castle castle, int currentGold, int goldGainedPerRound){
        this.name = name;
        this.castle = castle;
        this.currentGold = currentGold;
        this.goldGainedPerRound = goldGainedPerRound;
    }

    public String getName() { return name; }

    public Castle getCastle() { return castle; }

    public int getCurrentGold() { return currentGold; }

    public void setCurrentGold(int currentGold) {
        this.currentGold = currentGold;
    }

    public int getGoldGainedPerRound() { return goldGainedPerRound; }

    public void setGoldGainedPerRound(int goldGainedPerRound) {
        this.goldGainedPerRound = goldGainedPerRound;
    }

    public Player getPlayer() { return this; }

    public void addGold(int gold) {
        currentGold += gold;
    }
    public void reduceGold(int gold) { currentGold -= gold; }

}
