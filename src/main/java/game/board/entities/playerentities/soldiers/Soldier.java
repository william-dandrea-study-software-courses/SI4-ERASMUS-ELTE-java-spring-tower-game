package game.board.entities.playerentities.soldiers;

import game.board.entities.playerentities.PlayerEntity;
import game.utils.Position;

/**
 * @author D'Andréa William
 */
public class Soldier extends PlayerEntity {

    private int healthPoint;
    private int numberOfMoveAtEachRound;
    private int killRewards;
    private boolean isAlive;


    public Soldier(Position position, double price, int healthPoint) {
        super(position, price);
        this.healthPoint = healthPoint;
        this.isAlive = true;
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

    public void move(){
        if(numberOfMoveAtEachRound != 0){

        }
    }
}
