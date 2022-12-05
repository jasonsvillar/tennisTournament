package com.jasonvillar.tennisTournament.controller;

import com.jasonvillar.tennisTournament.model.Player;
import com.jasonvillar.tennisTournament.model.Round;
import com.jasonvillar.tennisTournament.model.enums.GenderEnum;
import com.jasonvillar.tennisTournament.service.PlayerService;
import com.jasonvillar.tennisTournament.service.RoundService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/round")
@Api(value = "api/v1", tags = "Round Controller")
public class RoundController {
    @Autowired
    RoundService roundService;

    @Autowired
    PlayerService playerService;

    @ApiOperation(value = "Get new round", notes = "The method 'newRound' in the Service also receives a player list of 4 players for testing", tags = "testing")
    @GetMapping("/newRound")
    public Round newRound() {
        List<Player> playerList = playerService.newPlayersByGenderAndRounds(GenderEnum.FEMALE, 2);
        return roundService.newRound(1, 1, playerList);
    }

    @ApiOperation(value = "Execute an existing round", notes = "Execute a round of 4 player list for testing", tags = "testing")
    @GetMapping("/executeRound")
    public Round executeRound() {
        List<Player> playerList = playerService.newPlayersByGenderAndRounds(GenderEnum.FEMALE, 2);
        Round round =  roundService.newRound(1, 1, playerList);
        roundService.executeRound(round);
        return round;
    }

    @ApiOperation(value = "Get all matches winners of a round", notes = "Get the winners of a round of 4 player list for testing. The result should by a list of 2 players", tags = "testing")
    @GetMapping("/getWinnersByRound")
    public List<Player> getWinnersByRound() {
        List<Player> playerList = playerService.newPlayersByGenderAndRounds(GenderEnum.FEMALE, 2);
        Round round =  roundService.newRound(1, 1, playerList);
        roundService.executeRound(round);
        return roundService.getWinnersByRound(round);
    }
}
