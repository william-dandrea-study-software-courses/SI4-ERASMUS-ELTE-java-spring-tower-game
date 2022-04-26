package com.softwaretechnology.tourgame.theknigh.service.game.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author D'Andréa William
 */
public class Constants {

    public final static List<Position> POSITION_WE_CAN_GO = new ArrayList<Position>() {{
        add(new Position(1 , 0 ));
        add(new Position(0 , -1));
        add(new Position(-1, 0 ));
        add(new Position(0 , 1));
    }};


}
