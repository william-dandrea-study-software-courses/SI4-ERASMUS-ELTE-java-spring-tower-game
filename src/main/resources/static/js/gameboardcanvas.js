SIZE_BOARD = 15
const c = document.querySelector('.game-board-canvas');

let board = [];
const canvasSize = 700;

function resetBoard() {
    board = [];

    const square_length = canvasSize / SIZE_BOARD

    for(let i = 0; i < SIZE_BOARD; i++) {
        board.push(Array(SIZE_BOARD).fill(0));
    }


    let index = 0;
    for (let y = 0; y < SIZE_BOARD; y++) {
        for (let x = 0; x < SIZE_BOARD; x++) {

            const x_top_left = x * square_length
            const y_top_left = y * square_length
            const x_bottom_right = square_length + (x * square_length)
            const y_bottom_right = square_length + (y * square_length)

            board[y][x] = {
                index: index,
                is_clicked: false,
                x_bottom_right: x_bottom_right,
                y_bottom_right: y_bottom_right,
                x_top_left: x_top_left,
                y_top_left: y_top_left,
                number_of_monsters: 0,
                number_of_player1_fast_soldier: 0,
                number_of_player1_killer_soldier: 0,
                number_of_player1_flight_soldier: 0,
                number_of_player2_fast_soldier: 0,
                number_of_player2_killer_soldier: 0,
                number_of_player2_flight_soldier: 0,
                is_player1_normal_tower: false,
                is_player1_freeze_tower: false,
                is_player1_sniper_tower: false,
                is_player2_normal_tower: false,
                is_player2_freeze_tower: false,
                is_player2_sniper_tower: false,
                is_castle: false,
                is_gold_mine: false,
                is_obstacle: false,
            }
            index += 1;
        }
    }


}

function draw(canvas) {


    canvas.setAttribute('width', canvasSize+'px');
    canvas.setAttribute('height', canvasSize+'px');

    let ctx = canvas.getContext('2d')
    let size = canvas.offsetWidth;
    let square = (size-(SIZE_BOARD + 1))/SIZE_BOARD;
    ctx.fillStyle = 'black';
    ctx.fillRect(0, 0, size, size);

    for (let y = 0; y < SIZE_BOARD; y++) {
        for (let x = 0; x < SIZE_BOARD; x++) {


            const x_left = (x*(square+1))+1;
            const y_top = (y*(square+1))+1;

            ctx.fillStyle = 'white';
            ctx.fillRect(x_left, y_top, square, square);
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


resetBoard(c);
console.log(board[0][0])




draw(c);



c.addEventListener('mousedown', function(e) {
    let [xMouse, yMouse] = getCursorPosition(c, e)
    console.log(xMouse,yMouse)

    for (let y = 0; y < SIZE_BOARD; y++) {
        for (let x = 0; x < SIZE_BOARD; x++) {

            if (isInTheRectangle(xMouse, yMouse, board[y][x].x_top_left, board[y][x].y_top_left, board[y][x].x_bottom_right, board[y][x].y_bottom_right)) {
                board[y][x].is_clicked = !board[y][x].is_clicked
            }
        }
    }

    draw(c)

})

window.addEventListener('resize', () => {
    draw(c);
})



