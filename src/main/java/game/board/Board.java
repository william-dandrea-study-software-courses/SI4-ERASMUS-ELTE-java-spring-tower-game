package game.board;


import game.board.entities.gameentities.castles.Castle;
import game.board.entities.gameentities.obstacles.Obstacle;
import game.settings.Settings;
import game.settings.game.ObstacleSettings;
import game.utils.BoardDimension;
import game.utils.Position;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * @author D'Andréa William
 * @author Andreas Tsironis
 */

public class Board{


/**
 * The game board initiation
 * @param dimension
 * The dimension of the board (x, y)
 * @param tiles
 * Tiles on this board
 **/
    /** We have only an instance of the Board, as it is the board the players use, but
     * the player can change the dimensions of the board at the settings. He would have to
     * use a function that calls Board(BoardDimension dimension) **/

    private static BoardDimension dimension;
    /** It is a static array, because after its initiation, we will have the same  amount of tiles
     * until the player chooses to change the dimensions*/
    private static Tile board[][];

    private Castle castle1;
    private Castle castle2;

    public Board(BoardDimension dimension, int castleInitialHealthPoint, int numberOfObstacle) {
        Board.dimension = dimension;

        for (int i=0;i<=Board.dimension.getLength();i++){
            for (int j=0;j<=Board.dimension.getWidth();j++){

                /** We fill the board with empty tiles*/
                board[i][j] = new Tile(new Position(i,j));
            }
        }
        for (int i=0;i<=Board.dimension.getLength();i++){
            for (int j=0;j<=Board.dimension.getWidth();j++){

                /**Every tile knows its adjacent neighbours,for making the game quicker while playtime */
                board[i][j].initializeNeighbourTiles();

            }
        }

        Random rand = new Random();
        /** Castle 1 would be on the top row */
        Position castle1Pos = new Position(rand.nextInt(this.dimension.getLength()), 0);
        /** Castle 2 would be on the button row */
        Position castle2Pos = new Position(rand.nextInt(this.dimension.getLength()), this.dimension.getWidth());

        /** Create the two castles and add them on the corresponding tiles */
        this.castle1 = new Castle(castle1Pos, castleInitialHealthPoint);
        board[castle1.getPosition().getX()][castle1.getPosition().getY()].addEntityOnTheTile(castle1);
        this.castle2 = new Castle(castle2Pos, castleInitialHealthPoint);
        board[castle2.getPosition().getX()][castle2.getPosition().getY()].addEntityOnTheTile(castle2);

        /** Create obstacles on the board, the obstacles should not be close to the castles by 6 step
         and each obstacle should have different position*/
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        Position position = new Position(rand.nextInt(this.dimension.getLength()), this.dimension.getWidth());
        Obstacle obstacle = new Obstacle(position);

        for(int i=0; i<numberOfObstacle; i++){
            int repeat = 0;
            boolean repeatFlag = true;
            position.setXYGetPosition(rand.nextInt(this.dimension.getLength()), this.dimension.getWidth());
            obstacle.setPosition(position);
            /** Checking if the obstacle is too close to the castle */
            while(obstacle.manhattanDistance(castle1) < 6 || obstacle.manhattanDistance(castle2) < 6){
                position.setXYGetPosition(rand.nextInt(this.dimension.getLength()), this.dimension.getWidth());
                obstacle.setPosition(position);
            }
            /** Check if the obstacle's position is overlap with others */
            while(repeatFlag){
                repeat = 0;
                repeatFlag = true;
                for (Obstacle ob: obstacles) {
                    if(ob.getPosition() == obstacle.getPosition()){
                        repeat += 1;
                    }
                }
                if(repeat == 0){
                    repeatFlag = false;
                }else {
                    position.setXYGetPosition(rand.nextInt(this.dimension.getLength()), this.dimension.getWidth());
                    obstacle.setPosition(position);
                }
            }
            obstacles.add(obstacle);
        }
        /** Checking repeating finished, add them on the board's tiles. */
        for (Obstacle ob: obstacles) {
            board[ob.getPosition().getY()][ob.getPosition().getY()].addEntityOnTheTile(ob);
        }

    }

    public Castle getCastle1(){
        return castle1;
    }

    public Castle getCastle2(){
        return castle2;
    }

    public static BoardDimension getDimension() {
        return dimension;
    }
    /** We know  the position as a whole type*/
    public static Tile getTile (Position position){

        return board[position.getX()][position.getY()];
    }
    /** We know  the position as a separate length and width */
    public static Tile getTile (int x,int y){

        return board[x][y];
    }
}