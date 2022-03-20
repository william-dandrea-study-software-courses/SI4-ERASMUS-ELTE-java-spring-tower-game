package game.gamemanaging;

import game.board.entities.gameentities.castles.Castle;
/**
 * @author D'Andréa William
 */
public class Player {

    private char[] name;
    private Castle castle;
    private int currentGold;
    private int goldGainedPerRound;

    public Player(char[] name, Castle castle, int currentGold, int goldGainedPerRound){
        this.name = name;
        this.castle = castle;
        this.currentGold = currentGold;
        this.goldGainedPerRound = goldGainedPerRound;
    }

}
