package game.board;


import game.utils.BoardDimension;
import game.utils.Position;

import java.util.List;



/**
 * @author D'Andréa William
 * @author Andreas Tsironis
 */

public class Board{


/**
 * The game board initiation
 * @param dimension
 * The dimension of the board (x, y)
 * @param tiles
 * Tiles on this board
 **/
    /** We have only an instance of the Board, as it is the board the players use, but
     * the player can change the dimensions of the board at the settings. He would have to
     * use a function that calls Board(BoardDimension dimension) **/

    private static BoardDimension dimension;
    /** It is a static array, because after its initiation, we will have the same  amount of tiles
     * until the player chooses to change the dimensions*/
    private static Tile board[][];

    public Board(BoardDimension dimension) {
        Board.dimension = dimension;

        for (int i=0;i<=Board.dimension.getLength();i++){
            for (int j=0;j<=Board.dimension.getWidth();j++){

                /** We fill the board with empty tiles*/
                board[i][j] = new Tile(new Position(i,j));
            }
        }
        for (int i=0;i<=Board.dimension.getLength();i++){
            for (int j=0;j<=Board.dimension.getWidth();j++){

                /**Every tile knows its adjacent neighbours,for making the game quicker while playtime */
                board[i][j].initializeNeighbourTiles();

            }
        }

    }
    public static BoardDimension getDimension() {
        return dimension;
    }
    /** We know  the position as a whole type*/
    public static Tile getTile (Position position){

        return board[position.getX()][position.getY()];
    }
    /** We know  the position as a separate length and width */
    public static Tile getTile (int x,int y){

        return board[x][y];
    }


}