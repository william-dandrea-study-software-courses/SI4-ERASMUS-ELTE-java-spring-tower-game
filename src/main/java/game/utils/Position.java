package game.utils;

/**
 * @author D'Andréa William
 */
public class Position {

    private int x;
    private int y;

    /**
     * 2D position representing the entity's position on the board
     * @param x
     * x position
     * @param y
     * y position
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }



    /**
     * @author Andreas Tsironis
     */



        /** More complexes functions,that combine the previous functions to have them just in case they have to be used,
         * in order to make later code to be more readable and less complex,even thought the previous functions are enough of their own.*/


        public void setXY(int x,int y) {
            this.x = x;
            this.y = y;
        }

        public Position setXGetPosition(int x) {
            this.x = x;
            return this;
        }

        public Position setYGetPosition(int y) {
            this.y = y;
            return this;
        }
        public Position setXYGetPosition(int x,int y) {
            this.x = x;
            this.y = y;
            return this;
        }



    }

