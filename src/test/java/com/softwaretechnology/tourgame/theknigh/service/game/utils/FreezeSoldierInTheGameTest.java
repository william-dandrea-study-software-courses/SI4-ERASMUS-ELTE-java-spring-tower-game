package com.softwaretechnology.tourgame.theknigh.service.game.utils;

import com.softwaretechnology.tourgame.theknigh.service.game.Game;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FreezeSoldierInTheGameTest {

    GeneralSettings generalSettings = new GeneralSettings(10, 10, 3, 2);
    CastelSettings castelSettings = new CastelSettings(200, 10);
    GoldSettings goldSettings = new GoldSettings(100, 30, 200, 30);
    MonsterSettings monsterSettings = new MonsterSettings(10, 3);
    ObstacleSettings obstacleSettings = new ObstacleSettings(2, 2);
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
        this.game = new Game(settings);
    }

    @Test
    void equalsTest(){
        this.game.launchGame();

        this.game.nextRound();
        this.game.addFastUnit(1);
        Soldier soldier = this.game.getPlayer1().getAllSoldiers().get(0);
        soldier.setPosition(new Position(1,1));
        FreezeSoldierInTheGame freezeSoldierInTheGame = new FreezeSoldierInTheGame(soldier.getPosition(), 1, soldier);
        FreezeSoldierInTheGame freezeSoldierInTheGame1 = new FreezeSoldierInTheGame(soldier.getPosition(), 1, soldier);
        assertTrue(freezeSoldierInTheGame.equals(freezeSoldierInTheGame1));
    }

    @Test
    void hashCodeTest(){
        this.game.launchGame();

        this.game.nextRound();
        this.game.addFastUnit(1);
        Soldier soldier = this.game.getPlayer1().getAllSoldiers().get(0);
        soldier.setPosition(new Position(1,1));
        FreezeSoldierInTheGame freezeSoldierInTheGame = new FreezeSoldierInTheGame(soldier.getPosition(), 1, soldier);
        assertEquals(soldier.getPosition().hashCode()*31 + soldier.hashCode(), freezeSoldierInTheGame.hashCode());
    }
}
