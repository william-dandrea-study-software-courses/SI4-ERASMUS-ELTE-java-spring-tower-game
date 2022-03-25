package game.settings;

import game.settings.game.*;
import game.settings.soldiers.FastSoldierSettings;
import game.settings.soldiers.FlightSoldierSettings;
import game.settings.soldiers.KillerSoldierSettings;
import game.settings.soldiers.SoldierMainSettings;
import game.settings.towers.FreezeTowerSettings;
import game.settings.towers.NormalTowerSettings;
import game.settings.towers.SniperTowerSettings;
import game.settings.towers.TowerMainSettings;

/**
 * @author D'Andréa William
 */
public class Settings {

    private final GeneralSettings generalSettings;
    private final CastleSettings castelSettings;
    private final GoldSettings goldSettings;
    private final MonsterSettings monsterSettings;
    private final ObstacleSettings obstacleSettings;
    private final SoldierMainSettings soldierMainSettings;
    private final FastSoldierSettings fastSoldierSettings;
    private final FlightSoldierSettings flightSoldierSettings;
    private final KillerSoldierSettings killerSoldierSettings;
    private final TowerMainSettings towerMainSettings;
    private final FreezeTowerSettings freezeTowerSettings;
    private final NormalTowerSettings normalTowerSettings;
    private final SniperTowerSettings sniperTowerSettings;

    /**
     * The settings of the game
     * @param generalSettings
     * @param castelSettings
     * @param goldSettings
     * @param monsterSettings
     * @param obstacleSettings
     * @param soldierMainSettings
     * @param fastSoldierSettings
     * @param flightSoldierSettings
     * @param killerSoldierSettings
     * @param towerMainSettings
     * @param freezeTowerSettings
     * @param normalTowerSettings
     * @param sniperTowerSettings
     */
    public Settings(GeneralSettings generalSettings, CastleSettings castelSettings, GoldSettings goldSettings, MonsterSettings monsterSettings, ObstacleSettings obstacleSettings, SoldierMainSettings soldierMainSettings, FastSoldierSettings fastSoldierSettings, FlightSoldierSettings flightSoldierSettings, KillerSoldierSettings killerSoldierSettings, TowerMainSettings towerMainSettings, FreezeTowerSettings freezeTowerSettings, NormalTowerSettings normalTowerSettings, SniperTowerSettings sniperTowerSettings) {
        this.generalSettings = generalSettings;
        this.castelSettings = castelSettings;
        this.goldSettings = goldSettings;
        this.monsterSettings = monsterSettings;
        this.obstacleSettings = obstacleSettings;
        this.soldierMainSettings = soldierMainSettings;
        this.fastSoldierSettings = fastSoldierSettings;
        this.flightSoldierSettings = flightSoldierSettings;
        this.killerSoldierSettings = killerSoldierSettings;
        this.towerMainSettings = towerMainSettings;
        this.freezeTowerSettings = freezeTowerSettings;
        this.normalTowerSettings = normalTowerSettings;
        this.sniperTowerSettings = sniperTowerSettings;
    }

    public GeneralSettings getGeneralSettings() {
        return generalSettings;
    }

    public CastleSettings getCastelSettings() {
        return castelSettings;
    }

    public GoldSettings getGoldSettings() {
        return goldSettings;
    }

    public MonsterSettings getMonsterSettings() {
        return monsterSettings;
    }

    public ObstacleSettings getObstacleSettings() {
        return obstacleSettings;
    }

    public SoldierMainSettings getSoldierMainSettings() {
        return soldierMainSettings;
    }

    public FastSoldierSettings getFastSoldierSettings() {
        return fastSoldierSettings;
    }

    public FlightSoldierSettings getFlightSoldierSettings() {
        return flightSoldierSettings;
    }

    public KillerSoldierSettings getKillerSoldierSettings() {
        return killerSoldierSettings;
    }

    public TowerMainSettings getTowerMainSettings() {
        return towerMainSettings;
    }

    public FreezeTowerSettings getFreezeTowerSettings() {
        return freezeTowerSettings;
    }

    public NormalTowerSettings getNormalTowerSettings() {
        return normalTowerSettings;
    }

    public SniperTowerSettings getSniperTowerSettings() {
        return sniperTowerSettings;
    }
}
