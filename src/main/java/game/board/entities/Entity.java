package game.board.entities;

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

    public LinkedList<Entity> pathfinding(Tile start,Tile Destination){

        Set<String> unpassable_entities = new Set<String>(Arrays.asList("Obstacle","Tower")) ;

    }


}
