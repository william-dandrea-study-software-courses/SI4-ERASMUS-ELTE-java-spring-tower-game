package game.board.entities.playerentities.building.towers;

import game.board.Board;
import game.board.Tile;
import game.board.entities.Entity;
import game.board.entities.playerentities.building.BuildingEntity;
import game.board.entities.playerentities.soldiers.Soldier;
import game.gamemanaging.Player;
import game.utils.Position;

/**
 * @author D'Andréa William
 *
 * - Percentage of initial construction cost for tower removal
 */
public class Tower extends BuildingEntity {

    private int shootingRange;
    private int simultaneousStrike;
    private int damageToSoldier=1;

    /**
     * Tower entity extended from BuildingEntity
     * @param position
     * @param owner
     * @param shootingRange
     * The shooting range of the tower
     * @param simultaneousStrike
     * The number of soldier it can attack at one time
     */
    public Tower(Position position, Player owner, int price, int shootingRange, int simultaneousStrike, int damageToSoldier) {
        super(position, owner, price);

        this.shootingRange = shootingRange;
        this.simultaneousStrike = simultaneousStrike;
        this.damageToSoldier = damageToSoldier;
    }


    public void attack(Board board){
        for (int i=0; i<=board.getDimension().getLength(); i++) {
            for (int j=0;j<=board.getDimension().getWidth();j++) {
                Tile tile = board.getTile(i,j);
                for(Entity entity: tile.getEntitiesOnTheTile()){
                    if (entity instanceof Soldier && entity.getOwner() != this.getOwner()) {
                        ((Soldier) entity).gotHit(1);
                    }
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
