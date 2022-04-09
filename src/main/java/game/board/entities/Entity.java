package game.board.entities;


import java.util.Collections;

import java.util.List;

import java.util.ArrayList;



import game.board.Tile;
import game.gamemanaging.Player;
import game.utils.Position;
import game.board.Board;
/**
 @author Andreas Tsironis

 */

public class Entity {

    private Tile tile ;
    private Player owner;
    private Position position;
    private List<String> unpassable_entities;
    private List<Tile> path;


    public Entity(Tile tile, Player owner) {
        this.tile = tile;
        this.position = tile.getPosition() ;
        this.owner = owner;
        this.unpassable_entities = List.of("Obstacle","Tower");

    }

    public Entity(Position position,Player owner) {

        this.tile = Board.getTile(position);
        this.position = position ;
        this.owner = owner;
        this.unpassable_entities = List.of("Obstacle","Tower");

    }
    public Entity(Tile tile) {
        this.tile = tile;
        this.position = tile.getPosition() ;
        this.unpassable_entities = List.of("Obstacle","Tower");

    }
    public Entity(Position position) {

        this.tile = Board.getTile(position);
        this.position = position ;
        this.unpassable_entities = List.of("Obstacle","Tower");

    }


    //*I think the entity should save the tile and get the position from the tile*/
    public Position getPosition() {

        return this.position ;
    }

    public Tile getTile() {

        return this.tile ;
    }

    public List <Tile> getPath(){

        return this.path;
    }

    public Tile getNextTilePath(){

        return this.path.get(this.path.size()-1);
    }

    public void setPosition(Position position) {

        this.tile = Board.getTile(position);
        this.position = position ;
    }

    public Player getOwner() {return owner;}

    public void setOwner(Player owner) {this.owner = owner;}

    /**
     * Get the distance between this entity and another entity
     * @param entity
     * @return
     */
    public int manhattanDistance(Entity entity){

        int x_distance= Math.abs(entity.getPosition().getX() - this.position.getX()) ;
        int y_distance= Math.abs(entity.getPosition().getY() - this.position.getY()) ;
        return x_distance + y_distance ;
    }

    /**
     * Get the distance between this entity and another tile
     * @param tile
     * @return
     */
    public int manhattanDistance(Tile tile){

        int x_distance= Math.abs(tile.getPosition().getX() - this.position.getX()) ;
        int y_distance= Math.abs(tile.getPosition().getY() - this.position.getY()) ;
        return x_distance + y_distance ;
    }


    /**Checks for both if it has previous path saved, if the destination was the same as before and if any new
     * towers have gone to the way. If everything has been the same, return true. Else, search for a new path.*/
    public boolean checkPath(Entity destination){
        if (this.path!=null) {
            /**The first element has the destination and the last element has the first tile we move to,
             * as it is easier to remove it*/
            if (this.path.get(0) == destination.getTile()) {

                if (this.checkExistingPathForUnpassableEntities()) {

                    return true;
                }
            }
        }
        return this.pathfinding(destination);
    }

    public boolean checkExistingPathForUnpassableEntities(){

        if (this.path!=null){
            for(int i=0;i<this.path.size();i++){

                List <Entity> entities = path.get(i).getEntitiesOnTheTile();

                for (int j=0;j<entities.size();j++){

                    if (unpassable_entities.contains(entities.get(j).getClass().getSimpleName())){

                        return false;
                    }
                }


            }
            return true;
        }
        else{
            return false;
        }
    }
    public boolean pathfinding (Entity destination){

        return  pathfinding(this.getTile(),destination.getTile()) ;
    }


    public boolean pathfinding(Entity start,Entity destination){

        return  pathfinding(start.getTile(),destination.getTile()) ;
    }


