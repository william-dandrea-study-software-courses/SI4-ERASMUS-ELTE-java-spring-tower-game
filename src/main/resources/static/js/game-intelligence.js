const URL_GAME_INFOS = 'http://localhost:8080/manager/game-infos';
const URL_VERIFY_TILE_AVAILABILITY = 'http://localhost:8080/manager/select-new-tile-for-building';
const URL_ADD_FREEZE_TOWER = 'http://localhost:8080/manager/add-freeze-tower';
const URL_ADD_SNIPER_TOWER = 'http://localhost:8080/manager/add-sniper-tower';
const URL_ADD_NORMAL_TOWER = 'http://localhost:8080/manager/add-normal-tower';
const URL_ADD_GOLD_MINE = 'http://localhost:8080/manager/add-gold-mine';
const URL_ADD_KILLER_UNIT = 'http://localhost:8080/manager/add-killer-unit';
const URL_ADD_FAST_UNIT = 'http://localhost:8080/manager/add-fast-unit';
const URL_ADD_FLIGHT_UNIT = 'http://localhost:8080/manager/add-flight-unit';
const URL_DELETE_TOWER = 'http://localhost:8080/manager/delete-tower';
const URL_INCREASE_TOWER = 'http://localhost:8080/manager/increase-tower';
const URL_NEXT_ROUND = 'http://localhost:8080/manager/next-round';

const PLAYER_1_COLOR = "#5970E9";
const PLAYER_2_COLOR = "#E95962";


let gameInfos = {};
let currentPlayer = 1;
let currentPlayerColor = PLAYER_1_COLOR;

let board = undefined;
const CANVA_SIZE = 700;








function mainLoop() {

    setInfosRound().then(() => {
        console.log(gameInfos)

        setupTheBoard();
        console.log(board);
    })

    verifyAvailabilityTail(-1,-1).then(r => {
        console.log(r);
    })

}






/** Verify if a player won, return the number of the winner or 0 if any winner */
function verifyIfAPlayerWon() {
    if (gameInfos.isPlayer1Won)
        return 1;
    if (gameInfos.isPlayer2Won)
        return 2;
    return 0;
}

/** Setup the board with the informations in the gameInfo */
function setupTheBoard() {

    board = new Board(gameInfos.board.dimension.width, gameInfos.board.dimension.width);

    const square_length = (CANVA_SIZE + 2) / (gameInfos.board.dimension.width);

    // Setup the tiles
    gameInfos.board.tiles.forEach(tileGameInfo => {
        const x_top_left = tileGameInfo.position.x * square_length
        const y_top_left = tileGameInfo.position.y * square_length

        const x_bottom_right = square_length + x_top_left
        const y_bottom_right = square_length + y_top_left

        board.addTile(new Tile(tileGameInfo.position.x, tileGameInfo.position.y, x_bottom_right, y_bottom_right, x_top_left, y_top_left))
    });

    // Setup the obstacles
    gameInfos.board.obstacles.forEach(tileObstacle => {
        let tileBoard = board.getTileAtPosition(tileObstacle.position.x, tileObstacle.position.y);
        if (tileBoard) {
            tileBoard.isObstacle = true;
        }
    });

    // Setup the castles
    let tileBoardCastle1 = board.getTileAtPosition(gameInfos.player1.castle.position.x, gameInfos.player1.castle.position.y);
    if (tileBoardCastle1) {
        tileBoardCastle1.isPlayer1Castle = true;
    }

    let tileBoardCastle2 = board.getTileAtPosition(gameInfos.player2.castle.position.x, gameInfos.player2.castle.position.y);
    if (tileBoardCastle2) {
        tileBoardCastle2.isPlayer2Castle = true;
    }

    // Setup the towers
    gameInfos.player1.allTowers.forEach(towerGI => {
        let tileBoard = board.getTileAtPosition(towerGI.position.x, towerGI.position.y);

        if (tileBoard) {
            if (towerGI.name === "freeze_tower_entity") {
                tileBoard.isPlayer1FreezeTower = true;
            }

            if (towerGI.name === "normal_tower_entity") {
                tileBoard.isPlayer1NormalTower = true;
            }

            if (towerGI.name === "sniper_tower_entity") {
                tileBoard.isPlayer1SniperTower = true;
            }
        }
    });

    gameInfos.player2.allTowers.forEach(towerGI => {
        let tileBoard = board.getTileAtPosition(towerGI.position.x, towerGI.position.y);

        if (tileBoard) {
            if (towerGI.name === "freeze_tower_entity") {
                tileBoard.isPlayer2FreezeTower = true;
            }

            if (towerGI.name === "normal_tower_entity") {
                tileBoard.isPlayer2NormalTower = true;
            }

            if (towerGI.name === "sniper_tower_entity") {
                tileBoard.isPlayer2SniperTower = true;
            }
        }
    });

    // Setup the goldMines
    gameInfos.player1.allGoldMines.forEach(goldMineGI => {
        let tileBoard = board.getTileAtPosition(goldMineGI.position.x, goldMineGI.position.y);

        if (tileBoard) {
            tileBoard.isPlayer1GoldMine = true;
        }
    });
    gameInfos.player2.allGoldMines.forEach(goldMineGI => {
        let tileBoard = board.getTileAtPosition(goldMineGI.position.x, goldMineGI.position.y);

        if (tileBoard) {
            tileBoard.isPlayer2GoldMine = true;
        }
    });

    // Setup the soldiers
    gameInfos.player1.allSoldiers.forEach(soldierGI => {
        let tileBoard = board.getTileAtPosition(soldierGI.position.x, soldierGI.position.y);

        if (tileBoard) {
            if (soldierGI.name === "fast_soldier_entity")
                tileBoard.addPlayer1FastSoldier(soldierGI.healthPoint);

            if (soldierGI.name === "flight_soldier_entity")
                tileBoard.addPlayer1FlightSoldier(soldierGI.healthPoint);

            if (soldierGI.name === "killer_soldier_entity")
                tileBoard.addPlayer1KillerSoldier(soldierGI.healthPoint);
        }
    });

    gameInfos.player2.allSoldiers.forEach(soldierGI => {
        let tileBoard = board.getTileAtPosition(soldierGI.position.x, soldierGI.position.y);

        if (tileBoard) {
            if (soldierGI.name === "fast_soldier_entity")
                tileBoard.addPlayer2FastSoldier(soldierGI.healthPoint);

            if (soldierGI.name === "flight_soldier_entity")
                tileBoard.addPlayer2FlightSoldier(soldierGI.healthPoint);

            if (soldierGI.name === "killer_soldier_entity")
                tileBoard.addPlayer2KillerSoldier(soldierGI.healthPoint);
        }
    });



}


