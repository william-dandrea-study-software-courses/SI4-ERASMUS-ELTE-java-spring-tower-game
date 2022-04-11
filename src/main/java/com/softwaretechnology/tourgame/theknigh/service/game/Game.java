package com.softwaretechnology.tourgame.theknigh.service.game;

import com.softwaretechnology.tourgame.theknigh.service.game.board.Board;
import com.softwaretechnology.tourgame.theknigh.service.game.board.Tile;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.Entity;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.gameentities.castles.Castle;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.gameentities.obstacles.Obstacle;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.PlayerEntity;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.building.BuildingEntity;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.building.goldmines.GoldMine;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.building.towers.FreezeTower;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.building.towers.NormalTower;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.building.towers.SniperTower;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.building.towers.Tower;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.soldiers.FastSoldier;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.soldiers.FlightSoldier;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.soldiers.KillerSoldier;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.soldiers.Soldier;
import com.softwaretechnology.tourgame.theknigh.service.game.gamemanaging.Player;
import com.softwaretechnology.tourgame.theknigh.service.game.settings.Settings;
import com.softwaretechnology.tourgame.theknigh.service.game.settings.game.ObstacleSettings;
import com.softwaretechnology.tourgame.theknigh.service.game.utils.*;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

import static com.softwaretechnology.tourgame.theknigh.service.game.utils.Constants.POSITION_WE_CAN_GO;

/**
 * @author D'Andréa William
 */

@Data
public class Game {

    private final static int COST_OF_INCREASE_LEVEL_TOWER = 15;

    private Settings settings;
    private Board board;
    private Player player1;
    private Player player2;

    private boolean isPlayer1Won = false;
    private boolean isPlayer2Won = false;

    private int round = 0;

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




    public Game nextRound() {
        round += 1;
        // We switch the user who play
        this.player1.setPlaying(!this.player1.isPlaying());
        this.player2.setPlaying(!this.player2.isPlaying());

        manageSoldiersDuringPlay();
        increaseAmountOfGoldForEachPlayer();

        updateHealthPointsOfUnitBecauseOfTowers();

        return this;

    }

    private void updateHealthPointsOfUnitBecauseOfTowers() {

        if (isNewRound()) {





        }
    }

    private boolean isNewRound() {
        return this.round % 2 == 0;
    }

    private void increaseAmountOfGoldForEachPlayer() {

        if (isNewRound()) {
            int numberOfGOldMinePlayer1 = this.player1.getAllGoldMines().size();
            int numberOfGOldMinePlayer2 = this.player2.getAllGoldMines().size();

            this.player1.increaseCurrentGold(numberOfGOldMinePlayer1 * this.settings.getGoldSettings().getAddedGoldAtEachRoundWithGoldMine() + this.settings.getGoldSettings().getAddedGoldAtEachRound());
            this.player2.increaseCurrentGold(numberOfGOldMinePlayer2 * this.settings.getGoldSettings().getAddedGoldAtEachRoundWithGoldMine() + this.settings.getGoldSettings().getAddedGoldAtEachRound());
        }

    }


    private void manageSoldiersDuringPlay() {

        if (isNewRound()) {
            for (Soldier soldier : player1.getAllSoldiers()) {

                MyAStartAlgorithm aStartAlgorithm = new MyAStartAlgorithm(this, soldier.getPosition(), player2.getCastle().getPosition());
                List<Position> path = aStartAlgorithm.getPathPositions();
                List<Position> pathSoldier = path.stream().limit(soldier.getNumberOfMoveAtEachRound()).collect(Collectors.toList());

                if (pathSoldier.contains(player2.getCastle().getPosition())) {
                    System.out.println("Chateau atteint !");
                    soldier.setPosition(player2.getCastle().getPosition());

                    this.player2.getCastle().removeHealthPoint(this.settings.getCastelSettings().getHealthPointsRemovedWhenSoldierReachCastle());

                    if (this.player2.getCastle().getHealthPoint() <= 0) {
                        System.out.println("Player 1 won");
                        isPlayer1Won = true;
                    }
                } else {
                    // Player 1 is somewhere in the map
                    Position newPositionSoldier = pathSoldier.get(pathSoldier.size() - 1);
                    soldier.setPosition(newPositionSoldier);
                }

            }


            for (Soldier soldier : player2.getAllSoldiers()) {

                MyAStartAlgorithm aStartAlgorithm = new MyAStartAlgorithm(this, soldier.getPosition(), player1.getCastle().getPosition());
                List<Position> path = aStartAlgorithm.getPathPositions();

                List<Position> pathSoldier = path.stream().limit(soldier.getNumberOfMoveAtEachRound()).collect(Collectors.toList());

                if (pathSoldier.contains(player1.getCastle().getPosition())) {
                    System.out.println("Chateau atteint !");
                    soldier.setPosition(player1.getCastle().getPosition());

                    this.player1.getCastle().removeHealthPoint(this.settings.getCastelSettings().getHealthPointsRemovedWhenSoldierReachCastle());

                    if (this.player1.getCastle().getHealthPoint() <= 0) {
                        System.out.println("Player 2 won");
                        isPlayer2Won = true;
                    }
                } else {
                    // Player 1 is somewhere in the map
                    Position newPositionSoldier = pathSoldier.get(pathSoldier.size() - 1);
                    soldier.setPosition(newPositionSoldier);
                }
            }

            this.player1.removeSoldierAtThisPositon(this.player2.getCastle().getPosition());
            this.player2.removeSoldierAtThisPositon(this.player1.getCastle().getPosition());
        }


    }




