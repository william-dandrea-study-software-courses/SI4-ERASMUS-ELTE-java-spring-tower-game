package com.softwaretechnology.tourgame.theknigh.controller;

import com.softwaretechnology.tourgame.theknigh.service.GameService;
import com.softwaretechnology.tourgame.theknigh.service.game.Game;
import com.softwaretechnology.tourgame.theknigh.service.game.settings.Settings;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author D'Andréa William
 */
@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/manager")
public class GameManagingController {


    GameService gameService = GameService.getInstance();



    @GetMapping(path="/settings")
    public ResponseEntity<Settings> getSettingsPage() {

        Game game = gameService.getGame();
        log.info("start game request: {}", game.getSettings());


        return ResponseEntity.ok(game.getSettings());
    }




}
