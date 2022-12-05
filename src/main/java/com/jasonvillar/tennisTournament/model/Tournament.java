package com.jasonvillar.tennisTournament.model;

import com.jasonvillar.tennisTournament.model.enums.GenderEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Tournament {
    public Tournament(GenderEnum gender, int roundsTotal, List<Player> playerList) {
        this.gender = gender;
        this.roundsTotal = roundsTotal;
        this.playerList = playerList;
    }

    int tournamentNumber;
    GenderEnum gender;
    List<Player> playerList;
    int roundsTotal;
    Player playerWinner;
    List<Round> roundList;
    Date date = new Date();
}
