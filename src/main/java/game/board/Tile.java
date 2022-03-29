package game.board;

import game.board.entities.Entity;
import game.utils.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author D'Andréa William
 */
public class Tile {

    private final Position position;
    private final List<Entity> entitiesOnTheTile;

    /**
     * Initializing of tile
     * @param position
     * position of this tile
     * @param entitiesOnTheTile
     * Entities on this tile
     */
    public Tile(Position position) {
        this.position = position;
        this.entitiesOnTheTile = new ArrayList<>();
    }

    public Position getPosition() {
        return position;
    }

    public List<Entity> getEntitiesOnTheTile() {
        return entitiesOnTheTile;
    }

    public void addEntityOnTheTile(Entity entity) {
        this.entitiesOnTheTile.add(entity);
    }

    
}

    

