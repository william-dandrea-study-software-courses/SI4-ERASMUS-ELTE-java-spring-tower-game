package com.softwaretechnology.tourgame.theknigh.controller;

import com.softwaretechnology.tourgame.theknigh.model.embedded.OnePlayer;
import com.softwaretechnology.tourgame.theknigh.model.embedded.PlayingPlayerAndPosition;
import com.softwaretechnology.tourgame.theknigh.service.GameService;
import com.softwaretechnology.tourgame.theknigh.service.game.Game;
import com.softwaretechnology.tourgame.theknigh.service.game.board.Board;
import com.softwaretechnology.tourgame.theknigh.service.game.board.Tile;
import com.softwaretechnology.tourgame.theknigh.service.game.board.entities.Entity;
import com.softwaretechnology.tourgame.theknigh.service.game.gamemanaging.Player;
import com.softwaretechnology.tourgame.theknigh.service.game.settings.Settings;
import com.softwaretechnology.tourgame.theknigh.service.game.utils.Position;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author D'Andréa William
 */
@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/manager")
public class GameManagingController {


    GameService gameService = GameService.getInstance();


    @GetMapping(path="/game-infos")
    public ResponseEntity<Game> getGameInfos() {
        return ResponseEntity.ok(this.gameService.getGame());
    }



    @PostMapping(path = "/select-new-tile-for-building")
    public ResponseEntity<Boolean> selectNewTileForBuild(@RequestBody PlayingPlayerAndPosition playingPlayerAndPosition) {



        if (playingPlayerAndPosition.getPlayingPlayer() == 1)
            return ResponseEntity.ok(this.gameService.getGame().canPlayer1PutNewEntityAtThePosition(playingPlayerAndPosition.getPosition()));

        if (playingPlayerAndPosition.getPlayingPlayer() == 2)
            return ResponseEntity.ok(this.gameService.getGame().canPlayer2PutNewEntityAtThePosition(playingPlayerAndPosition.getPosition()));

        return ResponseEntity.ok(false);
    }



    @PostMapping(path = "/add-freeze-tower")
    public ResponseEntity<Boolean> addAPIFreezeTower(@RequestBody PlayingPlayerAndPosition playingPlayerAndPosition) {

        if (this.gameService.getGame().addNewFreezeTowerPlayer(playingPlayerAndPosition.getPosition(), playingPlayerAndPosition.getPlayingPlayer())) {
            return ResponseEntity.ok(true);
        }

        return ResponseEntity.ok(false);
    }

    @PostMapping(path = "/add-normal-tower")
    public ResponseEntity<Boolean> addAPINormalTower(@RequestBody PlayingPlayerAndPosition playingPlayerAndPosition) {

        if (this.gameService.getGame().addNewNormalTowerPlayer(playingPlayerAndPosition.getPosition(), playingPlayerAndPosition.getPlayingPlayer())) {
            return ResponseEntity.ok(true);
        }

        return ResponseEntity.ok(false);
    }

    @PostMapping(path = "/add-sniper-tower")
    public ResponseEntity<Boolean> addAPISniperTower(@RequestBody PlayingPlayerAndPosition playingPlayerAndPosition) {

        if (this.gameService.getGame().addNewSniperTowerPlayer(playingPlayerAndPosition.getPosition(), playingPlayerAndPosition.getPlayingPlayer())) {
            return ResponseEntity.ok(true);
        }

        return ResponseEntity.ok(false);
    }


    @PostMapping(path = "/add-gold-mine")
    public ResponseEntity<Boolean> addAPIGoldMine(@RequestBody PlayingPlayerAndPosition playingPlayerAndPosition) {

        if (this.gameService.getGame().addGoldMinePlayer(playingPlayerAndPosition.getPosition(), playingPlayerAndPosition.getPlayingPlayer())) {
            return ResponseEntity.ok(true);
        }

        return ResponseEntity.ok(false);
    }


    @PostMapping(path = "/add-killer-unit")
    public ResponseEntity<Boolean> addAPIKillerUnit(@RequestBody PlayingPlayerAndPosition player) {
        if (this.gameService.getGame().addKillerUnit(player.getPlayingPlayer()))
            return ResponseEntity.ok(true);

        return ResponseEntity.ok(false);
    }

    @PostMapping(path = "/add-fast-unit")
    public ResponseEntity<Boolean> addAPIFastUnit(@RequestBody PlayingPlayerAndPosition player) {

        if (this.gameService.getGame().addFastUnit(player.getPlayingPlayer()))
            return ResponseEntity.ok(true);

        return ResponseEntity.ok(false);
    }

    @PostMapping(path = "/add-flight-unit")
    public ResponseEntity<Boolean> addAPIFlightUnit(@RequestBody PlayingPlayerAndPosition player) {

        if (this.gameService.getGame().addFlightUnit(player.getPlayingPlayer()))
            return ResponseEntity.ok(true);

        return ResponseEntity.ok(false);
    }

    @PostMapping(path = "/delete-tower")
    public ResponseEntity<Integer> deleteTower(@RequestBody PlayingPlayerAndPosition player) {
        return ResponseEntity.ok(this.gameService.getGame().deleteTower(player.getPlayingPlayer(), player.getPosition().getX(), player.getPosition().getY()));
    }


    @PostMapping(path = "/increase-tower")
    public ResponseEntity<Boolean> increaseTower(@RequestBody PlayingPlayerAndPosition player) {
        return ResponseEntity.ok(this.gameService.getGame().increaseTower(player.getPlayingPlayer(), player.getPosition().getX(), player.getPosition().getY()));
    }








}
