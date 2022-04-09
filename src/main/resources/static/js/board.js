let CURRENT_PLAYER = 1;
const PLAYER_1_COLOR = "#5970E9";
const PLAYER_2_COLOR = "#E95962";

let playerColor = "#5970E9";

let board = [];
const canvasSize = 700;
let SIZE_BOARD = 10;
let SIZE_ONE_TILE = (canvasSize / SIZE_BOARD)

let gameInfos = {}
let obstacles = []
let player1Infos = {}
let player2Infos = {}

const c = document.querySelector('.game-board-canvas');






getDatasFromGameEngine().then(() => {
    initializePrice();
    changePlayer(CURRENT_PLAYER)
    resetBoard(c);
    drawInit(c);

    c.addEventListener('mousedown', async function (e) {
        let [xMouse, yMouse] = getCursorPosition(c, e)
        console.log(xMouse, yMouse)

        for (let y = 0; y < SIZE_BOARD; y++) {
            for (let x = 0; x < SIZE_BOARD; x++) {

                if (isInTheRectangle(xMouse, yMouse, board[y][x].x_top_left, board[y][x].y_top_left, board[y][x].x_bottom_right, board[y][x].y_bottom_right)) {

                    console.log("Clicked in a case", board[y][x].index)


                    const checkBoxInfo = document.getElementById("get-informations-tile");
                    const checkBoxNew = document.getElementById("add-new-entity");

                    if (checkBoxInfo.checked) {
                        alert(JSON.stringify(board[y][x]));
                    }

                    if (checkBoxNew.checked) {

                        await $.ajax({
                            url: 'http://localhost:8080/manager/select-new-tile-for-building',
                            type: 'POST',
                            contentType: "application/json",
                            data: JSON.stringify({
                                position: {
                                    x: x,
                                    y: y,
                                },
                                playingPlayer: CURRENT_PLAYER
                            }),
                            async: false,
                            success: function (data) {
                                if (data) {
                                    board[y][x].is_clicked = true;
                                    drawInit(c)

                                    if (confirm("Do you want to place a new tower or gold mine on this place ?")) {
                                        alert("Click on a tower ir gold mine");

                                        const normalTower = document.getElementById('normal-tower-button');
                                        const freezeTower = document.getElementById('freeze-tower-button');
                                        const sniperTower = document.getElementById('sniper-tower-button');


                                        function actionClickNormalTower(e) {
                                            addingTower('normal', x, y)
                                            normalTower.removeEventListener("click", actionClickNormalTower);
                                            freezeTower.removeEventListener("click", actionClickFreezeTower);
                                            sniperTower.removeEventListener("click", actionClickSniperTower);
                                        }

                                        function actionClickFreezeTower(e) {
                                            addingTower('freeze', x, y)
                                            normalTower.removeEventListener("click", actionClickNormalTower);
                                            freezeTower.removeEventListener("click", actionClickFreezeTower);
                                            sniperTower.removeEventListener("click", actionClickSniperTower);
                                        }

                                        function actionClickSniperTower(e) {
                                            addingTower('sniper', x, y)
                                            normalTower.removeEventListener("click", actionClickNormalTower);
                                            freezeTower.removeEventListener("click", actionClickFreezeTower);
                                            sniperTower.removeEventListener("click", actionClickSniperTower);
                                        }

                                        normalTower.addEventListener("click", actionClickNormalTower);
                                        freezeTower.addEventListener("click", actionClickFreezeTower);
                                        sniperTower.addEventListener("click", actionClickSniperTower);


                                    } else {
                                        board[y][x].is_clicked = false;
                                    }

                                } else {
                                    alert("You cannot place a new building here because it's not in your radius")
                                }
                            },
                            error: function (error) {
                                console.log(error);
                            },
                        })



                    }


                }
            }
        }

        drawInit(c);

    })


    const killerUnitButton = document.getElementById('killer-unit-button')
    const fastUnitButton = document.getElementById('fast-unit-button')
    const flightUnitButton = document.getElementById('flight-unit-button')

    killerUnitButton.addEventListener('click', () => {
        addingNewUnit('killer')
    });

    fastUnitButton.addEventListener('click', () => {
        addingNewUnit('fast')
    });

    flightUnitButton.addEventListener('click', () => {
        addingNewUnit('flight')
    });

    window.addEventListener('resize', () => {
        drawInit(c);
    })

})


function addingNewUnit(nameUnit) {



    if (confirm("Do you want to add a new " + nameUnit + " ?")) {
        $.ajax({
            url: 'http://localhost:8080/manager/add-' + nameUnit + '-unit',
            type: 'POST',
            contentType: "application/json",
            data: JSON.stringify({
                position: {
                    x: 0,
                    y: 0,
                },
                playingPlayer: CURRENT_PLAYER
            }),
            async: false,
            success: function (data) {

                if (data) {
                    getDatasFromGameEngine().then(() => {
                        resetBoard(c);
                        drawInit(c);
                    });
                } else {
                    alert("You don't have enough gold");
                }

            },
            error: function (error) {
                console.log(error);
            },
        })

    }





}





