package com.softwaretechnology.tourgame.theknigh.service.game.utils;

import com.softwaretechnology.tourgame.theknigh.service.game.Game;
import com.softwaretechnology.tourgame.theknigh.service.game.board.Board;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.Entity;

import java.util.*;
import java.util.stream.Collectors;

import static com.softwaretechnology.tourgame.theknigh.service.game.utils.Constants.POSITION_WE_CAN_GO;

/**
 * @author D'Andréa William
 */
public class MyAStartAlgorithm {

    private final static int HORIZONTAL_VERTICAL_COST = 10;
    private final static int MAXIMUM_ITERATIONS = 100;

    // {x, y}



    private PriorityQueue<MyNode> openNodes;
    private Set<MyNode> closedNodes;

    private MyNode[][] searchBoard;
    private MyNode startNode;
    private MyNode endNode;

    private int currentNumberOfIterations;





    public MyAStartAlgorithm(Game game, Position startPosition, Position endPosition) {
        this.startNode = new MyNode(startPosition);
        this.endNode = new MyNode(endPosition);
        this.searchBoard = new MyNode[game.getBoard().getDimension().getLength()][game.getBoard().getDimension().getWidth()];

        this.currentNumberOfIterations = 0;

        this.closedNodes = new HashSet<>();
        this.openNodes = new PriorityQueue<MyNode>(new Comparator<MyNode>() {
            @Override
            public int compare(MyNode n1, MyNode n2) {
                return Integer.compare(n1.f, n2.f);
            }
        });

        this.initializeSearchBoardWithNodes(game.getAllBuildingEntities().stream().map(Entity::getPosition).collect(Collectors.toList()));
    }


    public MyAStartAlgorithm(Game game, List<Position> obstaclesPositions, Position startPosition, Position endPosition) {
        this.startNode = new MyNode(startPosition);
        this.endNode = new MyNode(endPosition);
        this.searchBoard = new MyNode[game.getBoard().getDimension().getLength()][game.getBoard().getDimension().getWidth()];

        this.currentNumberOfIterations = 0;

        this.closedNodes = new HashSet<>();
        this.openNodes = new PriorityQueue<MyNode>(new Comparator<MyNode>() {
            @Override
            public int compare(MyNode n1, MyNode n2) {
                return Integer.compare(n1.f, n2.f);
            }
        });

        this.initializeSearchBoardWithNodes(obstaclesPositions);
    }




    private void initializeSearchBoardWithNodes(List<Position> obstaclesPositions) {
        /** Initialize the searchBoard with all the nodes **/
        for (int y = 0; y < this.searchBoard.length; y++) {
            for (int x = 0; x < this.searchBoard[0].length; x++) {
                MyNode node = new MyNode(new Position(x, y));
                node.setHeuristic(this.endNode.position);

                List<Position> obstacles = obstaclesPositions;
                if (obstacles.contains(new Position(x, y)))
                    node.isObstacle = true;

                this.searchBoard[y][x] = node;
            }
        }
    }


    public List<Position> getPathPositions() {

        return this.generatePath().stream().map(n -> n.position).collect(Collectors.toList());

    }




    public List<MyNode> generatePath() {
        this.openNodes.add(this.startNode);


        /** While the openNodes contains a Node **/
        while (!this.openNodes.isEmpty()) {
            this.currentNumberOfIterations += 1;

            if (checkMaxIteration()) {
                break;
            }

            // Get the first node in the openNodes Queue
            MyNode currentNode = this.openNodes.poll();

            // Add the currentNode to the closed nodes (explored nodes)
            this.closedNodes.add(currentNode);

            // If the current node is the endNode, we reach the destination
            if (currentNode.equals(this.endNode)) {
                // We can get the path from the startNode to the endNode
                return this.getTheFinalPath(currentNode);
            } else {
                // We didn't reach the destination, we have to continue the exploration
                this.exploreAdjacentsNodes(currentNode);
            }
        }

        return new ArrayList<>();
    }



