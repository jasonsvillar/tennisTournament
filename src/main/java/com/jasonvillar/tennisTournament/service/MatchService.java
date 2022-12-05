package com.jasonvillar.tennisTournament.service;

import com.jasonvillar.tennisTournament.model.Player;
import com.jasonvillar.tennisTournament.model.QualityPlayerGender;
import com.jasonvillar.tennisTournament.model.Match;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.jasonvillar.tennisTournament.utils.MathUtils.getRandomNumber;

@Service
@RequiredArgsConstructor
public class MatchService {

    //I didn't use @autowired here to ensure speed of unit tests
    private final PlayerService playerService;

    //I didn't use @autowired here to ensure speed of unit tests
    private final QualityPlayerGenderService qualityPlayerGenderService;

    public List<Match> newMatchList(int tournamentNumber, int round, List<Player> playerList) {
        List<Match> matchList = new ArrayList<>();
        List<Player> playerListProcessed = new ArrayList<>(playerList);
        int matchNumber = 0;
        while (!playerListProcessed.isEmpty()) {
            matchNumber++;
            Match match = this.newMatch(tournamentNumber, round, playerListProcessed);
            match.setMatchNumber(matchNumber);
            matchList.add(match);

            playerListProcessed.removeIf(player -> Objects.equals(player, match.getPlayer1()));
            playerListProcessed.removeIf(player -> Objects.equals(player, match.getPlayer2()));
        }
        return matchList;
    }

    public Match newMatch(int tournamentNumber, int roundNumber, List<Player> playerList) {
        List<Player> playerMatchList = playerService.getTwoPlayersByPlayerList(playerList);

        Match match = new Match(tournamentNumber, roundNumber, playerMatchList.get(0), playerMatchList.get(1));

        double luckOfPlayer1 = getRandomNumber(0.1, 1);
        double luckOfPlayer2 = getRandomNumber(0.1, 1);

        match.setLuckPlayer1(luckOfPlayer1);
        match.setLuckPlayer2(luckOfPlayer2);

        return match;
    }

    public Player calculateWinnerOfMatch(Match match) {
        Player playerWinner = null;

        while (playerWinner == null) {
            List<QualityPlayerGender> qualityList = qualityPlayerGenderService.getQualityPlayerGenderListByGender(match.getPlayer1().getGender());

            double totalAbilityPlayer1 = match.getPlayer1().getAbility() * match.getLuckPlayer1();
            double totalAbilityPlayer2 = match.getPlayer2().getAbility() * match.getLuckPlayer2();

            double totalQualityPlayer1 = 0, totalQualityPlayer2 = 0;

            for (QualityPlayerGender quality: qualityList) {
                for (QualityPlayerGender qualityPlayer1: match.getPlayer1().getQualityPlayerGenderList()) {
                    if (qualityPlayer1.getQuality().equals(quality.getQuality())) {
                        totalQualityPlayer1 = totalQualityPlayer1 + qualityPlayer1.getValue();
                    }
                }

                for (QualityPlayerGender qualityPlayer2: match.getPlayer2().getQualityPlayerGenderList()) {
                    if (qualityPlayer2.getQuality().equals(quality.getQuality())) {
                        totalQualityPlayer2 = totalQualityPlayer2 + qualityPlayer2.getValue();
                    }
                }
            }

            double totalScorePlayer1 = totalQualityPlayer1 + totalAbilityPlayer1;
            double totalScorePlayer2 = totalQualityPlayer2 + totalAbilityPlayer2;

            if (totalScorePlayer1 > totalScorePlayer2) {
                playerWinner = match.getPlayer1();
            }

            if (totalScorePlayer2 > totalScorePlayer1) {
                playerWinner = match.getPlayer2();
            }
        }

        return playerWinner;
    }
}
