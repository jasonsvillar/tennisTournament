package com.jasonvillar.tennisTournament.service;

import com.jasonvillar.tennisTournament.model.Player;
import com.jasonvillar.tennisTournament.model.QualityPlayerGender;
import com.jasonvillar.tennisTournament.model.enums.GenderEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.jasonvillar.tennisTournament.utils.MathUtils.getRandomNumber;

@Service
@RequiredArgsConstructor
public class PlayerService {

    //I didn't use @autowired here to ensure speed of unit tests
    private final QualityPlayerGenderService qualityPlayerGenderService;

    private static final Map<String, GenderEnum> nameGenderMap = new HashMap<>();
    static {
        nameGenderMap.put("Erica", GenderEnum.FEMALE);
        nameGenderMap.put("Nathalia", GenderEnum.FEMALE);
        nameGenderMap.put("Vanesa", GenderEnum.FEMALE);
        nameGenderMap.put("Daniela", GenderEnum.FEMALE);
        nameGenderMap.put("Norma", GenderEnum.FEMALE);
        nameGenderMap.put("Belen", GenderEnum.FEMALE);
        nameGenderMap.put("Iara", GenderEnum.FEMALE);
        nameGenderMap.put("Clara", GenderEnum.FEMALE);
        nameGenderMap.put("Maria", GenderEnum.FEMALE);
        nameGenderMap.put("Cintia", GenderEnum.FEMALE);
        nameGenderMap.put("Lucia", GenderEnum.FEMALE);
        nameGenderMap.put("Sofia", GenderEnum.FEMALE);
        nameGenderMap.put("Martina", GenderEnum.FEMALE);
        nameGenderMap.put("Leonela", GenderEnum.FEMALE);
        nameGenderMap.put("Julia", GenderEnum.FEMALE);
        nameGenderMap.put("Paula", GenderEnum.FEMALE);
        nameGenderMap.put("Valeria", GenderEnum.FEMALE);
        nameGenderMap.put("Emma", GenderEnum.FEMALE);
        nameGenderMap.put("Sara", GenderEnum.FEMALE);

        nameGenderMap.put("Jason", GenderEnum.MALE);
        nameGenderMap.put("Pepe", GenderEnum.MALE);
        nameGenderMap.put("Juan", GenderEnum.MALE);
        nameGenderMap.put("Mateo", GenderEnum.MALE);
        nameGenderMap.put("Tadeo", GenderEnum.MALE);
        nameGenderMap.put("Matias", GenderEnum.MALE);
        nameGenderMap.put("Lucas", GenderEnum.MALE);
        nameGenderMap.put("Diego", GenderEnum.MALE);
        nameGenderMap.put("Carlos", GenderEnum.MALE);
        nameGenderMap.put("Oscar", GenderEnum.MALE);
        nameGenderMap.put("Hugo", GenderEnum.MALE);
        nameGenderMap.put("Martin", GenderEnum.MALE);
        nameGenderMap.put("Leo", GenderEnum.MALE);
        nameGenderMap.put("Daniel", GenderEnum.MALE);
        nameGenderMap.put("Alejandro", GenderEnum.MALE);
        nameGenderMap.put("Manuel", GenderEnum.MALE);
        nameGenderMap.put("Emanuel", GenderEnum.MALE);
        nameGenderMap.put("Dylan", GenderEnum.MALE);
        nameGenderMap.put("Ivan", GenderEnum.MALE);
    }

    private static final List<String> surnameList = new ArrayList<>();
    static {
        surnameList.add("Villar");
        surnameList.add("Argento");
        surnameList.add("Flores");
        surnameList.add("Chavez");
        surnameList.add("Benitez");
        surnameList.add("Ferreira");
        surnameList.add("Valdes");
        surnameList.add("Torres");
        surnameList.add("Brittes");
        surnameList.add("Fernandez");
        surnameList.add("Perez");
        surnameList.add("Escobar");
        surnameList.add("Cruz");
        surnameList.add("Olivera");
        surnameList.add("Galeano");
        surnameList.add("Espindola");
        surnameList.add("Ozeñuk");
        surnameList.add("Rivero");
        surnameList.add("Rivas");
        surnameList.add("Ramirez");
        surnameList.add("Diaz");
        surnameList.add("Lopez");
    }

    public Map<String, GenderEnum> getDefaultPlayerNamesByGender(GenderEnum gender) {
        Map<String, GenderEnum> name = nameGenderMap.entrySet()
                .stream()
                .filter(map -> gender.equals(map.getValue()))
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));

        return name;
    }

    public List<String> getDefaultPlayerSurnames() {
        return surnameList;
    }

    public Map<String, GenderEnum> getDefaultPlayerNames() {
        return nameGenderMap;
    }

    public String getRandomNameByGender(GenderEnum gender) {
        Map<String, GenderEnum> names = this.getDefaultPlayerNamesByGender(gender);
        int nameListQuantity = names.size();
        int nameRandomInt = (int) getRandomNumber(0, nameListQuantity - 1);
        return names.keySet().toArray()[nameRandomInt].toString();
    }

    public String getRandomSurname() {
        int surnameListQuantity = surnameList.size();
        int surnameRandomInt = (int) getRandomNumber(0, surnameListQuantity - 1);
        return surnameList.get(surnameRandomInt);
    }

    public Player newRandomPlayerByGender(GenderEnum gender) {
        String name = getRandomNameByGender(gender);
        String surname = getRandomSurname();

        int ability = (int) getRandomNumber(0, 100);

        Player player = new Player(gender, name, surname, ability);

        List<QualityPlayerGender> qualityListWithValues = qualityPlayerGenderService.newQualitiesWithValuesByGender(gender);
        player.setQualityPlayerGenderList(qualityListWithValues);

        return player;
    }

    public List<Player> newPlayersByGenderAndRounds(GenderEnum gender, int rounds) {
        List<Player> playerList = new ArrayList<>();
        int playersQuantity = (int) Math.pow(2, rounds);
        for (int iteration = 1; iteration <= playersQuantity; iteration++) {
            Player player = this.newRandomPlayerByGender(gender);
            player.setPlayerNumber(iteration);

            playerList.add(player);
        }
        return playerList;
    }

    public List<Player> getTwoPlayersByPlayerList(List<Player> playerList) {
        List<Player> playerListProcessed = new ArrayList<>(playerList);
        List<Player> playerForMatchList = new ArrayList<>();

        Player player1 = this.getRandomPlayerFromPlayerList(playerListProcessed);
        playerForMatchList.add(player1);

        playerListProcessed.removeIf(player -> Objects.equals(player, player1));

        playerForMatchList.add(this.getRandomPlayerFromPlayerList(playerListProcessed));

        return playerForMatchList;
    }

    public Player getRandomPlayerFromPlayerList(List<Player> playerList) {
        int playerSelectedIndex = (int) getRandomNumber(0, playerList.size() - 1);
        return playerList.get(playerSelectedIndex);
    }
}
