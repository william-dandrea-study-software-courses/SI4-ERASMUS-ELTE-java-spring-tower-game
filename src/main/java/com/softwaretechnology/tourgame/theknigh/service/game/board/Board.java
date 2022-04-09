package com.softwaretechnology.tourgame.theknigh.service.game.board;



import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.gameentities.obstacles.Obstacle;
import com.softwaretechnology.tourgame.theknigh.service.game.utils.BoardDimension;
import com.softwaretechnology.tourgame.theknigh.service.game.utils.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author D'Andréa William
 */
public class Board {

    private final BoardDimension dimension;
    private final List<Tile> tiles;

    private List<Obstacle> obstacles;


    public Board(BoardDimension dimension, List<Obstacle> obstacles) {
        this.dimension = dimension;
        this.tiles = new ArrayList<>();

        for (int y = 0; y < this.dimension.getLength(); y++) {
            for (int x = 0; x < this.dimension.getWidth(); x++) {
                this.tiles.add(new Tile(new Position(x, y)));
            }
        }

        this.obstacles = obstacles;
    }

    public BoardDimension getDimension() {
        return dimension;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public Optional<Tile> tileAtPosition(Position position) {
        return this.tiles.stream().filter(p -> p.getPosition().getX() == position.getX() &&  p.getPosition().getY() == position.getY()).findAny();
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }
}
