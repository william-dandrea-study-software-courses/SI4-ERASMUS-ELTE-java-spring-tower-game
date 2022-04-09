package com.softwaretechnology.tourgame.theknigh.service.game.board.entities.gameentities;

import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.Entity;
import com.softwaretechnology.tourgame.theknigh.service.game.utils.Position;


/**
 * @author D'Andréa William
 */
public class GameEntity extends Entity {

    private String name = "game_entity";

    public GameEntity(Position position) {
        super(position);
    }

    @Override
    public String getName() {
        return name;
    }
}
