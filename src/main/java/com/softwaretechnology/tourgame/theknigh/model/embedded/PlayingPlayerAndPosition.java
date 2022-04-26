package com.softwaretechnology.tourgame.theknigh.model.embedded;

import com.softwaretechnology.tourgame.theknigh.service.game.utils.Position;
import lombok.Data;

/**
 * @author D'Andréa William
 */

@Data
public class PlayingPlayerAndPosition {

    private Position position;
    private int playingPlayer;

    public PlayingPlayerAndPosition(Position position, int playingPlayer) {
        this.position = position;
        this.playingPlayer = playingPlayer;
    }

    public Position getPosition() {
        return position;
    }

    public int getPlayingPlayer() {
        return playingPlayer;
    }


}
