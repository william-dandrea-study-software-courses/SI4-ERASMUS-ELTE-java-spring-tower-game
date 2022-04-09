package com.softwaretechnology.tourgame.theknigh.service.game.board.entities.gameentities.monsters;

import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.gameentities.GameEntity;
import com.softwaretechnology.tourgame.theknigh.service.game.utils.Position;


/**
 * @author D'Andréa William
 *
 * - Turn interval for monsters to arrive
 * - Number of monsters on the map
 */
public class Monster extends GameEntity {


    private String name = "monster_entity";

    public Monster(Position position) {
        super(position);
    }

    @Override
    public String getName() {
        return name;
    }
}
