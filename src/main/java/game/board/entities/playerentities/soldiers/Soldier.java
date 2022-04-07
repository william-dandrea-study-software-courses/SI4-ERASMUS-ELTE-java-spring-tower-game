package game.board.entities.playerentities.soldiers;

import game.board.entities.playerentities.PlayerEntity;
import game.gamemanaging.Player;
import game.utils.Position;
import game.board.Tile;
import game.board.entities.Entity;
/**
 * @author D'Andréa William
 */
public class Soldier extends PlayerEntity {

    private int healthPoint;
    private int numberOfMoveAtEachRound;
    private int numberOfMoveThisRound;
    private int killRewards;
    private boolean isAlive;

    /**
     * Soldier entity extended from PlayerEntity
     * @param position
     * @param price
     * @param healthPoint
     * health point of the soldier
     */
    public Soldier(Position position, Player owner, double price, int healthPoint, int numberOfMoveAtEachRound) {
        super(position, owner, price);
        this.healthPoint = healthPoint;
        this.isAlive = true;
        this.numberOfMoveAtEachRound=numberOfMoveAtEachRound;
        this.numberOfMoveThisRound=numberOfMoveAtEachRound;
    }


    public int getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public int getNumberOfMoveAtEachRound() {
        return numberOfMoveAtEachRound;
    }

    public void setNumberOfMoveAtEachRound(int numberOfMoveAtEachRound) {this.numberOfMoveAtEachRound = numberOfMoveAtEachRound; }

    public void refreshMovement(){this.numberOfMoveThisRound=numberOfMoveAtEachRound;}

    public int getKillRewards() { return killRewards; }

    public void setKillRewards(int killRewards) { this.killRewards = killRewards; }

    public boolean isAlive() {
        if(healthPoint <= 0){
            isAlive = false;
            return false;
        }
        return isAlive;
    }

    public void gotHit(int damage){
        this.healthPoint -= damage;
    }

    public void gotFrozen(){ this.numberOfMoveAtEachRound = 0; }
    /**
     * @author Andreas Tsironis
     * */
    public void move(Entity destination){

        this.pathfinding(destination);
        Tile nextTile;
        int lastElement= this.getPath().size() - 1 ;
        if(this.numberOfMoveAtEachRound != 0){

            nextTile = this.getNextTilePath();
            /**it is basically copies an exact copy of the object at the next tile and deletes it from the previous tile*/
            nextTile.addEntityOnTheTile(this);
            this.getTile().removeEntityOnTheTile(this);
            /**At path,we have the last element as the next one.*/
            this.getPath().remove(lastElement);
            this.numberOfMoveAtEachRound--;

        }
    }
}