    public boolean increaseTower(int indexPlayer, int x, int y) {
        Player player = null;
        if (indexPlayer == 1) {
            player = player1;
        } else {
            player = player2;
        }

        Optional<PlayerEntity> entity = player.getEntities().stream().filter(e -> e.getPosition().getX() == x && e.getPosition().getY() == y).findAny();
        if (entity.isPresent()) {

            if (player.getCurrentGold() >= COST_OF_INCREASE_LEVEL_TOWER) {
                Tower tower = (Tower) entity.get();
                tower.increaseSimultaneousStrike();
                player.decreaseCurrentGold(COST_OF_INCREASE_LEVEL_TOWER);
                return true;
            }

        }

        return false;
    }



    public int deleteTower(int indexPlayer, int x, int y) {
        Player player = null;
        if (indexPlayer == 1) {
            player = player1;
        } else {
            player = player2;
        }

        Optional<PlayerEntity> entity = player.getEntities().stream().filter(e -> e.getPosition().getX() == x && e.getPosition().getY() == y).findAny();
        int gldToAdd = 0;
        if (entity.isPresent()) {
            Tower tower = (Tower) entity.get();
            gldToAdd = (int) (tower.getPrice() * tower.getPercentageRewardIfDestroyed());
            player.increaseCurrentGold(gldToAdd);
            player.getEntities().remove(tower);

        }

        return gldToAdd;
    }



    public Boolean addKillerUnit(int indexPlayer) {
        Player player = null;
        if (indexPlayer == 1) {
            player = player1;
        } else {
            player = player2;
        }
        if (player.getCurrentGold() >= this.settings.getKillerSoldierSettings().getPrice()) {
            Soldier soldier = new KillerSoldier(player.getCastle().getPosition(), this.settings.getKillerSoldierSettings());
            player.getEntities().add(soldier);


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
            Soldier soldier = new FastSoldier(player.getCastle().getPosition(), this.settings.getFastSoldierSettings());
            player.getEntities().add(soldier);


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

            Soldier soldier = new FlightSoldier(player.getCastle().getPosition(), this.settings.getFlightSoldierSettings());
            player.getEntities().add(soldier);
            player.decreaseCurrentGold(this.settings.getFlightSoldierSettings().getPrice());
            return true;

        }
        return false;

    }

    /**
     *
     * @param position
     * @return true if you can place the tour (all the units are moved), or false if you cannot place the tower at this place
     */
    public boolean verifyAndMoveSoldierOnThePlaceWhereThePlayerWantToAddBuildingEntity(Position position) {

        List<Soldier> allSoldiers = this.player1.getAllSoldiers();
        allSoldiers.addAll(this.player2.getAllSoldiers());

        // Search all the soldiers
        for (Soldier soldier : allSoldiers) {

            // If a soldier is on the position of the building entity
            if (soldier.getPosition().equals(position)) {

                // We'll have 4 try (4 potential moves : POSITION_WE_CAN_GO), if we find any way, we cannot place the tower here
                int numberOfTry = 0;

                // We try to watch the position arounds
                for (Position possiblePosition : POSITION_WE_CAN_GO) {
                    possiblePosition.setX(position.getX() + possiblePosition.getX());
                    possiblePosition.setY(position.getY() + possiblePosition.getY());

                    // Now we watch if there exist a path from here to the castle
                    boolean wayBetweenSoldierAndCastles = verifyIfPathBetween1PositionAnd2Castles(possiblePosition);

                    if (wayBetweenSoldierAndCastles) {
                        soldier.setPosition(possiblePosition);
                        break;
                    }

                    numberOfTry += 1;
                }

                if (numberOfTry == POSITION_WE_CAN_GO.size()) {
                    return false;
                }
            }
        }

        // If any entity, we can place the building entity
        return true;
    }




