package com.jasonvillar.tennisTournament.service;

import com.jasonvillar.tennisTournament.model.QualityPlayerGender;
import com.jasonvillar.tennisTournament.model.enums.GenderEnum;
import com.jasonvillar.tennisTournament.repository.QualityPlayerGenderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.jasonvillar.tennisTournament.utils.MathUtils.getRandomNumber;

@Service
@RequiredArgsConstructor
public class QualityPlayerGenderService {

    //I didn't use @autowired here to ensure speed of unit tests
    private final QualityPlayerGenderRepository qualityPlayerGenderRepository;

    public List<QualityPlayerGender> getQualityPlayerGenderListByGender(GenderEnum gender) {
        return qualityPlayerGenderRepository.getQualityPlayerGenderListByGender(gender);
    }

    public List<QualityPlayerGender> setValueRandomToPlayerGenderQualityList(List<QualityPlayerGender> qualityPlayerGenderList) {
        for (QualityPlayerGender quality: qualityPlayerGenderList) {
            quality.setValue(getRandomNumber(0.1, 1));
        }

        return qualityPlayerGenderList;
    }

    public List<QualityPlayerGender> newQualitiesWithValuesByGender(GenderEnum gender) {
        List<QualityPlayerGender> qualityPlayerGenderList = this.getQualityPlayerGenderListByGender(gender);
        return this.setValueRandomToPlayerGenderQualityList(qualityPlayerGenderList);
    }
}
