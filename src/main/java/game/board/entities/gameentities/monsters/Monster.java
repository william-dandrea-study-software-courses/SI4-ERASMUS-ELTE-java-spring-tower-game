package game.board.entities.gameentities.monsters;

import game.board.Tile;
import game.board.entities.Entity;
import game.board.entities.gameentities.GameEntity;
import game.board.entities.playerentities.soldiers.Soldier;
import game.utils.Position;
import java.util.Random;

/**
 * @author D'Andréa William
 *
 * - Turn interval for monsters to arrive
 * - Number of monsters on the map
 */
public class Monster extends GameEntity {

    private int damage;
    private int speed;
    public Monster(Position position, int damage, int speed) {

        super(position);
        this.damage = damage;
        this.speed = speed;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void randomMove() {
        Tile nextTile = null;
        Random random = new Random();
        int step = random.nextInt(2);
        int leftRight = random.nextInt(2);
        int upDown = random.nextInt(2);
        if(step == 0){
            return;
        }
        while(nextTile == null){  /** Make sure not touch the boundary of the board */
            if(leftRight == 0 && upDown == 0){
                nextTile = this.getDirectionTile(-1, 1);
            }else if(leftRight == 1 && upDown == 0){
                nextTile = this.getDirectionTile(1, 1);
            }else if(leftRight == 0 && upDown == 1){
                nextTile = this.getDirectionTile(-1, -1);
            }else if(leftRight == 1 && upDown == 1){
                nextTile = this.getDirectionTile(1, -1);
            }
            leftRight = random.nextInt(2);
            upDown = random.nextInt(2);
        }
        nextTile.addEntityOnTheTile(this);
        this.getTile().removeEntityOnTheTile(this);
    }

    public void attack(){
        for(Entity entity: this.getTile().getEntitiesOnTheTile()){
            if (entity instanceof Soldier) {
                ((Soldier) entity).gotHit(1);
            }
        }
    }


}
