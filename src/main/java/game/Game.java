package game;

import game.board.Board;
import game.board.entities.Entity;
import game.board.entities.playerentities.building.goldmines.GoldMine;
import game.board.entities.playerentities.soldiers.Soldier;
import game.gamemanaging.Player;
import game.settings.Settings;
import game.utils.BoardDimension;
import game.utils.Position;

/**
 * @author D'Andréa William
 */
public class Game {


    private final Settings settings;
    private Board board;
    private Player player1;
    private Player player2;

    /**
     * @author Tian Zhenman
     * Initiate the game with the board, two player and the castle of each other
     * @param settings
     * The settings getting from before the beginning of the game
     */
    public Game(Settings settings) {
        this.settings = settings;

        /** Initiate board */
        BoardDimension boardDimension = new BoardDimension(settings.getGeneralSettings().getLengthBoard(), settings.getGeneralSettings().getWidthBoard());
        int numberOfObstacle = settings.getObstacleSettings().getNumberOfObstacles();
        int castleInitialHealthPoint = settings.getCastelSettings().getInitialHealthPoints();
        this.board = new Board(boardDimension, castleInitialHealthPoint, numberOfObstacle);

        /** Initiate Player */
        int initialGold = settings.getGoldSettings().getInitialAmountOfGold();
        int goldGainPerRound = settings.getGoldSettings().getAddedGoldAtEachRound();
        Player player1 = new Player("Player1", board.getCastle1(), initialGold, goldGainPerRound);
        Player player2 = new Player("Player2", board.getCastle2(), initialGold, goldGainPerRound);
        board.getCastle1().setOwner(player1);
        board.getCastle2().setOwner(player2);
    }

    /**
     * @author Tian Zhenman
     * For button create goldmine, with the in parameter position(mouse click)
     * and player (decided by turn)
     * @param position
     * @param player
     */
    public void createGoldMine(Position position, Player player){
        int goldMinePrice = settings.getGoldSettings().getPriceOfGoldMine();
        int increaseGoldDistributedAtEachRound = settings.getGoldSettings().getAddedGoldAtEachRoundWithGoldMine();
        GoldMine goldMine = new GoldMine(position, player, goldMinePrice, increaseGoldDistributedAtEachRound);
        board.getTile(position).addEntityOnTheTile(goldMine);
        /** this player's each round's gold gets increased */
        player.setGoldGainedPerRound(player.getGoldGainedPerRound() + increaseGoldDistributedAtEachRound);
        /** this player's gold decreased by the amount of goldmine needed */
        player.reduceGold(goldMinePrice);
    }


    /**
     * @author Tian Zhenman
     * Check if the game is over by checking the health point of castle,
     * The System.out.print is just for demostration.
     */
    public void checkIfGameOver(){
        if(board.getCastle1().getHealthPoint() == 0 && board.getCastle2().getHealthPoint() == 0){
            System.out.println("Draw!");
        }else if(board.getCastle1().getHealthPoint() == 0){
            System.out.println("Player 2 wins!");
        }else if(board.getCastle2().getHealthPoint() == 0){
            System.out.println("Player 1 wins!");
        }
    }

    /**
     * @author Tian Zhenman
     * for the game check after each round if any soldier is dead or arrive enemy castle,
     * if so then remove them from the board.
     * And add responding gold for two players.
     */
    public void checkAfterRound(){
        for (int i=0;i<=board.getDimension().getLength();i++){
            for (int j=0;j<=board.getDimension().getWidth();j++){
                for (Entity entity: board.getTile(i, j).getEntitiesOnTheTile()) {
                    if (entity instanceof Soldier){
                        if(((Soldier) entity).getHealthPoint() == 0){
                            /** Add killRewards to the opposite */
                            if(entity.getOwner() == player1){
                                player2.addGold(((Soldier) entity).getKillRewards());
                            } else {player1.addGold(((Soldier) entity).getKillRewards());}
                            /** remove form board */
                            board.getTile(i, j).removeEntityOnTheTile(entity);
                        }else if(((Soldier) entity).arriveCastle()){
                            board.getTile(i, j).removeEntityOnTheTile(entity);
                        }
                    }
                }
            }
        }
        player1.addGold(player1.getGoldGainedPerRound());
        player2.addGold(player2.getGoldGainedPerRound());
    }

    protected void launchGame() {
        System.out.println(this.settings.getCastelSettings());
    }




}
