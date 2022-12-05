package com.jasonvillar.tennisTournament.controller;

import com.jasonvillar.tennisTournament.model.Player;
import com.jasonvillar.tennisTournament.model.Tournament;
import com.jasonvillar.tennisTournament.model.Round;
import com.jasonvillar.tennisTournament.model.enums.GenderEnum;
import com.jasonvillar.tennisTournament.service.PlayerService;
import com.jasonvillar.tennisTournament.service.RoundService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value = "api/v1", tags = "Tournament Controller")
@RequestMapping("/tournament")
public class TournamentController {
    @Autowired
    PlayerService playerService;

    @Autowired
    RoundService roundService;

    List<Tournament> tournamentList = new ArrayList<>();

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Finished Tournament")
    })
    @ApiOperation(value = "Start and finish the tournament", notes = "Returns all data from the Tournament")
    @GetMapping("/executeWithRandomPlayers/{gender}/{rounds}")
    public Tournament executeWithRandomPlayers(@ApiParam(value = "MALE, FEMALE", required = true) @PathVariable GenderEnum gender,
                                               @ApiParam(value = "Total number of rounds", required = true) @PathVariable int rounds) {
        List<Player> playerList = playerService.newPlayersByGenderAndRounds(gender, rounds);
        Tournament tournament = new Tournament(gender, rounds, playerList);
        tournament.setTournamentNumber(tournamentList.size() + 1);
        int tournamentNumber = tournament.getTournamentNumber();

        Player winnerTournament = null;
        List<Player> winners = new ArrayList<>(playerList);

        List<Round> roundList = new ArrayList<>();
        for (int round = 1; round <= rounds; round++) {
            Round newRound = roundService.newRound(tournamentNumber, round, winners);
            roundService.executeRound(newRound);
            winners = roundService.getWinnersByRound(newRound);

            roundList.add(newRound);

            if (round == rounds) {
                winnerTournament = winners.get(0);
            }
        }

        tournament.setPlayerWinner(winnerTournament);
        tournament.setRoundList(roundList);
        tournamentList.add(tournament);

        return tournament;
    }

    @ApiOperation(value = "Get all tournament numbers")
    @GetMapping("/getAllNumberList")
    public List<Integer> getAllNumberList() {
        List<Integer> numberList = new ArrayList<>();
        for (Tournament tournament: tournamentList) {
            numberList.add(tournament.getTournamentNumber());
        }

        return numberList;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Tournament not found")
    })
    @ApiOperation(value = "Get a tournament by a number")
    @GetMapping("/getByNumber/{number}")
    public Tournament getByNumber(@PathVariable int number, HttpServletResponse response) {
        for (Tournament tournament: tournamentList) {
            if (tournament.getTournamentNumber() == number) {
                return tournament;
            }
        }

        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        response.setHeader("Message", "Tournament not found");
        return null;
    }
}
