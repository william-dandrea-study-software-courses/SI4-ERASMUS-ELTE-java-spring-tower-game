package game.board.entities.playerentities.building.towers;

import game.board.Board;
import game.board.Tile;
import game.board.entities.Entity;
import game.board.entities.playerentities.soldiers.Soldier;
import game.gamemanaging.Player;
import game.utils.Position;

/**
 * @author D'Andréa William
 */
public class FreezeTower extends Tower {

    private int numberOfTurnWhereTheSoldierIsFreeze;

    /**
     * The freeze tower entity extended from Tower
     * @param position
     * @param price
     * @param shootingRange
     * @param simultaneousStrike
     * @param damageToSoldiers
     * @param numberOfTurnWhereTheSoldierIsFreeze
     * number of turn where the soldier in the range is frozen
     */
    public FreezeTower(Position position, Player owner, int price, int shootingRange, int simultaneousStrike, int damageToSoldiers, int numberOfTurnWhereTheSoldierIsFreeze) {
        super(position, owner, price, shootingRange, simultaneousStrike, damageToSoldiers);
        this.numberOfTurnWhereTheSoldierIsFreeze = numberOfTurnWhereTheSoldierIsFreeze;
    }

    @Override
    public void attack(Board board){
        for (int i=0; i<=board.getDimension().getLength(); i++) {
            for (int j=0;j<=board.getDimension().getWidth();j++) {
                Tile tile = board.getTile(i,j);
                if(this.manhattanDistance(tile)<=this.getShootingRange()){
                    int strikeNum = 0;
                    for(Entity entity: tile.getEntitiesOnTheTile()){
                        if (entity instanceof Soldier && entity.getOwner() != this.getOwner()) {
                            ((Soldier) entity).gotHit(this.getDamageToSoldier());
                            strikeNum++;
                        }
                        if(strikeNum >= this.getSimultaneousStrike()){return;}
                    }
                }
            }
        }
    }


    public int getNumberOfTurnWhereTheSoldierIsFreeze() {
        return numberOfTurnWhereTheSoldierIsFreeze;
    }

    public void setNumberOfTurnWhereTheSoldierIsFreeze(int numberOfTurnWhereTheSoldierIsFreeze) {
        this.numberOfTurnWhereTheSoldierIsFreeze = numberOfTurnWhereTheSoldierIsFreeze;
    }

}
