package game.board.entities.playerentities.building.towers;

import game.board.Board;
import game.board.Tile;
import game.board.entities.Entity;
import game.board.entities.playerentities.building.BuildingEntity;
import game.board.entities.playerentities.soldiers.Soldier;
import game.utils.Position;

/**
 * @author D'Andréa William
 *
 * - Percentage of initial construction cost for tower removal
 */
public class Tower extends BuildingEntity {

    private int shootingRange;
    private int simultaneousStrike;
    private int initialAttactPoint=1;

    /**
     * Tower entity extended from BuildingEntity
     * @param position
     * @param owner
     * @param shootingRange
     * The shooting range of the tower
     * @param simultaneousStrike
     * The number of soldier it can attack at one time
     * @param percentageRewardIfDestroyed
     * @param healthPoint
     */
    public Tower(Position position, String owner float price, int shootingRange, int simultaneousStrike, float percentageRewardIfDestroyed, int healthPoint) {
        super(position, owner, price, percentageRewardIfDestroyed);

        this.shootingRange = shootingRange;
        this.simultaneousStrike = simultaneousStrike;
    }

    public void attack(Board board){
        for (Tile tile : board.getTiles()) {
            for (Entity entity: tile.getEntitiesOnTheTile()) {
                if(entity instanceof Soldier && entity.getOwner() != this.getOwner()){
                    ((Soldier) entity).gotHit(1);
                }
            }
        }
    }

    public int getShootingRange() {
        return shootingRange;
    }

    public void setShootingRange(int shootingRange) {
        this.shootingRange = shootingRange;
    }

    public int getSimultaneousStrike() {
        return simultaneousStrike;
    }

    public void setSimultaneousStrike(int simultaneousStrike) {
        this.simultaneousStrike = simultaneousStrike;
    }


}
