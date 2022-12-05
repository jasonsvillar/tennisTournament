package com.jasonvillar.tennisTournament.controller;

import com.jasonvillar.tennisTournament.model.QualityPlayerGender;
import com.jasonvillar.tennisTournament.model.enums.GenderEnum;
import com.jasonvillar.tennisTournament.service.QualityPlayerGenderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/qualityGender")
@Api(value = "api/v1", tags = "Quality Player Gender Controller")
public class QualityPlayerGenderController {
    @Autowired
    QualityPlayerGenderService qualityPlayerGenderService;

    @ApiOperation(value = "Get hardcoded list of qualities by gender")
    @GetMapping("/getQualityListByGender/{gender}")
    public List<QualityPlayerGender> getQualityPlayerGenderListByGender(@PathVariable GenderEnum gender) {
        return qualityPlayerGenderService.getQualityPlayerGenderListByGender(gender);
    }

    @ApiOperation(value = "Get hardcoded list of qualities by gender with values for new players")
    @GetMapping("/newQualitiesWithValuesByGender/{gender}")
    public List<QualityPlayerGender> newQualitiesWithValuesByGender(@PathVariable GenderEnum gender) {
        return qualityPlayerGenderService.newQualitiesWithValuesByGender(gender);
    }
}
