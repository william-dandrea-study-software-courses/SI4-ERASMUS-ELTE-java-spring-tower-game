package com.softwaretechnology.tourgame.theknigh.service.game;

import com.softwaretechnology.tourgame.theknigh.service.game.board.Board;
import com.softwaretechnology.tourgame.theknigh.service.game.board.Tile;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.Entity;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.gameentities.castles.Castle;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.gameentities.monsters.Monster;
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

    private boolean isMonsterTurn = false;
    private List<Entity> monsters = new ArrayList<>();

    private int round = 0;
    private List<FreezeSoldierInTheGame> soldiersAreFreezeUntilTheRound = new ArrayList<>();

    public Game(Settings settings) {
        this.settings = settings;
        this.board = new Board(new BoardDimension(this.settings.getGeneralSettings().getLengthBoard(), this.settings.getGeneralSettings().getWidthBoard()), this.setupObstacles());
        this.setupTheCastle();

        this.launchGame();
    }

    /**
     * Launch the game by first initiating the two players
     */
    public void launchGame() {

        // We initialize, the player 1 start first
        this.player1.setPlaying(true);
        this.player2.setPlaying(false);

    }

    public Board getBoard() {
        return board;
    }

    /**
     * Processing the behaviors of all the units on the board,
     * while adding the money for both player and switch the
     * control between them too.
     * @return
     * the game itself
     */
    public Game nextRound() {
        round += 1;
        // We switch the user who play
        this.player1.setPlaying(!this.player1.isPlaying());
        this.player2.setPlaying(!this.player2.isPlaying());

        manageSoldiersDuringPlay();
        increaseAmountOfGoldForEachPlayer();

        updateHealthPointsOfUnitBecauseOfTowers(1);
        updateHealthPointsOfUnitBecauseOfTowers(2);

        generateMonsters();

        manageKillerSoldier(1);
        manageKillerSoldier(2);



        return this;

    }

    /**
     * In the beginning of a new round if one player's killing soldier
     * is at the same position of another player's killing soldier,
     * they fight each other.
     * @param indexPlayer
     * The current player
     */
    private void manageKillerSoldier(int indexPlayer) {
        Player player = null;
        Player playerEnemy = null;
        if (indexPlayer == 1) {
            player = player1;
            playerEnemy = player2;
        } else {
            player = player2;
            playerEnemy = player1;
        }


        if (this.isNewRound()) {

            for (Soldier soldier : player.getAllSoldiers()) {

                for (Soldier enemySoldier : playerEnemy.getAllSoldiers()) {

                    if (enemySoldier instanceof KillerSoldier && enemySoldier.getPosition().equals(soldier.getPosition())) {

                        soldier.removeHealthPoints(this.settings.getKillerSoldierSettings().getDamagesInflictedToOtherSoldiers());

                    }
                }
            }
        }
    }

    /**
     * If the round is a setting up monster appearing round,
     * creating default amount of monster on the free place
     * on the board then for both player's army, the soldier
     * who has the same position with the monster get 5 points
     * damage from the monster's appearing position.
     */
    private void generateMonsters() {

        if (this.round % this.settings.getMonsterSettings().getRoundsFrequencyOfPopping() == 0) {
            this.isMonsterTurn = true;
            List<Position> allFreePositions = getAllFreePositions();

            // Get N random element from this list
            Collections.shuffle(allFreePositions);

            for (int i = 0; i < this.settings.getMonsterSettings().getPoppingMonsterAtEachNRounds(); i++) {
                this.monsters.add(new Monster(allFreePositions.get(i)));
                List<Soldier> player1UnitsAtThisPosition = player1.getEntitiesInThisPosition(allFreePositions.get(i));
                List<Soldier> player2UnitsAtThisPosition = player2.getEntitiesInThisPosition(allFreePositions.get(i));

                if (!player1UnitsAtThisPosition.isEmpty()) {
                    player1UnitsAtThisPosition.forEach(s -> s.removeHealthPoints(5));
                }
                if (!player2UnitsAtThisPosition.isEmpty()) {
                    player2UnitsAtThisPosition.forEach(s -> s.removeHealthPoints(5));
                }
            }
        } else {
            this.isMonsterTurn = false;
            this.monsters = new ArrayList<>();
        }
    }



    /**
     * This method remove health point to the soldier if there are in the area of a tower
     */
    private void updateHealthPointsOfUnitBecauseOfTowers(int indexPlayer) {

        Player player = null;
        Player playerEnemy = null;
        if (indexPlayer == 1) {
            player = player1;
            playerEnemy = player2;
        } else {
            player = player2;
            playerEnemy = player1;
        }


        List<Tower> towersPlayer = player.getAllTowers();

        if (isNewRound()) {
            for (Tower towerPlayer : towersPlayer) {

                List<Position> positionsInThisArea = (new Radius(towerPlayer.getShootingRange(), towerPlayer.getPosition())).getPositions();
                List<Soldier> enemySoldiersInThisArea = playerEnemy.soldierAtThisPositions(positionsInThisArea);
                enemySoldiersInThisArea = enemySoldiersInThisArea.stream().limit(towerPlayer.getSimultaneousStrike()).collect(Collectors.toList());

                if (towerPlayer instanceof FreezeTower) {

                    towerPlayer = (FreezeTower) towerPlayer;

                    for (Soldier frozeSoldier : enemySoldiersInThisArea) {

                        FreezeSoldierInTheGame freezeSoldierInTheGame = new FreezeSoldierInTheGame(frozeSoldier.getPosition(), this.round + ((FreezeTower) towerPlayer).getNumberOfTurnWhereTheSoldierIsFreeze(), frozeSoldier);

                        if (!soldiersAreFreezeUntilTheRound.contains(freezeSoldierInTheGame)) {
                            soldiersAreFreezeUntilTheRound.add(freezeSoldierInTheGame);
                        }
                    }


                } else {

                    Player finalPlayerEnemy = playerEnemy;
                    Tower finalTowerPlayer = towerPlayer;
                    enemySoldiersInThisArea.forEach(s -> finalPlayerEnemy.removeHealthPointToSoldier(s, finalTowerPlayer.getSimultaneousStrike()));
                }
            }



            // Now we reset the position of the frozen soldiers
            for (FreezeSoldierInTheGame freSol : soldiersAreFreezeUntilTheRound) {
                freSol.freezeSoldier.setPosition(freSol.positionWhereHeIsFreeze);
            }
        }
    }

    private boolean isNewRound() {
        return this.round % 2 == 0;
    }

    /**
     * After each round adding the gold for both players by calculating
     * the number of goldmines they hold and plus the default each round
     * gold gaining.
     */
    private void increaseAmountOfGoldForEachPlayer() {

        if (isNewRound()) {
            int numberOfGOldMinePlayer1 = this.player1.getAllGoldMines().size();
            int numberOfGOldMinePlayer2 = this.player2.getAllGoldMines().size();

            this.player1.increaseCurrentGold(numberOfGOldMinePlayer1 * this.settings.getGoldSettings().getAddedGoldAtEachRoundWithGoldMine() + this.settings.getGoldSettings().getAddedGoldAtEachRound());
            this.player2.increaseCurrentGold(numberOfGOldMinePlayer2 * this.settings.getGoldSettings().getAddedGoldAtEachRoundWithGoldMine() + this.settings.getGoldSettings().getAddedGoldAtEachRound());
        }

    }

    /**
     * Implementing the movement of the fast soldier and flight soldier,
     * where the flight soldier can ignore the obstacles on the board.
     * When the soldier reaches enemy's castle, and make damage to the
     * castle while checking if the castle is destroyed.
     */
    private void manageSoldiersDuringPlay() {

        if (isNewRound()) {
            for (Soldier soldier : player1.getAllSoldiers()) {

                MyAStartAlgorithm aStartAlgorithm = new MyAStartAlgorithm(this, soldier.getPosition(), player2.getCastle().getPosition());

                if (soldier instanceof FlightSoldier) {
                    List<Position> buildingEntitiesAndNoObstacles = this.player1.getBuildingEntities().stream().map(Entity::getPosition).collect(Collectors.toList());
                    buildingEntitiesAndNoObstacles.addAll(this.player2.getBuildingEntities().stream().map(Entity::getPosition).collect(Collectors.toList()));

                    aStartAlgorithm = new MyAStartAlgorithm(this, buildingEntitiesAndNoObstacles, soldier.getPosition(), player2.getCastle().getPosition());
                }


                List<Position> path = aStartAlgorithm.getPathPositions();
                List<Position> pathSoldier = path.stream().limit(soldier.getNumberOfMoveAtEachRound()).collect(Collectors.toList());

                if (pathSoldier.contains(player2.getCastle().getPosition())) {

                    soldier.setPosition(player2.getCastle().getPosition());

                    this.player2.getCastle().removeHealthPoint(this.settings.getCastelSettings().getHealthPointsRemovedWhenSoldierReachCastle());

                    if (this.player2.getCastle().getHealthPoint() <= 0) {

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

                    soldier.setPosition(player1.getCastle().getPosition());

                    this.player1.getCastle().removeHealthPoint(this.settings.getCastelSettings().getHealthPointsRemovedWhenSoldierReachCastle());

                    if (this.player1.getCastle().getHealthPoint() <= 0) {

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


    /**
     * Upgrading a tower on the specific position
     * @param indexPlayer
     * the index of the player
     * @param x
     * x-position of the specific position
     * @param y
     * y-position of the specific position
     * @return
     * if the upgrading is success or not
     */
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


    /**
     * Deleting a tower on the specific position
     * @param indexPlayer
     * the index of the player
     * @param x
     * x-position of the specific position
     * @param y
     * y-position of the specific position
     * @return
     * the recycling gold of the tower, if fail then return 0
     */
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


    /**
     * create a killer soldier for the specific player
     * @param indexPlayer
     * the index of the player
     * @return
     * if the creating is successful
     */
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

    /**
     * create a fast soldier for the specific player
     * @param indexPlayer
     * the index of the player
     * @return
     * if the creating is successful
     */
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

    /**
     * create a flight soldier for the specific player
     * @param indexPlayer
     * the index of the player
     * @return
     * if the creating is successful
     */
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
     * Checking if the placing of a tower is allowed or not
     * @param position
     * The specific position
     * @return true if you can place the tower (all the units are moved), or false if you cannot place the tower at this place
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


    /**
     * create a goldmine for the specific player
     * @param position
     * the specific position
     * @param indexPlayer
     * the specific player
     * @return
     * if the creating is successful
     */
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
                    player.decreaseCurrentGold(this.settings.getGoldSettings().getPriceOfGoldMine());
                    return true;
                }
            }
            player.getEntities().remove(goldMine);
        }
        return false;
    }

    /**
     * create a freeze tower for the specific player
     * @param position
     * the specific position
     * @param indexPlayer
     * the specific player
     * @return
     * if the creating is successful
     */
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

    /**
     * create a normal tower for the specific player
     * @param position
     * the specific position
     * @param indexPlayer
     * the specific player
     * @return
     * if the creating is successful
     */
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

    /**
     * create a sniper tower for the specific player
     * @param position
     * the specific position
     * @param indexPlayer
     * the specific player
     * @return
     * if the creating is successful
     */
    public boolean addNewSniperTowerPlayer(Position position, int indexPlayer) {
        Player player = null;
        if (indexPlayer == 1) {
            player = player1;
        } else {
            player = player2;
        }

        if (player.getCurrentGold() >= this.settings.getSniperTowerSettings().getPrice()) {
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

    /**
     * Check if the specific position is available for a player to put units on it
     * @param position
     * the specific position
     * @param indexPlayer
     * the index of the player
     * @return
     * if the position is available
     */
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


    /**
     * Randomly set both player's castle on the position of the top and bottom of the board,
     * an assign them to the player respectively
     */
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


    /**
     * Generating the obstacles according to the requirements and the default settings
     * @return
     * the list of the obstacles
     */
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

    /**
     * Check if the name belongs to one of the building entity
     * @param nameEntity
     * the string name of the entity
     * @return
     * if it's true or not
     */
    private boolean isBuildingEntity(String nameEntity) {
        return Objects.equals(nameEntity, "freeze_tower_entity")
                || Objects.equals(nameEntity, "normal_tower_entity")
                || Objects.equals(nameEntity, "sniper_tower_entity")
                || Objects.equals(nameEntity, "goldmine_entity")
                ;
    }

    /**
     * Initial check for the position if it's available to build a building
     * @param position
     * The specific position
     * @return
     * if it's true or not
     */
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




    /**
     * Getting a list of all the building entities including the obstacles
     * @return
     * the list of the entities
     */
    public List<Entity> getAllBuildingEntities() {

        List<Entity> allBuildingEntities = new ArrayList<>();

        allBuildingEntities.addAll(board.getObstacles());
        allBuildingEntities.addAll(player1.getBuildingEntities());
        allBuildingEntities.addAll(player2.getBuildingEntities());

        return allBuildingEntities;
    }


    /**
     * By filter the board with all the building entities(including castles) we get all the free positions on the board
     * @return
     * the list of the free positions
     */
    public List<Position> getAllFreePositions() {

        List<Entity> allBuildingEntities = getAllBuildingEntities();
        allBuildingEntities.add(this.player1.getCastle());
        allBuildingEntities.add(this.player2.getCastle());

        List<Position> allBuildingEntitiesP = allBuildingEntities.stream().map(Entity::getPosition).collect(Collectors.toList());

        List<Position> allPositions = this.board.getTiles().stream().map(Tile::getPosition).collect(Collectors.toList());
//        allPositions.addAll(allBuildingEntitiesP);
        allPositions.removeAll(allBuildingEntitiesP);
//        return allPositions.stream().distinct().collect(Collectors.toList());
        return allPositions;
    }

    /**
     * Check if the way between two castle has been blocked
     * @return
     * if it's true or not
     */
    private boolean verifyIfPathBetween2Castles() {

        MyAStartAlgorithm aStat = new MyAStartAlgorithm(this, this.player1.getCastle().getPosition(), this.player2.getCastle().getPosition());

        return !aStat.getPathPositions().isEmpty();
    }

    /**
     * Check if the way between two castles and a position has been blocked
     * @param position
     * the specific position
     * @return
     * if it's true or not
     */
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

    public boolean isMonsterTurn() {
        return isMonsterTurn;
    }

    public List<Entity> getMonsters() {
        return monsters;
    }
}