/** Go to the next round and update the gameInfos variable */
async function nextRound() {
    gameInfos = await GETRequest(URL_NEXT_ROUND);
}
/** Delete the tower from current user at (positionX, positionY) and return a Promise on Integer (number of gold remaining, if fail, 0) */
async function deleteTower(positionX, positionY) {
    return POSTRequest(URL_DELETE_TOWER, positionAndPlayerJSON(positionX, positionY));
}
/** Increase the tower from current user at (positionX, positionY) and return a Promise on Boolean (false if fail) */
async function increaseTower(positionX, positionY) {
    return POSTRequest(URL_INCREASE_TOWER, positionAndPlayerJSON(positionX, positionY));
}
/** Add new killer unit and return a Promise on Boolean (false if fail to add new unit) */
async function addKillerUnit() {
    return POSTRequest(URL_ADD_KILLER_UNIT, positionAndPlayerJSON(0, 0));
}
/** Add new flight unit and return a Promise on Boolean (false if fail to add new unit) */
async function addFlightUnit() {
    return POSTRequest(URL_ADD_FLIGHT_UNIT, positionAndPlayerJSON(0, 0));
}
/** Add new fast unit and return a Promise on Boolean (false if fail to add new unit) */
async function addFastUnit() {
    return POSTRequest(URL_ADD_FAST_UNIT, positionAndPlayerJSON(0, 0));
}
/** Add new gold mine and return a Promise on Boolean (false if fail to add new gold mine) */
async function addGoldMine(positionX, positionY) {
    return POSTRequest(URL_ADD_GOLD_MINE, positionAndPlayerJSON(positionX, positionY));
}
/** Add new freeze tower and return a Promise on Boolean (false if fail to add new tower) */
async function addFreezeTower(positionX, positionY) {
    return POSTRequest(URL_ADD_FREEZE_TOWER, positionAndPlayerJSON(positionX, positionY));
}
/** Add new sniper tower and return a Promise on Boolean (false if fail to add new tower) */
async function addSniperTower(positionX, positionY) {
    return POSTRequest(URL_ADD_SNIPER_TOWER, positionAndPlayerJSON(positionX, positionY));
}
/** Add new normal tower and return a Promise on Boolean (false if fail to add new tower) */
async function addNormalTower(positionX, positionY) {
    return POSTRequest(URL_ADD_NORMAL_TOWER, positionAndPlayerJSON(positionX, positionY));
}
/** Update the info round */
async function setInfosRound() {
    gameInfos = await GETRequest(URL_GAME_INFOS);
}
/** Verify if the current user can place a building in the (positionX, positionY) tail, return false if he can't*/
async function verifyAvailabilityTail(positionX, positionY) {
    return POSTRequest(URL_VERIFY_TILE_AVAILABILITY, positionAndPlayerJSON(positionX, positionY));
}
/** Generate a JSOn wigth the position and the current user for doing the POST request */
function positionAndPlayerJSON(positionX, positionY) {
    return JSON.stringify({
        position: {
            x: positionX,
            y: positionY,
        },
        playingPlayer: currentPlayer,
    });
}
/** Normal POST request */
async function POSTRequest(url, body) {
    let result = undefined;
    await fetch(url, {
        method: "POST",
        headers: {
            'content-type': 'application/json'
        },
        body: body,
    })
        .then(res => res.json())
        .then((out) => {
            result = out;
        }).catch(err => console.error(err));

    return result;
}
/** Normal GET request */
async function GETRequest(url) {
    let result = undefined;
    await fetch(url, {method: "GET",})
        .then(res => res.json())
        .then((out) => {
            result = out;
        }).catch(err => console.error(err));
    return result;
}



