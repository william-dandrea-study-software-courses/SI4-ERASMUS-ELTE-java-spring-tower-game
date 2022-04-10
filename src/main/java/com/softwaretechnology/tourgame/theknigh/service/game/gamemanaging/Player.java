package com.softwaretechnology.tourgame.theknigh.service.game.gamemanaging;

import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.Entity;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.gameentities.castles.Castle;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.PlayerEntity;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.building.BuildingEntity;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.soldiers.Soldier;
import com.softwaretechnology.tourgame.theknigh.service.game.utils.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author D'Andréa William
 */
public class Player {

    private int id;
    private int currentGold;
    private Castle castle;
    private List<PlayerEntity> entities;
    private boolean isPlaying;


    public Player(int id, int currentGold, Castle castle) {
        this.id = id;
        this.currentGold = currentGold;
        this.castle = castle;
        this.entities = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public int getCurrentGold() {
        return currentGold;
    }

    public Castle getCastle() {
        return castle;
    }

    public List<PlayerEntity> getEntities() {
        return entities;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }


    public void decreaseCurrentGold(int amount) {
        this.currentGold -= amount;
    }

    public void increaseCurrentGold(int gldToAdd) {
        this.currentGold += gldToAdd;
    }


    public List<Entity> getCastleAndBuildingEntities() {

        List<Entity> bEntities = new ArrayList<>();
        // bEntities.add(castle);
        bEntities.addAll(this.entities.stream().filter(e -> e instanceof BuildingEntity).collect(Collectors.toList()));

        return bEntities;

    }


    public List<Soldier> getAllSoldiers() {

        List<PlayerEntity> soldiersP = this.entities.stream().filter(e -> e instanceof Soldier).collect(Collectors.toList());

        List<Soldier> soldiers = new ArrayList<>();

        for (PlayerEntity s : soldiersP) {
            if (s instanceof Soldier) {
                soldiers.add((Soldier) s);
            }
        }


        return soldiers;

    }



    public void removeSoldierAtThisPositon(Position position) {
        List<PlayerEntity> newEntities = new ArrayList<>();

        for (PlayerEntity entity : this.entities) {
            if (!entity.getPosition().equals(position)){
                newEntities.add(entity);
            }
        }

        this.entities = newEntities;
    }

}
