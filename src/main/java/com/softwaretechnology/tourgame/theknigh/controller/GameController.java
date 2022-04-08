package com.softwaretechnology.tourgame.theknigh.controller;

import com.softwaretechnology.tourgame.theknigh.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class GameController {

    // private final GameService gameService;



    @GetMapping(path="/settings")
    public String getSettingsPage(Model model) {
        return "settings";
    }

    @GetMapping(path="/home")
    public String getHomePage(Model model) {
        return "home";
    }


    @GetMapping(path="/play")
    public String getGamePage(Model model) {
        return "game";
    }




}
