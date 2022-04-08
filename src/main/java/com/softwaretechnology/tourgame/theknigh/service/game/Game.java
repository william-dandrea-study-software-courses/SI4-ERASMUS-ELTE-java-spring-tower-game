package com.softwaretechnology.tourgame.theknigh.service.game;


import com.softwaretechnology.tourgame.theknigh.service.game.settings.Settings;
import lombok.Data;

/**
 * @author D'Andréa William
 */

@Data
public class Game {

    private final Settings settings;


    public Game(Settings settings) {
        this.settings = settings;
    }

    protected void launchGame() {
        System.out.println(this.settings.getCastelSettings());
    }

    public Settings getSettings() {
        return settings;
    }
}
