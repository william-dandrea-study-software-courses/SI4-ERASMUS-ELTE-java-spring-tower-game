package game.board;


import game.utils.BoardDimension;
import game.utils.Position;

import java.util.List;
import java.util.Optional;

/**
 * @author D'Andréa William
 */
public class Board {

    private final BoardDimension dimension;
    private final List<Tile> tiles;


    public Board(BoardDimension dimension, List<Tile> tiles) {
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