function addingTower(nameTower, x, y) {
    alert("You placed a " + nameTower + " Tower")

    $.ajax({
        url: 'http://localhost:8080/manager/add-' + nameTower + '-tower',
        type: 'POST',
        contentType: "application/json",
        async: false,
        data: JSON.stringify({
            position: {
                x: x,
                y: y,
            },
            playingPlayer: CURRENT_PLAYER
        }),
        success: function (data) {

            if (data) {
                getDatasFromGameEngine().then(() => {
                    resetBoard(c);
                    drawInit(c);
                });
            } else {
                alert("You don't have enough gold");
            }

        },
        error: function (error) {
            console.log(error);
        },
    })


}












async function getDatasFromGameEngine() {

    await $.ajax({
        url: 'http://localhost:8080/manager/game-infos',
        type: 'GET',
        contentType: "application/json",
        async: false,
        success: function (data) {
            gameInfos = data;
            SIZE_BOARD = gameInfos.board.dimension.width;
            obstacles = gameInfos.board.obstacles;
            player1Infos = gameInfos.player1;
            player2Infos = gameInfos.player2;
            SIZE_ONE_TILE = (canvasSize / SIZE_BOARD)
            CURRENT_PLAYER = gameInfos.player1.playing ? 1 : 2;
        },
        error: function (error) {
            console.log(error);
        },
    })

}




















// ****************************************************************************************************************** //
// *************************************************** PLAYER ******************************************************* //
// ****************************************************************************************************************** //


function changePlayer(playerNumber) {

    CURRENT_PLAYER = playerNumber;

    if (CURRENT_PLAYER === 1) {
        playerColor = PLAYER_1_COLOR;
    } else {
        playerColor = PLAYER_2_COLOR;
    }


    document.getElementById('game-page-header-player').textContent = "Player " + CURRENT_PLAYER;
    document.getElementById('game-page-header-coins').style.backgroundColor = playerColor;
}






// ****************************************************************************************************************** //
// *************************************************** BOARD ******************************************************** //
// ****************************************************************************************************************** //









function resetBoard() {
    board = [];

    const square_length = (canvasSize + 2) / (SIZE_BOARD)

    for(let i = 0; i < SIZE_BOARD; i++) {
        board.push(Array(SIZE_BOARD).fill(0));
    }


    let index = 0;
    for (let y = 0; y < SIZE_BOARD; y++) {
        for (let x = 0; x < SIZE_BOARD; x++) {

            const x_top_left = x * square_length
            const y_top_left = y * square_length

            const x_bottom_right = square_length + x_top_left
            const y_bottom_right = square_length + y_top_left

            board[y][x] = {
                index: index,
                is_clicked: false,
                x_bottom_right: x_bottom_right,
                y_bottom_right: y_bottom_right,
                x_top_left: x_top_left,
                y_top_left: y_top_left,
                number_of_monsters: 0,
                player1_fast_soldier: [],
                player1_killer_soldier: [],
                player1_flight_soldier: [],
                player2_fast_soldier: [],
                player2_killer_soldier: [],
                player2_flight_soldier: [],
                is_player1_normal_tower: false,
                is_player1_freeze_tower: false,
                is_player1_sniper_tower: false,
                is_player2_normal_tower: false,
                is_player2_freeze_tower: false,
                is_player2_sniper_tower: false,
                is_castle_player1: false,
                is_castle_player2: false,
                is_gold_mine_player1: false,
                is_gold_mine_player2: false,
                is_obstacle: false,
            }
            index += 1;
        }
    }



    let obstacle;
    for (obstacle of obstacles) {


        board[obstacle.position.y][obstacle.position.x].is_obstacle = true;

    }

    board[player1Infos.castle.position.y][player1Infos.castle.position.x].is_castle_player1 = true;
    board[player2Infos.castle.position.y][player2Infos.castle.position.x].is_castle_player2 = true;



    let entity;
    for (entity of player1Infos.entities) {

        if (entity.name === "freeze_tower_entity") {
            board[entity.position.y][entity.position.x].is_player1_freeze_tower = true;
        }

        if (entity.name === "normal_tower_entity") {
            board[entity.position.y][entity.position.x].is_player1_normal_tower = true;
        }

        if (entity.name === "sniper_tower_entity") {
            board[entity.position.y][entity.position.x].is_player1_sniper_tower = true;
        }

        if (entity.name === "goldmine_entity") {
            board[entity.position.y][entity.position.x].is_gold_mine_player1 = true;
        }

        if (entity.name === "fast_soldier_entity") {
            board[entity.position.y][entity.position.x].player1_fast_soldier.push(entity.healthPoint);
        }

        if (entity.name === "flight_soldier_entity") {
            board[entity.position.y][entity.position.x].player1_flight_soldier.push(entity.healthPoint);
        }

        if (entity.name === "killer_soldier_entity") {
            board[entity.position.y][entity.position.x].player1_killer_soldier.push(entity.healthPoint);
        }
    }

    for (entity of player2Infos.entities) {

        if (entity.name === "freeze_tower_entity") {
            board[entity.position.y][entity.position.x].is_player2_freeze_tower = true;
        }

        if (entity.name === "normal_tower_entity") {
            board[entity.position.y][entity.position.x].is_player2_normal_tower = true;
        }

        if (entity.name === "sniper_tower_entity") {
            board[entity.position.y][entity.position.x].is_player2_sniper_tower = true;
        }

        if (entity.name === "goldmine_entity") {
            board[entity.position.y][entity.position.x].is_gold_mine_player2 = true;
        }

        if (entity.name === "fast_soldier_entity") {
            board[entity.position.y][entity.position.x].player2_fast_soldier.push(entity.healthPoint);
        }

        if (entity.name === "flight_soldier_entity") {
            board[entity.position.y][entity.position.x].player2_flight_soldier.push(entity.healthPoint);
        }

        if (entity.name === "killer_soldier_entity") {
            board[entity.position.y][entity.position.x].player2_killer_soldier.push(entity.healthPoint);
        }
    }



}





