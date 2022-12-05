package com.jasonvillar.tennisTournament.repository;

import com.jasonvillar.tennisTournament.model.QualityPlayerGender;
import com.jasonvillar.tennisTournament.model.enums.GenderEnum;
import com.jasonvillar.tennisTournament.model.enums.QualityEnum;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class QualityPlayerGenderRepository {
    public List<QualityPlayerGender> getQualityPlayerGenderListByGender(GenderEnum gender) {
        List<QualityPlayerGender> qualityPlayerGenderList = new ArrayList<>();
        if (gender.equals(GenderEnum.MALE)) {
            qualityPlayerGenderList.add(new QualityPlayerGender(GenderEnum.MALE, QualityEnum.STRENGTH));
            qualityPlayerGenderList.add(new QualityPlayerGender(GenderEnum.MALE, QualityEnum.DISPLACEMENT_SPEED));
        }

        if (gender.equals(GenderEnum.FEMALE)) {
            qualityPlayerGenderList.add(new QualityPlayerGender(GenderEnum.FEMALE, QualityEnum.REACTION_TIME));
        }

        return qualityPlayerGenderList;
    }
}
