const MAIN_URL = "http://localhost:8080/manage-settings/"

let settings = {
    "generalSettings": {
        "widthBoard": 10,
        "lengthBoard": 10,
        "radiusToPlaceBuilding": 3,
        "ennemyForbiddenRadiusForBuilding": 2
    },
    "castelSettings": {
        "initialHealthPoints": 200,
        "healthPointsRemovedWhenSoldierReachCastle": 10
    },
    "goldSettings": {
        "initialAmountOfGold": 100,
        "addedGoldAtEachRound": 30,
        "priceOfGoldMine": 200,
        "addedGoldAtEachRoundWithGoldMine": 30
    },
    "monsterSettings": {
        "poppingMonsterAtEachNRounds": 10,
        "roundsFrequencyOfPopping": 3
    },
    "obstacleSettings": {
        "numberOfObstacles": 2,
        "radiusOfObstacles": 3
    },
    "soldierMainSettings": {
        "anything": 0
    },
    "fastSoldierSettings": {
        "price": 50,
        "initialHealthPoints": 30,
        "numberOfMovesAtEachRound": 5,
        "numberOfTileHeCanJump": 2
    },
    "flightSoldierSettings": {
        "price": 30,
        "initialHealthPoints": 20,
        "numberOfMovesAtEachRound": 5
    },
    "killerSoldierSettings": {
        "price": 50,
        "initialHealthPoints": 40,
        "numberOfMovesAtEachRound": 5,
        "damagesInflictedToOtherSoldiers": 5
    },
    "towerMainSettings": {
        "anything": 0
    },
    "freezeTowerSettings": {
        "price": 30,
        "shootingRange": 3,
        "numberOfRoundsWhereTheSoldierInTheAreaAreFreeze": 1
    },
    "normalTowerSettings": {
        "price": 25,
        "shootingRange": 3,
        "numberOfSimultaneousStrikes": 3
    },
    "sniperTowerSettings": {
        "price": 50,
        "shootingRange": 5,
        "numberOfSimultaneousStrikes": 2
    }
}

async function getSettings() {
    await $.ajax({
        url: MAIN_URL,
        type: 'GET',
        contentType: "application/json",
        success: function (data) {
            settings = data;
            modifyValueSettingsPage();
        },
        error: function (error) {
            console.log(error);
        },
    })
}


async function setSettings() {
    await $.ajax({
        url: MAIN_URL,
        type: 'POST',
        contentType: "application/json",
        data: JSON.stringify(settings),
        success: function (data) {
            settings = data;
            modifyValueSettingsPage();
        },
        error: function (error) {
            console.log(error);
        },
    })
}

function modifyValueSettingsPage() {

    document.getElementById('widthBoard').value = settings.generalSettings.widthBoard;
    document.getElementById('lengthBoard').value = settings.generalSettings.lengthBoard;
    document.getElementById('radiusToPlaceBuilding').value = settings.generalSettings.radiusToPlaceBuilding;
    document.getElementById('enemyForbiddenRadiusForBuilding').value = settings.generalSettings.ennemyForbiddenRadiusForBuilding;

    document.getElementById('initialHealthPointsCastle').value = settings.castelSettings.initialHealthPoints;
    document.getElementById('healthPointsRemovedWhenSoldierReachCastle').value = settings.castelSettings.healthPointsRemovedWhenSoldierReachCastle;

    document.getElementById('initialAmountOfGold').value = settings.goldSettings.initialAmountOfGold;
    document.getElementById('addedGoldAtEachRound').value = settings.goldSettings.addedGoldAtEachRound;
    document.getElementById('priceOfGoldMine').value = settings.goldSettings.priceOfGoldMine;
    document.getElementById('addedGoldAtEachRoundWithGoldMine').value = settings.goldSettings.addedGoldAtEachRoundWithGoldMine;

    document.getElementById('poppingMonsterAtEachNRounds').value = settings.monsterSettings.poppingMonsterAtEachNRounds;
    document.getElementById('roundsFrequencyOfPopping').value = settings.monsterSettings.roundsFrequencyOfPopping;

    document.getElementById('numberOfObstacles').value = settings.obstacleSettings.numberOfObstacles;
    document.getElementById('radiusOfObstacles').value = settings.obstacleSettings.radiusOfObstacles;

    document.getElementById('priceFastSoldier').value = settings.fastSoldierSettings.price;
    document.getElementById('initialHealthPointsFastSoldier').value = settings.fastSoldierSettings.initialHealthPoints;
    document.getElementById('numberOfMovesAtEachRoundFastSoldier').value = settings.fastSoldierSettings.numberOfMovesAtEachRound;
    document.getElementById('numberOfTileHeCanJumpFastSoldier').value = settings.fastSoldierSettings.numberOfTileHeCanJump;

    document.getElementById('priceFlightSoldier').value = settings.flightSoldierSettings.price;
    document.getElementById('initialHealthPointsFlightSoldier').value = settings.flightSoldierSettings.initialHealthPoints;
    document.getElementById('numberOfMovesAtEachRoundFlightSoldier').value = settings.flightSoldierSettings.numberOfMovesAtEachRound;

    document.getElementById('priceKillerSoldier').value = settings.killerSoldierSettings.price;
    document.getElementById('initialHealthPointsKillerSoldier').value = settings.killerSoldierSettings.initialHealthPoints;
    document.getElementById('numberOfMovesAtEachRoundKillerSoldier').value = settings.killerSoldierSettings.numberOfMovesAtEachRound;
    document.getElementById('damagesInflictedToOtherSoldiersKillerSoldier').value = settings.killerSoldierSettings.damagesInflictedToOtherSoldiers;

    document.getElementById('priceFreezeTower').value = settings.freezeTowerSettings.price;
    document.getElementById('shootingRangeFreezeTower').value = settings.freezeTowerSettings.shootingRange;
    document.getElementById('numberOfRoundsWhereTheSoldierInTheAreaAreFreezeFreezeTower').value = settings.freezeTowerSettings.numberOfRoundsWhereTheSoldierInTheAreaAreFreeze;

    document.getElementById('priceNormalTower').value = settings.normalTowerSettings.price;
    document.getElementById('shootingRangeNormalTower').value = settings.normalTowerSettings.shootingRange;
    document.getElementById('numberOfSimultaneousStrikesNormalTower').value = settings.normalTowerSettings.numberOfSimultaneousStrikes;

    document.getElementById('priceSniperTower').value = settings.sniperTowerSettings.price;
    document.getElementById('shootingRangeSniperTower').value = settings.sniperTowerSettings.shootingRange;
    document.getElementById('numberOfSimultaneousStrikesSniperTower').value = settings.sniperTowerSettings.numberOfSimultaneousStrikes;
}


