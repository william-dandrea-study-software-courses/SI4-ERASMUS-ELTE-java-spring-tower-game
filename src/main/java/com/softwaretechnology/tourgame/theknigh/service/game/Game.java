package com.softwaretechnology.tourgame.theknigh.service.game;


import com.softwaretechnology.tourgame.theknigh.service.game.settings.Settings;

/**
 * @author D'Andréa William
 */
public class Game {


    private final Settings settings;


    public Game(Settings settings) {
        this.settings = settings;
    }


    protected void launchGame() {
        System.out.println(this.settings.getCastelSettings());
    }


}
