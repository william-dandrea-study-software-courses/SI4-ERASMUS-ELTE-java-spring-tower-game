package com.softwaretechnology.tourgame.theknigh.controller;

import com.softwaretechnology.tourgame.theknigh.service.GameService;
import com.softwaretechnology.tourgame.theknigh.service.game.Game;
import com.softwaretechnology.tourgame.theknigh.service.game.settings.Settings;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author D'Andréa William
 */



@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/manage-settings")
public class SettingsController {

    GameService gameService = GameService.getInstance();


    @GetMapping("")
    public ResponseEntity<Settings> getSettings() {

        Game game = gameService.getGame();
        // log.info("start game request: {}", game.getSettings());

        return ResponseEntity.ok(game.getSettings());
    }

    @PostMapping("")
    public ResponseEntity<Settings> setSettings(@RequestBody Settings request) {

        this.gameService.getGame().setSettings(request);

        log.info("Yoooo: {}", this.gameService.getGame().getSettings().getGeneralSettings().getWidthBoard());

        return ResponseEntity.ok(this.gameService.getGame().getSettings());
    }


}
