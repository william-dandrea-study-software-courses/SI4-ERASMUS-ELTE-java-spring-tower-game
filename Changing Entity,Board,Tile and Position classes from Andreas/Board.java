package game.board;


import game.utils.BoardDimension;
import game.utils.Position;

import java.util.List;

import java.util.Optional;

/**
 * @author D'Andréa William
 */

public class Board{

    private static BoardDimension dimension;
    private static Tile board[][];

    public Board(BoardDimension dimension) {
        Board.dimension = dimension;
        
        for (int i=0;i<=Board.dimension.getLength();i++){
            for (int j=0;j<=Board.dimension.getWidth();j++){

                    board[i][j] = new Tile(new Position(i,j));
            }
        }

    }
    public static BoardDimension getDimension() {
        return dimension;
    }

    public static Tile getTile (Position position){

        return board[position.getX()][position.getY()];    
    }

    public static Tile getTile (int x,int y){

        return board[x][y];    
    }


}


 /*
public class Board {


  /*
    private final BoardDimension dimension;
    private final List<Tile> tiles;

    /**
     * The game board initiation
     * @param dimension
     * The dimension of the board (x, y)
     * @param tiles
     * Tiles on this board
     
  

    public Board(BoardDimension dimension) {
        this.dimension = dimension;
        this.tiles = tiles;
    }

    public BoardDimension getDimension() {
        return dimension;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    private Optional<Tile> tileAtPosition(Position position) {
        return this.tiles.stream().filter(p -> p.getPosition().getX() == position.getX() &&  p.getPosition().getY() == position.getY()).findAny();
    }
}
*/

