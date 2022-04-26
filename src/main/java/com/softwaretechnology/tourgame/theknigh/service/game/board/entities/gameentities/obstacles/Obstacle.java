package com.softwaretechnology.tourgame.theknigh.service.game.board.entities.gameentities.obstacles;


import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.gameentities.GameEntity;
import com.softwaretechnology.tourgame.theknigh.service.game.utils.Position;

/**
 * @author D'Andréa William
 *
 * - Number of obstacles
 * - Radius of obstacles
 */
public class Obstacle extends GameEntity {

    private String name = "obstacle_entity";

    public Obstacle(Position position) {
        super(position);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Obstacle{" +
                "position='" + getPosition() + '\'' +
                '}';
    }
}


