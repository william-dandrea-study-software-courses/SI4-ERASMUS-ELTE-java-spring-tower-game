package game.board.entities.playerentities.soldiers;

import game.board.entities.gameentities.castles.Castle;
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
    private int frozenRound = 0;

    /**
     * Soldier entity extended from PlayerEntity
     * @param position
     * @param price
     * @param healthPoint
     * health point of the soldier
     */
    public Soldier(Position position, Player owner, int price, int healthPoint, int numberOfMoveAtEachRound) {
        super(position, owner, price);
        this.healthPoint = healthPoint;
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

    public int getFrozenRound() { return frozenRound; }

    public void setFrozenRound(int frozenRound) { this.frozenRound = frozenRound; }

    //    public boolean isAlive() {
//        if(healthPoint <= 0){
//            isAlive = false;
//            return false;
//        }
//        return isAlive;
//    }

    public void gotHit(int damage){
        this.healthPoint -= damage;
    }

    public void gotFrozen(int frozenRound){
        this.frozenRound = frozenRound;
    }
    /**
     * @author Andreas Tsironis
     * */
//    public void move(Entity destination){
//
//        this.pathfinding(destination);
//        Tile nextTile;
//        int lastElement= this.getPath().size() - 1 ;
//        if(this.numberOfMoveAtEachRound != 0){
//
//            nextTile = this.getNextTilePath();
//            /**it is basically copies an exact copy of the object at the next tile and deletes it from the previous tile*/
//            nextTile.addEntityOnTheTile(this);
//            this.getTile().removeEntityOnTheTile(this);
//            /**At path,we have the last element as the next one.*/
//            this.getPath().remove(lastElement);
//            this.numberOfMoveAtEachRound--;
//
//        }
//    }

    /**
     * Alternative move method
     */
    public void move(Entity destination){

        this.pathfinding(destination);
        Tile nextTile;
        if(this.frozenRound == 0){
            while(this.numberOfMoveThisRound != 0){
                int lastElement= this.getPath().size() - 1 ;
                nextTile = this.getNextTilePath();
                /**it is basically copies an exact copy of the object at the next tile and deletes it from the previous tile*/
                nextTile.addEntityOnTheTile(this);
                this.getTile().removeEntityOnTheTile(this);
                /**At path,we have the last element as the next one.*/
                this.getPath().remove(lastElement);
                this.numberOfMoveThisRound--;

            }
            // Reset number of movement for next round
            this.numberOfMoveThisRound = this.numberOfMoveAtEachRound;
        }
        // If the soldier is frozen, skip the movement and minus the frozenRound by 1
        if(this.frozenRound>0){
            this.frozenRound -= 1;
        }

    }

    /**
     * @author Tian Zhenman
     * The enemy castle
     * @return
     * Whether the soldier arrived the enemy castle, if arrived let the castle's health points minus 1 and return true, else return false
     */
    public boolean arriveCastle(){
        if(this.getPath() == null){
            return true;
        }
        return false;
    }

}
