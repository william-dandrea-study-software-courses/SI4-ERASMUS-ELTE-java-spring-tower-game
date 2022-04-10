package game;

import game.board.Board;
import game.board.entities.Entity;
import game.board.entities.gameentities.obstacles.Obstacle;
import game.board.entities.playerentities.building.BuildingEntity;
import game.board.entities.playerentities.building.goldmines.GoldMine;
import game.board.entities.playerentities.building.towers.FreezeTower;
import game.board.entities.playerentities.building.towers.NormalTower;
import game.board.entities.playerentities.building.towers.SniperTower;
import game.board.entities.playerentities.building.towers.Tower;
import game.board.entities.playerentities.soldiers.FastSoldier;
import game.board.entities.playerentities.soldiers.FlightSoldier;
import game.board.entities.playerentities.soldiers.KillerSoldier;
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
    private final Board board;
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

        /* Initiate board */
        BoardDimension boardDimension = new BoardDimension(settings.getGeneralSettings().getLengthBoard(), settings.getGeneralSettings().getWidthBoard());
        int numberOfObstacle = settings.getObstacleSettings().getNumberOfObstacles();
        int castleInitialHealthPoint = settings.getCastelSettings().getInitialHealthPoints();
        this.board = new Board(boardDimension, castleInitialHealthPoint, numberOfObstacle);

        /* Initiate Player */
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
     * and player (decided by turn), if player don't have enough gold or the
     * position is not suitable it will return false
     * @param position
     * Position of the goldmine (mouse hover)
     * @param player
     * Owner of the goldmine
     */
    public boolean createGoldMine(Position position, Player player){
        int goldMinePrice = settings.getGoldSettings().getPriceOfGoldMine();
        int increaseGoldDistributedAtEachRound = settings.getGoldSettings().getAddedGoldAtEachRoundWithGoldMine();
        /* the player don't have enough gold */
        if(player.getCurrentGold() < goldMinePrice){
            return false;
        }
        GoldMine goldMine = new GoldMine(position, player, goldMinePrice, increaseGoldDistributedAtEachRound);
        /* if the goldmine can be place at this position */
        if(checkGoldMinePlaceable(goldMine)){
            board.getTile(position).addEntityOnTheTile(goldMine);
            /* this player's each round's gold gets increased */
            player.setGoldGainedPerRound(player.getGoldGainedPerRound() + increaseGoldDistributedAtEachRound);
            /* this player's gold decreased by the amount of goldmine needed */
            player.reduceGold(goldMinePrice);
            return true;
        }else{return false;}
    }

    /**
     * @author Bai Zimo
     * For button create normal tower, with the in parameter position(mouse click)
     * and player (decided by turn), if player don't have enough gold or the
     * position is not suitable it will return false
     * @param position
     * Position of the normal tower (mouse hover)
     * @param player
     * Owner of the normal tower
     */
    public boolean createNormalTower(Position position, Player player){
        int price = settings.getNormalTowerSettings().getPrice();
        int shootingRange = settings.getNormalTowerSettings().getShootingRange();
        int simultaneousStrike = settings.getNormalTowerSettings().getNumberOfSimultaneousStrikes();
        int damageToSoldiers = settings.getNormalTowerSettings().getDamageToSoldier();
        /* the player don't have enough gold */
        if(player.getCurrentGold() < price){
            return false;
        }
        NormalTower normalTower = new NormalTower(position, player, price, shootingRange, simultaneousStrike, damageToSoldiers);
        /* if the normal tower can be place at this position */
        if(checkTowerPlaceable(normalTower)){
            board.getTile(position).addEntityOnTheTile(normalTower);
            /* this player's gold decreased by the amount of normal tower needed */
            player.reduceGold(price);
            return true;
        }else{return false;}
    }

    /**
     * @author Bai Zimo
     * For button create sniper tower, with the in parameter position(mouse click)
     * and player (decided by turn), if player don't have enough gold or the
     * position is not suitable it will return false
     * @param position
     * Position of the sniper tower (mouse hover)
     * @param player
     * Owner of the sniper tower
     */
    public boolean createSniperTower(Position position, Player player){
        int price = settings.getSniperTowerSettings().getPrice();
        int shootingRange = settings.getSniperTowerSettings().getShootingRange();
        int simultaneousStrike = settings.getSniperTowerSettings().getNumberOfSimultaneousStrikes();
        int damageToSoldiers = settings.getSniperTowerSettings().getDamageToSoldier();
        /* the player don't have enough gold */
        if(player.getCurrentGold() < price){
            return false;
        }
        SniperTower sniperTower = new SniperTower(position, player, price, shootingRange, simultaneousStrike, damageToSoldiers);
        /* if the sniper tower can be place at this position */
        if(checkTowerPlaceable(sniperTower)){
            board.getTile(position).addEntityOnTheTile(sniperTower);
            /* this player's gold decreased by the amount of sniper tower needed */
            player.reduceGold(price);
            return true;
        }else{return false;}
    }

    /**
     * @author Bai Zimo
     * For button create freeze tower, with the in parameter position(mouse click)
     * and player (decided by turn), if player don't have enough gold or the
     * position is not suitable it will return false
     * @param position
     * Position of the freeze tower (mouse hover)
     * @param player
     * Owner of the freeze tower
     */
    public boolean createFreezeTower(Position position, Player player){
        int price = settings.getFreezeTowerSettings().getPrice();
        int shootingRange = settings.getFreezeTowerSettings().getShootingRange();
        int simultaneousStrike = settings.getFreezeTowerSettings().getNumberOfSimultaneousStrikes();
        int numberOfRound = settings.getFreezeTowerSettings().getNumberOfRoundsWhereTheSoldierInTheAreaAreFreeze();
        int damageToSoldiers = settings.getSniperTowerSettings().getDamageToSoldier();
        /* the player don't have enough gold */
        if(player.getCurrentGold() < price){
            return false;
        }
        FreezeTower freezeTower = new FreezeTower(position, player, price, shootingRange, simultaneousStrike, numberOfRound, damageToSoldiers);
        /* if the freeze tower can be place at this position */
        if(checkTowerPlaceable(freezeTower)){
            board.getTile(position).addEntityOnTheTile(freezeTower);
            /* this player's gold decreased by the amount of freeze tower needed */
            player.reduceGold(price);
            return true;
        }else{return false;}
    }

    /**
     * @author Tian Zhenman
     * For button create a fast soldier for one player, with the parameter player,
     * if the player don't have enough gold it will return false
     * @param player
     * Owner of the FastSoldier
     */
    public boolean createFastSoldier(Player player){
        Position position = player.getCastle().getPosition();
        int FastSoldierPrice = settings.getFastSoldierSettings().getPrice();
        int FastSoldierHealthPoint = settings.getFastSoldierSettings().getInitialHealthPoints();
        int killRewards = settings.getFastSoldierSettings().getKillRewards();
        int numberOfMoveAtEachRound = settings.getFastSoldierSettings().getNumberOfTileHeCanJump();
        /* the player don't have enought gold */
        if(player.getCurrentGold() < FastSoldierPrice){
            return false;
        }
        FastSoldier fastSoldier = new FastSoldier(position, player, FastSoldierPrice, FastSoldierHealthPoint, numberOfMoveAtEachRound, killRewards, numberOfMoveAtEachRound);
        board.getTile(position).addEntityOnTheTile(fastSoldier);
        /* this player's gold decreased by the amount of fast soldier needed */
        player.reduceGold(FastSoldierPrice);
        return true;
    }

    /**
     * @author Tian Zhenman
     * For button create a Flight soldier for one player, with the parameter player,
     * if the player don't have enough gold it will return false
     * @param player
     * The owner of the flight soldier
     */
    public boolean createFlightSoldier(Player player){
        Position position = player.getCastle().getPosition();
        int FlightSoldierPrice = settings.getFlightSoldierSettings().getPrice();
        int FlightSoldierHealthPoint = settings.getFlightSoldierSettings().getInitialHealthPoints();
        int killRewards = settings.getFlightSoldierSettings().getKillRewards();
        int numberOfMoveAtEachRound = settings.getFlightSoldierSettings().getNumberOfMovesAtEachRound();
        /* the player don't have enought gold */
        if(player.getCurrentGold() < FlightSoldierPrice){
            return false;
        }
        FlightSoldier flightSoldier = new FlightSoldier(position, player, FlightSoldierPrice, FlightSoldierHealthPoint, numberOfMoveAtEachRound, killRewards);
        board.getTile(position).addEntityOnTheTile(flightSoldier);
        /* this player's gold decreased by the amount of fast soldier needed */
        player.reduceGold(FlightSoldierPrice);
        return true;
    }

    /**
     * @author Tian Zhenman
     * For button create a Killer soldier for one player, with the parameter player,
     * if the player don't have enough gold it will return false
     * @param player
     * The owner of the killer solider
     */
    public boolean createKillerSoldier(Player player){
        Position position = player.getCastle().getPosition();
        int KillerSoldierPrice = settings.getKillerSoldierSettings().getPrice();
        int KillerSoldierHealthPoint = settings.getKillerSoldierSettings().getInitialHealthPoints();
        int killRewards = settings.getKillerSoldierSettings().getKillRewards();
        int numberOfMoveAtEachRound = settings.getKillerSoldierSettings().getNumberOfMovesAtEachRound();
        int damageToSoldier = settings.getKillerSoldierSettings().getDamagesInflictedToOtherSoldiers();
        /* the player don't have enought gold */
        if(player.getCurrentGold() < KillerSoldierPrice){
            return false;
        }
        KillerSoldier killerSoldier = new KillerSoldier(position, player, KillerSoldierPrice, KillerSoldierHealthPoint, numberOfMoveAtEachRound, killRewards, damageToSoldier);
        board.getTile(position).addEntityOnTheTile(killerSoldier);
        /* this player's gold decreased by the amount of fast soldier needed */
        player.reduceGold(KillerSoldierPrice);
        return true;
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
                            /* Add killRewards to the opposite */
                            if(entity.getOwner() == player1){
                                player2.addGold(((Soldier) entity).getKillRewards());
                            } else {player1.addGold(((Soldier) entity).getKillRewards());}
                            /* remove form board */
                            board.getTile(i, j).removeEntityOnTheTile(entity);
                            /* Enemy castle get invaded */
                        }else if(((Soldier) entity).arriveCastle() != null){
                            ((Soldier) entity).arriveCastle().gotInvaded(1);
                            board.getTile(i, j).removeEntityOnTheTile(entity);
                        }
                    }
                }
            }
        }
        /* Add each-round gold for both players */
        player1.addGold(player1.getGoldGainedPerRound());
        player2.addGold(player2.getGoldGainedPerRound());

        /* Check if the game is over by checking the health point of castle,
             The System.out.print is just for demonstration. */
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
     * Check if a tower can be place at it's position
     * @param tower
     * The tower
     * @return
     * if the tower can be placed
     */
    public boolean checkTowerPlaceable(Tower tower){
        boolean ourSidePlaceable = false;
        boolean enemySidePlaceable = true;
        // First check if this position has any other obstacles or buildings
        for (Entity entity: tower.getTile().getEntitiesOnTheTile()) {
            if(entity instanceof Obstacle || entity instanceof BuildingEntity){
                return false;
            }
        }
        for (int i=0;i<=board.getDimension().getLength();i++) {
            for (int j = 0; j <= board.getDimension().getWidth(); j++) {
                for (Entity entity : board.getTile(i, j).getEntitiesOnTheTile()) {
                    /* Check if the tower is too far from our buildings */
                    if(entity.getOwner() == tower.getOwner()){
                        if(entity.manhattanDistance(tower) < settings.getGeneralSettings().getRadiusToPlaceBuilding()){
                            ourSidePlaceable = true;
                        }
                    /* Check if the tower is too close to the enemy's buildings */
                    }else if(entity.getOwner() != tower.getOwner()){
                        if(entity.manhattanDistance(tower) < settings.getGeneralSettings().getEnemyForbiddenRadiusForBuilding()){
                            enemySidePlaceable = false;
                        }
                    }
                    /* Check if the tower blocks the way of two sides of soldiers */
                    if(entity instanceof Soldier){
                        if(entity.getOwner() == player1){
                            if(!entity.checkPath(player2.getCastle())){
                                return false;
                            }
                        }else if(!entity.checkPath(player1.getCastle())){
                            return false;
                        }
                    }
                }
            }
        }
        return ourSidePlaceable && enemySidePlaceable;
    }

    /**
     * @author Tian Zhenman
     * Check if a goldmine can be place at it's position
     * @param goldMine
     * The goldmine
     * @return
     * if the goldmine can be placed
     */
    public boolean checkGoldMinePlaceable(GoldMine goldMine) {
        for (int i = 0; i <= board.getDimension().getLength(); i++) {
            for (int j = 0; j <= board.getDimension().getWidth(); j++) {
                for (Entity entity : board.getTile(i, j).getEntitiesOnTheTile()) {
                    /* Check if the tower blocks the way of two sides of soldiers */
                    if(entity instanceof Soldier){
                        if(entity.getOwner() == player1){
                            if(!entity.checkPath(player2.getCastle())){
                                return false;
                            }
                        }else if(!entity.checkPath(player1.getCastle())){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * For recycle the buildings the player got 50% of the initial building price back
     * @param building
     * The recycled building
     */
    public void recycleBuildings(BuildingEntity building){
        building.getOwner().addGold(building.getPrice()/2);
        board.getTile(building.getPosition()).removeEntityOnTheTile(building);
    }

    /**
     * For upgrading the tower, if the tower reach the max level or the player don't
     * have enough money, it returns false.
     * @param tower
     * The upgrading tower
     * @return
     * if upgrading successful
     */
    public boolean upgradeTower(Tower tower){
        if(tower instanceof NormalTower){
            if(tower.getOwner().getCurrentGold() < settings.getNormalTowerSettings().getUpgradeCost()){
                return false;
            }
            return tower.upgrade();
        }else if(tower instanceof SniperTower){
            if(tower.getOwner().getCurrentGold() < settings.getSniperTowerSettings().getUpgradeCost()){
                return false;
            }
            return tower.upgrade();
        }else {
            if(tower.getOwner().getCurrentGold() < settings.getFreezeTowerSettings().getUpgradeCost()){
                return false;
            }
            return tower.upgrade();
        }
    }

    protected void launchGame() {
        System.out.println(this.settings.getCastelSettings());
    }




}