// ****************************************************************************************************************** //
// *************************************************** DRAW BOARD ********************************************************* //
// ****************************************************************************************************************** //


function drawInit(canvas) {

    const amountOfGoldHTML = document.getElementById('amount-of-gold');

    if (CURRENT_PLAYER === 1) {
        amountOfGoldHTML.textContent = gameInfos.player1.currentGold + ' GLD';
    } else {
        amountOfGoldHTML.textContent = gameInfos.player2.currentGold + ' GLD';
    }


    canvas.setAttribute('width', canvasSize+'px');
    canvas.setAttribute('height', canvasSize+'px');

    let ctx = canvas.getContext('2d')
    let size = canvas.offsetWidth;
    ctx.fillStyle = 'black';
    ctx.fillRect(0, 0, size, size);

    for (let y = 0; y < SIZE_BOARD; y++) {
        for (let x = 0; x < SIZE_BOARD; x++) {




            let isNeededToDrawRect = true;
            const x_top_left = board[y][x].x_top_left - 1
            const y_top_left = board[y][x].y_top_left - 1
            const length = board[y][x].y_bottom_right - board[y][x].y_top_left - 1
            ctx.fillStyle = 'white';


            if (board[y][x].is_clicked) {
                ctx.fillStyle = 'red';
                isNeededToDrawRect = true;
            }

            if (board[y][x].is_player1_normal_tower) {

                ctx.fillStyle = PLAYER_1_COLOR;
                ctx.fillRect(x_top_left, y_top_left, length, length);
                ctx.fillStyle = '#82DA7B';
                ctx.fillRect(x_top_left + 20, y_top_left + 20, length-40, length-40);
                isNeededToDrawRect = false;
            }

            if (board[y][x].is_player2_normal_tower) {

                ctx.fillStyle = PLAYER_2_COLOR;
                ctx.fillRect(x_top_left, y_top_left, length, length);
                ctx.fillStyle = '#82DA7B';
                ctx.fillRect(x_top_left + 20, y_top_left + 20, length-40, length-40);
                isNeededToDrawRect = false;
            }

            if (board[y][x].is_player1_freeze_tower) {

                ctx.fillStyle = PLAYER_1_COLOR;
                ctx.fillRect(x_top_left, y_top_left, length, length);
                ctx.fillStyle = '#9CE5DC';
                ctx.fillRect(x_top_left + 20, y_top_left + 20, length-40, length-40);
                isNeededToDrawRect = false;
            }

            if (board[y][x].is_player2_freeze_tower) {

                ctx.fillStyle = PLAYER_2_COLOR;
                ctx.fillRect(x_top_left, y_top_left, length, length);
                ctx.fillStyle = '#9CE5DC';
                ctx.fillRect(x_top_left + 20, y_top_left + 20, length-40, length-40);
                isNeededToDrawRect = false;
            }

            if (board[y][x].is_player1_sniper_tower) {

                ctx.fillStyle = PLAYER_1_COLOR;
                ctx.fillRect(x_top_left, y_top_left, length, length);
                ctx.fillStyle = '#ff0013';
                ctx.fillRect(x_top_left + 20, y_top_left + 20, length-40, length-40);
                isNeededToDrawRect = false;
            }

            if (board[y][x].is_player2_sniper_tower) {

                ctx.fillStyle = PLAYER_2_COLOR;
                ctx.fillRect(x_top_left, y_top_left, length, length);
                ctx.fillStyle = '#ff0010';
                ctx.fillRect(x_top_left + 20, y_top_left + 20, length-40, length-40);
                isNeededToDrawRect = false;
            }

            if (board[y][x].is_castle_player1) {
                ctx.fillStyle = PLAYER_1_COLOR;
                isNeededToDrawRect = true;
            }

            if (board[y][x].is_castle_player2) {
                ctx.fillStyle = PLAYER_2_COLOR;
                isNeededToDrawRect = true;
            }


            if (board[y][x].is_gold_mine_player1) {
                ctx.fillStyle = PLAYER_1_COLOR;
                ctx.fillRect(x_top_left, y_top_left, length, length);
                ctx.fillStyle = '#FFB200';
                ctx.fillRect(x_top_left + 20, y_top_left + 20, length-40, length-40);
                isNeededToDrawRect = false;
            }

            if (board[y][x].is_gold_mine_player2) {
                ctx.fillStyle = PLAYER_2_COLOR;
                ctx.fillRect(x_top_left, y_top_left, length, length);
                ctx.fillStyle = '#FFB200';
                ctx.fillRect(x_top_left + 20, y_top_left + 20, length-40, length-40);
                isNeededToDrawRect = false;
            }

            if (board[y][x].is_obstacle) {
                ctx.fillStyle = '#7F818F';
                isNeededToDrawRect = true;
            }

            /*

            if (board[y][x].player1_fast_soldier !== [] || board[y][x].player1_flight_soldier !== [] || board[y][x].player1_killer_soldier !== []) {

                ctx.beginPath();
                ctx.fillStyle = PLAYER_1_COLOR;
                ctx.arc(x_top_left + (SIZE_ONE_TILE/2) , y_top_left + (SIZE_ONE_TILE/2), (SIZE_ONE_TILE/4), 0, Math.PI, true);
                ctx.fill();

                isNeededToDrawRect = false;
            }

            if (board[y][x].player2_fast_soldier !== [] || board[y][x].player2_flight_soldier !== [] || board[y][x].player2_killer_soldier !== []) {

                ctx.beginPath();
                ctx.fillStyle = PLAYER_2_COLOR;
                ctx.arc(x_top_left + (SIZE_ONE_TILE/2) , y_top_left + (SIZE_ONE_TILE/2), (SIZE_ONE_TILE/4), 0, Math.PI, false);
                ctx.fill();

                isNeededToDrawRect = false;
            } */

            if (isNeededToDrawRect) {
                ctx.fillRect(x_top_left, y_top_left, length, length);
                ctx.fill();
            }
        }
    }
}

