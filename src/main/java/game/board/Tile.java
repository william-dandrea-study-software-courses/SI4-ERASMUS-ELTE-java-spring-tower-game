package game.board;

import game.board.entities.Entity;
import game.utils.BoardDimension;
import game.utils.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author D'Andréa William
 */
public class Tile {

    private final Position position;
    private final List<Entity> entitiesOnTheTile;
    private  List <Tile> neighboorTile ;
    /**
     * Initializing of tile
     * @param position
     * position of this tile
     * @param entitiesOnTheTile
     * Entities on this tile
     */
    public Tile(Position position) {
        this.position = position;
        this.entitiesOnTheTile = new ArrayList<>();
    }

    public Position getPosition() {
        return position;
    }

    public List<Entity> getEntitiesOnTheTile() {
        return entitiesOnTheTile;
    }

    public void addEntityOnTheTile(Entity entity) {
        this.entitiesOnTheTile.add(entity);
    }

    /**
     * @author Andreas Tsironis
     */

    /**Finding the distance between two tiles or entities.We could only have the entity,
    but I wanted to add some more flexibility. */

    public int manhattanDistance(Entity entity){
        /**We only take horizontal and vertical distances,as units only move in those two ways*/

        int x_distance= Math.abs(entity.getPosition().getX() - this.position.getX()) ;
        int y_distance= Math.abs(entity.getPosition().getY() - this.position.getY()) ;
        return x_distance + y_distance ;
    }



    public int manhattanDistance(Tile tile){


        int x_distance= Math.abs(tile.getPosition().getX() - this.position.getX()) ;
        int y_distance= Math.abs(tile.getPosition().getY() - this.position.getY()) ;
        return x_distance + y_distance ;
    }


    /**When we use the Board constructor, every tile gets its 4 neighbours.*/
    public void initializeNeighbourTiles(){

        this.neighboorTile = this.getAdjacentTilesOrthogonal();
    }
    /**We use the 3 methods below that they just refers to the getAdjacentTiles(), as
     * they will be probably the 3 most probable usages of the function and not having any
     * potential problems with misused parameters*/
    public ArrayList <Tile>  getAdjacentTilesOrthogonal(){

        return getAdjacentTiles(1,0);

    }

    public ArrayList <Tile> getAdjacentTilesDiagonal(){

        return getAdjacentTiles(0,1);
    }

    public ArrayList <Tile> getAdjacentTilesCircle(){

        return getAdjacentTiles(1,1);
    }



    public ArrayList <Tile>  getAdjacentTiles(int orthogonalRadius,int diagonalRadius){

        BoardDimension Dimension = Board.getDimension() ;
        int length = BoardDimension.getLength();
        int width = Dimension.getWidth();
        int adjamentPositionLenght;
        int adjamentPositionWidth;

        ArrayList <Tile> neighboorTile = new  ArrayList<Tile>();

        /* Get all orthogonal tiles first, thought the direction of the clock, starting at the bottom tile. */

        for (int i= -orthogonalRadius;i<=orthogonalRadius;i++) {

            if (i!=0){

                adjamentPositionWidth = width + i ;
                if (adjamentPositionWidth>=0 && adjamentPositionWidth<width){

                    neighboorTile.add(Board.getTile(0,adjamentPositionWidth)) ;

                }

                adjamentPositionLenght = length + i ;
                if (adjamentPositionLenght>=0 && adjamentPositionLenght<length){

                    neighboorTile.add(Board.getTile(adjamentPositionLenght,0)) ;

                }


            }
        }
        /*Get the diagonal tiles after, again by direction of the clock, starting from  bottom left one.*/

        for (int i= -diagonalRadius;i<=diagonalRadius;i++){

            adjamentPositionLenght = length + i;
            if (adjamentPositionLenght>=0 && adjamentPositionLenght<length){

                for (int j= -diagonalRadius;j<=diagonalRadius;j++){

                    adjamentPositionWidth = width + i;

                    if (i!=0 && j!=0){

                        if (adjamentPositionWidth>=0 && adjamentPositionWidth<width){

                            neighboorTile.add(Board.getTile(adjamentPositionLenght,adjamentPositionWidth));

                        }
                    }
                }
            }

        }

        return neighboorTile;
    }
}

    

