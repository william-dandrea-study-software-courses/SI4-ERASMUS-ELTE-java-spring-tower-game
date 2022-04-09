package com.softwaretechnology.tourgame.theknigh.service.game.board.entities;


import com.softwaretechnology.tourgame.theknigh.service.game.utils.Position;

/**
 * @author D'Andréa William
 */
public class Entity {

    private Position position;
    private String name = "Entity";


    public Entity(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }
}