    private void exploreAdjacentsNodes(MyNode currentNode) {


        for (Position positionPossible : POSITION_WE_CAN_GO) {

            if (checkMaxIteration())
                break;

            // We have to verify if this position is inside the board
            Position potentialPositionNode = new Position(currentNode.position.getX() + positionPossible.getX(), currentNode.position.getY() + positionPossible.getY());

            if (        potentialPositionNode.getX() >= 0
                    &&  potentialPositionNode.getX() < this.searchBoard[0].length
                    &&  potentialPositionNode.getY() >= 0
                    && potentialPositionNode.getY() < this.searchBoard.length
            ) {
                // The currentNode is situated in the gameBoard
                this.checkNode(currentNode, potentialPositionNode);
            }
        }
    }


    private void checkNode(MyNode currentNode, Position potentialPositionNode) {

        MyNode adjacentNode = searchBoard[potentialPositionNode.getY()][potentialPositionNode.getX()];

        // If the adjacent node is not an obstacle and he was not visited before
        if (!adjacentNode.isObstacle && !closedNodes.contains(adjacentNode)) {

            // If the adjacentNode is not in the current path
            if (!openNodes.contains(adjacentNode)) {
                // We continue the exploration
                adjacentNode.g = currentNode.g + HORIZONTAL_VERTICAL_COST;
                adjacentNode.parent = currentNode;
                adjacentNode.f = adjacentNode.g + adjacentNode.h;

                this.openNodes.add(adjacentNode);
            } else {
                // We did a loop

                if ((currentNode.g + HORIZONTAL_VERTICAL_COST) < adjacentNode.g) {
                    adjacentNode.g = currentNode.g + HORIZONTAL_VERTICAL_COST;
                    adjacentNode.parent = currentNode;
                    adjacentNode.f = adjacentNode.g + adjacentNode.h;

                    // PriorityQueue can sort again its contents with the modified "finalCost"
                    this.openNodes.remove(adjacentNode);
                    this.openNodes.add(adjacentNode);

                }
            }
        }
    }


    private List<MyNode> getTheFinalPath(MyNode currentNode) {

        List<MyNode> pathToDestination = new ArrayList<MyNode>();
        pathToDestination.add(currentNode);

        MyNode parentNode;

        // While the parent node is not the finalNode, we add the node to the path in the position 0 (because the path is from the start to the end)
        while ((parentNode = currentNode.parent) != null) {
            // First we check if we are not in an infinite loop
            this.currentNumberOfIterations += 1;
            if (checkMaxIteration())
                break;

            pathToDestination.add(0, parentNode);
            currentNode = parentNode;
        }

        return pathToDestination;
    }



    private boolean checkMaxIteration() {

        if (this.currentNumberOfIterations >= MAXIMUM_ITERATIONS) {
            return true;
        }
        return false;

    }


    /*
    *
    *   this.currentNumberOfIterations += 1;
        if (this.currentNumberOfIterations == MAXIMUM_ITERATIONS) {
            return new ArrayList<>();
        }
     */


}



class MyNode {

    public Position position;
    public MyNode parent;
    public boolean isObstacle;

    public int g;
    public int f;
    public int h;



    public MyNode(Position position) {
        this.parent = null;
        this.position = position;
        this.g = 0;
        this.h = 0;
        this.f = 0;
        this.isObstacle = false;
    }



    public void setHeuristic(Position endPoint) {
        this.h = Math.abs(endPoint.getX() - position.getX())
                + Math.abs(endPoint.getY() - position.getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyNode myNode = (MyNode) o;

        return position != null ? position.equals(myNode.position) : myNode.position == null;
    }

    @Override
    public String toString() {
        return "MyNode{" +
                "position=" + position +
                ", isObstacle=" + isObstacle +
                '}';
    }
}