function getElementsOnThePageAndModifySettingsField() {

    settings.generalSettings.widthBoard = document.getElementById('widthBoard').value;
    settings.generalSettings.lengthBoard = document.getElementById('widthBoard').value;
    settings.generalSettings.radiusToPlaceBuilding = document.getElementById('radiusToPlaceBuilding').value;
    settings.generalSettings.ennemyForbiddenRadiusForBuilding = document.getElementById('enemyForbiddenRadiusForBuilding').value;

    settings.castelSettings.initialHealthPoints = document.getElementById('initialHealthPointsCastle').value;
    settings.castelSettings.healthPointsRemovedWhenSoldierReachCastle = document.getElementById('healthPointsRemovedWhenSoldierReachCastle').value;

    settings.goldSettings.initialAmountOfGold = document.getElementById('initialAmountOfGold').value;
    settings.goldSettings.addedGoldAtEachRound = document.getElementById('addedGoldAtEachRound').value;
    settings.goldSettings.priceOfGoldMine = document.getElementById('priceOfGoldMine').value;
    settings.goldSettings.addedGoldAtEachRoundWithGoldMine = document.getElementById('addedGoldAtEachRoundWithGoldMine').value;

    settings.monsterSettings.poppingMonsterAtEachNRounds = document.getElementById('poppingMonsterAtEachNRounds').value;
    settings.monsterSettings.roundsFrequencyOfPopping = document.getElementById('roundsFrequencyOfPopping').value;

    settings.obstacleSettings.numberOfObstacles = document.getElementById('numberOfObstacles').value;
    settings.obstacleSettings.radiusOfObstacles = document.getElementById('radiusOfObstacles').value;

    settings.fastSoldierSettings.price = document.getElementById('priceFastSoldier').value;
    settings.fastSoldierSettings.initialHealthPoints = document.getElementById('initialHealthPointsFastSoldier').value;
    settings.fastSoldierSettings.numberOfMovesAtEachRound = document.getElementById('numberOfMovesAtEachRoundFastSoldier').value;
    settings.fastSoldierSettings.numberOfTileHeCanJump = document.getElementById('numberOfTileHeCanJumpFastSoldier').value;

    settings.flightSoldierSettings.price = document.getElementById('priceFlightSoldier').value;
    settings.flightSoldierSettings.initialHealthPoints = document.getElementById('initialHealthPointsFlightSoldier').value;
    settings.flightSoldierSettings.numberOfMovesAtEachRound = document.getElementById('numberOfMovesAtEachRoundFlightSoldier').value;

    settings.killerSoldierSettings.price = document.getElementById('priceKillerSoldier').value;
    settings.killerSoldierSettings.initialHealthPoints = document.getElementById('initialHealthPointsKillerSoldier').value;
    settings.killerSoldierSettings.numberOfMovesAtEachRound = document.getElementById('numberOfMovesAtEachRoundKillerSoldier').value;
    settings.killerSoldierSettings.damagesInflictedToOtherSoldiers = document.getElementById('damagesInflictedToOtherSoldiersKillerSoldier').value;

    settings.freezeTowerSettings.price = document.getElementById('priceFreezeTower').value;
    settings.freezeTowerSettings.shootingRange = document.getElementById('shootingRangeFreezeTower').value;
    settings.freezeTowerSettings.numberOfRoundsWhereTheSoldierInTheAreaAreFreeze = document.getElementById('numberOfRoundsWhereTheSoldierInTheAreaAreFreezeFreezeTower').value;

    settings.normalTowerSettings.price = document.getElementById('priceNormalTower').value;
    settings.normalTowerSettings.shootingRange = document.getElementById('shootingRangeNormalTower').value;
    settings.normalTowerSettings.numberOfSimultaneousStrikes = document.getElementById('numberOfSimultaneousStrikesNormalTower').value;

    settings.sniperTowerSettings.price = document.getElementById('priceSniperTower').value;
    settings.sniperTowerSettings.shootingRange = document.getElementById('shootingRangeSniperTower').value;
    settings.sniperTowerSettings.numberOfSimultaneousStrikes = document.getElementById('numberOfSimultaneousStrikesSniperTower').value;

}






async function controlSettingsPage() {
    await getSettings();
}


controlSettingsPage();

let submitForms =document.getElementsByClassName('settings-form')

Array.from(submitForms).forEach(function(element) {
    element.addEventListener('click', () => {
        getElementsOnThePageAndModifySettingsField();
        setSettings();
    });
});




