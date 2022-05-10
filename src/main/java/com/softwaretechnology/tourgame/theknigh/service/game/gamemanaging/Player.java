package com.softwaretechnology.tourgame.theknigh.service.game.gamemanaging;

import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.Entity;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.gameentities.castles.Castle;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.PlayerEntity;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.building.BuildingEntity;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.building.goldmines.GoldMine;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.building.towers.FreezeTower;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.building.towers.NormalTower;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.building.towers.SniperTower;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.building.towers.Tower;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.soldiers.FastSoldier;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.soldiers.FlightSoldier;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.soldiers.KillerSoldier;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.playerentities.soldiers.Soldier;
import com.softwaretechnology.tourgame.theknigh.service.game.utils.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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


    public List<BuildingEntity> getBuildingEntities() {

        List<Entity> bEntities = this.entities.stream().filter(e -> e instanceof BuildingEntity).collect(Collectors.toList());
        List<BuildingEntity> BuildingEntities = new ArrayList<>();

//        bEntities.addAll(this.entities.stream().filter(e -> e instanceof BuildingEntity).collect(Collectors.toList()));
        for (Entity s : bEntities) {
            if (s instanceof BuildingEntity) {
                BuildingEntities.add((BuildingEntity) s);
            }
        }

        return BuildingEntities;

    }

    public List<GoldMine> getAllGoldMines() {

        List<Entity> goldmineP = this.entities.stream().filter(e -> e instanceof GoldMine).collect(Collectors.toList());

        List<GoldMine> goldMines = new ArrayList<>();

        for (Entity s : goldmineP) {
            if (s instanceof GoldMine) {
                goldMines.add((GoldMine) s);
            }
        }

        return goldMines;
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

    public List<Tower> getAllTowers() {

        List<Entity> towersP = this.entities.stream().filter(e -> e instanceof Tower).collect(Collectors.toList());

        List<Tower> towers =  new ArrayList<>();

        for (Entity e : towersP) {
            if (e instanceof Tower) {
                towers.add((Tower) e);
            }
        }

        return towers;
    }


    public List<FreezeTower> getAllFreezeTowers() {

        List<FreezeTower> towers =  new ArrayList<>();

        for (Entity e : getAllTowers()) {
            if (e instanceof FreezeTower) {
                towers.add((FreezeTower) e);
            }
        }

        return towers;
    }

    public List<NormalTower> getAllNormalTowers() {

        List<NormalTower> towers =  new ArrayList<>();

        for (Entity e : getAllTowers()) {
            if (e instanceof NormalTower) {
                towers.add((NormalTower) e);
            }
        }

        return towers;
    }

    public List<SniperTower> getAllSniperTowers() {

        List<SniperTower> towers =  new ArrayList<>();

        for (Entity e : getAllTowers()) {
            if (e instanceof SniperTower) {
                towers.add((SniperTower) e);
            }
        }

        return towers;
    }

    public List<KillerSoldier> getAllKillerSoldiers() {

        List<KillerSoldier> soldiers =  new ArrayList<>();

        for (Entity e : getAllSoldiers()) {
            if (e instanceof KillerSoldier) {
                soldiers.add((KillerSoldier) e);
            }
        }

        return soldiers;
    }

    public List<FlightSoldier> getAllFlightSoldiers() {

        List<FlightSoldier> soldiers =  new ArrayList<>();

        for (Entity e : getAllSoldiers()) {
            if (e instanceof FlightSoldier) {
                soldiers.add((FlightSoldier) e);
            }
        }

        return soldiers;
    }

    public List<FastSoldier> getAllFastSoldiers() {

        List<FastSoldier> soldiers =  new ArrayList<>();

        for (Entity e : getAllSoldiers()) {
            if (e instanceof FastSoldier) {
                soldiers.add((FastSoldier) e);
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


    public List<Soldier> soldierAtThisPositions(List<Position> positions) {

        List<Soldier> soldierAtThisPosition = new ArrayList<>();

        for (Soldier soldier : this.getAllSoldiers()) {

            for (Position pos : positions) {
                if (pos.equals(soldier.getPosition())) {
                    soldierAtThisPosition.add(soldier);
                    break;
                }
            }
        }

        return soldierAtThisPosition;
    }


    public void removeHealthPointToSoldier(Soldier soldier, int numberOfHealthPointToRemove) {

        soldier.removeHealthPoints(numberOfHealthPointToRemove);

        if (soldier.getHealthPoint() <= 0) {
            this.entities.remove(soldier);
        }
    }



    public List<Soldier> getEntitiesInThisPosition(Position position) {

        List<Soldier> entitiesAtThisPosition = new ArrayList<>();

        for (Soldier entity : this.getAllSoldiers()) {

            if (entity.getPosition().equals(position)) {
                entitiesAtThisPosition.add(entity);
            }
        }

        return entitiesAtThisPosition;
    }
}
