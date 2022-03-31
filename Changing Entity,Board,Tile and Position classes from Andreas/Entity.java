package game.board.entities;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Set;
import java.util.List;
import java.util.Iterator;

import java.util.ArrayList;
import java.util.HashSet;


import game.board.Tile;
import game.utils.Position;
import game.board.Board; 
/**
    @author Andreas Tsironis creating general pathfinding method.

*/

public class Entity {

    private Tile tile ;                                
    private String owner;
    private Position position;
    /**
     * Entity type initiation
     * @param position
     * Position of the entity (x, y)
     * @param owner
     * Owner of this entity, to better identify between enemy
     */
    public Entity(Tile tile,String owner) {
        this.tile = tile;
        this.position = tile.getPosition() ;
        this.owner = owner;
    }
    public Entity(Position position,String owner) {
        
        this.tile = Board.getTile(position);
        this.position = position ;
        this.owner = owner;
    }

    //*I think the entity should save the tile and get the position from the tile*/
    public Position getPosition() {
        
        return this.position ;
    }

    public Tile getTile() {
        
        return this.tile ;
    }

    public void setPosition(Position position) {

        this.tile = Board.getTile(position);
        this.position = position ;
    }

    public String getOwner() {return owner;}

    public void setOwner(String owner) {this.owner = owner;}

    public int manhattanDistance(Entity entity){


        int x_distance= Math.abs(entity.getPosition().getX() - this.position.getX()) ;
        int y_distance= Math.abs(entity.getPosition().getY() - this.position.getY()) ;
        return x_distance + y_distance ;
   }

   public int manhattanDistance(Tile tile){


    int x_distance= Math.abs(tile.getPosition().getX() - this.position.getX()) ;
    int y_distance= Math.abs(tile.getPosition().getY() - this.position.getY()) ;
    return x_distance + y_distance ;
    }



    public LinkedList<Entity> pathfinding(Tile start, Tile destination){

         List<String> unpassable_entities = new ArrayList<String>(Arrays.asList("Obstacle","Tower")) ;

        return pathfinding(start,destination,unpassable_entities);
    }

    public LinkedList<Entity> pathfinding(Tile start,Tile destination,String unblock_entity){

        List<String> unpassable_entities = new ArrayList<String>(Arrays.asList("Obstacle","Tower")) ;
        unpassable_entities.remove(unblock_entity);
        return pathfinding(start,destination,unpassable_entities);
    }

    private LinkedList<Entity> pathfinding(Tile start,Tile destination,List <String> unpassable_entities){


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
                    if (node.getEntitiesOnTheTile().get(i).getClass().getSimpleName().equals(
                        unpassable_entities.get(j) )  ) {

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

        boolean bestPathFound = false ;

        while (!openList.isEmpty()){

           
            
            for (int i=0;i<openList.size();i++){
                
                Algorithm_Node  potentialBestNode = nodeList.get(openList.get(i).index) ;

                
                    if ( potentialBestNode.fValue < currentBestNode.fValue){

                        currentBestNode = potentialBestNode ;

                    }       
            }
            if (destination == currentBestNode.node) {
                bestPathFound= true;
                break;

            }
           Tile testingNeighboor ; 
           ArrayList <Tile >neighboorTile = new  ArrayList<Tile>();
           Position currentBestPosition = currentBestNode.node.getPosition() ;
          
           if (currentBestPosition.getY()>0){
                testingNeighboor = Board.getTile(currentBestPosition.getX(),currentBestPosition.getY()-1) ; 
                if (currentBestNode.checkPassable(testingNeighboor)) {

                    neighboorTile.add(testingNeighboor);
                }
            
            }

            if (currentBestPosition.getY() < (Board.getDimension().getWidth()-1)){
                testingNeighboor = Board.getTile(currentBestPosition.getX(),currentBestPosition.getY()+1) ; 
                if (currentBestNode.checkPassable(testingNeighboor)) {

                    neighboorTile.add(testingNeighboor);
                }
            
            }

            if (currentBestPosition.getX()>0){
                testingNeighboor = Board.getTile(currentBestPosition.getX()-1,currentBestPosition.getY()+1) ; 
                if (currentBestNode.checkPassable(testingNeighboor)) {

                    neighboorTile.add(testingNeighboor);
                }
            
            }

            if (currentBestPosition.getX()<Board.getDimension().getLength()-1){

                testingNeighboor = Board.getTile(currentBestPosition.getX()+1,currentBestPosition.getY()) ; 
                if (currentBestNode.checkPassable(testingNeighboor)) {

                    neighboorTile.add(testingNeighboor);
                }
            
            }

            
            for (int i=0;i<neighboorTile.size();i++){
                Boolean FoundOpenList= false;
                Boolean FoundClosedList= false;
                int nodeListIndex ;
                for (int j=0; (j<openList.size() && j<closedList.size()) || (FoundOpenList || FoundClosedList );j++){

                    if (openList.get(j).node == neighboorTile.get(i)) {
                        FoundOpenList=true;
                        nodeListIndex = j;
                    }   
                    if (closedList.get(j).node == neighboorTile.get(i)){
                        FoundClosedList=true;
                        nodeListIndex = j;
                    }
                }
                
                if (!FoundOpenList && !FoundClosedList){

                    Algorithm_Node tempNode = new Algorithm_Node(neighboorTile.get(i),currentBestNode) ;
                    nodeList.add(tempNode);
                    openList.add(new OpenClosedList_Node(tempNode.node,nodeList.size()-1));

                }
                else {
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
            }

           


           
        
        
      
    }

   
  
   /** 
    /**
     * Get the distance to another entity (double value)
     * @param e
     * The target entity
     * @return
     
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
     
    public double getDistanceFromAnotherEntity(Tile t){
        double x_distance = Math.abs(this.position.getX()-t.getPosition().getX());
        double y_distance = Math.abs(this.position.getY()-t.getPosition().getY());
        return Math.sqrt(x_distance * x_distance + y_distance * y_distance);
    }
    */
}
