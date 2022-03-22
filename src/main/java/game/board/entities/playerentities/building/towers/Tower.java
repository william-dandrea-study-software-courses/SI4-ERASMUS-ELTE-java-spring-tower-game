package game.board.entities.playerentities.building.towers;

import game.board.entities.playerentities.building.BuildingEntity;
import game.utils.Position;
import game.board.entities.Entity;

/**
 * @author D'Andréa William
 *
 * - Percentage of initial construction cost for tower removal
 */
public class Tower extends BuildingEntity {

    private int shootingRange;
    private int simultaneousStrike;
    private int damageToSoldiers;
    private int level = 1;
    private int maxLevel = 3;
    private Entity target;
    private int refundPrice;
    private int upgradePrice;


    public Tower(Position position, float price, int shootingRange, int simultaneousStrike, int damageToSoldiers, int refundPrice, int upgradePrice, float percentageRewardIfDestroyed) {
        super(position, price, percentageRewardIfDestroyed);

        this.shootingRange = shootingRange;
        this.simultaneousStrike = simultaneousStrike;
        this.damageToSoldiers = damageToSoldiers;
        this.refundPrice = refundPrice;
        this.upgradePrice = upgradePrice;
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

    public int getDamageToSoldiers() { return damageToSoldiers; }

    public void setDamageToSoldiers(int damageToSoldiers) { this.damageToSoldiers = damageToSoldiers; }

    public int getLevel() { return level; }

    public Entity getTarget() { return target; }

    public void setTarget(Entity target) { this.target = target; }

    public int getRefundPrice() { return refundPrice; }

    public void setRefundPrice(int refundPrice) { this.refundPrice = refundPrice; }


    public boolean upgrade() {
        if(level < maxLevel){
            level++;
            shootingRange++;
            simultaneousStrike++;
            damageToSoldiers++;
            //player.currentGold -= upgradePrice;
            return true;
        }
        return false;
    }

    public void refundTower() {
        //player.currentGold += refundPrice;
    }


}
