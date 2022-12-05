package com.jasonvillar.tennisTournament.service;

import com.jasonvillar.tennisTournament.model.Player;
import com.jasonvillar.tennisTournament.model.Match;
import com.jasonvillar.tennisTournament.model.Round;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoundService {

    //I didn't use @autowired here to ensure speed of unit tests
    private final MatchService matchService;

    public Round newRound(int tournamentNumber, int round, List<Player> playerList) {
        List<Match> matchList = matchService.newMatchList(tournamentNumber, round, playerList);
        return new Round(round, tournamentNumber, matchList);
    }

    public void executeRound(Round round) {
        for (Match match: round.getMatchList()) {
            Player winner = matchService.calculateWinnerOfMatch(match);
            match.setWinner(winner);
        }
    }

    public List<Player> getWinnersByRound(Round round) {
        List<Player> winners = new ArrayList<>();
        for (Match match: round.getMatchList()) {
            winners.add(match.getWinner());
        }
        return winners;
    }
}
