package com.softwaretechnology.tourgame.theknigh.service.game.board;



import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.Entity;
import com.softwaretechnology.tourgame.theknigh.service.game.utils.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * @author D'Andréa William
 */
public class Tile {

    private final Position position;
    // private final List<Entity> entitiesOnTheTile;


    public Tile(Position position) {
        this.position = position;
        // this.entitiesOnTheTile = new ArrayList<>();
    }


    public Position getPosition() {
        return position;
    }
    /*
    public List<Entity> getEntitiesOnTheTile() {
        return entitiesOnTheTile;
    }

    public void addEntityOnTheTile(Entity entity) {
        this.entitiesOnTheTile.add(entity);
    }*/

    @Override
    public String toString() {
        return "Tile{" +
                "position=" + position +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tile tile = (Tile) o;

        return position != null ? position.equals(tile.position) : tile.position == null;
    }

    @Override
    public int hashCode() {
        return position != null ? position.hashCode() : 0;
    }
}
