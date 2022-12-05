package com.jasonvillar.tennisTournament.controller;

import com.jasonvillar.tennisTournament.model.Match;
import com.jasonvillar.tennisTournament.model.Player;
import com.jasonvillar.tennisTournament.model.enums.GenderEnum;
import com.jasonvillar.tennisTournament.service.MatchService;
import com.jasonvillar.tennisTournament.service.PlayerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/match")
@Api(value = "api/v1", tags = "Match Controller")
public class MatchController {
    @Autowired
    MatchService matchService;

    @Autowired
    PlayerService playerService;

    @ApiOperation(value = "Get new match", notes = "Create a match of 2 players from 4 players list", tags = "testing")
    @GetMapping("/newMatch")
    public Match newMatch() {
        List<Player> playerList = playerService.newPlayersByGenderAndRounds(GenderEnum.MALE, 2);
        playerList = playerService.getTwoPlayersByPlayerList(playerList);
        return matchService.newMatch(1, 1, playerList);
    }

    @ApiOperation(value = "Calculate the winner of match", notes = "Returns an ended match with the winner", tags = "testing")
    @GetMapping("/calculateWinnerOfMatch")
    public Match calculateWinnerOfMatch() {
        List<Player> playerList = playerService.newPlayersByGenderAndRounds(GenderEnum.MALE, 2);
        playerList = playerService.getTwoPlayersByPlayerList(playerList);
        Match match = matchService.newMatch(1, 1, playerList);
        Player winner = matchService.calculateWinnerOfMatch(match);
        match.setWinner(winner);
        return match;
    }

    @ApiOperation(value = "Get a list of matches for a round of 4 players", notes = "Must returns 2 matches", tags = "testing")
    @GetMapping("/newMatchList")
    public List<Match> newMatchList() {
        List<Player> playerList = playerService.newPlayersByGenderAndRounds(GenderEnum.MALE, 2);
        return matchService.newMatchList(1, 1, playerList);
    }
}