class Board {

    tiles = [];

    constructor(height, width) {
        this.height = height;
        this.width = width;
    }

    addTile(tile) {
        this.tiles.push(tile);
    }


    getTileAtPosition(postionX, positionY) {
        return this.tiles.find(t => t.positionX === postionX && t.positionY === positionY)
    }


}

class Tile {

    _isObstacle = false;
    _isPlayer1NormalTower = false;
    _isPlayer1FreezeTower = false;
    _isPlayer1SniperTower = false;
    _isPlayer2NormalTower = false;
    _isPlayer2FreezeTower = false;
    _isPlayer2SniperTower = false;
    _isPlayer1Castle = false;
    _isPlayer2Castle = false;
    _isPlayer1GoldMine = false;
    _isPlayer2GoldMine = false;

    _player1FastSoldier = [];
    _player1KillerSoldier = [];
    _player1FlightSoldier = [];
    _player2FastSoldier = [];
    _player2KillerSoldier = [];
    _player2FlightSoldier = [];

    _numberOfMonsters = 0;

    constructor(positionX, positionY, xBottomRight, yBottomRight, xTopLeft, yTopLeft) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.xBottomRight = xBottomRight;
        this.yBottomRight = yBottomRight;
        this.xTopLeft = xTopLeft;
        this.yTopLeft = yTopLeft;
    }

    addPlayer1FastSoldier(healthPoint) {
        this._player1FastSoldier.push(healthPoint);
    }

    addPlayer2FastSoldier(healthPoint) {
        this._player2FastSoldier.push(healthPoint);
    }

    addPlayer1KillerSoldier(healthPoint) {
        this._player1KillerSoldier.push(healthPoint);
    }

    addPlayer2KillerSoldier(healthPoint) {
        this._player2KillerSoldier.push(healthPoint);
    }

    addPlayer1FlightSoldier(healthPoint) {
        this._player1FlightSoldier.push(healthPoint);
    }

    addPlayer2FlightSoldier(healthPoint) {
        this._player2FlightSoldier.push(healthPoint);
    }


    set isObstacle(value) {
        this._isObstacle = value;
    }

    set isPlayer1NormalTower(value) {
        this._isPlayer1NormalTower = value;
    }

    set isPlayer1FreezeTower(value) {
        this._isPlayer1FreezeTower = value;
    }

    set isPlayer1SniperTower(value) {
        this._isPlayer1SniperTower = value;
    }

    set isPlayer2NormalTower(value) {
        this._isPlayer2NormalTower = value;
    }

    set isPlayer2FreezeTower(value) {
        this._isPlayer2FreezeTower = value;
    }

    set isPlayer2SniperTower(value) {
        this._isPlayer2SniperTower = value;
    }

    set isPlayer1Castle(value) {
        this._isPlayer1Castle = value;
    }

    set isPlayer2Castle(value) {
        this._isPlayer2Castle = value;
    }

    set isPlayer1GoldMine(value) {
        this._isPlayer1GoldMine = value;
    }

    set isPlayer2GoldMine(value) {
        this._isPlayer2GoldMine = value;
    }

    set numberOfMonsters(value) {
        this._numberOfMonsters = value;
    }
}

mainLoop();
