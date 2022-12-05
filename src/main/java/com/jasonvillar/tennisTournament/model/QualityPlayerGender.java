package com.jasonvillar.tennisTournament.model;

import com.jasonvillar.tennisTournament.model.enums.GenderEnum;
import com.jasonvillar.tennisTournament.model.enums.QualityEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QualityPlayerGender {
    GenderEnum gender;
    QualityEnum quality;
    double value;

    public QualityPlayerGender(GenderEnum gender, QualityEnum quality) {
        this.gender = gender;
        this.quality = quality;
    }

    public QualityPlayerGender(GenderEnum gender, QualityEnum quality, double value) {
        this.gender = gender;
        this.quality = quality;
        this.value = value;
    }
}
