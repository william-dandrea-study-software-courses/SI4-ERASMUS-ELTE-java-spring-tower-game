package com.softwaretechnology.tourgame.theknigh.controller;

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




    @GetMapping(path="/player1-infos")
    public ResponseEntity<Player> getPlayer1Infos() {
        return ResponseEntity.ok(this.gameService.getGame().getPlayer1());
    }


    @GetMapping(path="/player2-infos")
    public ResponseEntity<Player> getPlayer2Infos() {
        return ResponseEntity.ok(this.gameService.getGame().getPlayer2());
    }

    @GetMapping(path="/board-infos")
    public ResponseEntity<Board> getBoardInfos() {
        return ResponseEntity.ok(this.gameService.getGame().getBoard());
    }




    @PostMapping(path = "select-new-tile-for-building")
    public ResponseEntity<Boolean> selectNewTileForBuild(@RequestBody PlayingPlayerAndPosition playingPlayerAndPosition) {

        log.info("Yoooo: {}", playingPlayerAndPosition);

        if (playingPlayerAndPosition.getPlayingPlayer() == 1)
            return ResponseEntity.ok(this.gameService.getGame().canPlayer1PutNewEntityAtThePosition(playingPlayerAndPosition.getPosition()));

        if (playingPlayerAndPosition.getPlayingPlayer() == 2)
            return ResponseEntity.ok(this.gameService.getGame().canPlayer2PutNewEntityAtThePosition(playingPlayerAndPosition.getPosition()));


        return ResponseEntity.ok(false);

    }





}