    public boolean addGoldMinePlayer(Position position, int indexPlayer) {

        Player player = null;
        if (indexPlayer == 1) {
            player = player1;
        } else {
            player = player2;
        }

        if (player.getCurrentGold() >= this.settings.getGoldSettings().getPriceOfGoldMine()) {

            GoldMine goldMine = new GoldMine(position, this.settings.getGoldSettings());
            player.getEntities().add(goldMine);

            if (this.verifyIfPathBetween2Castles()) {

                if (verifyAndMoveSoldierOnThePlaceWhereThePlayerWantToAddBuildingEntity(position)) {
                    player.decreaseCurrentGold(this.settings.getFreezeTowerSettings().getPrice());
                    return true;
                }


            }

            player.getEntities().remove(goldMine);
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
            Tower tower = new FreezeTower(position, this.settings.getFreezeTowerSettings());
            player.getEntities().add(tower);

            if (this.verifyIfPathBetween2Castles()) {
                if (verifyAndMoveSoldierOnThePlaceWhereThePlayerWantToAddBuildingEntity(position)) {
                    player.decreaseCurrentGold(this.settings.getFreezeTowerSettings().getPrice());
                    return true;
                }
            }
            player.getEntities().remove(tower);

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
            Tower tower = new NormalTower(position, this.settings.getNormalTowerSettings());
            player.getEntities().add(tower);

            if (this.verifyIfPathBetween2Castles()) {
                if (verifyAndMoveSoldierOnThePlaceWhereThePlayerWantToAddBuildingEntity(position)) {
                    player.decreaseCurrentGold(this.settings.getNormalTowerSettings().getPrice());
                    return true;
                }
            }
            player.getEntities().remove(tower);
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
            Tower tower = new SniperTower(position, this.settings.getSniperTowerSettings());
            player.getEntities().add(tower);

            if (this.verifyIfPathBetween2Castles()) {

                if (verifyAndMoveSoldierOnThePlaceWhereThePlayerWantToAddBuildingEntity(position)) {
                    player.decreaseCurrentGold(this.settings.getSniperTowerSettings().getPrice());
                    return true;
                }
            }
            player.getEntities().remove(tower);
        }
        return false;
    }



    public boolean canPlayerPutNewEntityAtThePosition(Position position, int indexPlayer) {
        Player player = null;
        if (indexPlayer == 1) {
            player = player1;
        } else {
            player = player2;
        }

        int radius = settings.getGeneralSettings().getRadiusToPlaceBuilding();

        if (isPlaceAvailable(position)) {
            Position castlePosition = player.getCastle().getPosition();
            List<Position> positionsAvailable = (new Radius(radius, castlePosition.getX(), castlePosition.getY())).getPositions();


            for (Entity entity : player.getBuildingEntities()) {
                positionsAvailable.addAll((new Radius(radius, entity.getPosition().getX(), entity.getPosition().getY())).getPositions());
            }

            return positionsAvailable.contains(position);
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

        int maxIteration = 0;


        for (int currentObstacle = 0; currentObstacle < numberOfObstacles; currentObstacle++) {


            int xCenterObstacleCurrent = rand.nextInt(settings.getGeneralSettings().getWidthBoard() - 1);

            while (xCenterObstacle.contains(xCenterObstacleCurrent)) {
                xCenterObstacleCurrent = rand.nextInt(settings.getGeneralSettings().getWidthBoard() - 1);
                if (maxIteration >= 1000)
                    break;
            }
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


    public List<Entity> getAllBuildingEntities() {

        List<Entity> allBuildingEntities = new ArrayList<>();

        allBuildingEntities.addAll(board.getObstacles());
        allBuildingEntities.addAll(player1.getBuildingEntities());
        allBuildingEntities.addAll(player2.getBuildingEntities());

        return allBuildingEntities;
    }


    private boolean verifyIfPathBetween2Castles() {

        MyAStartAlgorithm aStat = new MyAStartAlgorithm(this, this.player1.getCastle().getPosition(), this.player2.getCastle().getPosition());

        return !aStat.getPathPositions().isEmpty();
    }

    private boolean verifyIfPathBetween1PositionAnd2Castles(Position position) {

        MyAStartAlgorithm aStat = new MyAStartAlgorithm(this, position, this.player2.getCastle().getPosition());
        MyAStartAlgorithm aStat2 = new MyAStartAlgorithm(this, position, this.player1.getCastle().getPosition());

        return !aStat.getPathPositions().isEmpty() && !aStat.getPathPositions().isEmpty();
    }


    public boolean getIsPlayer1Won() {
        return this.isPlayer1Won;
    }
    public boolean getIsPlayer2Won() {
        return this.isPlayer2Won;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }



}
