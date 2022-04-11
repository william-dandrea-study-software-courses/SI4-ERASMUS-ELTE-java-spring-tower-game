package com.softwaretechnology.tourgame.theknigh.service.game.board.entities.gameentities;

import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.Entity;
import com.softwaretechnology.tourgame.theknigh.service.game.utils.Position;


/**
 * @author D'Andréa William
 * GameEntity type drived from Entity type
 */
public class GameEntity extends Entity {

    public GameEntity(Position position) {
        super(position);
    }

    @Override
    public String getName() {
        return "game_entity";
    }
}
