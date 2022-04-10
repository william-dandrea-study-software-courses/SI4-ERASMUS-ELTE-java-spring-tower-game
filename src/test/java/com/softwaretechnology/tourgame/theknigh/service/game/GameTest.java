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
        this.game.nextRound();


    }
}
