package com.softwaretechnology.tourgame.theknigh.service.game.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author D'Andréa William
 */
public class Radius {

    private int radius = 1;
    private int initialX;
    private int initialY;

    public Radius(int radius, int initialX, int initialY) {
        this.radius = radius;
        this.initialX = initialX;
        this.initialY = initialY;
    }

    public List<Position> getPositions() {

        List<Position> positionsWith0 = getPositionsWhenZero();
        List<Position> positions = new ArrayList<>();

        for (Position position: positionsWith0) {

            positions.add(new Position(position.getX() + initialX, position.getY() + initialY));

        }

        return positions;

    }



    public List<Position> getPositionsWhenZero() {

        int yInit = - (this.radius - 1);
        List<Position> positions = new ArrayList<>();


        for (int elem :  generateRows()) {


            int middle = elem / 2;
            int startX = -middle;
            int endX = middle;

            for (int x = startX; x <= endX; x++) {
                positions.add(new Position(x, yInit));
            }


            yInit += 1;
        }


        return positions ;

    }



    List<Integer> generateRows() {

        List<Integer> rows = new ArrayList<>();
        rows.add(1);

        if (this.radius == 1) {
            return rows;
        }

        int dLine = 1;
        boolean increase = true;

        for (int i = -(this.radius - 1); i < (this.radius - 1); i++) {

            if (increase)
                dLine += 2;
            else
                dLine -= 2;

            if (dLine == (2*(this.radius - 1) + 1)) {
                increase = false;
            }

            rows.add(dLine);
        }

        return rows;
    }







}
