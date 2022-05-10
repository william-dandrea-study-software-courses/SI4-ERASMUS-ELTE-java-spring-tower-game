package com.softwaretechnology.tourgame.theknigh.service.game.gamemanaging;

import com.softwaretechnology.tourgame.theknigh.service.game.Game;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.building.towers.NormalTower;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.building.towers.Tower;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.soldiers.FastSoldier;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.soldiers.Soldier;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    GeneralSettings generalSettings = new GeneralSettings(10, 10, 3, 2);
    CastelSettings castelSettings = new CastelSettings(200, 10);
    GoldSettings goldSettings = new GoldSettings(100, 30, 200, 30);
    MonsterSettings monsterSettings = new MonsterSettings(10, 3);
    ObstacleSettings obstacleSettings = new ObstacleSettings(2, 1);
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
    void simpleGetterTest(){
        this.game.launchGame();

        assertEquals(1, this.game.getPlayer1().getId());

        assertEquals(100, this.game.getPlayer1().getCurrentGold());

        this.game.addFastUnit(1);
        Soldier soldier = this.game.getPlayer1().getAllSoldiers().get(0);
        assertTrue(this.game.getPlayer1().getEntities().contains(soldier));

        assertTrue(this.game.getPlayer1().isPlaying());
        this.game.getPlayer1().setPlaying(false);
        assertFalse(this.game.getPlayer1().isPlaying());

        this.game.getPlayer2().decreaseCurrentGold(10);
        assertEquals(90, this.game.getPlayer2().getCurrentGold());

        this.game.getPlayer2().increaseCurrentGold(10);
        assertEquals(100, this.game.getPlayer2().getCurrentGold());
    }

   @Test
   void getBuildingEntitiesTest(){
       this.game.launchGame();

       assertEquals(0, this.game.getPlayer1().getBuildingEntities().size());
       Position position = new Position(this.game.getPlayer1().getCastle().getPosition().getX(), 1);
       assertTrue(this.game.addNewNormalTowerPlayer(position,1));
       this.game.addFastUnit(1);
       assertEquals(1, this.game.getPlayer1().getBuildingEntities().size());
       assertTrue(this.game.getPlayer1().getBuildingEntities().get(0) instanceof NormalTower);
   }

    @Test
    void getAllGoldMinesTest(){
        this.game.launchGame();

        this.game.getPlayer1().increaseCurrentGold(1000);
        assertEquals(0, this.game.getPlayer1().getAllGoldMines().size());
        assertTrue(this.game.addGoldMinePlayer(new Position(this.game.getPlayer1().getCastle().getPosition().getX(), 1),1));
        assertTrue(this.game.addGoldMinePlayer(new Position(this.game.getPlayer1().getCastle().getPosition().getX(), 2),1));

        assertEquals(2, this.game.getPlayer1().getAllGoldMines().size());
    }

    @Test
    void getAllSoldiersTest(){
        this.game.launchGame();

        List<Soldier> testList;
        this.game.getPlayer1().increaseCurrentGold(1000);
        this.game.addFastUnit(1);
        this.game.addFlightUnit(1);
        testList = this.game.getPlayer1().getAllSoldiers();
        assertEquals(2, testList.size());
    }

    @Test
    void getAllTowersTest(){
        this.game.launchGame();

        this.game.getPlayer1().increaseCurrentGold(1000);
        assertEquals(0, this.game.getPlayer1().getAllTowers().size());
        Position position = new Position(this.game.getPlayer1().getCastle().getPosition().getX(), 1);
        assertTrue(this.game.addNewNormalTowerPlayer(position,1));
        position = new Position(this.game.getPlayer1().getCastle().getPosition().getX(), 2);
        assertTrue(this.game.addNewNormalTowerPlayer(position,1));
        assertEquals(2, this.game.getPlayer1().getAllTowers().size());
    }

    @Test
    void removeSoldierAtThisPositionTest(){
        this.game.launchGame();

        this.game.getPlayer1().increaseCurrentGold(1000);
        this.game.addFastUnit(1);
        this.game.addFlightUnit(1);
        assertEquals(2, this.game.getPlayer1().getAllSoldiers().size());
        this.game.getPlayer1().removeSoldierAtThisPositon(this.game.getPlayer1().getCastle().getPosition());
        assertEquals(0, this.game.getPlayer1().getAllSoldiers().size());

    }

    @Test
    void soldierAtThisPositionsTest(){
        this.game.launchGame();

        this.game.getPlayer1().increaseCurrentGold(1000);
        List<Position> testList = new ArrayList<>();
        testList.add(this.game.getPlayer1().getCastle().getPosition());
        this.game.addFastUnit(1);
        this.game.addFlightUnit(1);
        assertEquals(this.game.getPlayer1().soldierAtThisPositions(testList).size(), 2);
    }

    @Test
    void removeHealthPointToSoldierTest(){
        this.game.launchGame();

        this.game.getPlayer1().increaseCurrentGold(1000);
        this.game.addFastUnit(1);
        this.game.getPlayer1().removeHealthPointToSoldier(this.game.getPlayer1().getAllSoldiers().get(0), 10);
        assertEquals(20, this.game.getPlayer1().getAllSoldiers().get(0).getHealthPoint());
    }

    @Test
    void getEntitiesInThisPositionTest(){
        this.game.launchGame();

        this.game.getPlayer1().increaseCurrentGold(1000);
        this.game.addFastUnit(1);
        this.game.addFlightUnit(1);
        List<Soldier> testList;
        testList = this.game.getPlayer1().getEntitiesInThisPosition(this.game.getPlayer1().getCastle().getPosition());
        assertEquals(2, testList.size());
    }
}
