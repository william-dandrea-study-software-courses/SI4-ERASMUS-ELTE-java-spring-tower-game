package com.softwaretechnology.tourgame.theknigh.service.game;

import com.softwaretechnology.tourgame.theknigh.service.game.settings.Settings;
import com.softwaretechnology.tourgame.theknigh.service.game.settings.game.*;
import com.softwaretechnology.tourgame.theknigh.service.game.settings.soldiers.FastSoldierSettings;
import com.softwaretechnology.tourgame.theknigh.service.game.settings.soldiers.FlightSoldierSettings;
import com.softwaretechnology.tourgame.theknigh.service.game.settings.soldiers.KillerSoldierSettings;
import com.softwaretechnology.tourgame.theknigh.service.game.settings.soldiers.SoldierMainSettings;
import com.softwaretechnology.tourgame.theknigh.service.game.settings.towers.FreezeTowerSettings;
import com.softwaretechnology.tourgame.theknigh.service.game.settings.towers.NormalTowerSettings;
import com.softwaretechnology.tourgame.theknigh.service.game.settings.towers.SniperTowerSettings;
import com.softwaretechnology.tourgame.theknigh.service.game.settings.towers.TowerMainSettings;
import com.softwaretechnology.tourgame.theknigh.service.game.utils.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author D'Andréa William
 */
class GameTest {

    GeneralSettings generalSettings = new GeneralSettings(10, 10, 3, 2);
    CastelSettings castelSettings = new CastelSettings(200, 10);
    GoldSettings goldSettings = new GoldSettings(100, 30, 200, 30);
    MonsterSettings monsterSettings = new MonsterSettings(10, 3);
    ObstacleSettings obstacleSettings = new ObstacleSettings(2, 3);
    SoldierMainSettings soldierMainSettings = new SoldierMainSettings();
    FastSoldierSettings fastSoldierSettings = new FastSoldierSettings(50, 30, 5, 2);
    KillerSoldierSettings killerSoldierSettings = new KillerSoldierSettings(50, 40, 5, 5);
    FlightSoldierSettings flightSoldierSettings = new FlightSoldierSettings(30, 20, 5);
    TowerMainSettings towerMainSettings = new TowerMainSettings();
    FreezeTowerSettings freezeTowerSettings = new FreezeTowerSettings(30, 3,1);
    NormalTowerSettings normalTowerSettings = new NormalTowerSettings(25, 3, 3);
    SniperTowerSettings sniperTowerSettings = new SniperTowerSettings(50, 5, 2);

    Settings settings = new Settings(generalSettings, castelSettings, goldSettings, monsterSettings, obstacleSettings,
            soldierMainSettings, fastSoldierSettings, flightSoldierSettings,killerSoldierSettings, towerMainSettings,
            freezeTowerSettings, normalTowerSettings, sniperTowerSettings);

    Game game;

    @BeforeEach
    void setUp() {
        this.game = new Game(this.settings);
    }

    @Test
    void launchGame() {

        this.game.launchGame();

        this.game.nextRound();

        this.game.addFastUnit(1);
        this.game.addNewNormalTowerPlayer(new Position(1,2),1);
        this.game.increaseTower(1, 1, 2);
        this.game.nextRound();

    }

    @Test
    void increaseTower(){
        this.game.launchGame();
        this.game.nextRound();
        this.game.addNewNormalTowerPlayer(new Position(1,1), 1);
        assertTrue(this.game.increaseTower(1,1,1));
        this.game.getPlayer1().decreaseCurrentGold(75);
        assertFalse(this.game.increaseTower(1,1,1));
        assertEquals(12, this.game.deleteTower(1,1,1));
    }

    @Test
    void deleteTowerTest(){
        this.game.launchGame();
        this.game.nextRound();
        assertEquals(0, this.game.deleteTower(1,1,1));
        this.game.addNewNormalTowerPlayer(new Position(1,1), 1);
        assertEquals(12, this.game.deleteTower(1,1,1));
    }

    @Test
    void addKillerUnit(){
        this.game.launchGame();
        this.game.nextRound();

        //without enough gold
        this.game.getPlayer1().decreaseCurrentGold(100);
        assertFalse(this.game.addKillerUnit(1));
    }

