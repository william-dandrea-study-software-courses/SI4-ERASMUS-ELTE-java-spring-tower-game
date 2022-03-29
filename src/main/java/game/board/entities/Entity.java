package game.board.entities;

import game.board.Tile;
import game.utils.Position;
import java.utils.LinkedList;
import java.utils.Set;
import java.utils.Arrays;
/**
 * @author D'Andréa William
 */

/**
    @author Andreas Tsironis creating general pathfinding method.

*/
public class Entity {

    private Position position;
    /*private Tile tile                                */

    public Entity(/*Tile tile,*/Position position) {
        //this.tile = tile;
        //this.position=tile.getPosition();
        this.position = position;
    }
    //*I think the entity should save the tile and get the position from the tile*/
    public Position getPosition() {
        /*return tile.getPosition()*/
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    /**
    @author Andreas Tsironis 
    Creating pathfinding(Tile start,Tile destination) for using algorithm A* at situations that we cannot pass default entities
    and pathfinding(Tile start,Tile destination,String unblock_entity) for special occasion, like the flying soldier
    which is able to fly at obstacles 

    */

    public LinkedList<Entity> pathfinding(Tile start,Tile destination){

        Set<String> block_entities = new Set<String>(Arrays.asList("Obstacle","Tower")) ;

        return pathfinding(start,destination,block_entities);
    }

    public LinkedList<Entity> pathfinding(Tile start,Tile destination,String unblock_entity){

        Set<String> block_entities = new Set<String>(Arrays.asList("Obstacle","Tower")) ;
        block_entities.remove(unblock_entity);
        return pathfinding(start,destination,block_entities) ;
    }   
    


    private LinkedList<Entity> pathfinding(Tile start,Tile destination,Set<String> blockunpassable_entities){
/*Those are the nodes of the algorithm A*,which will we save the save the nescessary information. It is only used at the scope of the algorithm */
        class Algorithm_Node {
        
         Tile tile;
         Position position ;
         int gValue;
         int heuristicsValue;
         int fValue;
        Algorithm_Node parent;
        Algorithm_Node (tile tile,Algorithm_Node parent){
            this.tile   = tile;
            this.position = tile.position ;
            this.parent = parent;
            this.g_Value = parent.getgValue() + 1;
    
            this.heuristicsValue = tile.getDistanceFromAnotherEntity(start);
            this.fValue= g_Value+fValue
        }    
        /*The start node*/
         Algorithm_Node (tile tile){
            this.tile   = tile;
            this.position = tile.position ;
            this.g_Value = 0;
            this.parent = null;
            this.heuristicsValue = tile.getDistanceFromAnotherEntity(start);
            this.fValue= g_Value+fValue
        } 
        }

	Set<Tile> OpenSet = new Set<Tile>();
	OpenSet.add(start);
	Set<Tile> ClosedSet = new Set<Tile>();
       
    }

    /**
     * Get the distance to another entity (double value)
     * @param e
     * The target entity
     * @return
     */
    public double getDistanceFromAnotherEntity(Entity e){
        double x_distance = Math.abs(this.position.getX()-e.getPosition().getX());
        double y_distance = Math.abs(this.position.getY()-e.getPosition().getY());
        return Math.sqrt(x_distance * x_distance + y_distance * y_distance);
    }

    /**
     * Get the distance to another tile (double value)
     * @param t
     * The target tile
     * @return
     */
    public double getDistanceFromAnotherEntity(Tile t){
        double x_distance = Math.abs(this.position.getX()-t.getPosition().getX());
        double y_distance = Math.abs(this.position.getY()-t.getPosition().getY());
        return Math.sqrt(x_distance * x_distance + y_distanc
        e * y_distance);
    }

}
