package com.jasonvillar.tennisTournament.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Round {
    int roundNumber;
    int tournamentNumber;
    List<Match> matchList;

    public Round(int roundNumber, int tournamentNumber, List<Match> matchList) {
        this.roundNumber = roundNumber;
        this.tournamentNumber = tournamentNumber;
        this.matchList = matchList;
    }
}
