package com.softwaretechnology.tourgame.theknigh.service.game;


import com.softwaretechnology.tourgame.theknigh.service.game.board.Board;
import com.softwaretechnology.tourgame.theknigh.service.game.board.Tile;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.Entity;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.gameentities.castles.Castle;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.gameentities.obstacles.Obstacle;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.PlayerEntity;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.building.goldmines.GoldMine;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.building.towers.FreezeTower;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.building.towers.NormalTower;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.building.towers.SniperTower;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.soldiers.FastSoldier;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.soldiers.FlightSoldier;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.soldiers.KillerSoldier;
import com.softwaretechnology.tourgame.theknigh.service.game.gamemanaging.Player;
import com.softwaretechnology.tourgame.theknigh.service.game.settings.Settings;
import com.softwaretechnology.tourgame.theknigh.service.game.settings.game.GoldSettings;
import com.softwaretechnology.tourgame.theknigh.service.game.settings.game.ObstacleSettings;
import com.softwaretechnology.tourgame.theknigh.service.game.utils.BoardDimension;
import com.softwaretechnology.tourgame.theknigh.service.game.utils.Position;
import com.softwaretechnology.tourgame.theknigh.service.game.utils.Radius;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

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
        this.setupTheCastle();


        this.launchGame();
    }

    public void launchGame() {

        // We initialize, the player 1 start first
        this.player1.setPlaying(true);
        this.player2.setPlaying(false);
    }







    public Boolean addKillerUnit(int indexPlayer) {
        Player player = null;
        if (indexPlayer == 1) {
            player = player1;
        } else {
            player = player2;
        }
        if (player.getCurrentGold() >= this.settings.getKillerSoldierSettings().getPrice()) {
            player.getEntities().add(new KillerSoldier(player.getCastle().getPosition(), this.settings.getKillerSoldierSettings()));
            player.decreaseCurrentGold(this.settings.getKillerSoldierSettings().getPrice());
            return true;
        }
        return false;
    }

    public Boolean addFastUnit(int indexPlayer) {
        Player player = null;
        if (indexPlayer == 1) {
            player = player1;
        } else {
            player = player2;
        }
        if (player.getCurrentGold() >= this.settings.getFastSoldierSettings().getPrice()) {
            player.getEntities().add(new FastSoldier(player.getCastle().getPosition(), this.settings.getFastSoldierSettings()));
            player.decreaseCurrentGold(this.settings.getFastSoldierSettings().getPrice());
            return true;
        }
        return false;

    }

    public Boolean addFlightUnit(int indexPlayer) {
        Player player = null;
        if (indexPlayer == 1) {
            player = player1;
        } else {
            player = player2;
        }
        if (player.getCurrentGold() >= this.settings.getFlightSoldierSettings().getPrice()) {
            player.getEntities().add(new FlightSoldier(player.getCastle().getPosition(), this.settings.getFlightSoldierSettings()));
            player.decreaseCurrentGold(this.settings.getFlightSoldierSettings().getPrice());
            return true;
        }
        return false;

    }


    public boolean addGoldMinePlayer(Position position, int indexPlayer) {

        Player player = null;
        if (indexPlayer == 1) {
            player = player1;
        } else {
            player = player2;
        }

        if (player.getCurrentGold() >= this.settings.getGoldSettings().getPriceOfGoldMine()) {
            player.getEntities().add(new GoldMine(position, this.settings.getGoldSettings()));
            player.decreaseCurrentGold(this.settings.getFreezeTowerSettings().getPrice());
            return true;
        }
        return false;
    }


    public boolean addNewFreezeTowerPlayer(Position position, int indexPlayer) {
        Player player = null;
        if (indexPlayer == 1) {
            player = player1;
        } else {
            player = player2;
        }

        if (player.getCurrentGold() >= this.settings.getFreezeTowerSettings().getPrice()) {
            player.getEntities().add(new FreezeTower(position, this.settings.getFreezeTowerSettings()));
            player.decreaseCurrentGold(this.settings.getFreezeTowerSettings().getPrice());
            return true;
        }
        return false;
    }

    public boolean addNewNormalTowerPlayer(Position position, int indexPlayer) {
        Player player = null;
        if (indexPlayer == 1) {
            player = player1;
        } else {
            player = player2;
        }

        if (player.getCurrentGold() >= this.settings.getFreezeTowerSettings().getPrice()) {
            player.getEntities().add(new NormalTower(position, this.settings.getNormalTowerSettings()));
            player.decreaseCurrentGold(this.settings.getNormalTowerSettings().getPrice());
            return true;
        }
        return false;
    }

    public boolean addNewSniperTowerPlayer(Position position, int indexPlayer) {
        Player player = null;
        if (indexPlayer == 1) {
            player = player1;
        } else {
            player = player2;
        }

        if (player.getCurrentGold() >= this.settings.getFreezeTowerSettings().getPrice()) {
            player.getEntities().add(new SniperTower(position, this.settings.getSniperTowerSettings()));
            player.decreaseCurrentGold(this.settings.getSniperTowerSettings().getPrice());
            return true;
        }
        return false;
    }



    public boolean canPlayer1PutNewEntityAtThePosition(Position position) {
        int radius = settings.getGeneralSettings().getRadiusToPlaceBuilding();



        if (isPlaceAvailable(position)) {


            Position castlePosition = player1.getCastle().getPosition();
            List<Position> positionsAvailable = (new Radius(radius, castlePosition.getX(), castlePosition.getY())).getPositions();
            System.out.println(positionsAvailable);

            if (positionsAvailable.contains(position))
                return true;


            for (PlayerEntity entity : player1.getEntities()) {

                if (isBuildingEntity(entity.getName())) {
                    positionsAvailable = (new Radius(radius, castlePosition.getX(), castlePosition.getY())).getPositions();
                    System.out.println(positionsAvailable);
                    if (positionsAvailable.contains(position))
                        return true;
                }
            }
        }






        return false;
    }


    public boolean canPlayer2PutNewEntityAtThePosition(Position position) {
        int radius = settings.getGeneralSettings().getRadiusToPlaceBuilding();

        if (!isPlaceAvailable(position))
            return false;

        Position castlePosition = player2.getCastle().getPosition();
        List<Position> positionsAvailable = (new Radius(radius, castlePosition.getX(), castlePosition.getY())).getPositions();
        if (positionsAvailable.contains(position))
            return true;

        for (PlayerEntity entity : player2.getEntities()) {
            if (isBuildingEntity(entity.getName())) {
                positionsAvailable = (new Radius(radius, castlePosition.getX(), castlePosition.getY())).getPositions();
                if (positionsAvailable.contains(position))
                    return true;
            }
        }

        return false;
    }



    private boolean isPlayer1Won() {
        return this.player2.getCastle().getHealthPoint() <= 0;
    }

    private boolean isPlayer2Won() {
        return this.player1.getCastle().getHealthPoint() <= 0;
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

        Random rand = new Random();

        ObstacleSettings obstacleSettings = this.settings.getObstacleSettings();
        int numberOfObstacles = obstacleSettings.getNumberOfObstacles();
        int radiusOfObstacles = obstacleSettings.getRadiusOfObstacles();

        List<Integer> xCenterObstacle = new ArrayList<>();
        List<Position> positions = new ArrayList<>();
        List<Obstacle> obstacles = new ArrayList<>();



        for (int currentObstacle = 0; currentObstacle < numberOfObstacles; currentObstacle++) {



            int xCenterObstacleCurrent = rand.nextInt(settings.getGeneralSettings().getWidthBoard() - 1);

            while (xCenterObstacle.contains(xCenterObstacleCurrent))
                xCenterObstacleCurrent = rand.nextInt(settings.getGeneralSettings().getWidthBoard() - 1);

            xCenterObstacle.add(xCenterObstacleCurrent);
        }

        for (int x : xCenterObstacle) {
            int yPos = settings.getGeneralSettings().getLengthBoard() / 2;

            positions.addAll((new Radius(radiusOfObstacles, x, yPos)).getPositions());

        }

        for (Position pos: positions) {

            if (pos.getX() >= 0 && pos.getY() >= 0 && pos.getX() < settings.getGeneralSettings().getWidthBoard() && pos.getY() < settings.getGeneralSettings().getWidthBoard() )
                obstacles.add(new Obstacle(pos));
        }

        return obstacles;
    }







    private boolean isBuildingEntity(String nameEntity) {
        return Objects.equals(nameEntity, "freeze_tower_entity")
                || Objects.equals(nameEntity, "normal_tower_entity")
                || Objects.equals(nameEntity, "sniper_tower_entity")
                || Objects.equals(nameEntity, "goldmine_entity")
                ;
    }


    private boolean isPlaceAvailable(Position position) {
        // No castle, obstacle, towers, mine

        List<Position> obstacles = this.board.getObstacles().stream().map(Entity::getPosition).collect(Collectors.toList());
        List<Position> buildingEntityPlayer1 = this.player1.getEntities().stream().filter(en -> isBuildingEntity(en.getName())).map(Entity::getPosition).collect(Collectors.toList());
        List<Position> buildingEntityPlayer2 = this.player2.getEntities().stream().filter(en -> isBuildingEntity(en.getName())).map(Entity::getPosition).collect(Collectors.toList());


        if (position.equals(player1.getCastle().getPosition()))
            return false;

        if (position.equals(player2.getCastle().getPosition()))
            return false;

        if (obstacles.contains(position))
            return false;

        if (buildingEntityPlayer1.contains(position))
            return false;

        if (buildingEntityPlayer2.contains(position))
            return false;

        return true;
    }







    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }



}
