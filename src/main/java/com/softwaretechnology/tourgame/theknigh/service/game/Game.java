package com.softwaretechnology.tourgame.theknigh.service.game;


import com.softwaretechnology.tourgame.theknigh.service.game.board.Board;
import com.softwaretechnology.tourgame.theknigh.service.game.board.Tile;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.gameentities.castles.Castle;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.gameentities.obstacles.Obstacle;
import com.softwaretechnology.tourgame.theknigh.service.game.gamemanaging.Player;
import com.softwaretechnology.tourgame.theknigh.service.game.settings.Settings;
import com.softwaretechnology.tourgame.theknigh.service.game.settings.game.ObstacleSettings;
import com.softwaretechnology.tourgame.theknigh.service.game.utils.BoardDimension;
import com.softwaretechnology.tourgame.theknigh.service.game.utils.Position;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * @author D'Andréa William
 */

@Data
public class Game {

    private Settings settings;
    private Board board;
    private Player player1;
    private Player player2;






    public Game(Settings settings) {
        this.settings = settings;
        this.board = new Board(new BoardDimension(this.settings.getGeneralSettings().getLengthBoard(), this.settings.getGeneralSettings().getWidthBoard()), this.setupObstacles());

        this.launchGame();
    }

    public void launchGame() {

        this.setupTheCastle();


        System.out.println(board.getTiles());
    }





    private void setupTheCastle() {

        Random rand = new Random();

        int xPositionPlayer1Castle = rand.nextInt(board.getDimension().getWidth() - 1);
        int yPositionPlayer1Castle = 0;

        int xPositionPlayer2Castle = rand.nextInt(board.getDimension().getWidth() - 1);
        int yPositionPlayer2Castle = board.getDimension().getLength() - 1;

        Position positionCastlePlayer1 = new Position(xPositionPlayer1Castle, yPositionPlayer1Castle);
        Optional<Tile> opTilePlayer1 = this.board.tileAtPosition(positionCastlePlayer1);
        Position positionCastlePlayer2 = new Position(xPositionPlayer2Castle, yPositionPlayer2Castle);
        Optional<Tile> opTilePlayer2 = this.board.tileAtPosition(positionCastlePlayer2);

        if (opTilePlayer1.isPresent()) {
            Tile tile = opTilePlayer1.get();
            Castle castle1 = new Castle(positionCastlePlayer1, this.settings.getCastelSettings().getInitialHealthPoints());
            // tile.addEntityOnTheTile(castle1);
            this.player1 = new Player(1, this.settings.getGoldSettings().getInitialAmountOfGold(), castle1);
        }

        if (opTilePlayer2.isPresent()) {
            Tile tile = opTilePlayer2.get();
            Castle castle2 = new Castle(positionCastlePlayer2, this.settings.getCastelSettings().getInitialHealthPoints());
            // tile.addEntityOnTheTile(castle2);
            this.player2 = new Player(1, this.settings.getGoldSettings().getInitialAmountOfGold(), castle2);
        }
    }



    private List<Obstacle> setupObstacles() {

        ObstacleSettings obstacleSettings = this.settings.getObstacleSettings();
        int numberOfObstacles = obstacleSettings.getNumberOfObstacles();
        int radiusOfObstacles = obstacleSettings.getRadiusOfObstacles();



        for (int currentObstacle = 0; currentObstacle <= numberOfObstacles; currentObstacle++) {

        }



        return new ArrayList<>();
    }











    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;

        System.out.println(this.settings);
    }
}
