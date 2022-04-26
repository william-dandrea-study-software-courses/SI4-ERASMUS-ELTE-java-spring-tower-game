package com.softwaretechnology.tourgame.theknigh.service.game.utils;

import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.soldiers.Soldier;

/**
 * @author D'Andréa William
 */
public class FreezeSoldierInTheGame {

    public Position positionWhereHeIsFreeze;
    public int roundWhereTheSoldierWillBeUnfreeze;
    public Soldier freezeSoldier;


    public FreezeSoldierInTheGame(Position positionWhereHeIsFreeze, int roundWhereTheSoldierWillBeUnfreeze, Soldier freezeSoldier) {
        this.positionWhereHeIsFreeze = positionWhereHeIsFreeze;
        this.roundWhereTheSoldierWillBeUnfreeze = roundWhereTheSoldierWillBeUnfreeze;
        this.freezeSoldier = freezeSoldier;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FreezeSoldierInTheGame that = (FreezeSoldierInTheGame) o;

        if (positionWhereHeIsFreeze != null ? !positionWhereHeIsFreeze.equals(that.positionWhereHeIsFreeze) : that.positionWhereHeIsFreeze != null)
            return false;
        return freezeSoldier != null ? freezeSoldier.equals(that.freezeSoldier) : that.freezeSoldier == null;
    }

    @Override
    public int hashCode() {
        int result = positionWhereHeIsFreeze != null ? positionWhereHeIsFreeze.hashCode() : 0;
        result = 31 * result + (freezeSoldier != null ? freezeSoldier.hashCode() : 0);
        return result;
    }
}
