package com.jasonvillar.tennisTournament.service;

import com.jasonvillar.tennisTournament.model.Match;
import com.jasonvillar.tennisTournament.model.Player;
import com.jasonvillar.tennisTournament.model.enums.GenderEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class MatchServiceTest {
    private MatchService matchService;

    private final List<Player> playerListFull = new ArrayList<>();
    {
        playerListFull.add(new Player(GenderEnum.FEMALE, "Clara", "Perez", 85));
        playerListFull.add(new Player(GenderEnum.FEMALE, "Sara", "Ferreira", 100));
        playerListFull.add(new Player(GenderEnum.FEMALE, "Lara", "Fernandez", 50));
        playerListFull.add(new Player(GenderEnum.FEMALE, "Iara", "Martinez", 25));
    }

    private final List<Player> playerListOfTwo = new ArrayList<>();
    {
        playerListOfTwo.add(new Player(GenderEnum.FEMALE, "Clara", "Perez", 85));
        playerListOfTwo.add(new Player(GenderEnum.FEMALE, "Sara", "Ferreira", 100));
    }

    @BeforeEach
    void initUseCase() {
        PlayerService playerService = Mockito.mock(PlayerService.class);
        Mockito.when(
                playerService.getTwoPlayersByPlayerList(playerListFull)
        ).thenReturn(playerListOfTwo);

        QualityPlayerGenderService qualityPlayerGenderService = Mockito.mock(QualityPlayerGenderService.class);
        matchService = new MatchService(playerService, qualityPlayerGenderService);
    }

    @Test
    public void given_tournament_round_2players_then_create_a_new_match() {
        Match match = matchService.newMatch(1, 1, playerListFull);
        Assertions.assertNotNull(match);
    }
}