function getCursorPosition(canvas, event) {
    const rect = canvas.getBoundingClientRect()
    const x = event.clientX - rect.left
    const y = event.clientY - rect.top
    return [x, y]
}

function isInTheRectangle(mouseX, mouseY, rectangleXTopLeft, rectangleYTopLeft, rectangleXBottomRight, rectangleYBottomRight) {

    const cond1 = mouseX >= rectangleXTopLeft;
    const cond2 = mouseX < rectangleXBottomRight;
    const cond3 = mouseY >= rectangleYTopLeft;
    const cond4 = mouseY < rectangleYBottomRight;

    return cond1 && cond2 && cond3 && cond4
}



function initializePrice() {

    document.getElementById('symbol-KIU').textContent = 'KIU - ' + gameInfos.settings.killerSoldierSettings.initialHealthPoints + 'HP'
    document.getElementById('price-KIU').textContent = gameInfos.settings.killerSoldierSettings.price + 'GLD'
    document.getElementById('symbol-FAU').textContent = 'FAU - ' + gameInfos.settings.fastSoldierSettings.initialHealthPoints + 'HP'
    document.getElementById('price-FAU').textContent = gameInfos.settings.fastSoldierSettings.price+ 'GLD'
    document.getElementById('symbol-FLU').textContent = 'FLU - ' + gameInfos.settings.flightSoldierSettings.initialHealthPoints + 'HP'
    document.getElementById('price-FLU').textContent = gameInfos.settings.flightSoldierSettings.price+ 'GLD'

    document.getElementById('price-NOT').textContent = gameInfos.settings.normalTowerSettings.price + 'GLD'
    document.getElementById('price-FRT').textContent = gameInfos.settings.freezeTowerSettings.price + 'GLD'
    document.getElementById('price-SNT').textContent = gameInfos.settings.sniperTowerSettings.price + 'GLD'


    document.getElementById('goldmine-price').textContent = gameInfos.settings.goldSettings.priceOfGoldMine + 'GLD'
}