    @Test
    void addFastUnit(){
        this.game.launchGame();
        this.game.nextRound();

        //without enough gold
        this.game.getPlayer1().decreaseCurrentGold(100);
        assertFalse(this.game.addFastUnit(1));
    }

    @Test
    void addFlightUnitTest(){
        this.game.launchGame();
        this.game.nextRound();

        //without enough gold
        this.game.getPlayer1().decreaseCurrentGold(100);
        assertFalse(this.game.addFlightUnit(1));
    }

    @Test
    void verifyAndMoveSoldierOnThePlaceWhereThePlayerWantToAddBuildingEntityTest(){
        this.game.launchGame();

        this.game.nextRound();
        this.game.getPlayer1().increaseCurrentGold(1000);
        this.game.addFastUnit(1);

        //surrounding the player 1 castle by tower when the player1 soldier is at the player1 castle
        Position position = new Position(this.game.getPlayer1().getCastle().getPosition().getX(), 1);
        this.game.addNewNormalTowerPlayer(position,1);
        position = new Position(this.game.getPlayer1().getCastle().getPosition().getX()+1, 0);
        this.game.addNewNormalTowerPlayer(position,1);
        position = new Position(this.game.getPlayer1().getCastle().getPosition().getX()-1, 0);
        this.game.addNewNormalTowerPlayer(position,1);

        assertFalse(this.game.verifyAndMoveSoldierOnThePlaceWhereThePlayerWantToAddBuildingEntity(this.game.getPlayer1().getCastle().getPosition()));

    }

    @Test
    void addGoldMinePlayerTest(){
        this.game.launchGame();
        this.game.nextRound();

        //Position is good, but without enough gold
        this.game.getPlayer1().decreaseCurrentGold(100);
        Position position = new Position(this.game.getPlayer1().getCastle().getPosition().getX(), 1);
        assertFalse(this.game.addGoldMinePlayer(position,1));

    }

    @Test
    void addNewFreezeTowerPlayerTest(){
        this.game.launchGame();
        this.game.nextRound();

        //Position is good, but without enough gold
        this.game.getPlayer1().decreaseCurrentGold(100);
        Position position = new Position(this.game.getPlayer1().getCastle().getPosition().getX(), 1);
        assertFalse(this.game.addNewFreezeTowerPlayer(position,1));

    }

    @Test
    void addNewNormalTowerPlayerTest(){
        this.game.launchGame();
        this.game.nextRound();

        //Position is good, but without enough gold
        this.game.getPlayer1().decreaseCurrentGold(100);
        Position position = new Position(this.game.getPlayer1().getCastle().getPosition().getX(), 1);
        assertFalse(this.game.addNewNormalTowerPlayer(position,1));

    }

    @Test
    void addNewSniperTowerPlayerTest(){
        this.game.launchGame();
        this.game.nextRound();

        //Position is good, but without enough gold
        this.game.getPlayer1().decreaseCurrentGold(100);
        Position position = new Position(this.game.getPlayer1().getCastle().getPosition().getX(), 1);
        assertFalse(this.game.addNewSniperTowerPlayer(position,1));

    }

    @Test
    void canPlayerPutNewEntityAtThePosition(){
        assertFalse(this.game.canPlayerPutNewEntityAtThePosition(this.game.getPlayer1().getCastle().getPosition(), 1));

        Position position = new Position(this.game.getPlayer1().getCastle().getPosition().getX(), 1);
        assertTrue(this.game.canPlayerPutNewEntityAtThePosition(position, 1));

    }

    @Test
    void getAllBuildingEntitiesTest(){

        assertEquals(this.game.getBoard().getObstacles(), this.game.getAllBuildingEntities());
    }
//    @Test
//    void getAllFreePositionsTest(){
//        this.game.launchGame();
//
//        this.game.nextRound();
//        List<Position> allBuildingsPositions = this.game.getAllBuildingEntities().stream().map(Entity::getPosition).collect(Collectors.toList());
//        List<Position> allFreePositions = this.game.getAllFreePositions();
//
//        for(Position pos: allBuildingsPositions){
//            assertTrue(allFreePositions.contains(pos));
//        }
//    }


}
