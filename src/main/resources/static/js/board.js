// ****************************************************************************************************************** //
// *************************************************** PLAYER ******************************************************* //
// ****************************************************************************************************************** //
const PLAYER_1_COLOR = "#5970E9";
const PLAYER_2_COLOR = "#E95962";


let CURRENT_PLAYER = 1;
let playerColor = "#5970E9";

function changePlayer() {

    if (CURRENT_PLAYER === 1) {
        playerColor = PLAYER_1_COLOR;
    } else {
        playerColor = PLAYER_2_COLOR;
    }


    document.getElementById('game-page-header-player').textContent = "Player " + CURRENT_PLAYER;
    document.getElementById('game-page-header-coins').style.backgroundColor = playerColor;


}


changePlayer()



// ****************************************************************************************************************** //
// *************************************************** BOARD ******************************************************** //
// ****************************************************************************************************************** //
const c = document.querySelector('.game-board-canvas');

let board = [];
const canvasSize = 700;
const SIZE_BOARD = 18;
const SIZE_ONE_TILE = (canvasSize / SIZE_BOARD)



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
                player1_fast_soldier: [5, 10, 12, 20],
                player1_killer_soldier: [],
                player1_flight_soldier: [],
                player2_fast_soldier: [5, 10, 12, 20],
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
}


function drawInit(canvas) {


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

            ctx.fillStyle = 'white';
            ctx.fillRect(x_top_left, y_top_left, length, length);


            if (board[y][x].is_clicked) {
                ctx.fillStyle = 'red';
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

            if (board[y][x].player1_fast_soldier !== [] || board[y][x].player1_flight_soldier !== [] || board[y][x].player1_killer_soldier !== []) {

                ctx.beginPath();
                ctx.fillStyle = PLAYER_1_COLOR;
                ctx.arc(x_top_left + (SIZE_ONE_TILE/2) , y_top_left + (SIZE_ONE_TILE/2), (SIZE_ONE_TILE/3), 0, Math.PI, true);
                ctx.fill();



                isNeededToDrawRect = false;
            }

            if (board[y][x].player2_fast_soldier !== [] || board[y][x].player2_flight_soldier !== [] || board[y][x].player2_killer_soldier !== []) {

                ctx.beginPath();
                ctx.fillStyle = PLAYER_2_COLOR;
                ctx.arc(x_top_left + (SIZE_ONE_TILE/2) , y_top_left + (SIZE_ONE_TILE/2), (SIZE_ONE_TILE/3), 0, Math.PI, false);
                ctx.fill();


                isNeededToDrawRect = false;
            }














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



// ****************************************************************************************************************** //
// *************************************************** MAIN ********************************************************* //
// ****************************************************************************************************************** //


resetBoard(c);
drawInit(c);




c.addEventListener('mousedown', function(e) {
    let [xMouse, yMouse] = getCursorPosition(c, e)
    console.log(xMouse,yMouse)

    for (let y = 0; y < SIZE_BOARD; y++) {
        for (let x = 0; x < SIZE_BOARD; x++) {

            if (isInTheRectangle(xMouse, yMouse, board[y][x].x_top_left, board[y][x].y_top_left, board[y][x].x_bottom_right, board[y][x].y_bottom_right)) {
                console.log("Clicked in a case", board[y][x].index)
                board[y][x].is_clicked = !board[y][x].is_clicked
            }
        }
    }

    drawInit(c);


})

window.addEventListener('resize', () => {
    drawInit(c);
})









/*
let img = new Image();
            img.src = '../ressources/tower/freeze_tower_image.png';
            img.onload = function() {
                ctx.drawImage(img, x_top_left, y_top_left, SIZE_ONE_TILE - 20, SIZE_ONE_TILE - 20);
            };
 */
