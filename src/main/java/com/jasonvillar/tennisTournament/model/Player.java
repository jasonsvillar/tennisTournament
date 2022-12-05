package com.jasonvillar.tennisTournament.model;

import com.jasonvillar.tennisTournament.model.enums.GenderEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Player {
    long playerNumber;
    GenderEnum gender;
    String name;
    String surname;
    int ability;
    List<QualityPlayerGender> qualityPlayerGenderList;

    public Player(GenderEnum gender, String name, String surname, int ability) {
        this.gender = gender;
        this.name = name;
        this.surname = surname;
        this.ability = ability;
    }
}
