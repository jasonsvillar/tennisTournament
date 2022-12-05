package com.jasonvillar.tennisTournament.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Match {
    int matchNumber;
    int tournamentNumber;
    int roundNumber;
    double luckPlayer1;
    double luckPlayer2;
    Player player1;
    Player player2;
    Player winner;

    public Match(int tournamentNumber, int roundNumber, Player player1, Player player2) {
        this.tournamentNumber = tournamentNumber;
        this.roundNumber = roundNumber;
        this.player1 = player1;
        this.player2 = player2;
    }
}