    public boolean pathfinding(Tile start,Tile destination){


        class OpenClosedList_Node{

            Tile node;
            int index;
            OpenClosedList_Node(Tile node,int index){

                this.node = node;
                this.index = index;
            }
        }

        class Algorithm_Node{

            Tile node;
            Algorithm_Node parent_node;
            private int gValue;
            int hValue;
            int fValue;

            Algorithm_Node(Tile node,Algorithm_Node parent_node){

                this.node = node;
                this.parent_node = parent_node ;
                this.gValue = parent_node.gValue + 1;
                this.hValue = node.manhattanDistance(destination);
                this.fValue = gValue + hValue ;

            }

            Algorithm_Node(Tile node){
                this.node = node;
                this.parent_node = null ;
                this.gValue = 0 ;
                this.hValue = node.manhattanDistance(destination);
                this.fValue = gValue + hValue ;
            }

            void setfValue() {

                this.fValue = this.gValue + this.hValue ;
            }

            boolean checkPassable (Tile node){

                for (int i=0;i<= node.getEntitiesOnTheTile().size() ; i++)
                {
                    for (int j=0; j<= unpassable_entities.size(); j++){
                        unpassable_entities.get(j) ;

                        if (unpassable_entities.contains(node.getEntitiesOnTheTile().get(j).getClass().getSimpleName()))
                        {

                            return false ;

                        }
                    }
                }
                return true ;
            }
        }




        ArrayList <Algorithm_Node> nodeList = new ArrayList<> ();
        List <OpenClosedList_Node> openList= new ArrayList<> ();
        List <OpenClosedList_Node> closedList = new ArrayList<> ();

        nodeList.add(new Algorithm_Node(start)) ;
        openList.add(new OpenClosedList_Node(nodeList.get(0).node,0)) ;
        Algorithm_Node currentBestNode = nodeList.get(0);

        boolean PathFound = false ;

        while (!openList.isEmpty()){



            for (int i=0;i<openList.size();i++){

                Algorithm_Node  potentialBestNode = nodeList.get(openList.get(i).index) ;


                if ( potentialBestNode.fValue < currentBestNode.fValue){

                    currentBestNode = potentialBestNode ;

                }
            }
            if (destination == currentBestNode.node) {
                PathFound= true;
                break;
            }

            List <Tile> neighbourTiles =  currentBestNode.node.getNeighbourTiles();

            for (int i=0;i<=neighbourTiles.size();i++){
                if (!currentBestNode.checkPassable(neighbourTiles.get(i))){
                    neighbourTiles.remove(i);

                }

            }


            for (int i=0;i<neighbourTiles.size();i++){
                Boolean FoundOpenList= false;
                Boolean FoundClosedList= false;
                int nodeListIndex=0;

                for (int j=0; (j<openList.size() && j<closedList.size()) || (FoundOpenList || FoundClosedList );j++){

                    if (j<openList.size()){
                        if (openList.get(j).node == neighbourTiles.get(i)) {
                            FoundOpenList=true;
                            nodeListIndex = j;
                        }
                    }
                    if (j<closedList.size())
                        if (closedList.get(j).node == neighbourTiles.get(i)){
                            FoundClosedList=true;
                            nodeListIndex = j;
                        }
                }

                if (!FoundOpenList && !FoundClosedList){

                    Algorithm_Node tempNode = new Algorithm_Node(neighbourTiles.get(i),currentBestNode) ;
                    nodeList.add(tempNode);
                    openList.add(new OpenClosedList_Node(tempNode.node,nodeList.size()-1));

                }
                else
                {

                    int NewgValue = currentBestNode.gValue+1;
                    if (NewgValue < nodeList.get(nodeListIndex).gValue){

                        nodeList.get(nodeListIndex).gValue = NewgValue ;
                        nodeList.get(nodeListIndex).setfValue() ;
                        nodeList.get(nodeListIndex).parent_node = currentBestNode ;
                        if (FoundClosedList) {
                            OpenClosedList_Node temp = closedList.get(nodeListIndex) ;
                            closedList.remove(nodeListIndex) ;
                            openList.add(temp );
                        }
                    }


                }
                OpenClosedList_Node temp = openList.get(nodeListIndex) ;
                closedList.add(temp);
            }
        }

        this.path.clear();
        if (PathFound){
            /**We save the first tile as the last index and the destination as the first*/
            while (currentBestNode.parent_node!= null){

                this.path.add(currentBestNode.node);
                currentBestNode = currentBestNode.parent_node;


            }
        }
        return PathFound;


    }






}

