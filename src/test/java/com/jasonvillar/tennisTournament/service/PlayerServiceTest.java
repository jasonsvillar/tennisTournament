package com.jasonvillar.tennisTournament.service;

import com.jasonvillar.tennisTournament.model.Player;
import com.jasonvillar.tennisTournament.model.QualityPlayerGender;
import com.jasonvillar.tennisTournament.model.enums.GenderEnum;
import com.jasonvillar.tennisTournament.model.enums.QualityEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlayerServiceTest {
    private PlayerService playerService;

    private final List<Player> playerListFull = new ArrayList<>();
    {
        playerListFull.add(new Player(GenderEnum.FEMALE, "Clara", "Perez", 85));
        playerListFull.add(new Player(GenderEnum.FEMALE, "Sara", "Ferreira", 100));
        playerListFull.add(new Player(GenderEnum.FEMALE, "Lara", "Fernandez", 50));
        playerListFull.add(new Player(GenderEnum.FEMALE, "Iara", "Martinez", 25));
    }

    @BeforeEach
    void initUseCase() {
        List<QualityPlayerGender> qualityListWithValues = new ArrayList<>();
        {
            qualityListWithValues.add(new QualityPlayerGender(GenderEnum.FEMALE, QualityEnum.REACTION_TIME, 0.5));
        }

        QualityPlayerGenderService qualityPlayerGenderService = Mockito.mock(QualityPlayerGenderService.class);
        Mockito.when(
                qualityPlayerGenderService.newQualitiesWithValuesByGender(GenderEnum.FEMALE)
        ).thenReturn(qualityListWithValues);

        playerService = new PlayerService(qualityPlayerGenderService);
    }

    @Test
    public void given_gender_then_get_default_names() {
        Map<String, GenderEnum> names = playerService.getDefaultPlayerNamesByGender(GenderEnum.MALE);
        Assertions.assertNotEquals(0, names.size());
    }

    @Test
    public void given_gender_then_get_a_random_name() {
        String name = playerService.getRandomNameByGender(GenderEnum.MALE);
        Assertions.assertNotEquals("", name);
    }

    @Test
    public void get_a_random_surname() {
        String surname = playerService.getRandomSurname();
        Assertions.assertNotEquals("", surname);
    }

    @Test
    public void given_gender_then_get_a_new_player() {
        Player player = playerService.newRandomPlayerByGender(GenderEnum.FEMALE);
        Assertions.assertNotNull(player);
    }

    @Test
    public void given_gender_and_rounds_get_total_random_player_list() {
        for (int rounds = 4; rounds >= 1; rounds--) {
            List<Player> playerList = playerService.newPlayersByGenderAndRounds(GenderEnum.FEMALE, rounds);
            Assertions.assertEquals(Math.pow(2, rounds), playerList.size());
        }
    }

    @Test
    public void given_player_list_then_get_2randomPlayers() {
        List<Player> playerList = playerService.getTwoPlayersByPlayerList(playerListFull);
        Assertions.assertEquals(2, playerList.size());
    }

    @Test
    public void given_player_list_then_get_a_random_player() {
        Player player = playerService.getRandomPlayerFromPlayerList(playerListFull);
        Assertions.assertNotNull(player);
    }
}
