package game.board.entities;

import game.utils.Position;

/**
 * @author D'Andréa William
 */
public class Entity {

    private Position position;

    private boolean isDestructible;

    public Entity(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }


}
